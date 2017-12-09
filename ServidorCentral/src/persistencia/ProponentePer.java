package persistencia;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;

@Entity
public class ProponentePer {
	
	@Id
	@GeneratedValue
	private String id;
	private String nickname;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private String contra;
	@Temporal(TemporalType.DATE)
	private Calendar fechaNacimiento;
	@Temporal(TemporalType.DATE)
	private Calendar fechaBaja;
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Collection<PropuestaPer> propuestas;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}

	public Collection<PropuestaPer> getPropuestas() {
		return propuestas;
	}
	public void setPropuestas(Collection<PropuestaPer> propuestas) {
		this.propuestas = propuestas;
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
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Calendar getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Calendar fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	@Override
	public String toString() {
		return  nickname + " -- " + nombre + " " + apellido;
	}

}
