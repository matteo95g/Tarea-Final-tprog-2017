package logica;

import java.util.Calendar;
import datatypes.Retorno;

public class Colaboracion {

	private Integer ident;
	private Integer monto;
	private Calendar fechaColaboracion;
	private Retorno retornoElegido;
	private Propuesta propuesta;
	private Usuario usuario;
	private boolean paga;
	private Pago pago;
	
	public Colaboracion(Integer ident, Integer monto, Calendar fechaColaboracion, Retorno retornoElegido, Propuesta propuesta, Usuario usuario) {
		this.ident = ident; 
		this.monto = monto;
		this.fechaColaboracion = fechaColaboracion;
		this.retornoElegido = retornoElegido;
		this.propuesta = propuesta;
		this.usuario = usuario;
		this.paga = false;
		this.pago = null;
	}	

	public Integer getId() {
		return ident;
	}
	public void setId(int ident) {
		this.ident = ident;
	}
	
	public Integer getMonto() {
		return monto;
	}
//	public void setMonto(int monto) {
//		this.monto = monto;
//	}
//	
	public Calendar getFechaColaboracion() {
		return fechaColaboracion;
	}
//	public void setFechaColaboracion(Calendar fechaColaboracion) {
//		this.fechaColaboracion = fechaColaboracion;
//	}
//	
	public Retorno getRetornoElegido() {
		return retornoElegido;
	}
//	public void setRetornoElegido(Retorno retornoElegido) {
//		this.retornoElegido = retornoElegido;
//	}
//	
	public Propuesta getPropuesta() {
		return propuesta;
	}
//	public void setPropuesta(Propuesta propuesta) {
//		this.propuesta = propuesta;
//	}
//
	public Usuario getUsuario() {
		return usuario;
	}
	
	
	@Override
    public String toString() {
        return "ident: " + String.valueOf(getId())+ " - " + getPropuesta().getTitulo();
    } 
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Colaboracion other = (Colaboracion) obj;
		if (ident == null) {
			if (other.ident != null)
				return false;
		} else if (!(ident.equals(other.ident)))
			return false;
		if (monto == null) {
			if (other.monto != null)
				return false;
		} else if (!monto.equals(other.monto))
			return false;
//		if (propuesta == null) {
//			if (other.propuesta != null)
//				return false;
//		} else if (!propuesta.equals(other.propuesta))
//			return false;
		if (retornoElegido != other.retornoElegido)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	
}
