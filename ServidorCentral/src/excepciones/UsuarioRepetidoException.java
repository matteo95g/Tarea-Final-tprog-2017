package excepciones;

@SuppressWarnings("serial")
public class UsuarioRepetidoException extends Exception {
	
	public UsuarioRepetidoException(String string) {
		super(string);
	}
	
}