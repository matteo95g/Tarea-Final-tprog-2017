<%@page import="controllers.Home"%>
<%@page import="model.EstadoSesion"%>
<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtProponente"%>
<%@page import="publicador.DtColaborador"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<%!
private boolean existeEnLista(List<String> lst, String nick){
  return lst.contains(nick);
}
%>


<%
DtProponente prop;
String biog = "";

List<DtProponente> proponentes = (List<DtProponente>) request.getAttribute("proponentes");
List<String> uSeguidos = null;
DtUsuario dtUsrLog = Home.getUsuarioSesion(request);
EstadoSesion estado = Home.getEstadoSesion(request);
String usrlog = null;
boolean login = false;
if (estado == EstadoSesion.LOGIN_CORRECTO){
login = true;
uSeguidos = dtUsrLog.getSeguidos();
usrlog = dtUsrLog.getNickname();
}
%>

<script>  
  function cambEstado(useguido, id) {
    if ($(id).hasClass("active")) {
      var vaccion = "dejar";
    } else {
      var vaccion = "seguir";
    }    
    var datos = {
      seguido : useguido,
      accion : vaccion
    }
    $.get("SeguirUsr", datos);

    if ($(id).hasClass("active")) {
      $(id).removeClass("active");
    } else {
      $(id).addClass("active");
    }
  }
</script>
<% 

//LISTA DE PROPONENTES
Iterator<DtProponente> usr = proponentes.iterator();
int largoLstProp = proponentes.size();
while(largoLstProp > 3 && usr.hasNext()){
	largoLstProp = largoLstProp - 4;          
	%>
	<div class="card-group">
	  <div class="card">      
	    <%
	    prop = usr.next();
	    if (!prop.getImagen().equals("")) {
	    
	    %>
	    <img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
	    <%} else {%>
	    <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
	    <%}%>                    
	    <div class="card-block">
	      <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
	      <h5 class="card-title"> <%= prop.getNickname() %> </h5>
	      
	      <a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
	        <span class="glyphicon glyphicon-exclamation-sign"></span>
	      </a>
	      <%
	      
	        //Boton Seguir Usuario
	      
	        if (login) { //Existe Session
	        if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
	        %>                                                                                            
	
	        <button type="button"
	        class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active"
	        style="color: #03F643"
	        onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	        <%
	      } else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
	      %>
	      <button type="button"
	      class="btn btn-default btn-lg glyphicon glyphicon-ok-circle"
	      style="color: #03F643"
	      onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	      <%
	    }
	  }
  %>

	</div>
	</div>

<div class="card">
  <%
  if (usr.hasNext()){
  prop = usr.next();
  if (!prop.getImagen().equals("")) {
  %>
  <img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  <%} else {%>
  <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  <%}%>                    
  <div class="card-block">
    <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    <h5 class="card-title"> <%= prop.getNickname() %> </h5>

    
    <a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      <span class="glyphicon glyphicon-exclamation-sign"></span>
    </a>
    
    <%
    
  //Boton Seguir Usuario
    
  if (login) { //Existe Session
  if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
  %>                                                                                            

  <button type="button"
  class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active"
  style="color: #03F643"
  onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  <%
} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
%>
<button type="button"
class="btn btn-default btn-lg glyphicon glyphicon-ok-circle"
style="color: #03F643"
onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
<%
}
}
}
%>
</div>
</div>
<div class="card">
  <%
  if(usr.hasNext()){
  prop = usr.next();
  if (!prop.getImagen().equals("")) {
  %>
  <img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  <%} else {
  %>
  <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  <%}%>                   
  <div class="card-block">
    <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    <h5 class="card-title"> <%= prop.getNickname() %> </h5>
    
    <a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      <span class="glyphicon glyphicon-exclamation-sign"></span>
    </a>
    
    <%
    
  //Boton Seguir Usuario
    
  if (login) { //Existe Session
  if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
  %>                                                                                            

  <button type="button"
  class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active"
  style="color: #03F643"
  onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  <%
} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
%>
<button type="button"
class="btn btn-default btn-lg glyphicon glyphicon-ok-circle"
style="color: #03F643"
onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
<%
}
}
}
%>
</div>
</div>
<div class="card">
  <%
  if (usr.hasNext()){
  prop = usr.next();
  if (!prop.getImagen().equals("")) {
  %>
  <img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  <%} else {%>
  <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  <%}%>                   
  <div class="card-block">
    <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    <h5 class="card-title"> <%= prop.getNickname() %> </h5>
    
    <a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      <span class="glyphicon glyphicon-exclamation-sign"></span>
    </a>
    
    <%
    
  //Boton Seguir Usuario
    
  if (login) { //Existe Session
  if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
  %>                                                                                            

  <button type="button"
  class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active"
  style="color: #03F643"
  onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  <%
} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
%>
<button type="button"
class="btn btn-default btn-lg glyphicon glyphicon-ok-circle"
style="color: #03F643"
onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
<%
}
}
}
%>
</div>
</div>
</div>
<% }

if (largoLstProp == 3){ %>
	<div class="card-group">
 		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>
		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>

		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>
		<div class="card" style="border-color: #fff">
		</div>
	</div>
	
<%    
}else if (largoLstProp == 2){%>
	<div class="card-group">
		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>
		
 		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>

		<div class="card" style="border-color: #fff">
		</div>
		<div class="card" style="border-color: #fff">
		</div>
	</div>

<%}else if (largoLstProp == 1) { %>

	 <div class="card-group">
 		<div class="card">
 	 	<%
  		if (usr.hasNext()){
  			prop = usr.next();
  			if (!prop.getImagen().equals("")) {
  				%>
  				<img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>" alt="Card image cap">                    
  			<%} else {%>
  				<img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
  			<%}%>                   
  			<div class="card-block">
    		<h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
    		<h5 class="card-title"> <%= prop.getNickname() %> </h5> 
    		<a href="?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
      		<span class="glyphicon glyphicon-exclamation-sign"></span>
    		</a>
		    <%    
		    //Boton Seguir Usuario    
		    if (login) { //Existe Session
			    if (existeEnLista(uSeguidos, prop.getNickname()) &&  !usrlog.equals(prop.getNickname())) { //Es seguido y no es el mismo
			    	%>
			    	<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle active" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
	    			<%
  				} else if (!usrlog.equals(prop.getNickname()))  { //No es seguido y no es el mismo
  					%>
  					<button type="button" class="btn btn-default btn-lg glyphicon glyphicon-ok-circle" style="color: #03F643" onclick="cambEstado('<%=prop.getNickname()%>',this)"></button>
  					<%
				}
			}
		}
		%>
			</div>
		</div>
		<div class="card" style="border-color: #fff">
		</div>
		<div class="card" style="border-color: #fff">
		</div>
		<div class="card" style="border-color: #fff">
		</div>
	</div>             
<%}%>