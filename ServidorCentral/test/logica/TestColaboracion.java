package logica;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import datatypes.Retorno;
import logica.Categoria;
import logica.Colaboracion;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;
import logica.Usuario;

public class TestColaboracion {
	
	private Calendar fecha = Calendar.getInstance();
	private Colaborador colab = new Colaborador("pepe", "pepe", "pepe", "pepe", fecha, null);
	private Proponente proponente = new Proponente("pedro", "pedro", "lopez", "pedro", fecha, "", null);
	private Categoria categoria = new Categoria("");
	private Propuesta propuesta = new Propuesta("titulo", "", null, "casa", fecha, 420, 420, null, categoria, proponente);
	private Colaboracion colaboracion = new Colaboracion(1, 420, fecha, Retorno.Entradas, propuesta, colab);
	
	@Test
	public void testID() {
		colaboracion.setId(8);
		int iden = colaboracion.getId();
		assertEquals(iden, 8);
	}
	
	@Test
	public void testMonto() {
		int monto = colaboracion.getMonto();
		assertEquals(monto, 420); 
	}
	
	@Test
	public void testFecha() {
		Calendar fechaCol = colaboracion.getFechaColaboracion();
		assertEquals(fecha, fechaCol);
	}
	
	@Test
	public void testRetorno() {
		Retorno ret = colaboracion.getRetornoElegido();
		assertEquals(ret, Retorno.Entradas);
	}
	
	@Test
	public void testPropuesta() {
		Propuesta prop = colaboracion.getPropuesta();
		assertEquals(propuesta, prop);
	}
	
	@Test
	public void testUsuario() {
		Usuario colaborador = (Colaborador) colaboracion.getUsuario();
		assertEquals(colab, colaborador);
	}
	
	@Test
	public void testEqual() {
		assertEquals(colaboracion, colaboracion);
	}
	
		
}
