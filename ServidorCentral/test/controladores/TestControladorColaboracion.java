package controladores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import datatypes.Retorno;
import excepciones.PropuestaRepetidaException;
import excepciones.UsuarioYaColaboraException;
import interfaces.Fabrica;
import interfaces.IControladorColaboracion;
import interfaces.IControladorPropuesta;
import logica.Categoria;
import logica.Colaboracion;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;
import manejadores.ManejadorColaboracion;
import manejadores.ManejadorColaborador;
import manejadores.ManejadorProponente;

public class TestControladorColaboracion {
	
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorPropuesta icProp = fabrica.getIControladorPropuesta();
	private IControladorColaboracion icCol = fabrica.getIControladorColaboracion();
	
	private ManejadorColaborador mCol = ManejadorColaborador.getInstancia();
	private ManejadorProponente mProp = ManejadorProponente.getInstancia();
	private ManejadorColaboracion mCola = ManejadorColaboracion.getInstancia();
	
	
	
	@Test
	public void testAddColaboraciones() {
		Calendar fecha = Calendar.getInstance();
		Retorno retorno = Retorno.Entradas;
		Colaborador colaborador = new Colaborador("cola", "cola", "cola", "cola", fecha, null);
		Proponente proponente = new Proponente("prop", "prop", "prop", "prop", fecha, "prop", null); 
		Categoria categoria = new Categoria("");
		List<Retorno> retornos = new LinkedList<Retorno>();
		Propuesta propuesta = new Propuesta("propuesta", "desc1", null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		mCol.addColaborador(colaborador);
		mProp.addProponente(proponente);
		try {
			icProp.addPropuesta("propuesta", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e) {
			e.printStackTrace();
		}	
		
		try {
			icCol.agregarColaboracion("cola", "propuesta", 40, fecha, retorno);
		} catch(UsuarioYaColaboraException ex){
			ex.printStackTrace();			
		}
		
		Colaboracion colaboracionOK = new Colaboracion(2, 40, fecha, retorno, propuesta, colaborador);
		Colaboracion colaboraciontest = icCol.getColaboracion(2);

		assertEquals(colaboracionOK, colaboraciontest);
	}
	
	@Test
	public void testEliminarColaboracion(){
		Calendar fecha = Calendar.getInstance();
		Retorno retorno = Retorno.Entradas;
		Colaborador colaborador = new Colaborador("cola2", "cola2", "cola2", "cola2", fecha, null);
		Proponente proponente = new Proponente("prop2", "prop2", "prop2", "prop2", fecha, "prop2", null); 
		Categoria categoria = new Categoria("");
		List<Retorno> retornos = new LinkedList<Retorno>();
		Propuesta propuesta = new Propuesta("propuesta2", "desc1", null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		mCol.addColaborador(colaborador);
		mProp.addProponente(proponente);		
		try {
		icProp.addPropuesta("propuesta2", "desc1", null, null, "lugar1", fecha, 500, 500, retornos, categoria, proponente);
		} catch (PropuestaRepetidaException e){
			e.printStackTrace();
		}		
		
		try {
			icCol.agregarColaboracion("cola2", "propuesta2", 40, fecha, retorno);
		} catch(UsuarioYaColaboraException ex){
			ex.printStackTrace();
		}
		int num = mCola.getCanCol();
		Colaboracion colaboraciontest1 = icCol.getColaboracion(num);
		icCol.eliminarColaboracion("cola2", propuesta, num, 40);
		Colaboracion colaboraciontest2 = icCol.getColaboracion(num);
		boolean boolOk = true;
		
		if (colaboraciontest1.equals(colaboraciontest2)){
			boolOk = false;
		}
		assertTrue(boolOk);

	}

}
