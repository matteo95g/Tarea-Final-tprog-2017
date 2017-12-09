package manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Colaboracion;

public class ManejadorColaboracion {
	
	private Map<Integer, Colaboracion> colaboracionesId;
	private static ManejadorColaboracion instancia = null;
	private int canCol = 0;
	
	private ManejadorColaboracion() {
		colaboracionesId = new HashMap<Integer, Colaboracion>();
	}
	
	public static ManejadorColaboracion getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorColaboracion();
		}
		return instancia;
	}
	
	public void addColaboracion(Colaboracion col) {
		Integer ident = col.getId();
		colaboracionesId.put(ident, col);
		canCol ++;
	}
	public void removeColaboracion(Integer colab){
		colaboracionesId.remove(colab);
	}
	
	public Colaboracion getColaboracion(Integer ident) {
		return colaboracionesId.get(ident);
	}
	
	public Map<Integer, Colaboracion> getColaboraciones() {
		return colaboracionesId;
	}

	public int getCanCol() {
		return canCol;
	}



}
