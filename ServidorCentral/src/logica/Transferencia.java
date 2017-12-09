package logica;

public class Transferencia extends Pago {
	
	private String banco;
	private String titular;
	private String cuenta;
	
	public Transferencia(int monto, String banco, String titular, String cuenta) {
		super(monto);
		this.banco = banco;
		this.titular = titular;
		this.cuenta = cuenta;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
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
