package excepciones;

@SuppressWarnings("serial")
public class UsuarioYaEsSeguidorException extends Exception {
	public UsuarioYaEsSeguidorException(String exept) {
		super(exept);
	}
}