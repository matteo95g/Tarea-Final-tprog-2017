package controladores;

import java.util.Calendar;
import java.util.Map;

import datatypes.EstadoPropuesta;
import datatypes.Retorno;
import excepciones.UsuarioYaColaboraException;
import interfaces.IControladorColaboracion;
import logica.Colaboracion;
import logica.Colaborador;
import logica.FechaCambio;
import logica.Propuesta;
import manejadores.ManejadorColaboracion;
import manejadores.ManejadorColaborador;

public class ControladorColaboracion implements IControladorColaboracion {

	public int conseguirId(){
		ManejadorColaboracion manCategoria = ManejadorColaboracion.getInstancia();
		return manCategoria.getCanCol();
	}
	public void agregarColaboracion(String usuario, String propuesta, Integer monto, Calendar fechaColaboracion,
			Retorno retornoElegido) throws UsuarioYaColaboraException {
		ManejadorColaboracion manCategoria = ManejadorColaboracion.getInstancia();
		ManejadorColaborador mcola = ManejadorColaborador.getInstancia();
		ControladorPropuesta cProp = new ControladorPropuesta();
		Map<String, Colaborador> colaboradores = (Map<String, Colaborador>) cProp.obtenerColaboradores(propuesta);
		
		
      if (colaboradores != null && (colaboradores.containsKey(usuario))){
    	  throw new UsuarioYaColaboraException("El usuario " + usuario + " ya colabora en la propuesta");

      } else {
			Propuesta prop = cProp.obtenerPopuesta(propuesta);
			Colaborador Ucola = mcola.getColaboradorPorNick(usuario);
			int numCola = manCategoria.getCanCol() + 1;
			Colaboracion newColab = new Colaboracion(numCola, monto, fechaColaboracion, retornoElegido, prop, Ucola);
			manCategoria.addColaboracion(newColab);

			prop.agregarColaboracion(newColab);
			prop.agregarColaborador(Ucola);
			Ucola.agregarColaboracion(newColab);
			
			if(prop.getEstado().getEstado()== EstadoPropuesta.Publicada) {
				if(prop.getMontoActual()<prop.getMontoNecesario()) {
					prop.agregarEstadoAnterior(prop.getEstado());
					FechaCambio nuevo = new FechaCambio(Calendar.getInstance(),EstadoPropuesta.EnFinanciacion);
					prop.setEstado(nuevo);
					
				}
		
			}
      }
 
	}
	public void eliminarColaboracion(String usuario, Propuesta propuesta, Integer idC, Integer monto){
		ManejadorColaboracion manCategoria = ManejadorColaboracion.getInstancia();
		ManejadorColaborador mcola = ManejadorColaborador.getInstancia();
		Colaboracion colaEli = manCategoria.getColaboracion(idC);		
		Colaborador usuCola = mcola.getColaboradorPorNick(usuario);
		
		propuesta.eliminarColaboracion(colaEli);
		propuesta.eliminarColaborador(usuario);
		usuCola.eliminarColaboracion(idC);

		Integer montoAct= propuesta.getMontoActual() - monto;
		propuesta.setMontoActual(montoAct);
		manCategoria.removeColaboracion(idC);
	}
	
	public Colaboracion getColaboracion(int numCola){
		ManejadorColaboracion manCategoria = ManejadorColaboracion.getInstancia();
		Colaboracion retorno = manCategoria.getColaboracion(numCola);
		return retorno;
	}
}


