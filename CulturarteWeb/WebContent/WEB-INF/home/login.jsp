<!DOCTYPE html>
<html>

	<head>
		<jsp:include page="/WEB-INF/template/head.jsp"/>
		<title>CulturarteWeb :: Bienvenido</title>
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/template/header.jsp"/>
	
	    <div class="container">
			<form class="form-signin" style="padding-left: 35%;padding-right: 35%" action="iniciarSesion" method="post">				
				<h2 class="form-signin-heading">Ingrese sus datos</h2>
				<br/>
				<br/>
				<label for="inputEmail" class="sr-only">Nickname o Email</label>
				<input type="text" name="login" id="inputEmail" class="form-control" placeholder="Nickname o Email" required autofocus>
				<br/>
				<label for="inputPassword" class="sr-only">Contraseña</label>
				<input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Contraseña" required>
				<br/>
				<label><input type="checkbox" name="checkRecordar" value="">Recordarme</label>
				<br/>
				<br/>
				<button class="btn btn-lg btn-primary btn-block" onclick="submit()">Iniciar Sesión</button>
			</form>
		</div> <!-- /container -->

		<jsp:include page="/WEB-INF/template/footer.jsp"/>
	
	</body>
</html>