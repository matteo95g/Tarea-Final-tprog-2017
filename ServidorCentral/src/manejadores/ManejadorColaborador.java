package manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Colaborador;

public class ManejadorColaborador {
	
	private Map<String, Colaborador> colaboradoresNick;
	private Map<String, Colaborador> colaboradoresCorreo;
	private static ManejadorColaborador instancia = null;
	
	private ManejadorColaborador() {
		colaboradoresNick = new HashMap<String, Colaborador>();
		colaboradoresCorreo = new HashMap<String, Colaborador>();
	}
	
	public static ManejadorColaborador getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorColaborador();
		}
		return instancia;
	}
	
	public void addColaborador(Colaborador usr) {
		String nick = usr.getNickname();
		String correo = usr.getCorreoElectronico();
		colaboradoresCorreo.put(correo, usr);
		colaboradoresNick.put(nick, usr);
	}
	
	public Colaborador getColaboradorPorNick(String nick) {
		return colaboradoresNick.get(nick);
	}
	
	public Colaborador getColaboradorPorCorreo(String correo) {
		return colaboradoresCorreo.get(correo);
	}
	
	public Map<String, Colaborador> getColaboradoresNick() {
		return colaboradoresNick;
	}

}
