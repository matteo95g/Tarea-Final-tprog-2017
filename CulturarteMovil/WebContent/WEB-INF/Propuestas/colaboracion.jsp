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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CulturarteMovil :: Colaboracion</title>
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
 if (pro!=null)
	 System.out.print(pro.getTitulo());
 else
	 System.out.print("null");
 DtUsuario usuarioLogueado = Home.getUsuarioSesion(request);
 EstadoSesion estado = Home.getEstadoSesion(request);
 
 PublicadorService service = new PublicadorService();
 Publicador port = service.getPublicadorPort();

 
 //DtColecciones lstComent = port.obtenerComentarios(pro.getTitulo());   
 //DtColecciones lstCol = port.obtenerColaboraciones(pro.getTitulo());
 
 List<String> colaboradores = pro.getColaboradores();
 
 List<DtColaborador> colabs = port.getColaboradores().getColaboradores();
 
 %>
  <%!public static long daysBetween(Calendar startDate, Calendar endDate) {
	    long end = endDate.getTimeInMillis();
	    long start = startDate.getTimeInMillis();
	    return TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
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
	<%-- <div class="scrollmenu">
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


      <section class="resume-section p-3 p-lg-5 d-flex d-column" id="desc">
        <div class="my-auto">
          <h1 class="mb-0">Descripción:</h1>
          <p class="mb-5"> <%=pro.getDescripcion() %> </p>                  
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
      <section>
      <%String titulo= pro.getTitulo(); %>
      <form name="form-colaboracion" action="Colaborar?titulo=<%=titulo%>" method="post" accept-charset="UTF-8">
      <h1 class="mb-0">Colaboracion:</h1>
      <div class="my-auto"> --%>
 <%String titulo= pro.getTitulo(); %>     
 <div class="container">
  <h4 class="mb-0">Monto a colaborar:</h4>
  <form name="form-colaboracion" action="Colaborar?titulo=<%=titulo%>" method="post" accept-charset="UTF-8">   
      <input type="number" class="form-control" name="Monto" value="monto" min="1" placeholder="Monto a colaborar">
      <div class="my-auto">
        <h4 class="mb-0">Seleccione Retorno:</h4>
        <%
          int j=0;
          while (j< pro.getRetornos().size()){ 
            if(pro.getRetornos().get(j)== Retorno.ENTRADAS){ %>
              <label><input type="radio" name="retorno"value="Entradas"> Entradas</label>
            <%}
            else{%>
              <label><input type="radio" name="retorno" checked="checked"value="Porcentaje"> Porcentaje</label>
            <%}
          j++;
          }
          request.setAttribute("titulo",pro.getTitulo());
        %>
      </div>
    <div>
      <button class="btn btn-success" onclick="submit()">Colaborar</button>      
    </div>
  </form>
</div>
 




<jsp:include page="/WEB-INF/template/footer.jsp"/>
<script src="media/styles/custom/js/jqueryEasing.js"></script>
<script src="media/styles/custom/js/resume.js"></script>
</body>
</html>