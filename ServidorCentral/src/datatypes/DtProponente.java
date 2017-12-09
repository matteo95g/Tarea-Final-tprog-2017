package datatypes;

import java.util.ArrayList;
import java.util.Calendar;

import datatypes.DtUsuario;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtProponente extends DtUsuario{
	private String direccion;
	private String biografia;
	private String sitioWeb;
	private ArrayList<String> propuestas;

	public DtProponente() {
		super();
	}
	
	public DtProponente(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra, String direccion) {
		super(nickname, nombre, apellido, correoElectronico, fechaNacimiento, contra);
		this.direccion = direccion;
		this.propuestas = new ArrayList<String>();
		this.biografia = null;
		this.sitioWeb = null;
	}	

	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
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
	public ArrayList<String> getPropuestas() {
		return propuestas;
	}
	public void setPropuestas(ArrayList<String> propuestas) {
		this.propuestas = propuestas;
	}
}
