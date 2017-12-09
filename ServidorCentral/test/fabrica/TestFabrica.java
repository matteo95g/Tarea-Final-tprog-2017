package fabrica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import interfaces.Fabrica;
import interfaces.IControladorCategoria;
import interfaces.IControladorColaboracion;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;

public class TestFabrica {

	@Test
	public void testGetInstance() {
		Fabrica fab1 = Fabrica.getInstance();
		Fabrica fab2 = Fabrica.getInstance();
		assertEquals(fab1, fab2);
	}
	
	@Test
	public void testGetIContCat() {
		Fabrica fab = Fabrica.getInstance();
		IControladorCategoria iContCat = null;
		iContCat = fab.getIControladorCategoria();
		assertNotNull(iContCat);
	}
	
	@Test
	public void testGetIContUsu() {
		Fabrica fab = Fabrica.getInstance();
		IControladorUsuario iContUsu = null;
		iContUsu = fab.getIControladorUsuario();
		assertNotNull(iContUsu);
	}
	
	@Test
	public void testGetIContProp() {
		Fabrica fab = Fabrica.getInstance();
		IControladorPropuesta iContProp = null;
		iContProp = fab.getIControladorPropuesta();
		assertNotNull(iContProp);
	}
	
	@Test
	public void testGetIContColab() {
		Fabrica fab = Fabrica.getInstance();
		IControladorColaboracion iContColab = null;
		iContColab = fab.getIControladorColaboracion();
		assertNotNull(iContColab);
	}

}
