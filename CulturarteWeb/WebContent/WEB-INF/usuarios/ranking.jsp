<%@page import="publicador.DtUsuario"%>
<%@page import="publicador.DtProponente"%>
<%@page import="publicador.DtColaborador"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
<jsp:include page="/WEB-INF/template/head.jsp"/>
<title>Ranking de Usuarios</title>
<style>
         .card-img-top {
         width: auto !important;
         height: 120px !important;
         }	
      </style>
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp"/>
<%!
	private boolean existeEnLista(List<String> lst, String nick){
		Iterator<String> it = lst.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre){
			String usu = it.next();
			if (usu == nick){
				encontre = true;
			}
		}
		return encontre;
	}
%>
<%!private void ordenarLista(ArrayList<DtUsuario> lista){
    Iterator<DtUsuario> actual;
	DtUsuario usr,usr2;
	int siguiente;
    
    actual = lista.iterator();
    for(int i=0; i<lista.size();i++)
    {
         siguiente = i;
         siguiente++;
         while(siguiente<lista.size()){
              if(lista.get(i).getSeguidores().size() < lista.get(siguiente).getSeguidores().size()){
            	usr= lista.set(siguiente, lista.get(i));
                //lista.get(siguiente) = lista.get(actual);
                usr2= lista.set(i,usr);      
              }
              siguiente++;                    
         }        
    }
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
	ArrayList<DtUsuario>  proponentes = (ArrayList<DtUsuario>) request.getAttribute("proponentes");
	ArrayList<DtColaborador> colaboradores = (ArrayList<DtColaborador>) request.getAttribute("colaboradores");
	proponentes.addAll(colaboradores);
	ordenarLista(proponentes);
//LISTA DE USUARIOS
	DtUsuario prop;
	session = request.getSession();
	List<String> uSeguidos = null;  
	String usrlog = null;
	boolean login = false ;
if (session.getAttribute("usr_sesion") != null) {
  	login = true;
  	uSeguidos = (List<String>) request.getAttribute("seguidos");
  	usrlog = (String)session.getAttribute("usr_sesion");
}
%> 
<div class="container">
  <h1> Ranking de Usuarios por Seguidores </h1>
<% 

//LISTA DE PROPONENTES
	Iterator<DtUsuario> usr = proponentes.iterator();
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
      <img class="card-img-top" src="/CulturarteWeb/imagenes?id=<%= prop.getImagen() %>"alt="Card image cap">                    
      <%} else {%>
      <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
      <%}%>                    
      <div class="card-block">
        <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
        <h5 class="card-title"> <%= prop.getNickname() %> </h5>
        
        <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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

  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
      <%} else {%>
      <img class="card-img-top" src="media/img/defecto.gif" alt="Card image cap">
      <%}%>                   
 <div class="card-block">
  <h4 class="card-title"><%= prop.getNombre() + " " + prop.getApellido() %></h4>
  <h5 class="card-title"> <%= prop.getNickname() %> </h5>
  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
    
    <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
    
    <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
  
  <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
<div class="card" style="border-color: #fff">
</div>
<div class="card" style="border-color: #fff">
</div>
</div>
<%}else { %>
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
    
    <a href="usuarios?usuario=<%= prop.getNickname()%>" class="btn btn-default btn-lg" role="button">
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
<div class="card" style="border-color: #fff">
</div>
<div class="card" style="border-color: #fff">
</div>
<div class="card" style="border-color: #fff">
</div>
</div>            
<%}%> 
<jsp:include page="/WEB-INF/template/footer.jsp"/>
</body>
</html>