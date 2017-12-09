<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>

<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtPropuesta"%>

<%@page import="java.util.GregorianCalendar"%>
<%@page import="javax.xml.datatype.DatatypeFactory"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>

<%@page import="controllers.Home"%>
<%@page import="model.EstadoSesion"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CulturarteWeb :: Propuestas</title>
<jsp:include page="/WEB-INF/template/head.jsp"/>
<style>
	.card-group {
	    padding: 10rem;
	}
	
	.card-img-top {
		height: 192px !important;
		width: auto !important;
	}
	
	.btn{
		width: 50%;
		position: relative;
		float: right;
	}
	.links{
		padding-top: 3px;
		list-style: none;
	}
	</style>
		<link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp"/>	
	<div class="container">
		<h1> Propuestas </h1>
	<%
	
	String info = request.getParameter("info");
	
	PublicadorService service = new PublicadorService();
    Publicador port = service.getPublicadorPort();
	
	List<DtPropuesta> props = port.obtenerPropuestas().getPropuestas();
	
	EstadoSesion estado = Home.getEstadoSesion(request);
	DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
	
	
	DtPropuesta propAct;
	%>
	
	<%!			
	private boolean existeEnLista(List<String> lstFav, String titulo){
		return lstFav.contains(titulo);
	}
	%>
	
	<%
	List<DtPropuesta> list = new LinkedList<DtPropuesta>();
	if (info!= null){
		/* Pattern patron= Pattern.compile(info['i']); */
		Iterator<DtPropuesta> prop = props.iterator();
		while (prop.hasNext()){
			propAct= prop.next();
			String tit=propAct.getTitulo();
			String desc=propAct.getDescripcion();
			String lug= propAct.getLugar();
			tit = tit.toLowerCase();
			desc= desc.toLowerCase();
			lug= lug.toLowerCase();
			info = info.toLowerCase();
			if (tit.contains(info)){
				list.add(propAct);
			}
			else if(desc.contains(info)){
				list.add(propAct);
			}
			else if(lug.contains(info)){
				list.add(propAct);
			}
			
		}
	}
	if(list.isEmpty()){%>
		<h1 style="text-align:center">No se encontraron propuestas con esta informacion!</h1>
	<%}
	
	
	int largo = list.size(); 
	Iterator<DtPropuesta> prop = list.iterator();
	DtPropuesta pro;
		
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
   				 	Calendar calendar = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					Integer mesIng = calendar.get(Calendar.MONTH) + 1;
					Integer anioIng = calendar.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $ <%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>


  					<li class="links">  					
  						<a href="Buscador?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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
   				 	Calendar calendar2 = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng2 = calendar2.get(Calendar.DAY_OF_MONTH);
					Integer mesIng2 = calendar2.get(Calendar.MONTH) + 1;
					Integer anioIng2 = calendar2.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="Buscador?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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
   				 	Calendar calendar3 = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng3 = calendar3.get(Calendar.DAY_OF_MONTH);
					Integer mesIng3 = calendar3.get(Calendar.MONTH) + 1;
					Integer anioIng3 = calendar3.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="propuestas?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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
   				 	Calendar calendar4 = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng4 = calendar4.get(Calendar.DAY_OF_MONTH);
					Integer mesIng4 = calendar4.get(Calendar.MONTH) + 1;
					Integer anioIng4 = calendar4.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng4) +"/"+ Integer.toString(mesIng4) +"/"+ Integer.toString(anioIng4)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="Buscador?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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
   				 	Calendar calendar = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					Integer mesIng = calendar.get(Calendar.MONTH) + 1;
					Integer anioIng = calendar.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="Buscador?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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
   				 	Calendar calendar = pro.getFechaRealizacion().toGregorianCalendar();
   				 	Integer diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					Integer mesIng = calendar.get(Calendar.MONTH) + 1;
					Integer anioIng = calendar.get(Calendar.YEAR);					 
					 %>
    				<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
    				<li class="list-group-item">Precio de la entrada: $<%=pro.getPrecioEntrada() %></li>
    				<li class="list-group-item">Proponente: <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %></li>
  					</ul>

					<li class="links">  					
  						<a href="Buscador?propuesta=<%= pro.getTitulo()%>">
		  					<button type="button"
					        class="btn btn-default btn-lg glyphicon glyphicon glyphicon-exclamation-sign" style="color:blue">
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