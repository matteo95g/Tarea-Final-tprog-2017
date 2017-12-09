<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
	
		<jsp:include page="/WEB-INF/template/head.jsp"/>
		
		<link rel="stylesheet" href="media/styles/dateTimePicker/css/bootstrap-datetimepicker.min.css">

		<title>CulturarteWeb :: Registro</title>
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/template/header.jsp"/>
		
		<div class="col-md-4 col-md-offset-4" style="margin-top:50px;">
			<form id="signupform" class="form-horizontal" role="form" name="formularioUsuario" action="registrarUsuario" class="col-md-6 col-md-offset-3" enctype="multipart/form-data" method="post">
				<h2 class="form-signin-heading">Ingrese sus datos</h2>
				<br/>
				<div class="alert alert-danger" role="alert">
  					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  					<span class="sr-only">Error:</span>
  					El correo ingresado ya se encuentra registrado
				</div>
				<br/>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-menu-right"></i></span>
					<input type="text" class="form-control" name="NickName" value="" placeholder="NickName">
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
					<input type="email" class="form-control" name="E-mail" value="" placeholder="Email">
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
					<input type="text" class="form-control" name="Nombre" value="" placeholder="Nombre">
					<input type="text" class="form-control" name="Apellido" value="" placeholder="Apellido">
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
					<input type="password" class="form-control" name="Contrasenia" value="" placeholder="Contraseña">
					<input type="password" class="form-control" name="Confirmar_Contrasenia" value="" placeholder="Confirmar Contraseña">
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<div class="input-group date form_date" data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
						<input class="form-control" size="16" type="text" name="Fecha_de_Nacimiento" value="" placeholder="Fecha de Nacimiento">
					</div>
					<input type="hidden" id="dtp_input2" value="" />
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></span>
					<input type="file" name="uploaded" id="">
					<img name="imagen" id="preview" alt="" style="width:100%">
				</div>
				<div class="radio" style="margin-bottom: 25px">
					<label><input type="radio" name="optradio" value="Colaborador" checked="checked" onclick="Direccion.disabled=true; Biografia.disabled=true; Web.disabled=true">Colaborador</label>
					<label><input type="radio" name="optradio" value="Proponente" onclick="Direccion.disabled=false; Biografia.disabled=false; Web.disabled=false">Proponente</label>
				</div>
				
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
					<input type="text" class="form-control" name="Direccion" value="" placeholder="Dirección" disabled="disabled">
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-bold"></i></span>
					<textarea  class="form-control" name="Biografia" style="max-height: 300px; resize: vertical" placeholder="Biografia" disabled="disabled"></textarea>
				</div>
				<div style="margin-bottom: 25px" class="input-group">
					<span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
					<input type="text" class="form-control" name="Web" value="" placeholder="Dirección Web" disabled="disabled">
				</div>
				
				<br/>
				<div  style="text-align: center" class="form-group">
					<div>
						<button id="btn-signup" type=submit class="btn btn-lg btn-primary btn-block">Registrarse</button>
					</div>
				</div>
			</form>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>
			
		<jsp:include page="/WEB-INF/template/footer.jsp"/>
		
		<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.js"></script>
		
		<script type="text/javascript" src="media/styles/dateTimePicker/js/bootstrap-datetimepicker.es.js"></script>
		
		<script type="text/javascript" src="media/styles/custom/js/previewImg.js"></script>
		
		<script type="text/javascript" src="media/styles/custom/js/checkAltaUsuario.js"></script>
		
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