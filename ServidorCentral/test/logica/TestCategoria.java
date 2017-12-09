package logica;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import logica.Categoria;

public class TestCategoria {
	private Categoria categ;
	private Categoria categ1;
	@Test
	public void testCategoria(){
		categ =  new Categoria("name");
		categ1 = new Categoria("name");
		boolean boolOk = false;
		if (categ.equals(categ1)){
			boolOk= true;
		}
		assertTrue(boolOk);
	}	

	@Test
	public void testGetNombre(){
		categ = new Categoria("name");
		boolean bookOk=false;
		if ("name" == categ.getNombre()){
			bookOk= true;
		}
		assertTrue(bookOk);
	}
	
	@Test
	public void testGetPadre(){
		categ= new Categoria("name");
		boolean boolOk=false;
		if (null== categ.getPadre()){
			boolOk = true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testGetHijas(){
		categ= new Categoria("name");
		boolean boolOk=false;
		if (null== categ.getHijas()){
			boolOk = true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testSetNombre(){
		categ= new Categoria("");
		boolean boolOk=false;
		categ.setNombre("name");
		if ("name"== categ.getNombre()){
			boolOk= true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testSetPadre(){
		categ= new Categoria("name");
		categ1= new Categoria("padre");
		boolean boolOk = false;
		categ.setPadre(categ1);
		if (categ1 == categ.getPadre()){
			boolOk = true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testSetHijas(){
		categ= new Categoria("name");
		categ1 = new Categoria("hija");
		LinkedList<Categoria> hijas = new LinkedList<Categoria>();
		hijas.add(categ1);
		boolean boolOk = false;
		categ.setHijas(hijas);
		if (hijas== categ.getHijas()){
			boolOk=true;
		}
		assertTrue(boolOk);
	}
	
	@Test
	public void testAddHija(){
		categ = new Categoria("name");
		categ1 = new Categoria("hija");
		categ.addHija(categ1);
		LinkedList<Categoria> hija = new LinkedList<Categoria>();
		hija.add(categ1);
		boolean boolOk = false;
		if (categ.getHijas().equals(hija)){
			boolOk=true;
		}
		assertTrue(boolOk);
	}
	
	
}
