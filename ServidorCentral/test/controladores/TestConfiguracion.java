package controladores;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import controladores.Configuracion;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaColaboraException;

public class TestConfiguracion {
	
	private Configuracion config = Configuracion.getInstancia(); 
	 
	@Test
	public void testCargarDatos() {
		boolean boolok = false;
		try {
			config.cargarDatos(); 
			boolok = true;
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UsuarioYaColaboraException e) {
			e.printStackTrace();
		}
		assertTrue(boolok);
	}
	 
	@Test
	public void testCreatePathImagenes() {
		boolean boolok = false;
		config.createPathImagenes();
		boolok = true;
		assertTrue(boolok);
	}

}
