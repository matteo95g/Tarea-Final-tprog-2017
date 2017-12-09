<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">
      <!-- <link rel="stylesheet" type="text/css" href="media/styles/custom/casero.css"> -->
      <jsp:include page="/WEB-INF/template/head.jsp"/>
      <title>CulturarteWeb :: Usuarios</title>
      <style>
         .card-img-top {
         width: auto !important;
         height: 120px !important;
         }	
      </style>
   </head>
   <body>
      <jsp:include page="/WEB-INF/template/header.jsp"/>
      <div class="container">
         <h1> Consulta de Perfil de Usuario </h1>
         <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#menu1">Proponentes</a></li>
            <li><a data-toggle="tab" href="#menu2">Colaboradores</a></li>
         </ul>
         <div class="tab-content" style="padding:5%;">
            <div id="menu1" class="tab-pane fade in active">
               <h3>Proponentes:</h3>
             <jsp:include page="/WEB-INF/usuarios/listarProps.jsp"/>
            </div>
            <div id="menu2" class="tab-pane fade">
               <h3>Colaboradores:</h3>
             <jsp:include page="/WEB-INF/usuarios/listarColabs.jsp"/>
            </div>
         </div>
      </div>
      <jsp:include page="/WEB-INF/template/footer.jsp"/>
   </body>
</html>