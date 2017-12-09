package controladores;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import excepciones.CategoriaRepetidaException;
import interfaces.Fabrica;
import interfaces.IControladorCategoria;
import logica.Categoria;

public class TestControladorCategoria {
	private Fabrica fabrica = Fabrica.getInstance();	
	private IControladorCategoria iccat = fabrica.getIControladorCategoria();

	@Test
	public void testAgregarCategoria() {
		boolean bookOk = true;
		try {
			iccat.agregarCategoria("cat", "");
			iccat.agregarCategoria("cat", "");
		} catch (CategoriaRepetidaException e) {
			assertTrue(bookOk);
		}
	
	}
	
	@Test 
	public void testGetCategorias() {
		boolean bookOk = false;
		List<Categoria> lst = null;
		lst = iccat.getCategorias();
		if (lst != null)
			bookOk = true;
		assertTrue(bookOk);
	}

}
