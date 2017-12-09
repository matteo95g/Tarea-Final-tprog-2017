package controladores;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaEsSeguidorException;
import interfaces.Fabrica;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Proponente;
import logica.Usuario;
import manejadores.ManejadorColaborador;
import manejadores.ManejadorProponente;

public class TestControladorUsuario {
	
	private Fabrica fabrica = Fabrica.getInstance();
	private IControladorUsuario icu = fabrica.getIControladorUsuario();
	private ManejadorColaborador mCol = ManejadorColaborador.getInstancia();
	private ManejadorProponente mProp = ManejadorProponente.getInstancia();

	private byte[] arrayVacio = new byte[0];

	@Test
	public void testAgregarUsuarioColaborador() {
		Calendar cal = Calendar.getInstance();
		cal.set(1, 1, 2001);
		Colaborador colaboradorOK = new Colaborador("Colab1", "NomColab1", "ApColab1", "CorreoColab1", cal, null);
		try {
			icu.agregarUsuario("Colab1", "NomColab1", "ApColab1", "CorreoColab1", 1, 1, 2001, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Colaborador colaborador = mCol.getColaboradorPorNick("Colab1");
		assertEquals(colaboradorOK, colaborador);
	}
	
	@Test
	public void testAgregarUsuarioProponente() {
		Calendar cal = Calendar.getInstance();
		cal.set(1, 1, 2001);
		Proponente proponenteOK = new Proponente("Prop1", "NomProp1", "ApProp1", "CorreoProp1", cal, "PassProp1", "DirProp1");
		proponenteOK.setSitioWeb("WebProp1");
		proponenteOK.setBiografia("BioProp1");
		try {
			icu.agregarUsuario("Prop1", "NomProp1", "ApProp1", "CorreoProp1", 1, 1, 2002, arrayVacio, null, "Proponente", "DirProp1", "BioProp1", "WebProp1", "PassProp1");
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Proponente proponente = mProp.getProponentePorNick("Prop1");
		assertEquals(proponenteOK, proponente);
	}
	
	@Test
	public void testProponenteNickRepetido() {
		boolean excp = false;
		try {
			icu.agregarUsuario("Prop2", "NomProp2", "ApProp2", "CorreoProp2", 1, 1, 2002, arrayVacio, null, "Proponente", "DirProp2", "BioProp2", "WebProp2", null);
			icu.agregarUsuario("Prop2", "NomProp22", "ApProp22", "CorreoProp22", 1, 1, 2002, arrayVacio, null, "Proponente", "DirProp22", "BioProp22", "WebProp22", null);
		} catch (UsuarioRepetidoException e) {
			excp = true;
		} 
		assertTrue(excp);		
	}
	
	@Test
	public void testProponenteCorreoRepetido() {
		boolean excp = false;
		try {
			icu.agregarUsuario("Prop3", "NomProp3", "ApProp3", "CorreoProp3", 1, 1, 2002, arrayVacio, null, "Proponente", "DirProp3", "BioProp3", "WebProp3", null);
			icu.agregarUsuario("Prop32", "NomProp32", "ApProp32", "CorreoProp3", 1, 1, 2002, arrayVacio, null, "Proponente", "DirProp32", "BioProp32", "WebProp32", null);
		} catch (UsuarioRepetidoException e) {
			excp = true;
		}
		assertTrue(excp);		
	}
	
	@Test
	public void testColaboradorNickRepetido() {
		boolean excp = false;
		try {
			icu.agregarUsuario("Colab2", "NomColab2", "ApColab2", "CorreoColab2", 1, 1, 2001, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("Colab2", "NomColab22", "ApColab22", "CorreoColab22", 1, 1, 2001, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e) {
			excp = true;
		}
		assertTrue(excp);		
	}
	
	@Test
	public void testColaboradorCorreoRepetido() {
		boolean excp = false;
		try {
			icu.agregarUsuario("Colab3", "NomColab3", "ApColab3", "CorreoColab3", 1, 1, 2001, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("Colab32", "NomColab32", "ApColab32", "CorreoColab3", 1, 1, 2001, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e) {
			excp = true;
		}
		assertTrue(excp);		
	}
	
	@Test
	public void testNickLibreTrue() {
		boolean libre = icu.nickLibre("nickLibre");
		assertTrue(libre);
	}

	@Test
	public void testNickLibreFalse() {
		boolean libre = icu.nickLibre("Colab1");
		assertFalse(libre);
	}
	
	@Test
	public void testGetProponentes() {
		Map<String, Proponente> proponentes = null;
		proponentes = (HashMap<String, Proponente>) icu.getProponentes();
		assertNotNull(proponentes);
	}
	
	@Test
	public void testGetColaboradores() {
		Map<String, Colaborador> colaboradores = null;
		colaboradores = (HashMap<String, Colaborador>) icu.getColaboradores();
		assertNotNull(colaboradores);
	}
	
	@Test
	public void testSeguirUsuarioPropAProp() {
		boolean existe = false;
		try {
			icu.agregarUsuario("PropSeguidor1", "NomPropSeguidor1", "ApPropSeguidor1", "CorreoPropSeguidor1", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguidor1", "BioPropSeguidor1", "WebPropSeguidor1", null);
			icu.agregarUsuario("PropSeguido1", "NomPropSeguido1", "ApPropSeguido1", "CorreoPropSeguido1", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguido1", "BioPropSeguido1", "WebPropSeguido1", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropSeguidor1", "PropSeguido1");
			Proponente seguido = mProp.getProponentePorNick("PropSeguido1");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("PropSeguidor1");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(existe);
	}
	
	@Test
	public void testSeguirUsuarioPropAPropYaSigue() {
		boolean yaSigue = false;
		try {
			icu.agregarUsuario("PropSeguidor2", "NomPropSeguidor2", "ApPropSeguidor2", "CorreoPropSeguidor2", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguidor2", "BioPropSeguidor2", "WebPropSeguidor2", null);
			icu.agregarUsuario("PropSeguido2", "NomPropSeguido2", "ApPropSeguido2", "CorreoPropSeguido2", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguido2", "BioPropSeguido2", "WebPropSeguido2", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropSeguidor2", "PropSeguido2");
			icu.seguirUsuario("PropSeguidor2", "PropSeguido2");
		} catch (UsuarioYaEsSeguidorException e) {
			yaSigue = true;
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(yaSigue);
	}
	
	@Test
	public void testSeguirUsuarioPropACol() {
		boolean existe = false;
		try {
			icu.agregarUsuario("PropSeguidor3", "NomPropSeguidor3", "ApPropSeguidor3", "CorreoPropSeguidor3", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguidor3", "BioPropSeguidor3", "WebPropSeguidor3", null);
			icu.agregarUsuario("ColSeguido1", "NomColSeguido1", "ApColSeguido1", "CorreoColSeguido1", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropSeguidor3", "ColSeguido1");
			Colaborador seguido = mCol.getColaboradorPorNick("ColSeguido1");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("PropSeguidor3");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(existe);
	}
	
	@Test
	public void testSeguirUsuarioPropAColYaSigue() {
		boolean yaSigue = false;
		try {
			icu.agregarUsuario("PropSeguidor4", "NomPropSeguidor4", "ApPropSeguidor4", "CorreoPropSeguidor4", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguidor4", "BioPropSeguidor4", "WebPropSeguidor4", null);
			icu.agregarUsuario("ColSeguido2", "NomColSeguido2", "ApColSeguido2", "CorreoColSeguido2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropSeguidor4", "ColSeguido2");
			icu.seguirUsuario("PropSeguidor4", "ColSeguido2");
		} catch (UsuarioYaEsSeguidorException e) {
			yaSigue = true;
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(yaSigue);
	}
	
	@Test
	public void testSeguirUsuarioColAProp() {
		boolean existe = false;
		try {
			icu.agregarUsuario("ColSeguidor1", "NomColSeguidor1", "ApColSeguidor1", "CorreoColSeguidor1", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("PropSeguido3", "NomPropSeguido3", "ApPropSeguido3", "CorreoPropSeguido3", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguido3", "BioPropSeguido3", "WebPropSeguido3", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColSeguidor1", "PropSeguido3");
			Proponente seguido = mProp.getProponentePorNick("PropSeguido3");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("ColSeguidor1");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(existe);
	}
	
	@Test
	public void testSeguirUsuarioColAPropYaSigue() {
		boolean yaSigue = false;
		try {
			icu.agregarUsuario("ColSeguidor2", "NomColSeguidor2", "ApColSeguidor2", "CorreoColSeguidor2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("PropSeguido4", "NomPropSeguido4", "ApPropSeguido4", "CorreoPropSeguido4", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropSeguido4", "BioPropSeguido4", "WebPropSeguido4", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColSeguidor2", "PropSeguido4");
			icu.seguirUsuario("ColSeguidor2", "PropSeguido4");
		} catch (UsuarioYaEsSeguidorException e) {
			yaSigue = true;
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(yaSigue);
	}
	
	@Test
	public void testSeguirUsuarioColACol() {
		boolean existe = false;
		try {
			icu.agregarUsuario("ColSeguidor3", "NomColSeguidor3", "ApColSeguidor3", "CorreoColSeguidor3", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("ColSeguido3", "NomColSeguido3", "ApColSeguido3", "CorreoColSeguido3", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColSeguidor3", "ColSeguido3");
			Colaborador seguido = mCol.getColaboradorPorNick("ColSeguido3");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("ColSeguidor3");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(existe);
	}
	
	@Test
	public void testSeguirUsuarioColAColYaSigue() {
		boolean yaSigue = false;
		try {
			icu.agregarUsuario("ColSeguidor4", "NomColSeguidor4", "ApColSeguidor4", "CorreoColSeguidor4", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("ColSeguido4", "NomColSeguido4", "ApColSeguido4", "CorreoColSeguido4", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColSeguidor4", "ColSeguido4");
			icu.seguirUsuario("ColSeguidor4", "ColSeguido4");
		} catch (UsuarioYaEsSeguidorException e) {
			yaSigue = true;
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertTrue(yaSigue);
	}
	
	@Test
	public void testSeguirUsuarioSeguidorNoExiste() {
		boolean noExisteSeguidor = false;
		try {
			icu.agregarUsuario("Seguido1", "NomSeguido1", "ApSeguido1", "CorreoSeguido1", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("Seguidor1", "Seguido1");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			noExisteSeguidor = true;
		}
		assertTrue(noExisteSeguidor);
	}
	
	@Test
	public void testSeguirUsuarioSeguidoNoExiste() {
		boolean noExisteSeguido = false;
		try {
			icu.agregarUsuario("Seguidor2", "NomSeguidor2", "ApSeguidor2", "CorreoSeguidor2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("Seguidor2", "Seguido2");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			noExisteSeguido = true;
		}
		assertTrue(noExisteSeguido);
	}
	
	@Test
	public void testGetSeguidoresProp() {
		try {
			icu.agregarUsuario("PropGetSeg", "NomPropGetSeg", "ApPropGetSeg", "CorreoPropGetSeg", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropGetSeg", "BioPropGetSeg", "WebPropGetSeg", null);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Map<String, Usuario> seguidoresProp = null;
		seguidoresProp = (HashMap<String, Usuario>) icu.getSeguidores("PropGetSeg");
		assertNotNull(seguidoresProp);
	}
	
	@Test
	public void testGetSeguidoresCol() {
		try {
			icu.agregarUsuario("ColGetSeg", "NomColGetSeg", "ApColGetSeg", "CorreoColGetSeg", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Map<String, Usuario> seguidoresCol = null;
		seguidoresCol = (HashMap<String, Usuario>) icu.getSeguidores("ColGetSeg");
		assertNotNull(seguidoresCol);
	}
	
	@Test
	public void testGetSeguidosProp() {
		try {
			icu.agregarUsuario("PropGetSeg2", "NomPropGetSeg2", "ApPropGetSeg2", "CorreoPropGetSeg2", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropGetSeg2", "BioPropGetSeg2", "WebPropGetSeg2", null);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Map<String, Usuario> seguidosProp = null;
		seguidosProp = (HashMap<String, Usuario>) icu.getSeguidos("PropGetSeg2");
		assertNotNull(seguidosProp);
	}
	
	@Test
	public void testGetSeguidosCol() {
		try {
			icu.agregarUsuario("ColGetSeg2", "NomColGetSeg2", "ApColGetSeg2", "CorreoColGetSeg2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e) {
			e.printStackTrace();			
		}
		Map<String, Usuario> seguidosCol = null;
		seguidosCol = (HashMap<String, Usuario>) icu.getSeguidos("ColGetSeg2");
		assertNotNull(seguidosCol);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioPropAProp() {
		boolean existe = true;
		try {
			icu.agregarUsuario("PropDejarSeg1", "NomPropDejarSeg1", "ApPropDejarSeg1", "CorreoPropDejarSeg1", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropDejarSeg1", "BioPropDejarSeg1", "WebPropDejarSeg1", null);
			icu.agregarUsuario("PropDejarSeg2", "NomPropDejarSeg2", "ApPropDejarSeg2", "CorreoPropDejarSeg2", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropDejarSeg2", "BioPropDejarSeg2", "WebPropDejarSeg2", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropDejarSeg1", "PropDejarSeg2");
			icu.dejarSeguirUsuario("PropDejarSeg1", "PropDejarSeg2");
			Proponente seguido = mProp.getProponentePorNick("PropDejarSeg2");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("PropDejarSeg1");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertFalse(existe);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioPropACol() {
		boolean existe = true;
		try {
			icu.agregarUsuario("PropDejarSeg3", "NomPropDejarSeg3", "ApPropDejarSeg3", "CorreoPropDejarSeg3", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropDejarSeg3", "BioPropDejarSeg3", "WebPropDejarSeg3", null);
			icu.agregarUsuario("ColDejarSeg1", "NomColDejarSeg1", "ApColDejarSeg1", "CorreoColDejarSeg1", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("PropDejarSeg3", "ColDejarSeg1");
			icu.dejarSeguirUsuario("PropDejarSeg3", "ColDejarSeg1");
			Colaborador seguido = mCol.getColaboradorPorNick("ColDejarSeg1");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("PropDejarSeg3");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertFalse(existe);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioColAProp() {
		boolean existe = true;
		try {
			icu.agregarUsuario("ColDejarSeg2", "NomColDejarSeg2", "ApColDejarSeg2", "CorreoColDejarSeg2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("PropDejarSeg4", "NomPropDejarSeg4", "ApPropDejarSeg4", "CorreoPropDejarSeg4", 1, 1, 2002, arrayVacio, null, "Proponente", "DirPropDejarSeg4", "BioPropDejarSeg4", "WebPropDejarSeg4", null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColDejarSeg2", "PropDejarSeg4");
			icu.dejarSeguirUsuario("ColDejarSeg2", "PropDejarSeg4");
			Proponente seguido = mProp.getProponentePorNick("PropDejarSeg4");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("ColDejarSeg2");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertFalse(existe);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioColACol() {
		boolean existe = true;
		try {
			icu.agregarUsuario("ColDejarSeg3", "NomColDejarSeg3", "ApColDejarSeg3", "CorreoColDejarSeg3", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
			icu.agregarUsuario("ColDejarSeg4", "NomColDejarSeg4", "ApColDejarSeg4", "CorreoColDejarSeg4", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.seguirUsuario("ColDejarSeg3", "ColDejarSeg4");
			icu.dejarSeguirUsuario("ColDejarSeg3", "ColDejarSeg4");
			Colaborador seguido = mCol.getColaboradorPorNick("ColDejarSeg4");
			Map<String, Usuario> seguidores = (HashMap<String, Usuario>) seguido.getSeguidores();
			existe = seguidores.containsKey("ColDejarSeg3");
		} catch (UsuarioYaEsSeguidorException e) {
			e.printStackTrace();			
		} catch (UsuarioNoExisteException e) {
			e.printStackTrace();			
		}
		assertFalse(existe);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioSeguidorNoExiste() {
		boolean noExisteSeguidor = false;
		try {
			icu.agregarUsuario("SeguidoDejar1", "SeguidoDejar1", "SeguidoDejar1", "SeguidoDejar1", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.dejarSeguirUsuario("SeguidorDejar1", "SeguidoDejar1");
		} catch (UsuarioNoExisteException e) {
			noExisteSeguidor = true;
		}
		assertTrue(noExisteSeguidor);
	}
	
	@Test
	public void testDejarDeSeguirUsuarioSeguidoNoExiste() {
		boolean noExisteSeguido = false;
		try {
			icu.agregarUsuario("SeguidorDejar2", "NomSeguidorDejar2", "ApSeguidorDejar2", "CorreoSeguidorDejar2", 1, 1, 2002, arrayVacio, null, "Colaborador", null, null, null, null);
		} catch (UsuarioRepetidoException e1) {
			e1.printStackTrace();			
		}
		try {
			icu.dejarSeguirUsuario("SeguidorDejar2", "SeguidoDejar2");
		} catch (UsuarioNoExisteException e) {
			noExisteSeguido = true;
		}
		assertTrue(noExisteSeguido);
	}

}
