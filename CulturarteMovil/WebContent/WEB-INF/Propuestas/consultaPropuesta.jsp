<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.EstadoPropuesta"%>
<%@page import="publicador.Retorno"%>
<%@page import="model.EstadoSesion"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeFactory"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>

<%@page import="controllers.Home"%>


<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
   <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" type="text/css" href="media/styles/custom/consultaProp.css">
		<link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
		<jsp:include page="/WEB-INF/template/head.jsp"/>	   
		<title>CulturarteMovil :: Propuestas</title>
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
    </head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp"/>	



<div class="container">
<h2> Consulta de Propuesta </h2>

		<% 
			DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
			EstadoSesion estado = Home.getEstadoSesion(request);
			
			PublicadorService service = new PublicadorService();
	        Publicador port = service.getPublicadorPort();

			Calendar calendar;
			Integer diaIng;
			Integer mesIng;
			Integer anioIng;
			
			DtPropuesta pro;
			List<DtPropuesta> props = (List<DtPropuesta>) request.getAttribute("propuestas");	 
			
			
			int largo = props.size();
			List<DtPropuesta> copy= props.subList(0,largo);
			Iterator<DtPropuesta> prop = copy.iterator();
			%>
			
			<%!			
			private boolean existeEnLista(List<String> lstFav, String titulo){
				return lstFav.contains(titulo);
			}
			%>
			<div class="row">
				<%
				
				while (prop.hasNext()){
					DtPropuesta propu = prop.next();
					if (propu.getEstado().getEstado() == EstadoPropuesta.INGRESADA){
						prop.remove();
					}
				}
				
				largo = copy.size();
				prop = copy.iterator();			
					while(prop.hasNext()){	
						pro = prop.next();
				%>
				
						<div style="position: relative;" class="col-sm-6 col-md-4" >	
						 <h4><%=pro.getTitulo() %></h4>					
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
				<%} %>
			</div>

</div>
<jsp:include page="/WEB-INF/template/footer.jsp"/>
</body>

</html>