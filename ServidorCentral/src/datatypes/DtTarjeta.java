package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtTarjeta extends DtPago {
	
	private String tipo;
	private String titular;
	private int numero;
	private Calendar fechaVencimiento;
	private int cvc;
	
	public DtTarjeta(int monto, String tipo, String titular, int numero, Calendar fechaVencimiento, int cvc) {
		super(monto);
		this.tipo = tipo;
		this.titular = titular;
		this.numero = numero;
		this.fechaVencimiento = fechaVencimiento;
		this.cvc = cvc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Calendar getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Calendar fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

}
