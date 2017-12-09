package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtFechaCambio {
	
	private Calendar fecha;
	private EstadoPropuesta estado;
	
	public DtFechaCambio() {
		
	}
	
	public DtFechaCambio(Calendar fecha, EstadoPropuesta estado) {
		this.fecha = fecha;
		this.estado = estado;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public EstadoPropuesta getEstado() {
		return estado;
	}
	public void setEstado(EstadoPropuesta estado) {
		this.estado = estado;
	}

}
