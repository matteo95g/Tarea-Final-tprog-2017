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
   <style>
 	.card-group {
	    padding: 10rem;
	}
	
	.card-img-top {
		height: 192px !important;
		width: auto !important;
	}
	
	.links{
		padding-top: 3px;
		list-style: none;
	}
	</style>
		<link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
		<jsp:include page="/WEB-INF/template/head.jsp"/>	   
		<title>CulturarteWeb :: Propuestas</title>
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
		<h1> Consulta de Propuesta </h1>
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
			<%
			
			while (prop.hasNext()){
				DtPropuesta propu = prop.next();
				if (propu.getEstado().getEstado() == EstadoPropuesta.INGRESADA){
					prop.remove();
				}
			}
			
			largo=copy.size();
			prop= copy.iterator();
			if (largo > 0){				
				while(largo> 2){					
					largo= largo-3;					 
					pro= prop.next();					
			%>
			<div class="card-group">
			 <div class="card" style="width: 20rem;">
			 		<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				<%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $ <%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>
  					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>				        
				        			        
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>
			</div>			 
				<% 
				pro= prop.next();
				
				%>
							
				<div class="card" style="width: 20rem;">
  					<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				<%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR); %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>

				</div>
				
				<% 
				pro= prop.next();
				
				%>
				
				<div class="card" style="width: 20rem;">
  					<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				 <%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR); %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>

				</div>
				</div>
			<%	
				}
			} 
			if (largo == 2){ 
				pro= prop.next();%>
			<div class="card-group">	
           <div class="card" style="width: 20rem;">
  					<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				 <%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR); %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>

				</div>
			 
				<% 
				pro= prop.next();
				
				%>
							
				<div class="card" style="width: 20rem;">
  					<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				 <%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR); %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>

				</div>
				 <div class="card" style="border-color: #fff">
               </div>
               </div>
			
			
			<%} %>
			<% if (largo == 1){ 
				pro= prop.next();%>
				<div class="card-group">
            	<div class="card" style="width: 20rem;">
  					<%if (!pro.getImagen().equals("")) {%>
  						<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  					<%}else{ %>
  						<img class="card-img-top" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  				  	<%} %>
  					<div class="card-block">
    					<h4 class="card-title"style="text-align:center"><%= pro.getTitulo()%></h4>
  					</div>
  					<ul class="list-group list-group-flush">
    				<li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
    				 <%
    				 calendar = pro.getFechaRealizacion().toGregorianCalendar();
    				 diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					 mesIng = calendar.get(Calendar.MONTH) + 1;
					 anioIng = calendar.get(Calendar.YEAR); %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue; width: 50%; position: relative; float: right">
					        </button>
				        </a>
				        <%if (estado != EstadoSesion.NO_LOGIN){  %>
					        <%if (existeEnLista(usuarioLogueado.getFavoritas(), pro.getTitulo())) { %>
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
					    <%} %>
				    </li>

				</div>
				<div class="card" style="border-color: #fff">
               </div>
               <div class="card" style="border-color: #fff">
               </div>
               </div>				
		<%} %>
		


</div>
<jsp:include page="/WEB-INF/template/footer.jsp"/>
</body>

</html>