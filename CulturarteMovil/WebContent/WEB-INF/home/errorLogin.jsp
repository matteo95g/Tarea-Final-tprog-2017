<!DOCTYPE html>
<html>

	<head>
		<jsp:include page="/WEB-INF/template/head.jsp"/>
		<title>CulturarteWeb :: Bienvenido</title>
		<link rel="stylesheet" href="/media/styles/custom/signin.css">
	</head>
	
	<body>
	
		<jsp:include page="/WEB-INF/template/header.jsp"/>
	
	    <div class="container">
			<form class="form-signin" style="padding-left: 35%;padding-right: 35%" action="iniciarSesion" method="post">			
				<h2 class="form-signin-heading">Ingrese sus datos</h2>
				<br/>
				<div class="alert alert-danger" role="alert">
  					<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  					<span class="sr-only">Error:</span>
  					Ingrese un NickName o Email registrado
				</div>
				<br/>
				<label for="inputEmail" class="sr-only">Nickname o Email</label>
				<input type="text" id="inputEmail" class="form-control" placeholder="Nickname o Email" name="login" required autofocus>
				<br/>
				<label for="inputPassword" class="sr-only">Contrase�a</label>
				<input type="password" id="inputPassword" class="form-control" placeholder="Contrase�a" name="pass" required>
				<br/>
				<label><input type="checkbox" name="checkRecordar" value="">Recordarme</label>
				<br/>
				<br/>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar</button>
			</form>
		</div> <!-- /container -->

		<jsp:include page="/WEB-INF/template/footer.jsp"/>
	
	</body>
</html>