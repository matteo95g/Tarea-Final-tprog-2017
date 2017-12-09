<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.DtCategoria"%>
<%@page import="publicador.DtPropuesta"%>

<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.ListIterator" %>
<%@page import="javax.servlet.jsp.JspWriter"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Calendar"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CulturarteWeb :: Propuestas por categoria</title>
<jsp:include page="/WEB-INF/template/head.jsp"/>
 <link rel="stylesheet" href="media/styles/custom/sidebar.css">
 <link rel="stylesheet" type="text/css" href="media/styles/custom/cards.css">  
 <style>
 #sidebar ul{
	background: #222;
	color: white;
	padding: 5px;
	border-radius: 3px;
	text-decoration: none !important;
 }
 
 </style>
</head>
<body>
<jsp:include page="/WEB-INF/template/header.jsp"/>
<%!
private void cargarCategorias(DtCategoria categ, JspWriter out)throws IOException {
	if (categ.getHijas() != null) {
		if (categ.getNombre() == "Categoria") {
			out.print("<li><input type=\"radio\" onclick=\"myFunction()\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\" checked=\"checked\">"+categ.getNombre()+"<i class=\"icono derecha glyphicon glyphicon-chevron-down\"></i>");
		} else {
			out.print("<li><input type=\"radio\" onclick=\"myFunction()\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\">"+categ.getNombre()+"<i class=\"icono derecha glyphicon glyphicon-chevron-down\"></i>");
		}
	}	else {
		out.print("<li><input type=\"radio\" onclick=\"myFunction()\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\">"+categ.getNombre());
	}
	if (categ.getHijas() != null) {
		out.print("<ul>");
		ListIterator<DtCategoria> iter = categ.getHijas().listIterator();
		while (iter.hasNext()) {
			DtCategoria categHija = iter.next();
			cargarCategorias(categHija, out); 
		}
		out.print("</ul>");
	}
	out.print("</li>");
}
%>

<%

PublicadorService service = new PublicadorService();
Publicador port = service.getPublicadorPort();

List<DtPropuesta> lstProp = port.obtenerPropuestas().getPropuestas();



%>

<div id ="sidebar">
	<ul id="accordion">
		<%
		List<DtCategoria> cats = (ArrayList<DtCategoria>) request.getAttribute("categorias");
		DtCategoria primCat = ((ArrayList<DtCategoria>)cats).get(0);
		cargarCategorias(primCat, out); %>
	</ul>
</div>


<script>
function myFunction() {
	var radios = document.getElementsByName('categoria');
	for (var i = 0, length = radios.length; i < length; i++) {
	    if (radios[i].checked) {
	        // do whatever you want with the checked radio
	        var catSelect = radios[i].value;
	        $.get("categorias", catSelect);
	        window.location.replace("categorias?categorias=all?cat=" + catSelect);
	        // only one radio can be logically checked, don't check the rest
	        break;
	    }
	}
}
</script>
<div class="container" style="overflow: auto; height: 600px; width: 800px;">
	<%
        String name = request.getQueryString();		
 		final String str = name;
		final Matcher matcher = Pattern.compile("cat=").matcher(str);
		if(matcher.find()){
		    name = str.substring(matcher.end()).trim();
		}
	
    %>
	
 	 <% 
      Iterator<DtPropuesta> itProp = lstProp.iterator();
        while(!lstProp.isEmpty() && itProp.hasNext()) { 
        DtPropuesta pro = itProp.next();
        if (pro.getCategoria().equals(name)){
        %>        
        <div class="card mb-3">
            <%if (!pro.getImagen().equals("")) {%>
  				<img class="fotos-prop" style="height: 300px; width: 800px" src="/CulturarteWeb/imagenes?id=<%= pro.getImagen() %>" alt="Card image cap">
  			<%}else{ %>
  				<img class="fotos-prop" style="height: 300px; width: 800px" src="media/img/defecto-propuesta.gif" alt="Card image cap">
  			<%} %>
          <div class="card-block">
            <h4 class="card-title"><%=pro.getTitulo()%></h4>
            <p class="card-text"><%=pro.getDescripcion()%></p>
            <a href="propuestas?propuesta=<%=pro.getTitulo()%>" style="margin-left: 80%" class="btn btn-primary">Ver propuesta</a>
            <br/>
            
	        <%
	 		 Calendar calendar = pro.getFechaRealizacion().toGregorianCalendar();
	 	  	 int diaP = calendar.get(Calendar.DAY_OF_MONTH);
			 int mesP = calendar.get(Calendar.MONTH) + 1;
			 int anioP = calendar.get(Calendar.YEAR);
			%>           
            <p class="card-text"><small class="text-muted">Se realizará:<%=" " + diaP + "/" + mesP +"/" + anioP %></small></p>
          </div>
        </div>
        <%} %>        
        <%} %>
</div>




<jsp:include page="/WEB-INF/template/footer.jsp"/>
<!----------------- QUICKORDION ----------------->
<script src="media/styles/custom/js/jquery.quiccordion.js"></script>
        <script>
            $(document).ready(function(){
                $("#accordion").quiccordion();
                
                $("#accordion a").click(function(e){
                    e.preventDefault();
                });
            });
</script>
</body>
</html>