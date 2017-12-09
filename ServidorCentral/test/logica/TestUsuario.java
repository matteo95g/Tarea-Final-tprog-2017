package logica;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import logica.Colaborador;
import logica.Propuesta;
import logica.Usuario;

public class TestUsuario {

	@Test
	public void testSetGet() {
		boolean boolOk = true;
		Colaborador usr1 = new Colaborador("usr1", "usr1Nombre", "usr1Apellido", "usr1Correo", null, null);
		usr1.setNickname("usr1set");
		if (!(usr1.getNickname().equals("usr1set"))) {
			boolOk = false;
		}
		usr1.setNombre("usr1NombreSet");
		if (!(usr1.getNombre().equals("usr1NombreSet"))) {
			boolOk = false;
		}
		usr1.setApellido("usr1ApellidoSet");
		if (!(usr1.getApellido().equals("usr1ApellidoSet"))) {
			boolOk = false;
		}
		usr1.setCorreoElectronico("usr1CorreoSet");
		if (!(usr1.getCorreoElectronico().equals("usr1CorreoSet"))) {
			boolOk = false;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(2005, 5, 5);
		usr1.setFechaNacimiento(cal);
		if (!(usr1.getFechaNacimiento().equals(cal))) {
			boolOk = false;
		}
		usr1.setImagen("imagen.jpg");
		if (!(usr1.getImagen().equals("imagen.jpg"))) {
			boolOk = false;
		}
		Map<String, Usuario> seg = new HashMap<String, Usuario>();
		usr1.setSeguidores((Map<String, Usuario>) seg);
		
		Map<String, Propuesta> fav = new HashMap<String, Propuesta>();
		usr1.setFavoritas((Map<String, Propuesta>) fav);
		Map<String, Propuesta> favs = (Map<String, Propuesta>) usr1.getFavoritas();
		if (!favs.isEmpty()) {
			boolOk = false;
		}
		Map<String, Usuario> seguidos = new HashMap<String, Usuario>();
		usr1.setSeguidos((Map<String, Usuario>) seguidos);
		Map<String, Usuario> segs = (Map<String, Usuario>) usr1.getSeguidos();
		if (!segs.isEmpty()) {
			boolOk = false;
		}
		
		
		assertTrue(boolOk);
	}

}
