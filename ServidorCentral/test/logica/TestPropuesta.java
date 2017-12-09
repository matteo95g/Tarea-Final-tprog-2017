package logica;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import logica.Categoria;
import logica.Propuesta;

public class TestPropuesta {

	
	@Test
	public void testGetters(){
		Categoria cat = new Categoria("name");
		Calendar calen = Calendar.getInstance();
		Propuesta prop = new Propuesta("title", "desc", "img", "lugar", calen, 0, 0, null, null, null);
		boolean boolOk = true;
		String des = prop.getDescripcion();
		if (des != "desc"){
			boolOk=false;
		}
		if (prop.getImagen()!= "img"){
			boolOk=false;
		}
		if (prop.getLugar() != "lugar"){
			boolOk= false;
		}
		if (prop.getPrecioEntrada() != 0){
			boolOk=false;
		}
		if (prop.getMontoNecesario() != 0){
			boolOk=false;
		}
		if (prop.getFechaRealizacion() != calen){
			boolOk=false;
		}
		//p.agregarSeguidor(u);
		//p.eliminarSeguidor(u.getNickname());
		prop.setCategoria(cat);
		if (prop.getCategoria() != cat){
			boolOk=false;
		}
		
	

		
		assertTrue(boolOk);
		
	}
}
