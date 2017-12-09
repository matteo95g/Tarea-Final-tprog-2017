package interfaces;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaEsSeguidorException;
import logica.Colaborador;
import logica.Proponente;
import logica.Registros;
import logica.Usuario;

public interface IControladorUsuario {
	
	public abstract void agregarUsuario(String nick, String nombre, String apellido, String correo, int dia, int mes, int anio,
			byte[] imagen, String extImg, String perfil, String direccion, String biografia, String web, String contra) throws UsuarioRepetidoException;
	
	public abstract boolean nickLibre(String nick);
	
	public abstract Map<String, Proponente> getProponentes();
	
	public abstract Map<String, Colaborador> getColaboradores();
	
	public abstract void seguirUsuario(String seguidor, String seguido) throws UsuarioYaEsSeguidorException, UsuarioNoExisteException;
	
	public abstract Map<String, Usuario> getSeguidores(String usuario);
	
	public abstract Map<String, Usuario> getSeguidos(String usuario);
	
	public abstract void dejarSeguirUsuario(String seguidor, String seguido) throws UsuarioNoExisteException;
	
	public abstract Usuario getUsuarioPorNick(String nick);
	
	public abstract Usuario getUsuarioPorCorreo(String correo);
	
	public abstract boolean existeUsuarioPorNick(String nick);
	
	public abstract boolean existeUsuarioPorCorreo(String correo);
	
	public abstract void agregarRegistro(String dirIP, String dirURL, String navegador, String sistOP);
	
	public abstract List<Registros> getRegistros();
	
	public abstract void borrarProponente(String nickProp);

}
