package interfaces;

import java.util.Calendar;

import datatypes.Retorno;
import excepciones.UsuarioYaColaboraException;
import logica.Colaboracion;
import logica.Propuesta;

public interface IControladorColaboracion {
	
	public abstract int conseguirId();
	public abstract void agregarColaboracion(String usuario, String propuesta, Integer monto, Calendar fechaColaboracion,
			Retorno retornoElegido) throws UsuarioYaColaboraException ;
	public abstract void eliminarColaboracion(String usuario, Propuesta propuesta, Integer idC, Integer monto);
	
	public abstract Colaboracion getColaboracion(int identificador);
}
