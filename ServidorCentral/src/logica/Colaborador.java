package logica;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Colaborador extends Usuario {
	
	private Map<Integer, Colaboracion> colaboraciones;
	private Map<String, Comentario> comentarios;

	public Colaborador(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra) {
		super(nickname, nombre, apellido, correoElectronico, fechaNacimiento, contra);
		this.colaboraciones = new HashMap<Integer, Colaboracion>();
	}

	public Map<Integer, Colaboracion> getColaboraciones() {
		return colaboraciones;
	}
//	public void setColaboraciones(Map<Integer, Colaboracion> colaboraciones) {
//		this.colaboraciones = colaboraciones;
//	}
	
	public void agregarColaboracion(Colaboracion colab) {
		if (!colaboraciones.containsKey(colab.getId())) {
			colaboraciones.put(colab.getId(), colab);
		}
	}
	
	public void eliminarColaboracion(int ident) {
		colaboraciones.remove(ident);
	}
	
	
	@Override
    public String toString() {
        return getNombre() + " " + getApellido() + " - NickName: " + getNickname();
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaborador other = (Colaborador) obj;
		if (colaboraciones == null) {
			if (other.colaboraciones != null)
				return false;
		} else if (!colaboraciones.equals(other.colaboraciones))
			return false;
		return true;
	}

	public Map<String, Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Map<String, Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
	
}
