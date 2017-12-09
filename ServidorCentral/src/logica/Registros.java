package logica;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Registros {
	
	
	@Id
	@GeneratedValue
	private String id;
	private String dirIp;
	private String dirURL;
	private String navegador;
	private String sistOp;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fecha;
	
//	public Registros(String dirIP,String dirURL,String navegador, String sistOP, Calendar fecha ) {
//		
//		this.dirIp=dirIP;
//		this.dirURL=dirURL;
//		this.sistOp=sistOP;
//		this.navegador = navegador;
//		this.fecha = fecha;
//		
//	}
	
	
	
	
	public String getDirIp() {
		return dirIp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDirIp(String dirIp) {
		this.dirIp = dirIp;
	}
	public String getDirURL() {
		return dirURL;
	}
	public void setDirURL(String dirURL) {
		this.dirURL = dirURL;
	}
	public String getNavegador() {
		return navegador;
	}
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}
	public String getSistOp() {
		return sistOp;
	}
	public void setSistOp(String sistOp) {
		this.sistOp = sistOp;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	

}
