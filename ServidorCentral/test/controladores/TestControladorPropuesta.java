package controladores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import datatypes.Retorno;
import excepciones.PropuestaRepetidaException;
import interfaces.Fabrica;
import interfaces.IControladorPropuesta;
import logica.Categoria;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;
import manejadores.ManejadorPropuesta;

/**
 * @author matteo
 *
 */

public class TestControladorPropuesta {

	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorPropuesta icprop = fabrica.getIControladorPropuesta();
	private ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
	private Calendar fecha = Calendar.getInstance();
	private List<Retorno> retornos = new LinkedList<Retorno>();
	private Categoria categoria = new Categoria("");
	private Proponente proponente = new Proponente("prop", "prop", "prop", "prop", fecha, "prop", null);
	
	@Test
	public void testAddPropuesta() { 		
		Propuesta propuestaOK = new Propuesta("propuesta", "desc1", null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		try {
			icprop.addPropuesta("propuesta", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e) {
			e.printStackTrace();
		}
		propuestaOK = manProp.getPropuestaPorTitulo("propuesta");
		Propuesta propuesta = icprop.seleccionarPropuesta("propuesta");		
		assertEquals(propuestaOK, propuesta);    
	}  

	@Test
	public void testTituloLibre() {
		try {
			icprop.addPropuesta("propuesta", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e) {
			e.printStackTrace();
		}
		boolean boolOk = icprop.tituloLibre("propuesta");
		assertFalse(boolOk);
	}
	
	@Test
	public void testModificarPropuesta() {
		Propuesta propuestaOK = new Propuesta("propuesta", "desc1", null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		Propuesta propuesta = new Propuesta("propuesta", "desc1", null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		icprop.modificarPropuesta(propuesta, "propuesta", "desc1", "lugar1", fecha, 500, 500, 500, null, categoria);
		assertEquals(propuestaOK, propuesta); 		
	}
	
	@Test
	public void testGetPropuestasTitulo() {
		Map<String, Propuesta> propuestas = icprop.getPropuestasTitulo();
		boolean boolOk = false;
		if (propuestas != null)
			boolOk = true;
		assertTrue(boolOk); 		
	}
	
	@Test
	public void testGetPropuestas() {
		boolean boolOk = false;
		List<Propuesta> list = icprop.getPropuestas();
		if (list != null)
			boolOk = true;
		assertTrue(boolOk);
	}
	
	@Test
	public void testObtenerColaboradores() {
		try {
			icprop.addPropuesta("propuesta", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e) {
			e.printStackTrace();
		}
		boolean boolOk = false;
		Map<String, Colaborador> colabs = icprop.obtenerColaboradores("propuesta");
		if (colabs != null) {
			boolOk = true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testCopiarArchivos() {
		icprop.copiarArchivo("", "");
		assertTrue(true);
		
	}
	
	@Test
	public void testPropuestaRepetida() {
		boolean boolOk = false;
		try {
			icprop.addPropuesta("propuestarep", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
			icprop.addPropuesta("propuestarep", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e) {
			boolOk = true;
		}  
		assertTrue(boolOk);
	
	}
	
}
 