package logica;

import java.util.Calendar;
import org.junit.Test;

import logica.Proponente;

import static org.junit.Assert.assertTrue;


public class TestProponente {
	private Proponente prop;
	private Proponente prop1;
	
	@Test
	public void testProponente(){
		prop = new Proponente("nick", "name", "apellido", "correo", Calendar.getInstance(), "dir", null);
		prop1 = new Proponente("nick", "name", "apellido", "correo", Calendar.getInstance(), "dir", null);
		boolean boolOk = false;
		if (prop.equals(prop1)){
			boolOk =true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testGetDireccion(){
		prop = new Proponente("nick", "name", "apellido", "correo", Calendar.getInstance(), null, "dir");
		boolean boolOk =false;
 
		if ("dir" == prop.getDireccion()){
			boolOk=true;
		}
		assertTrue(boolOk);
	}
	
	
}
