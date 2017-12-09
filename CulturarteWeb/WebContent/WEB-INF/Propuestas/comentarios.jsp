<%@page import="publicador.DtColaborador"%>
<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.DtComentario"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="publicador.Retorno"%>
<%@page import="java.util.Iterator"%>
<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtColaboracion"%>
<%@page import="publicador.DtColecciones"%>
<%@page import="model.EstadoSesion"%>
<%@page import="controllers.Home"%>
<%@page import="java.util.Map"%>
<%@page import="publicador.EstadoPropuesta"%>
<%@page import="publicador.DtFechaCambio"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.PublicadorService"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeFactory"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@page import="java.util.TimeZone" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>CulturarteWeb :: Comentarios</title>
 <jsp:include page="/WEB-INF/template/head.jsp"/>
 <link rel="stylesheet" href="media/styles/custom/sidebar.css">
 <link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css"> 
 
  <link rel="stylesheet" href="media/styles/alertify/alertify.core.css"/>

<link rel="stylesheet" href="js_files/alertify/themes/alertify.default.css"/>

</head>
<body>
 <jsp:include page="/WEB-INF/template/header.jsp"/>
 
	<%!
	private boolean existeEnListaColaborador(List<DtColaborador> lst, String nick){
		Iterator<DtColaborador> it = lst.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre){
			DtColaborador col = it.next();
			if (col.getNickname().equals(nick)){
				encontre = true;
			}
		}
		return encontre;
	}
	%>
		<%!
	private boolean existeEnListaComentario(List<DtComentario> lst, String nick){
		Iterator<DtComentario> it = lst.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre){
			DtComentario com = it.next();
			if (com.getColaborador().equals(nick)){
				encontre = true;
			}
		}
		return encontre;
	}
	%>


<% 

 DtPropuesta pro = (DtPropuesta) request.getAttribute("propuesta");

 DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
 EstadoSesion estado = Home.getEstadoSesion(request);
 
 PublicadorService service = new PublicadorService();
 Publicador port = service.getPublicadorPort();

 
 DtColecciones lstComent = port.obtenerComentarios(pro.getTitulo());   
 
 
 DtColecciones lstCol = port.obtenerColaboraciones(pro.getTitulo());
 
 List<String> colaboradores = pro.getColaboradores();
 
 List<DtColaborador> colabs = port.getColaboradores().getColaboradores();
 
 %>
  <%!public static long daysBetween(Calendar startDate, Calendar endDate) {
	    long end = endDate.getTimeInMillis();
	    long start = startDate.getTimeInMillis();
	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
	  }
 %>

   <div id ="sidebar">
    <ul>
     <div class="card" style="width: 200px; margin-bottom: 5px; margin-top: 10px;">
      <%if (!pro.getImagen().equals("")) {%>
  		<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  	  <%}else{ %>
  	    <img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  	  <%} %>
      <div class="card-block">
       <h4 class="card-title"style="text-align:center; font-size: 16px; "><%= pro.getTitulo()%></h4>
        <% if (!pro.getDescripcion().equals("")) { %>
        	<a data-toggle="modal" data-target="#myModal" class="linksa22" ><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Descripción</a>
        <%} %>       
       <%-- <p class="card-text"><%=pro.getDescripcion() %></p> --%>
     </div> 
     <ul class="list-group list-group-flush">
       <li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
		<%
       Calendar calendar=pro.getFechaRealizacion().toGregorianCalendar();
       int diaIng = calendar.get(Calendar.DAY_OF_MONTH);
       int mesIng = calendar.get(Calendar.MONTH) + 1;
       int anioIng = calendar.get(Calendar.YEAR); %>
       <li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
       <li class="list-group-item">Precio de la entrada: $ <%=pro.getPrecioEntrada() %></li>
       <li class="list-group-item">Monto Necesario: $ <%=pro.getMontoNecesario() %></li>
       <li class="list-group-item">Monto Actual: $ <%=pro.getMontoActual() %></li>
       <%List<Retorno> ret= pro.getRetornos();
        Iterator<Retorno> retor=ret.iterator();
         int largoRet =ret.size();
         Retorno retorno1 = null;
         Retorno retorno2 =null;
         if (largoRet>0){
        	 retorno1= retor.next();
         	 largoRet--;
     	 }
      	 if (largoRet>0){
     	 	retorno2= retor.next();
     	}
     %>
     <li class="list-group-item">Retorno: <%if(retorno1!=null){%><%=retorno1%> <%} if(retorno2!=null){%><%="o " +retorno2%> <%} %></li>
     <li class="list-group-item">Categoria: <%=pro.getCategoria() %></li> 
    <%Calendar cal= pro.getEstado().getFecha().toGregorianCalendar();
    long dias = daysBetween(cal, Calendar.getInstance());
    if (dias >= 30){
   	 if(pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA){
   		 DtFechaCambio nuevo = new DtFechaCambio();
   		 GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		 XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
   		 nuevo.setFecha(fecha);
   		 nuevo.setEstado(EstadoPropuesta.NO_FINANCIADA);
   		 port.setEstado(pro,nuevo);
   		 pro.setEstado(nuevo);
   	 }else if(pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION){
   		 if (pro.getMontoActual() >= pro.getMontoNecesario()){
       		 DtFechaCambio nuevo = new DtFechaCambio();
       		 GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    		 XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
       		 nuevo.setFecha(fecha);
       		 nuevo.setEstado(EstadoPropuesta.FINANCIADA);
       		 port.setEstado(pro,nuevo);
   		 }else{
   			 
       		 DtFechaCambio nuevo = new DtFechaCambio();
       		 GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    		 XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
       		 nuevo.setFecha(fecha);
       		 nuevo.setEstado(EstadoPropuesta.NO_FINANCIADA);
       		 pro.setEstado(nuevo);
   		 }
   	 }
    }
    String strEstado;
    if (pro.getEstado().getEstado() == EstadoPropuesta.INGRESADA) {
    	strEstado = "Ingresada";
    } else if (pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA) {
    	strEstado = "Publicada";
    } else if (pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION) {
    	strEstado = "En Financiacion";
    } else if (pro.getEstado().getEstado() == EstadoPropuesta.FINANCIADA) {
    	strEstado = "Financiada";
    } else if (pro.getEstado().getEstado() == EstadoPropuesta.NO_FINANCIADA) {
    	strEstado = "No Financiada";
    } else {
    	strEstado = "Cancelada";
    }
    
    %>  
     <li class="list-group-item">Estado: <%= strEstado %></li>               
     <li class="list-group-item">Proponente: <a href="usuarios?usuario=<%= pro.getProponente()%>"> <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %> </a></li>               
   </ul>            
 </div> 
 
 
 
 <li class="links">
     <% 
    //SI YA PASARON MAS DE 30 DIAS NO MUESTRA EL BOTON EXTENDER
    cal= pro.getEstado().getFecha().toGregorianCalendar();
    if ( estado != EstadoSesion.NO_LOGIN && 
 	usuarioLogueado.getNickname().equals(pro.getProponente())  && 
 	 ( (pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA && daysBetween(cal, Calendar.getInstance()) < 31) || 
 			 (pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION && daysBetween(cal, Calendar.getInstance()) < 31) ) ) {  	 
 	 %>
   <a class="btn btn-success" style="width: 98px;" onclick="alert('Propuesta Extendida');
	<%
	
	DtFechaCambio nuevoEst= new DtFechaCambio();
	GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
 	nuevoEst.setFecha(fecha);
	nuevoEst.setEstado(pro.getEstado().getEstado());
	port.setEstado(pro,nuevoEst);
	%>" role="button">Extender</a>
  		<%} %>
   <% if ((estado != EstadoSesion.NO_LOGIN && usuarioLogueado.getNickname().equals(pro.getProponente()) ) && ( pro.getEstado().getEstado() == EstadoPropuesta.FINANCIADA)) { %> 
   <a class="btn btn-danger" onclick="alert('Propuesta Cancelada');
	<%
	DtFechaCambio nuevoEst= new DtFechaCambio();
	GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
	XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
 	nuevoEst.setFecha(fecha);
	nuevoEst.setEstado(EstadoPropuesta.CANCELADA);	
	port.setEstado(pro,nuevoEst);
	%>" style="width: 98px;" href="?propuesta=<%=pro.getTitulo()%>" role="button">Cancelar</a>

 </li>
 <%} %>
 	
 <% if ((estado != EstadoSesion.NO_LOGIN && !colaboradores.contains(usuarioLogueado.getNickname())) &&
         ((pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA)|| (pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION))&& existeEnListaColaborador(colabs, usuarioLogueado.getNickname())) { %>
	<a href="Colaborar?titulo=<%=pro.getTitulo()%>" style="width:200px" class="btn btn-info">Colaborar</a>
 <%} %>
</ul>
</div>


   <!----------------- COMENTARIOS ----------------->
 <div id="container">
    <div id="comentario" style="height: 40em">
         <div id="login-overlay" class="modal-dialog">
       <div class="modal-header">
         <h4 class="modal-title" id="myModalLabel">Comentarios</h4>
       </div>
       <div class="modal-body">
         <div class="row">
           <div class="col-sm-3">
           </div><!-- col-sm-1 -->
         </div> 
         
         
         <%
         
         Iterator<DtComentario> itComent = lstComent.getComentarios().iterator();
         
           while(!lstComent.getComentarios().isEmpty() && itComent.hasNext()) { 
           DtComentario coment = itComent.next();
           %>
           <div class="row">
             <div class="col-sm-9">
               <div class="panel panel-default" style="width: 580px;">
                 <div class="panel-heading">
                 	<%
        		  	calendar = coment.getFecha().toGregorianCalendar();
        		  	int dia = calendar.get(Calendar.DAY_OF_MONTH);
        		  	int mes = calendar.get(Calendar.MONTH) +1;
        		  	int anio = calendar.get(Calendar.YEAR);		  	
        		  	%>
                   	<strong><%=coment.getColaborador()%></strong><span class="text-muted"><%= " " + Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(anio) %></span>
                 </div>
                 <div class="panel-body">
                   <%=coment.getComentario()%>
                 </div><!-- /panel-body -->
               </div><!-- /panel panel-default -->
             </div><!-- /col-sm-5 -->
           </div><!-- /row -->
           
           <%} %>
            
            <% if (estado != EstadoSesion.NO_LOGIN &&  pro.getColaboradores().contains(usuarioLogueado.getNickname()) && !pro.getComentarios().contains(usuarioLogueado.getNickname())) { %>
           <div class="modal-header">
             <h4 class="modal-title" id="myModalLabel">Deja tu comentario!</h4>
           </div>      
           <div class="row">
             <div class="col-sm-12 col-md-12">
               <div class="panel ">             
                 
                 <form accept-charset="UTF-8" action="propuestas" method="POST">               
                   <div class="row" style="padding-top:10px;padding-bottom:20px;">                 
                     <div class="col-lg-6">
                       <input type="text" placeholder="<%=usuarioLogueado.getNickname()%>" class="form-control testBox">
                     </div>
                     <div class="col-lg-6">
                       <input type="email" placeholder="Email" class="form-control testBox">
                     </div>                
                   </div>                                                   
                   <textarea class="form-control counted" name="comentario" placeholder="Escribe tu comentario..." rows="5" style="margin-bottom:10px;"></textarea>
                   <%String titulo = pro.getTitulo(); 
                  
                   %>
                   <input type="hidden" name="propuesta" value="<%=titulo%>">
                   <input type="hidden" name="colaborador" value="<%=usuarioLogueado.getNickname()%>">
                   <button class="btn btn-info" type="submit">Aceptar</button>                   
                 </form>
               
               </div>
             </div>
           </div>
            <%} %>
         </div>
       </div>
     </div>
     </div>

     
     <!----------------------- COLABORADORES ----------------------->


     <div id="colaboradores" style="height: 40em">
         <div id="login-overlay" class="modal-dialog" style="width: 300px;" >
       <div class="modal-header">
         <h4 class="modal-title" id="myModalLabel">Colaboradores</h4>
       </div>
       <div class="modal-body">
         <div class="row">
           <div class="col-sm-3">
           </div><!-- /col-sm-1 -->
         </div>
         
        <%
         Iterator<DtColaboracion> itColab = lstCol.getColaboraciones().iterator();
         while(!lstCol.getColaboraciones().isEmpty() && itColab.hasNext()){ 
        	 DtColaboracion colab = itColab.next();
         %>         
          <div class="row">
             <div class="col-sm-9">
               <div class="panel panel-default" style="width: 300px;">
                 <div class="panel-heading">
                   <strong><%=port.getUsuarioPorNick(colab.getUsuario()).getNombre() + " " + port.getUsuarioPorNick(colab.getUsuario()).getApellido()%></strong><span class="text-muted"><%=" " + colab.getUsuario()%></span>
                 </div>
                 <div class="panel-body">
                   <%= "$ " + colab.getMonto().toString() %>
                 </div><!-- /panel-body -->
               </div><!-- /panel panel-default -->
             </div><!-- /col-sm-5 -->
           </div><!-- /row -->
          <%} %>                   
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
        <h4 class="modal-title">Descripción</h4>
      </div>
      <div class="modal-body">
        <p><%=pro.getDescripcion()%></p>
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
