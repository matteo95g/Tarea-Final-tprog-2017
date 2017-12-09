package controladores;

import java.util.LinkedList;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import interfaces.IControladorCategoria;
import logica.Categoria;
import manejadores.ManejadorCategoria;

public class ControladorCategoria implements IControladorCategoria {
	
	public ControladorCategoria() {
	}
	
	public void agregarCategoria(String nombre, String padre) throws CategoriaRepetidaException {
		ManejadorCategoria manCate = ManejadorCategoria.getInstancia();
		Categoria nCat = manCate.getCategoria(nombre);
		if (nCat != null) {
			throw new CategoriaRepetidaException("La categoria " + nombre + " ya existe");
		}
		Categoria nuevaCategoria;
		if (padre.equals("")) {
			List<Categoria> categorias = (LinkedList<Categoria>) manCate.getCategorias();
			Categoria primanCateat = ((LinkedList<Categoria>) categorias).getFirst();
			nuevaCategoria = new Categoria(nombre, primanCateat);
			primanCateat.addHija(nuevaCategoria);
		} else {
			Categoria padreCat = manCate.getCategoria(padre);
			nuevaCategoria = new Categoria(nombre, padreCat);
			padreCat.addHija(nuevaCategoria);
		}
		manCate.addCategoria(nuevaCategoria);
	
	}
	
	public List<Categoria> getCategorias() {  
		ManejadorCategoria manCate = ManejadorCategoria.getInstancia();
		List<Categoria> categorias = (LinkedList<Categoria>) manCate.getCategorias();
		return categorias;
	}
	
	public Categoria getCategoria(String nombre) {
		ManejadorCategoria manCate = ManejadorCategoria.getInstancia();
		Categoria nCat = manCate.getCategoria(nombre);
		return nCat;
		
	}

}
