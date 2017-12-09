<%@page import="publicador.DtPropuesta"%>
<%@page import="publicador.DtColaboracion"%>
<%@page import="controllers.Colaborar"%>
<%@page import="publicador.Publicador"%>
<%@page import="publicador.PublicadorService"%>
<%@page import="publicador.DtColaboracion"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
	<head>	
		<jsp:include page="/WEB-INF/template/head.jsp"/>
		<link rel="stylesheet" href="media/styles/dateTimePicker/css/bootstrap-datetimepicker.min.css">		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
        <script src="media/styles/custom/js/jquery.quiccordion.js"></script>
       
		<title>CulturarteMovil :: Pagar colaboración</title>
	</head>
<body>

	<%	
		String tit = "";
		Integer monto = 0;		
		String idCol = (String) request.getAttribute("idCol");
		PublicadorService service = new PublicadorService();
		Publicador port = service.getPublicadorPort();	 	
		if (idCol != null){
			DtColaboracion dtCol = port.getColaboracion(Integer.parseInt(idCol));
			tit = dtCol.getPropuesta();
			monto = dtCol.getMonto();
		}
		
	%>
	
 	<jsp:include page="/WEB-INF/template/header.jsp"/>
	<div class="container">
	<h2>Pagar colaboración</h2>
		<%request.setAttribute("monto",monto);
		request.setAttribute("id",idCol);%>
		
		<h5>Propuesta: <%=tit %></h5>
		<h5>Monto a pagar: $ <%=monto %></h5>
		<br>
	    <h6>Seleccione la forma de pago:</h6>
	    <br>
	    <ul class="nav nav-tabs">
	      <li class="active"><a data-toggle="tab" href="#home">PayPal</a></li>
	      <li><a data-toggle="tab" href="#menu1">Transferencia</a></li>
	      <li><a data-toggle="tab" href="#menu2">Tarjeta</a></li>
	    </ul>	
	    <div class="tab-content">
	    <!-- PAYPAL -->
	      <div id="home" class="tab-pane fade in active">
	        <br>
	        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/PayPal.svg/2000px-PayPal.svg.png" alt="PayPal" width="auto" height="42">
			<form style="margin: 5%" action="pagar?id=<%=idCol%>" method="post">
			  <div class="form-group">
			    <input type="text" class="form-control" id="monto" value="<%=monto %>" name="monto" placeholder="<%=monto %>" disabled>
			  </div>
			  <div class="form-group">
			  	
			    <input type="text" class="form-control" name="titular" placeholder="Titular">
			  </div>
			  <div class="form-group">
			    <input class="form-control" name="cuenta" placeholder="Cuenta">
			  </div>		  
			  <button type="submit" class="btn btn-primary">Confirmar</button>
			</form>			
	      </div>
	      <!-- BANCO -->
	      <div id="menu1" class="tab-pane fade">	        	        
	       	<img style="margin-left: 5%" src="http://www.pvhc.net/img131/edxxqqiyuvxriezuyytz.png" alt="Banco" width="auto" height="100">
			<form style="margin: 5%" action="pagar?id=<%=idCol%>" method="post">
			  <div class="form-group">
			  
			    <input type="number" type="hidden" class="form-control" id="monto" name="monto" placeholder="<%=monto %>" disabled>
			  </div>
			  <div class="form-group">
			    <input class="form-control" name="banco" placeholder="Banco">
			  </div>
			  <div class="form-group">
			    <input class="form-control" name="titular" placeholder="Titular">
			  </div>
			  <div class="form-group">
			    <input class="form-control" name="cuenta" placeholder="Cuenta">
			  </div>		  
			  <button type="submit" class="btn btn-primary">Confirmar</button>
			</form>
	        
	      </div>
	      <!-- TARJETA -->
	      <div id="menu2" class="tab-pane fade">
	        <img style="margin-left: 5%" src="https://image.flaticon.com/icons/svg/62/62802.svg" alt="Tarjeta" width="auto" height="100">
			<form role="form" style="margin: 5%" action="pagar?id=<%=idCol%>" method="post">
			  <div class="form-group">
			    <input type="number" class="form-control" id="monto" placeholder="<%=monto %>" disabled>
			  </div>
			  
			  <div class="form-group">
			    <input class="form-control" name="titular" placeholder="Titular">
			  </div>
			  
			  <div class="form-group">
				<label class="radio-inline">
			      <input type="radio" name="tipo" value="Credito">Credito
			    </label>
			    <label class="radio-inline">
			      <input type="radio" name="tipo" value="Debito">Debito
			    </label>
			  </div>
			  
			  <div class="form-group">
				  <div class="form-inline">				  
					    <input class="form-control" name="numero" placeholder="Numero">
					    <input class="form-control" name="cvs" placeholder="CVS">
				  </div>
			  </div>
			  
			  <div style="margin-bottom: 25px" class="input-group">
					<div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						<input class="form-control" size="16" type="text" name="Fecha_de_Vencimiento" value="" placeholder="Vencimiento">
					</div>
					<input type="hidden" id="dtp_input2" value="" />
				</div>			  
			  <button type="submit" class="btn btn-primary">Confirmar</button>
			</form>	      
			</div>
	    </div>

	
	
	
	</div>

	<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.js"></script>
	
	<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.es.js"></script>
	
	
	
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

   <jsp:include page="/WEB-INF/template/footer.jsp"/>
   <script src="media/styles/custom/js/jqueryEasing.js"></script>
   <script src="media/styles/custom/js/resume.js"></script>
</body>
</html>