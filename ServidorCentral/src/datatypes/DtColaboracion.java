package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtColaboracion {
	private Integer ident;
	private Integer monto;
	private Calendar fechaColaboracion;
	private Retorno retornoElegido;
	private String propuesta;
	private String usuario;
	private boolean paga;
	private DtPago pago;
	
	public DtColaboracion() {
		
	}
	
	public DtColaboracion(Integer ident, Integer monto, Calendar fechaColaboracion, Retorno retornoElegido, String propuesta, String usuario) {
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
	public String getPropuesta() {
		return propuesta;
	}
//	public void setPropuesta(Propuesta propuesta) {
//		this.propuesta = propuesta;
//	}
//
	public String getUsuario() {
		return usuario;
	}

	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	public DtPago getPago() {
		return pago;
	}

	public void setPago(DtPago pago) {
		this.pago = pago;
	}
	
}
