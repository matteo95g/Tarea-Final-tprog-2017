package manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Propuesta;

public class ManejadorPropuesta {
	
	private Map<String, Propuesta> propuestasTitulo;
	
	private static ManejadorPropuesta instancia = null;
	
	private ManejadorPropuesta() {
		propuestasTitulo = new HashMap<String, Propuesta>();
	}
	
	public static ManejadorPropuesta getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorPropuesta();
		}
		return instancia;
	}

	public void addPropuesta(Propuesta prop) {
		String titulo = prop.getTitulo();
		propuestasTitulo.put(titulo, prop);
	}

	public Map<String, Propuesta> getPropuestasTitulo() {
		return propuestasTitulo;
	}

	public void setPropuestasTitulo(Map<String, Propuesta> propuestasTitulo) {
		this.propuestasTitulo = propuestasTitulo;
	}
	
	public Propuesta getPropuestaPorTitulo(String titulo) { 
		return propuestasTitulo.get(titulo);
	}
	
	
}
