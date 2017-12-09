package datatypes;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private Calendar fechaNacimiento;
	private String imagen;
	private String contra;
	private ArrayList<String> seguidores;
	private ArrayList<String> seguidos;
	private ArrayList<String> favoritas;
	
	public DtUsuario() {};
	
	public DtUsuario(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contra = contra;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
		this.seguidores = new ArrayList<String>();
		this.seguidos = new ArrayList<String>();
		this.imagen = "";
		this.favoritas = new ArrayList<String>();
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContra(){
		return contra;
	}
	public void setContra(String contra){
		this.contra=contra;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public ArrayList<String> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(ArrayList<String> seguidores) {
		this.seguidores = seguidores;
	}

	public ArrayList<String> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(ArrayList<String> favoritas) {
		this.favoritas = favoritas;
	}
	
	public ArrayList<String> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(ArrayList<String> seguidos) {
		this.seguidos = seguidos;
	}

}
