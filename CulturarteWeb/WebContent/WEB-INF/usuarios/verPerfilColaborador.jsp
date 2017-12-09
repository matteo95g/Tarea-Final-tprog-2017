<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.DtColaborador"%>
<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.DtColaboracion"%>
<%@page import="publicador.DtColecciones"%>
<%@page import="publicador.EstadoPropuesta"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeFactory"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="controllers.Home"%>
<%@page import="model.EstadoSesion"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<jsp:include page="/WEB-INF/template/head.jsp"/>
	<link rel="stylesheet" href="media/styles/custom/sidebar.css">
	<link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">  
	<title>CulturarteWeb :: Perfil de Usuario</title>
	
	<style>
	.fotos-prop{
		height: 180px !important;
		width: auto !important;
	}	
	</style>
	

</head>

<body>
	<jsp:include page="/WEB-INF/template/header.jsp"/> 

	<% 
	
	PublicadorService service = new PublicadorService();
    Publicador port = service.getPublicadorPort();
    
    Calendar calendar;
	Integer dia;
	Integer mes;
	Integer anio; 


	DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
	EstadoSesion estado = Home.getEstadoSesion(request);
	
	DtColaborador colab = (DtColaborador) request.getAttribute("usuario"); 
	
	List<String> seguidoresName = colab.getSeguidores();
	ArrayList<DtUsuario> seguidores = new ArrayList<DtUsuario>();	
	for (int i = 0; i < seguidoresName.size(); i++){
		DtUsuario dtUsr = port.getUsuarioPorNick(seguidoresName.get(i));
		seguidores.add(dtUsr);
	}
	
	List<String> seguidosName = colab.getSeguidos();
	ArrayList<DtUsuario> seguidos = new ArrayList<DtUsuario>();	
	for (int i = 0; i < seguidosName.size(); i++){
		DtUsuario dtUsr = port.getUsuarioPorNick(seguidosName.get(i));
		seguidos.add(dtUsr);
	}	
	
	List<String> favoritasTitu = colab.getFavoritas();
	ArrayList<DtPropuesta> favoritas = new ArrayList<DtPropuesta>();
	Iterator<String> iter = favoritasTitu.iterator();
	while (iter.hasNext()){
		DtPropuesta dtProp = port.obtenerPropuestaPorTitulo(iter.next());
		if (dtProp.getTitulo() != null) {
			favoritas.add(dtProp);
		}
	}
	
	List<Integer> colaboradasInt = colab.getColaboraciones();	
	ArrayList<DtColaboracion> colaboradas = new ArrayList<DtColaboracion>();	
	for (int i = 0; i < colaboradasInt.size(); i++){
		DtColaboracion dtCol = port.getColaboracion(colaboradasInt.get(i));
		colaboradas.add(dtCol);
	}
	
	Iterator<DtColaboracion> itCol = colaboradas.iterator();	
	List<DtPropuesta> lstPropCol = new ArrayList<DtPropuesta>();
	
	while (!colaboradas.isEmpty() && itCol.hasNext()){
		DtColaboracion cola = itCol.next();
		lstPropCol.add(port.obtenerPropuestaPorTitulo(cola.getPropuesta()));
	}

	DtPropuesta pro;
	DtPropuesta pro2;
	%>

	<%!
	private boolean existeEnListaColab(List<DtColaborador> lst, String nick){
		Iterator<DtColaborador> it = lst.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre){
			DtColaborador colab = it.next();
			if (colab.getNickname().equals(nick)){
				encontre = true;
			}
		}
		return encontre;		
	}	
	%>
	
	
	<div id ="sidebar">
		<ul>
			<div class="card" style="width: 200px; margin-bottom: 5px; margin-top: 10px;">
				<%
				if (!colab.getImagen().equals("")){%>
				
					<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= colab.getImagen() %>" alt="Card image cap">
				<%}else {%>
					<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">				
				<%}%>
				<div class="card-block">
					<h4 class="card-title" ><%=colab.getNombre() + " " + colab.getApellido() %></h4>
				</div>
				<ul class="list-group list-group-flush">
					<%
					calendar = colab.getFechaNacimiento().toGregorianCalendar();
					dia = calendar.get(Calendar.DAY_OF_MONTH);
					mes = calendar.get(Calendar.MONTH) +1;
					anio = calendar.get(Calendar.YEAR);        
					%>
					<li class="list-group-item"><%= dia.toString() + "/" + mes.toString() + "/" + anio.toString() %></li>
					<li class="list-group-item"><a href="mailto:<%= colab.getCorreoElectronico() %>"><span class="glyphicon glyphicon-envelope" aria-hidden="true"> </span> Mail</a></li>
				</ul>
			</div>
			<li class="links"><a class="btn btn-info" style="width: 200px;" data-toggle="tab" href="#favoritas">Propuestas favoritas</a></li>
   			<li class="links"><a class="btn btn-info" style="width: 200px;" data-toggle="tab" href="#colaboradas">Propuestas colaboradas</a></li>
		</ul>
	</div>

<div id="container">   
 <div id="comentario" style="height: 40em">
  <div class="tab-content">
    <div id="favoritas" class="tab-pane fade in active">
      <h3>Favoritas</h3>        
      <% 
      Iterator<DtPropuesta> itProp = favoritas.iterator();
        while(!favoritas.isEmpty() && itProp.hasNext()) { 
        pro = itProp.next();
        %>        
        <div class="card mb-3">
            <%if (!pro.getImagen().equals("")) {%>
  				<img class="fotos-prop" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  			<%}else{ %>
  				<img class="fotos-prop" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  			<%} %>
          <div class="card-block">
            <h4 class="card-title"><%=pro.getTitulo()%></h4>
            <p class="card-text"><%=pro.getDescripcion()%></p>
            <a href="propuestas?propuesta=<%=pro.getTitulo()%>" style="margin-left: 80%" class="btn btn-primary">Ver propuesta</a>
            <br/>
			<%
			Calendar calendar1 = pro.getFechaRealizacion().toGregorianCalendar();
			Integer diaP = calendar1.get(Calendar.DAY_OF_MONTH);
			Integer mesP = calendar1.get(Calendar.MONTH) + 1;
			Integer anioP = calendar1.get(Calendar.YEAR);        
			%>      
            <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP + "/" + mesP +"/" + anioP %></small></p>
          </div>
        </div>        
        <%} %>
      
      </div>
      <div id="colaboradas" class="tab-pane fade">
        <h3>Colaboradas</h3>        
        <% 
        Iterator<DtPropuesta> itProp2 = lstPropCol.iterator();
          while(!lstPropCol.isEmpty() && itProp2.hasNext()) { 
          pro2 = itProp2.next();
	      if (pro2.getEstado() != null) {    
          	  if (pro2.getEstado().getEstado() != EstadoPropuesta.INGRESADA){
	          %>        
	          <div class="card mb-3">
	           <%if (!pro2.getImagen().equals("")) {%>
	  				<img class="fotos-prop" src="/CulturarteWeb/imagenes?id=<%= pro2.getImagen() %>" alt="Card image cap">
	  			<%}else{ %>
	  				<img class="fotos-prop" src="media/img/defecto-propuesta.gif" alt="Card image cap">
	  			<%} %>
	           <div class="card-block">
	             <h4 class="card-title"><%=pro2.getTitulo()%></h4>
	             <p class="card-text"><%=pro2.getDescripcion()%></p>
	             <a href="propuestas?propuesta=<%=pro2.getTitulo()%>" style="margin-left: 80%" class="btn btn-primary">Ver propuesta</a>
	             <br/>
	             <% if (estado != EstadoSesion.NO_LOGIN && usuarioLogueado.getNickname().equals(colab.getNickname()) ) { 
	            	 Iterator<DtColaboracion> itCol2 = colaboradas.iterator();	
	            	 while(!colaboradas.isEmpty() && itCol2.hasNext()){
	            		 DtColaboracion colabe = itCol2.next();
	            		 if (colabe.getPropuesta().equals(pro2.getTitulo())){
		            	     Calendar fechaColab = colabe.getFechaColaboracion().toGregorianCalendar();
		            	     Integer diaC = fechaColab.get(Calendar.DAY_OF_MONTH);
		            	     Integer mesC = fechaColab.get(Calendar.MONTH) +1;
		            	     Integer anioC = fechaColab.get(Calendar.YEAR);        
			            	 %>
			             	 <p class="card-text"><small class="text-muted"><%="Colaboraste con: $ " + colabe.getMonto().toString()%></small></p>
			             	 <p class="card-text"><small class="text-muted"><%="Fecha de la colaboracion: " + diaC + "/" + mesC + "/" + anioC%></small></p> 
			             	 <%
								//DtColecciones dtCol = port.obtenerColaboraciones(pro2.getTitulo());
			             	 	DtColaborador colabr = (DtColaborador) usuarioLogueado;
								List<Integer> lstColab = colabr.getColaboraciones();
								DtColaboracion colaboracion = null;
								for (int i = 0; i < lstColab.size(); i++){
									if (port.getColaboracion(lstColab.get(i)).getPropuesta().equals(pro2.getTitulo())){
										colaboracion = port.getColaboracion(lstColab.get(i));
									}
								}
								%>
								<%if(colaboracion.isPaga()) {%>
								  <p class="card-text"><small class="text-muted"><a href="constancia?id=<%=colaboracion.getIdent().toString()%>">Emitir constancia de pago</a></small></p>        
	             	 			<%} %>
	             	 	<%} %>
	             	 <%} %>
	             <%} %>             
	             <%
	             Calendar fecha2 = pro2.getFechaRealizacion().toGregorianCalendar();
	             Integer diaP2 = fecha2.get(Calendar.DAY_OF_MONTH);
	             Integer mesP2 = fecha2.get(Calendar.MONTH) +1;
	             Integer anioP2 = fecha2.get(Calendar.YEAR);        
	             %>       
	             <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP2 + "/" + mesP2 +"/" + anioP2 %></small></p>
	           </div>
	         </div>
	         <%}
	         } %>
         <%} %>
         
       </div>
    </div> 
  </div>
  
  <div id="colaboradores" style="height: 40em">
    <ul class="nav nav-tabs">
      <li class="active"><a data-toggle="tab" href="#seguidores">Seguidores</a></li>
      <li><a data-toggle="tab" href="#seguidos">Seguidos</a></li>
    </ul>
   
    <div class="tab-content">
        <!--------------- SEGUIDORES --------------->
      <div id="seguidores" class="tab-pane fade in active">       
          <div id="login-overlay" class="modal-dialog" style="width: 300px;" >
         <div class="modal-body">
           <div class="row">
             <div class="col-sm-3">
             </div><!-- /col-sm-1 -->
           </div>          
           <%
           Iterator<DtUsuario> itUsr = seguidores.iterator();
            while(!seguidores.isEmpty() && itUsr.hasNext()){ 
            DtUsuario usuario = itUsr.next();
            %>         
            <div class="row">
              <div class="col-sm-9">
                <div class="panel panel-default" style="width: 300px;">
                  <div class="panel-heading">
                    <%if (usuario.getImagen().equals(""))  {%>
                    	<img style="width:60px;" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                    <%}else{  %>
                    	<img style="width:60px;" src="/CulturarteWeb/imagenes?id=<%= usuario.getImagen() %>">
                    <%} %>
                    <strong><%=usuario.getNombre() + " " + usuario.getApellido()%></strong><span class="text-muted"><%=" " + usuario.getNickname()%></span>
                  </div>
                  <div class="panel-body">
                    <%
                    if (existeEnListaColab(port.getColaboradores().getColaboradores(), usuario.getNickname())){ %>
                    Colaborador
                    <%}else { %>
                    Proponente
                    <%} %>
                  </div><!-- /panel-body -->
                </div><!-- /panel panel-default -->
              </div><!-- /col-sm-5 -->
            </div><!-- /row -->
            <%} %>                   
          </div>
        </div>
      </div>
      <div id="seguidos" class="tab-pane fade">
                <!--------------- SEGUIDOS --------------->
      <div id="seguidos" class="tab-pane fade in active">       
          <div id="login-overlay" class="modal-dialog" style="width: 300px;" >
         <div class="modal-body">
           <div class="row">
             <div class="col-sm-3">
             </div><!-- /col-sm-1 -->
           </div>          
           <%
           Iterator<DtUsuario> itUsr2 = seguidos.iterator();
            while(!seguidos.isEmpty() && itUsr2.hasNext()){ 
            	DtUsuario usuario2 = itUsr2.next();
            %>         
            <div class="row">
              <div class="col-sm-9">
                <div class="panel panel-default" style="width: 300px;">
                  <div class="panel-heading">
                    <%if (usuario2.getImagen().equals(""))  {%>
                    	<img style="width:60px;" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                    <%}else{  %>
                    	<img style="width:60px;" src="/CulturarteWeb/imagenes?id=<%= usuario2.getImagen() %>">
                    <%} %>
                    <strong><%=usuario2.getNombre() + " " + usuario2.getApellido()%></strong><span class="text-muted"><%=" " + usuario2.getNickname()%></span>
                  </div>
                  <div class="panel-body">
                    <%if (existeEnListaColab(port.getColaboradores().getColaboradores(), usuario2.getNickname())){ %>
                    Colaborador
                    <%}else { %>
                    Proponente
                    <%} %>
                  </div><!-- /panel-body -->
                </div><!-- /panel panel-default -->
              </div><!-- /col-sm-5 -->
            </div><!-- /row -->
            <%} %>                   
          </div>
        </div>
      </div>
    </div>
  </div>
</div> 
</div>

	

	<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>