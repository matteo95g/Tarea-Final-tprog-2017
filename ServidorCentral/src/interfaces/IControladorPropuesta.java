package interfaces;


import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import datatypes.EstadoPropuesta;
import datatypes.Retorno;
import excepciones.PropuestaRepetidaException;
import logica.Categoria;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;

public interface IControladorPropuesta {
	
	public abstract void addPropuesta(String titulo, String descripcion, byte[] imagen, String extImg, String lugar, Calendar fecha, Integer precioEntrada,
			Integer montoNecesario, List<Retorno> retornos, Categoria categoria,
			Proponente proponente) throws PropuestaRepetidaException;
	
	public abstract List<Propuesta> getPropuestas();
	
	public abstract Propuesta seleccionarPropuesta(String titulo);
	
	public abstract void modificarPropuesta(Propuesta prop, String titulo, String descripcion, String lugar, Calendar fecha, Integer montoN, Integer montoA, Integer precio, File foto, Categoria categoria);
	
	//public abstract List<Propuesta> listarPropuestaPorEstado(String estado);
	
	public abstract Map<String, Colaborador> obtenerColaboradores(String titulo);
	
	public abstract boolean tituloLibre(String titulo);
	
	public abstract Map<String, Propuesta> getPropuestasTitulo();
	
	public abstract void copiarArchivo(String sourceFile, String destinationFile);
	
	public abstract void modificarEstado(String titulo, EstadoPropuesta estado);
	
	public abstract void cambiarEstadoProp(Propuesta pro);
	
	public abstract void eliminarPropuesta(Propuesta prop);
	
	
}
