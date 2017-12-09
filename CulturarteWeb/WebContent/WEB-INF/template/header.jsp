<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="controllers.Home"%>
<%@page import="controllers.Iniciar"%>
<%@page import="controllers.IniciarSesion"%>

<%@page import="publicador.Publicador"%>
<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtProponente"%>
<%@page import="publicador.DtColaborador"%>

<div id="header">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
	      		</button>
	      		<a class="navbar-brand" rel="home" href="home" title="Culturarte">
        			<img style="max-width:35px; margin-top: -7px;" src="media/img/icon.png">
    			</a>
    			<a class="navbar-brand" href="home" style="margin-left:-32px; font-weight: bold;">ulturarte</a>
	    	</div>
	    	<%
	    	 DtUsuario usr = Home.getUsuarioSesion(request); 
	    	if (usr == null) {
	    	%>
	    	<div class="collapse navbar-collapse" id="myNavbar">
	      		<ul class="nav navbar-nav">
	        		<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuarios <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="usuarios">Consulta de Perfil de Usuario</a></li>
	            			<li><a href="RankingUsuarios">Ver Ranking de Usuarios</a></li>
	          			</ul>
	        		</li>
	            	<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Propuestas <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="propuestas">Consulta de Propuestas</a></li>
	            			<li><a href="categorias?categorias=all">Consulta de Propuestas por Categoria</a></li>
	          			</ul>
	        		</li>
	      		</ul>
	      		
				<form class="navbar-form navbar-left" action="Buscador" role="form" method="post">        			
        			<div class="input-group">
				      <input type="text" class="form-control" placeholder="Titulo, descripcion, lugar" name="info">
				      <span class="input-group-btn">
				        <button class="btn btn-secondary" type="submit">Buscar</button>
				      </span>
				    </div>        			
     			</form>
     			 
	      		<ul class="nav navbar-nav navbar-right">
	        		<li><a href="altaUsuario"><span class="glyphicon glyphicon-user" style="color:#0081ff"></span>&nbsp;&nbsp;Registrarse</a></li>
	        		<li><a href="iniciar"><span class="glyphicon glyphicon-log-in" style="color:#03f643"></span>&nbsp;&nbsp;Iniciar Sesión</a></li>
	      		</ul>
	    	</div>
	    	
	    	<%
	    	} else {
	    		try {
	    			DtProponente prop = (DtProponente)usr;
	    	%>
	    	
	    	
	    	<div class="collapse navbar-collapse" id="myNavbar">
	      		<ul class="nav navbar-nav">
	        		<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuarios <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="usuarios">Consulta de Perfil de Usuario</a></li>
	          			</ul>
	        		</li>
	            	<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Propuestas <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="categorias">Alta de Propuesta</a></li>
	            			<li><a href="propuestas">Consulta de Propuestas</a></li>
	            			<li><a href="categorias?categorias=all">Consulta de Propuestas por Categoria</a></li>
	          			</ul>
	        		</li>
	      		</ul>
				<form class="navbar-form navbar-left" action="Buscador" role="form" method="post">        			
        			<div class="input-group">
				      <input type="text" class="form-control" placeholder="Titulo, descripcion, lugar" name="info">
				      <span class="input-group-btn">
				        <button class="btn btn-secondary" type="submit">Buscar</button>
				      </span>
				    </div>        			
     			</form>
	      		<ul class="nav navbar-nav navbar-right">
	        		<li><a href="usuarios?usuario=<%= prop.getNickname()%>"><span class="glyphicon glyphicon-user" style="color:#0081ff"></span>&nbsp;&nbsp;<%= prop.getNombre() %> <%= prop.getApellido() %></a></li>
	        		<li><a href="cerrarSesion"><span class="glyphicon glyphicon-log-out" style="color:#ff0000"></span>&nbsp;&nbsp;Cerrar Sesión</a></li>
	      		</ul>
	    	</div>
	    	
	    	<%
	    		} catch (Exception ex) {
	    			DtColaborador colab = (DtColaborador)usr;
	    	%>
	    	
	    	<div class="collapse navbar-collapse" id="myNavbar">
	      		<ul class="nav navbar-nav">
	        		<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Usuarios <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="usuarios">Consulta de Perfil de Usuario</a></li>
	          			</ul>
	        		</li>
	            	<li class="dropdown">
	          			<a class="dropdown-toggle" data-toggle="dropdown" href="#">Propuestas <span class="caret"></span></a>
	          			<ul class="dropdown-menu">
	            			<li><a href="propuestas">Consulta de Propuestas</a></li>
	            			<li><a href="categorias?categorias=all">Consulta de Propuestas por Categoría</a></li>
	          			</ul>
	        		</li>
	      		</ul>      
				<form class="navbar-form navbar-left" action="Buscador" role="form" method="post">        			
        			<div class="input-group">
				      <input type="text" class="form-control" placeholder="Titulo, descripcion, lugar" name="info">
				      <span class="input-group-btn">
				        <button class="btn btn-secondary" type="submit">Buscar</button>
				      </span>
				    </div>        			
     			</form>
	      		<ul class="nav navbar-nav navbar-right">
	        		<li><a href="usuarios?usuario=<%= colab.getNickname()%>"><span class="glyphicon glyphicon-user" style="color:#0081ff"></span>&nbsp;&nbsp;<%= colab.getNombre() %> <%= colab.getApellido() %></a></li>
	        		<li><a href="cerrarSesion"><span class="glyphicon glyphicon-log-out" style="color:#ff0000"></span>&nbsp;&nbsp;Cerrar Sesión</a></li>
	      		</ul>
	    	</div>
	    	
	    	<%
	    		}
	    	}
	    	%>
	    	
	  	</div>
	</nav>
</div>