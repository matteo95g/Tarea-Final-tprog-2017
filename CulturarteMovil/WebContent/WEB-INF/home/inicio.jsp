<%@ page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>

	<head>
	
   		<jsp:include page="/WEB-INF/template/head.jsp"/>

		<title>Culturarte Movil :: Bienvenido</title>
		
	</head>
    
	<body>
	
		<jsp:include page="/WEB-INF/template/header.jsp"/>
		
		<div class="container">
			<h2>Bienvenido a Culturarte</h2>
			<h5>La plataforma de crowdfunding mas grande de Uruguay</h5>
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- Wrapper for slides -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="media/img/ballet.jpg" alt="Los Angeles" style="width:100%;">
						<div class="carousel-caption">
							<h3>Romeo y Julieta</h3>
							<p>Uno de los ballets favoritos del director artístico, Julio Bocca</p>
						</div>
					</div>
					<div class="item">
						<img src="media/img/chicago.jpg" alt="Chicago" style="width:100%;">
						<div class="carousel-caption">
							<h3>Cantamel</h3>
							<p>Encontra tu talento</p>
						</div>
					</div>
					<div class="item">
						<img src="media/img/ny.jpg" alt="New York" style="width:100%;">
						<div class="carousel-caption">
							<h3>Pilsen Rock</h3>
							<p>Evento musical multitudinario</p>
						</div>
					</div>
		    	</div>
		
				<!-- Left and right controls -->
				<a class="left carousel-control" href="#myCarousel" data-slide="prev">
					<span class="glyphicon glyphicon-chevron-left"></span>
					<span class="sr-only">Previous</span>
				</a>
				<a class="right carousel-control" href="#myCarousel" data-slide="next">
					<span class="glyphicon glyphicon-chevron-right"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>

		</div>
		
		<jsp:include page="/WEB-INF/template/footer.jsp"/>
		
		
		
	
	</body>
	
</html>