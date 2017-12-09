

(function(){

var formulario = document.formularioUsuario,
	elementos = formulario.elements;

var validarInputs = function(){
	for (var i = 0; i < elementos.length; i++) {
		if (elementos[i].name == "NickName" || elementos[i].name == "E-mail" || elementos[i].name == "Nombre" || elementos[i].name == "Apellido") {
			if (elementos[i].value.length == 0) {
				alert('Ingrese su ' + elementos[i].name);
				return false;
			}
		}
		if (elementos[i].name == "Contrasenia") {
			if (elementos[i].value.length == 0) {
				alert('Ingrese una contraseña');
				return false;
			}
		}
		if (elementos[i].name == "Confirmar_Contrasenia") {
			if (elementos[i].value.length == 0) {
				alert('Ingrese la confirmación de la contraseña');
				return false;
			}
		}
		if (elementos[i].name == "Fecha_de_Nacimiento") {
			if (elementos[i].value.length == 0) {
				alert('Ingrese su fecha de nacimiento');
				return false;
			}
		}
		if (elementos[i].name == "Direccion") {
			if (elementos.optradio == "Proponente") {
				if (elementos[i].value.length == 0) {
					alert('Ingrese su ' + elementos[i].name);
					return false;
				}
			}
		}
	}
	
	if (elementos.Contrasenia.value != elementos.Confirmar_Contrasenia.value) {
		elementos.Contrasenia.value = "";
		elementos.Confirmar_Contrasenia.value = "";
		alert('Las contraseñas no coinciden');
		return false;
	}
	
	return true;
};

var enviar = function(e){
	if (!validarInputs()) {
		e.preventDefault();
	} else {
		formulario.submit();/*
		e.preventDefault();*/
	}
};

// --- Eventos ---
formulario.addEventListener("submit", enviar);

}())