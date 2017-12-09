<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.DtProponente"%>
<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.DtColaboracion"%>
<%@page import="publicador.DtColaborador"%>
<%@page import="publicador.EstadoPropuesta"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeFactory"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="controllers.Home"%>
<%@page import="model.EstadoSesion"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

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

PublicadorService service = new PublicadorService();
Publicador port = service.getPublicadorPort();
%>



<!DOCTYPE html>
<html>
<head>
	
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <jsp:include page="/WEB-INF/template/head.jsp"/>
  <link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
  <title>CulturarteWeb :: Perfil de Usuario</title>
  <link rel="stylesheet" type="text/css" href="media/styles/custom/sidebar.css">
	
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
  DtProponente prop = (DtProponente) request.getAttribute("usuario");

  DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
  EstadoSesion estado = Home.getEstadoSesion(request);

  List<String> lstSeguidores = prop.getSeguidores();    
  List<String> lstSeguidos = prop.getSeguidos();      
  List<String> lstProp = prop.getFavoritas();        
  List<String> lstPropPub = prop.getPropuestas();
          
  DtPropuesta pro;
  DtPropuesta pro2;

  String proString;
  String proString2;
  

  %>

<div id ="sidebar">
   <ul>
    <div class="card" style="width: 200px; margin-bottom: 5px; margin-top: 10px;">
      
				<%
				if (!prop.getImagen().equals("")){
				//String pathImg = "media/img/Culturarte/" + colab.getImagen();%>
					<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">
				<%}else {	
				%>				
					<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">				
				<%}%>
      <div class="card-block">
        <h4 class="card-title" ><%=prop.getNombre() + " " + prop.getApellido() %></h4>
        <% if (!prop.getBiografia().equals("")) { %>
        <a data-toggle="modal" data-target="#myModal" class="linksa22" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Biografía</a>
        <%} %>
      </div>
      <ul class="list-group list-group-flush">
      
     	<%
		Calendar fechaNac = prop.getFechaNacimiento().toGregorianCalendar();
		Integer dia = fechaNac.get(Calendar.DAY_OF_MONTH);
		Integer mes = fechaNac.get(Calendar.MONTH) +1;
		Integer anio = fechaNac.get(Calendar.YEAR);        
		%> 
       <li class="list-group-item"><%= dia.toString() + "/" + mes.toString() + "/" + anio.toString() %></li>
       <li class="list-group-item"><%=prop.getDireccion() %></li>
       <% if (!prop.getSitioWeb().equals("")){ %>
       <li class="list-group-item"><a href="<%= prop.getSitioWeb()%>"><span class="glyphicon glyphicon-globe" aria-hidden="true"> </span> Pagina Web</a></li>
       <%} %>
       <li class="list-group-item"><a href="mailto:<%= prop.getCorreoElectronico() %>"><span class="glyphicon glyphicon-envelope" aria-hidden="true"> </span> Mail</a></li>
     </ul>
   </div>
   <li class="links"><a class="btn btn-info" style="width: 200px;" data-toggle="tab" href="#favoritas">Propuestas favoritas</a></li>
   <li class="links"><a class="btn btn-info" style="width: 200px;" data-toggle="tab" href="#publicadas">Propuestas publicadas</a></li>
   <% if (estado != EstadoSesion.NO_LOGIN && usuarioLogueado.getNickname().equals(prop.getNickname()) ) { %> 
   		<li class="links"><a class="btn btn-info" style="width: 200px;" data-toggle="tab" href="#mis">Mis propuestas</a></li>
		<form id="signupform" class="form-horizontal" role="form" name="darseDeBaja"  class="col-md-6 col-md-offset-3" action="DarseDeBaja">
		   <button id="btn-signup" type="submit" value="enviar" style="width:200px; margin-top: 50%" class="btn btn-lg btn-primary btn-block">Darse de baja</button>
		</form>
   <%} %>
 </ul>
</div>

 <div id="container">   
 <div id="comentario" style="height: 40em">
  <div class="tab-content">
    <div id="favoritas" class="tab-pane fade in active">
      <h3>Favoritas</h3>        
      <% 
      Iterator<String> itProp = lstProp.iterator();
        while(!lstProp.isEmpty() && itProp.hasNext()) { 
        	proString = itProp.next();
        	pro = port.obtenerPropuestaPorTitulo(proString);
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
			Calendar fecha = pro.getFechaRealizacion().toGregorianCalendar();
			int diaP = fecha.get(Calendar.DAY_OF_MONTH);
			int mesP = fecha.get(Calendar.MONTH) +1;
			int anioP = fecha.get(Calendar.YEAR);        
			%>      
            <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP + "/" + mesP +"/" + anioP %></small></p>
          </div>
        </div>        
        <%} %>
        
      </div>
    <div id="publicadas" class="tab-pane fade">
        <h3>Publicadas y no en estado ingresadas</h3>        
        <% 
        Iterator<String> itProp2 = lstPropPub.iterator();
          while(!lstPropPub.isEmpty() && itProp2.hasNext()) { 
          	proString2 = itProp2.next();
          	pro2 = port.obtenerPropuestaPorTitulo(proString2);
          	if (!pro2.getEstado().getEstado().equals(EstadoPropuesta.INGRESADA)){
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
            <%
			Calendar fecha2 = pro2.getFechaRealizacion().toGregorianCalendar();
			int diaP2 = fecha2.get(Calendar.DAY_OF_MONTH);
			int mesP2 = fecha2.get(Calendar.MONTH) +1;
			int anioP2 = fecha2.get(Calendar.YEAR);        
			%>    
             <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP2 + "/" + mesP2 +"/" + anioP2 %></small></p>
           </div>
         </div>
         <%} %>
         <%} %>
         
       </div>
        <div id="mis" class="tab-pane fade">
        <h3>Mis propuestas ingresadas</h3>
        	<% 
        Iterator<String> itProp3 = lstPropPub.iterator();
          while(lstPropPub.isEmpty() && itProp3.hasNext()) { 
          	proString2 = itProp3.next();
          	pro2 = port.obtenerPropuestaPorTitulo(proString2);
          	if (!pro2.getEstado().getEstado().equals(EstadoPropuesta.INGRESADA)){
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
			<%
			Calendar fecha2 = pro2.getFechaRealizacion().toGregorianCalendar();
			int diaP2 = fecha2.get(Calendar.DAY_OF_MONTH);
			int mesP2 = fecha2.get(Calendar.MONTH) +1;
			int anioP2 = fecha2.get(Calendar.YEAR);        
			%>     
             <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP2 + "/" + mesP2 +"/" + anioP2 %></small></p>
           </div>
         </div>
         <%} %>
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
           Iterator<String> itUsr = lstSeguidores.iterator();
            while(itUsr.hasNext()){ 
            	String usrStr = itUsr.next();
            DtUsuario usuario = port.getUsuarioPorNick(usrStr);
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
                    <%if (existeEnListaColab(port.getColaboradores().getColaboradores(), usuario.getNickname())){ %>
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
           Iterator<String> itUsr2 = lstSeguidos.iterator();
            while(itUsr2.hasNext()){ 
            	String usrStr2 = itUsr2.next();
            	DtUsuario usuario2 = port.getUsuarioPorNick(usrStr2);
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

<!-- MODAL PARA MOSTRAR LA BIOGRAFIA -->
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">    
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Biografía</h4>
      </div>
      <div class="modal-body">
        <p><%=prop.getBiografia()%></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
      </div>
    </div>      
  </div>
</div>
<!-- ================================= -->


<jsp:include page="/WEB-INF/template/footer.jsp"/>

</body>
</html>