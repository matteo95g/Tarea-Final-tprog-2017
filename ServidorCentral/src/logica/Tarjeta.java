package logica;

import java.util.Calendar;

public class Tarjeta extends Pago {
	
	private String tipo;
	private String titular;
	private int numero;
	private Calendar fechaVencimiento;
	private int cvc;
	
	public Tarjeta(int monto, String tipo, String titular, int numero, Calendar fechaVencimiento, int cvc) {
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
