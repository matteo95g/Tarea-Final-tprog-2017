package logica;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Proponente extends Usuario {
	
	private String direccion;
	private String biografia;
	private String sitioWeb;
	private Map<String, Propuesta> propuestas;

	public Proponente(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra, String direccion) {
		super(nickname, nombre, apellido, correoElectronico, fechaNacimiento, contra);
		this.direccion = direccion;
		this.propuestas = new HashMap<String, Propuesta>();
		this.biografia = null;
		this.sitioWeb = null;
	
	}

	public String getDireccion() {
		return direccion;
	}
//	public void setDireccion(String direccion) {
//		this.direccion = direccion;
//	}

	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public Map<String, Propuesta> getPropuestas() {
		return propuestas;
	}
//	public void setPropuestas(Map<String, Propuesta> propuestas) {
//		this.propuestas = propuestas;
//	}
	
	public void agregarPropuesta(Propuesta prop) {
		if (!propuestas.containsKey(prop.getTitulo())) {
			propuestas.put(prop.getTitulo(), prop);
		}
	}
	
//	public void eliminarPropuesta(String titulo) {
//		propuestas.remove(titulo);
//	}
	
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
		Proponente other = (Proponente) obj;
		if (biografia == null) {
			if (other.biografia != null)
				return false;
		} else if (!biografia.equals(other.biografia))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (propuestas == null) {
			if (other.propuestas != null)
				return false;
		} else if (!propuestas.equals(other.propuestas))
			return false;
		if (sitioWeb == null) {
			if (other.sitioWeb != null)
				return false;
		} else if (!sitioWeb.equals(other.sitioWeb))
			return false;
		return true;
	}
	

}