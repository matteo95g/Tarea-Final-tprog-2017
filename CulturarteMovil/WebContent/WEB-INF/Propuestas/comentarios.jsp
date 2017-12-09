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
 <title>CulturarteMovil :: Consulta de propuesta</title>
	 <script>  
		  function marcarFav(propuesta, id) {
		    if ($(id).hasClass("glyphicon-star-empty")) {
		      var vaccion = "fav";
		    } else {
		      var vaccion = "noFav";
		    }    
		    var datos = {
		      prop : propuesta,
		      accion : vaccion
		    }
		    $.get("favoritas", datos);
		
		    if ($(id).hasClass("glyphicon-star-empty")) {
		      $(id).removeClass("glyphicon-star-empty");
		      $(id).addClass("glyphicon-star");
		      $(id).css("color", "yellow");
		    } else {
		      $(id).removeClass("glyphicon-star");
		      $(id).addClass("glyphicon-star-empty");
		      $(id).css("color", "black");
		      
		    }
		  }
	</script>
 <jsp:include page="/WEB-INF/template/head.jsp"/>
 
 
<link rel="stylesheet" href="media/styles/alertify/alertify.core.css"/>

<link rel="stylesheet" href="js_files/alertify/themes/alertify.default.css"/>
<link rel="stylesheet" type="text/css" href="media/styles/custom/consultaProp.css">

<link rel="stylesheet" type="text/css" href="media/styles/custom/resume.css">
<link rel="stylesheet" type="text/css" href="media/styles/custom/scroll.css">

</head>
<body>
 <jsp:include page="/WEB-INF/template/header.jsp"/>
 
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
 
 <div style="margin: 5%;">
	 <h3><%=pro.getTitulo() %></h3>	
	 <div class="thumbnail">						 
	    <a href="?propuesta=<%= pro.getTitulo()%>">
	      <%if (!pro.getImagen().equals("")) {%>
	                     <img class="portrait"  src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>">
	                 <%}else{ %>
	                     <img class="portrait" src="media/img/defecto-propuesta.gif">
	                 <%} %>
	    </a>
	</div>
</div>
	<div class="scrollmenu">
	  <a class="nav-link js-scroll-trigger" href="#desc">Descripción</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Lugar</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Fecha</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Precio</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Monto necesario</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Monto actual</a>
	  <a class="nav-link js-scroll-trigger" href="#info">Retorno</a> 
	  <a class="nav-link js-scroll-trigger" href="#info">Categoria</a> 
	  <a class="nav-link js-scroll-trigger" href="#info">Estado</a> 
	  <a class="nav-link js-scroll-trigger" href="#info">Proponente</a>
	  <%if(pro.getComentarios().size() > 0) { %>
	  	<a class="nav-link js-scroll-trigger" href="#coment">Comentarios</a>
	  <%} %>
	  <%if (pro.getColaboradores().size() > 0) {%>
	  	<a class="nav-link js-scroll-trigger" href="#colab">Colaboradores</a>
	  <%} %>
	</div>

    <div class="container-fluid p-0">

      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="desc">
        <div class="my-auto">
          <h1 class="mb-0">Descripción:</h1>
          <p class="mb-5"> <%=pro.getDescripcion() %> </p>          

<!-- NO HAY CASO DE USO MARACAR FAVORITA EN EL MOVIL -->
<%--            <%if (estado != EstadoSesion.NO_LOGIN){  %>
		        <%if (port.getUsuarioPorNick(usuarioLogueado.getNickname()).getFavoritas().contains(pro.getTitulo())) { %>
			        <button type="button"
			        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-star" style="color: yellow"
			        onclick="marcarFav('<%=pro.getTitulo()%>',this)">
			        </button>
			    <%}else{ %>
			    	<button type="button"
			        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-star-empty" style="color: black"
			        onclick="marcarFav('<%=pro.getTitulo()%>',this)">
			        </button>					    
			    <%} %>
		    <%} %> --%>
		    
		     <% if ((estado != EstadoSesion.NO_LOGIN && !colaboradores.contains(usuarioLogueado.getNickname())) &&
			         ((pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA)|| (pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION))&& existeEnListaColaborador(colabs, usuarioLogueado.getNickname())) { %>
				<a href="Colaborar?titulo=<%=pro.getTitulo()%>" class="btn btn-default btn-lg glyphicon glyphicon-piggy-bank"></a>
			 <%} %>
          
        </div>
      </section>
      
      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="info">
      <h1 class="mb-0">Información:</h1>
        <div class="my-auto">
          <h3 class="mb-0">Se realizara en:</h3>
          <p class="mb-5"> <%=pro.getLugar() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Fecha de realizacion:</h3>
          <p class="mb-5"> <%=pro.getFechaRealizacion().getDay() + " / " + pro.getFechaRealizacion().getMonth() + " / " + pro.getFechaRealizacion().getYear() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Precio de la entrada:</h3>
          <p class="mb-5"><%="$ " + pro.getPrecioEntrada().toString() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Monto necesario:</h3>
          <p class="mb-5"><%="$ " + pro.getMontoNecesario().toString() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Monto actual:</h3>
          <p class="mb-5"><%="$ " + pro.getMontoActual().toString() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Retornos:</h3>
          <%
          int i = 0;
          for (i = 0; i < pro.getRetornos().size() - 1; i++){          
          %>
          	<p class="mb-5"><%=pro.getRetornos().get(i).toString().toLowerCase() + ", " %> 
          <%} %>          
          	<%=pro.getRetornos().get(i).toString().toLowerCase()%>
          	</p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Categoria:</h3>
          <p class="mb-5"><%=pro.getCategoria() %> </p>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Estado:</h3>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.CANCELADA){ %>
          	<p class="mb-5">Cancelada</p>
          <%} %>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.EN_FINANCIACION){ %>
          	<p class="mb-5">En financiación</p>
          <%} %>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.FINANCIADA){ %>
          	<p class="mb-5">Financiada</p>
          <%} %>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.INGRESADA){ %>
          	<p class="mb-5">Ingresada</p>
          <%} %>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.NO_FINANCIADA){ %>
          	<p class="mb-5">No financiada</p>
          <%} %>
          <%if (pro.getEstado().getEstado() == EstadoPropuesta.PUBLICADA){ %>
          	<p class="mb-5">Publicada</p>
          <%} %>
        </div>
        <div class="my-auto">
          <h3 class="mb-0">Proponente:</h3>
          <p class="mb-5"><%= port.getUsuarioPorNick(pro.getProponente()).getNombre() + " " + port.getUsuarioPorNick(pro.getProponente()).getApellido()  %> </p>
        </div>       
      </section>
      
      <%if(pro.getComentarios().size() > 0) { %>
            
	       <section class="resume-section p-3 p-lg-5 d-flex d-column" id="coment">
	        <div class="my-auto">
	          <h1 class="mb-0">Comentarios:</h1>
	       <div id="comentario" >
	         <div id="login-overlay" class="modal-dialog">
	       <div class="modal-header">
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
		               <div class="panel panel-default" >
		                 <div class="panel-heading">
		                 	<%
		        		  	Calendar fechaNac = coment.getFecha().toGregorianCalendar();
		        		  	Integer dia = fechaNac.get(Calendar.DAY_OF_MONTH);
		        		  	Integer mes = fechaNac.get(Calendar.MONTH) +1;
		        		  	Integer anio = fechaNac.get(Calendar.YEAR);		  	
		        		  	%>
		                   	<strong><%=coment.getColaborador()%></strong><span class="text-muted"><%= " " + dia.toString() + "/" + mes.toString() + "/" + anio.toString() %></span>
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
	      </section>
	      <%} %>
	      
	  <%if (pro.getColaboradores().size() > 0) {%>
      
	      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="colab">
	        <div class="my-auto">
	          <h1 class="mb-0">Colaboradores:</h1>
	               <div id="colaboradores">
	         <div id="login-overlay" class="modal-dialog" >
	       <div class="modal-header">
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
	               <div class="panel panel-default">
	                 <div class="panel-heading">
	                   <strong><%=port.getUsuarioPorNick(colab.getUsuario()).getNombre() + " " + port.getUsuarioPorNick(colab.getUsuario()).getApellido()%></strong><span class="text-muted"><%=" " + colab.getUsuario()%></span>
	                 </div>
	                 <div class="panel-body">
	                   <%= "$ " + colab.getMonto().toString() %>
	                   <% 
	                   System.out.println(colab.isPaga());
	                   if (estado == EstadoSesion.LOGIN_CORRECTO && (port.getUsuarioPorNick(colab.getUsuario()).getNickname()).equals(usuarioLogueado.getNickname()) &&(!colab.isPaga())){ %>
							<a href="pagar?id=<%=colab.getIdent().toString()%>" style="width:30%; float: right" class="btn btn-info">Pagar</a>
 						<% } %>
	                 </div><!-- /panel-body -->
	               </div><!-- /panel panel-default -->
	             </div><!-- /col-sm-5 -->
	           </div><!-- /row -->
	          <%} %>                   
	        </div>
	     </div>
	     
	     </div>
	        </div>
	      </section>
	   <%} %>

    </div>
  
   

   <jsp:include page="/WEB-INF/template/footer.jsp"/>
   <script src="media/styles/custom/js/jqueryEasing.js"></script>
   <script src="media/styles/custom/js/resume.js"></script>
   
 </body>
 </html>
