package logica;

public class Paypal extends Pago {
	
	private String titular;
	private String cuenta;
	
	public Paypal(int monto, String titular, String cuenta) {
		super(monto);
		this.titular = titular;
		this.cuenta = cuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	
	

}
