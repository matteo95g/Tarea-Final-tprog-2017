package interfaces;

import java.util.List;

import excepciones.CategoriaRepetidaException;
import logica.Categoria;

public interface IControladorCategoria {
	
	public abstract void agregarCategoria(String nombre, String padre) throws CategoriaRepetidaException;
	
	public abstract List<Categoria> getCategorias();
	
	public abstract Categoria getCategoria(String nombre);
}
