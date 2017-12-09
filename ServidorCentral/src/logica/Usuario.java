package logica;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private Calendar fechaNacimiento;
	private String imagen;
	private String contra;
	private Map<String, Usuario> seguidores;
	private Map<String, Usuario> seguidos;
	private Map<String, Propuesta> favoritas;
	
	public Usuario(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contra = contra;
		this.correoElectronico = correoElectronico;
		this.fechaNacimiento = fechaNacimiento;
		this.seguidores = new HashMap<String, Usuario>();
		this.seguidos = new HashMap<String, Usuario>();
		this.imagen = null;
		this.favoritas = new HashMap<String, Propuesta>();
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

	public Map<String, Usuario> getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(Map<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	public void agregarSeguidor(Usuario usr) {
		seguidores.put(usr.getNickname(), usr);
	}
	
	public void eliminarSeguidor(String nick)  {
		if (seguidores.containsKey(nick)){
			seguidores.remove(nick);
		}
	}

	public Map<String, Propuesta> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(Map<String, Propuesta> favoritas) {
		this.favoritas = favoritas;
	}
	
	public void agregarFavorita(Propuesta prop) {
		if (!favoritas.containsKey(prop.getTitulo())) {
			favoritas.put(prop.getTitulo(), prop);
		}
	}
	
	public void eliminarFavorita(String titulo) {
		if (favoritas.containsKey(titulo)) {
			favoritas.remove(titulo);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		if (favoritas == null) {
			if (other.favoritas != null)
				return false;
		} else if (!favoritas.equals(other.favoritas))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (seguidores == null) {
			if (other.seguidores != null)
				return false;
		} else if (!seguidores.equals(other.seguidores))
			return false;
		return true;
	}

	public Map<String, Usuario> getSeguidos() {
		return seguidos;
	}

	public void setSeguidos(Map<String, Usuario> seguidos) {
		this.seguidos = seguidos;
	}
	
	public void agregarSeguido(Usuario usr) {
		seguidos.put(usr.getNickname(), usr);
	}
	
	public void eliminarSeguido(String nick)  {
		if (seguidos.containsKey(nick)){
			seguidos.remove(nick);
		}
	}
	
}