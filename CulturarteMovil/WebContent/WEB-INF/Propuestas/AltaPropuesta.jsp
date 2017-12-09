<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="publicador.DtCategoria"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList" %>
<%@page import="java.util.ListIterator" %>
<%@page import="javax.servlet.jsp.JspWriter"%>
<%@page import="java.io.*"%>

<!DOCTYPE html >
<html>
	<head>	

		<jsp:include page="/WEB-INF/template/head.jsp"/>
		<title>CulturarteMovil :: Alta Propuesta</title> 
		<link rel="stylesheet" href="media/styles/dateTimePicker/css/bootstrap-datetimepicker.min.css">
		
		

        <style>/* 
        	 <style>
            body,html{
                margin: 0;
                padding: 0;
                font-family: Arial;
            } */
            
      		.input-group.error label {
        		color: #D32F2F; 
        	}

        	.input-group.error label:hover {
         		 background: rgba(211, 47, 47, 0.2); 
         	}
        	.input-group.error label:before {
				border: 2px solid #D32F2F; 
				}
           /*  
            #accordion a:hover{
                background: #bbffbb;
                color: white;
            }
            
            #accordion .has-children.closed a{
                background: #888888;
                color: #ddffdd;
            }
            #accordion .has-children{

            }
            #accordion li{

            } */
        </style> 
	</head>
	<body>
		<header>
			<jsp:include page="/WEB-INF/template/header.jsp"/>	
		</header>
			<%!
			private void cargarCategorias(DtCategoria categ, JspWriter out)throws IOException {
				if (categ.getHijas() != null) {
					if (categ.getNombre() == "Categoria") {
						out.print("<li><input type=\"radio\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\" checked=\"checked\">"+categ.getNombre()+"<i class=\"icono derecha glyphicon glyphicon-chevron-down\"></i>");
					} else {
						out.print("<li><input type=\"radio\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\">"+categ.getNombre()+"<i class=\"icono derecha glyphicon glyphicon-chevron-down\"></i>");
					}
				}	else {
					out.print("<li><input type=\"radio\" name=\"categoria\" id=\""+ categ.getNombre()+"\" value=\""+ categ.getNombre()+"\">"+categ.getNombre());
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
		<div class="container">
			
			<div class="col-md-4 col-md-offset-4" style="margin-top:50px; border:5px groove; border-radius:5px">
				<form id="signupform" class="form-horizontal" role="form" name="formularioPropuesta"  class="col-md-6 col-md-offset-3" action="propuestaCorrecta" enctype="multipart/form-data" method="post" >
					<h2 class="form-signin-heading" style="text-align:center">Ingrese los datos de la propuesta</h2>
					<br/>
					
					<div id="signupalert" style="display:none" class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>
					<div>
					<label>Seleccione el tipo de espectaculo:</label>
					</div>
					<div>
						<ul id="accordion">
							<%
							List<DtCategoria> cats = (LinkedList<DtCategoria>) request.getAttribute("categorias");
							DtCategoria primCat = ((LinkedList<DtCategoria>)cats).getFirst();
							cargarCategorias(primCat, out); %>
						</ul>
					</div>
					<br/>
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-menu-right"></i></span>
						<input type="text" class="form-control" name="Titulo" value="" placeholder="Titulo">
					</div>
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-pencil"></i></span>
						<textarea  class="form-control" name="Descripcion" style="max-height: 300px; resize: vertical"  placeholder="Descripcion de la propuesta..."  ></textarea>
					</div>
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-pushpin"></i></span>
						<input type="text" class="form-control" name="Lugar" value="" placeholder="Lugar de realizacion">
					</div>
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-piggy-bank"></i></span>
						<input type="number" class="form-control" name="Precio_Entrada" value="" placeholder="Precio de las entradas (UYU)">
						<input type="number" class="form-control" name="Monto_Necesario" value="" placeholder="Monto necesario (UYU)">
					</div>
					<div style="margin-bottom: 25px" class="input-group">
						<div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							<input class="form-control" size="16" type="text" value="" placeholder="Fecha prevista" name="Fecha_Prevista">
						</div>
						<input type="hidden" id="dtp_input2" value="" />
					</div>
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></span>
						<input type="file" name="uploaded" id="">
						<img id="preview" name="imagen" alt="" style="width:100%">
						<input type="hidden" name="srcimg" id="srcimgid" value="">
					</div>
					<div>
						<label>Seleccione el tipo de retorno:</label>
					</div>
					<div class="radio">
						<label><input type="checkbox" name="retorno" value="Entradas">Entradas</label>
						<label><input type="checkbox" name="retorno" value="Porcentaje">Porcentaje</label>
					</div>
					<br/>
					<div  style="text-align: center" class="form-group">
						<div>
							<button id="btn-signup" type="submit" value="enviar" style="width:90%; margin: 5%" class="btn btn-lg btn-primary btn-block">Registrar</button>
						</div>
					</div>
				</form>
			</div>
</div>

			
			
<jsp:include page="/WEB-INF/template/footer.jsp"/>

        <script src="media/styles/custom/js/jquery.quiccordion.js"></script>
        <script>
            $(document).ready(function(){
                $("#accordion").quiccordion();
                
                $("#accordion a").click(function(e){
                    e.preventDefault();
                });
            });
		</script>
			
		
		
		<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.js"></script>		
		<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.es.js"></script>		
		<script type="text/javascript" src="media/styles/custom/js/previewImg.js"></script>
		<script type="text/javascript" src="media/styles/custom/js/checkAltaPropuesta.js"></script> 
		        
        <script type="text/javascript">
			$('.form_date').datetimepicker({
        		language:  'es',
        		weekStart: 1,
        		todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
    		});
		</script>
		

	</body>
</html>
			