<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.*"%>

<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>

<%@page import="model.EstadoSesion"%>
<%@page import="controllers.Home"%>

<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.Retorno"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CulturarteWeb :: Registrar Colaboracion</title>
<jsp:include page="/WEB-INF/template/head.jsp"/>
 <link rel="stylesheet" href="media/styles/custom/sidebar.css">
 <link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css"> 
</head>
<body>
 <jsp:include page="/WEB-INF/template/header.jsp"/>

	<%
	PublicadorService service = new PublicadorService();
    Publicador port = service.getPublicadorPort();
	
	DtPropuesta pro = (DtPropuesta) request.getAttribute("propuesta");
	String tit= pro.getTitulo();
	DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
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
       				<%-- <p class="card-text"><%=pro.getDescripcion() %></p> --%>
     			</div> 
     			<ul class="list-group list-group-flush">
     			  <li class="list-group-item">Lugar: <%=pro.getLugar() %></li>
       				<%
   				 	Calendar calendar = pro.getFechaRealizacion().toGregorianCalendar();
   				 	int diaIng = calendar.get(Calendar.DAY_OF_MONTH);
					int mesIng = calendar.get(Calendar.MONTH) + 1;
					int anioIng = calendar.get(Calendar.YEAR);       				
       				%>
       			<li class="list-group-item">Fecha a Realizar: <%= Integer.toString(diaIng) +"/"+ Integer.toString(mesIng) +"/"+ Integer.toString(anioIng)%></li>
      			<li class="list-group-item">Precio de la entrada: $ <%=pro.getPrecioEntrada() %></li>
       			<li class="list-group-item">Monto Necesario: $ <%=pro.getMontoNecesario() %></li>
       			<li class="list-group-item">Monto Actual: $ <%=pro.getMontoActual() %></li>
       <%List<Retorno> ret = pro.getRetornos();
        Iterator<Retorno> retor = ret.iterator();
         int largoRet = ret.size();
         Retorno retorno1 = null;
         Retorno retorno2 = null;
         if (largoRet>0){
         	retorno1= retor.next();
         	largoRet--;
       	 }
       	 if (largoRet>0){
       	 retorno2= retor.next();
     	}
     %>
     			<li class="list-group-item">Retorno: <%if(retorno1!=null){%><%=retorno1%> <%} if(retorno2!=null){%><%="y " +retorno2%> <%} %></li>
     			<li class="list-group-item">Categoria: <%=pro.getCategoria() %></li>
     			<li class="list-group-item">Estado: <%=pro.getEstado().getEstado() %></li>               
     			<li class="list-group-item">Proponente: <a href="usuarios?usuario=<%= pro.getProponente()%>"> <%=port.getUsuarioPorNick(pro.getProponente()).getNombre() +" "+ port.getUsuarioPorNick(pro.getProponente()).getApellido() %> </a></li>               
   				</ul>            
 			</div> 
 		</ul>
 	</div>
 	<!-- <div id="container">
    	<div id="comentario">
 
 		</div>
 	</div> -->
 	<div class="col-md-4 col-md-offset-4" style="margin-top:50px;">
		<form name="form-colaboracion" action="Colaborar" method="post" accept-charset="UTF-8">
				<h2 class="form-signin-heading">Ingrese los datos:</h2>
				<br/>
				<div id="signupalert" style="display:none" class="alert alert-danger">
					<p>Error:</p>
					<span></span>
				</div>
				<br/>
				<div style="margin-bottom: 25px" class="input-group">
				<input type="text"  class="form-control" name="titulo" value="<%=tit%>">
				<%request.setAttribute("titulo",tit); %>
					<span class="input-group-addon"><i class="glyphicon glyphicon-usd"></i></span>
					<input type="number" class="form-control" name="Monto" value="monto" min="1" placeholder="Monto a colaborar">
				</div>
				<h4 class="form-signin-heading">Seleccione el tipo de retorno:</h4>
				<div class="radio">
				<%if(( retorno1 == Retorno.ENTRADAS) || ((retorno2!=null) && (retorno2==Retorno.ENTRADAS))){%>
					
					<label><input type="radio" name="retorno" checked="checked"value="Entradas"> Entradas</label>
					<%} %>
				<%if((retorno1 == Retorno.PORCENTAJE)||((retorno2!=null) && (retorno2== Retorno.PORCENTAJE))){ %>
					<label><input type="radio" name="retorno"value="Porcentaje"> Porcentaje</label>
				<%} %>
				</div>
				
				<br/>
				<div  style="text-align: center"class="form-group">
					<div>
						<button class="btn btn-lg btn-primary btn-block" onclick="submit()">Registrar Colaboracion</button>
						<%-- <button id="btn-cance" href="/propuestas?propuesta=<%=pro.getTitulo()%>" onclick="alert('Se ha Cancelado la Colaboracion!')" class="btn btn-danger">Cancelar Colaboracion</button> --%>
					</div>
				</div>
			
			<br/>
			<br/>
			<br/>
			<br/>
			</form>	
		</div>
		
		
		
 
 <jsp:include page="/WEB-INF/template/footer.jsp"/>
</body>
</html>