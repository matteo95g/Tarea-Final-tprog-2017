package logica;

import java.util.Calendar;

import datatypes.EstadoPropuesta;

public class FechaCambio {
	
	private Calendar fecha;
	private EstadoPropuesta estado;
	
	public FechaCambio(Calendar fecha, EstadoPropuesta estado) {
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
//	public void setEstado(EstadoPropuesta estado) {
//		this.estado = estado;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FechaCambio other = (FechaCambio) obj;
		if (estado != other.estado)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}
	
	

}
