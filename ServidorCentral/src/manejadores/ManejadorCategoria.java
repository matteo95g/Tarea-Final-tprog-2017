package manejadores;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import logica.Categoria;

public class ManejadorCategoria {
	
	private List<Categoria> categorias;
	private static ManejadorCategoria instancia = null;
	
	private ManejadorCategoria() {
		categorias = new LinkedList<Categoria>();
		Categoria categoria = new Categoria("Categoria");
		categorias.add(categoria);
	}
	
	public static ManejadorCategoria getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorCategoria();
		}
		return instancia;
	}
	
	public void addCategoria(Categoria cat) {
		categorias.add(cat);
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public Categoria getCategoria(String nombre) {
		ListIterator<Categoria> iterI = categorias.listIterator();
		Categoria cat;
		Categoria ret = null;
		while (iterI.hasNext() && (ret == null)) {
			cat = iterI.next();
			if (cat.getNombre().equals(nombre)) {
				ret = cat;
			}
		}
		return ret;
	}

}
