package manejadores;

import java.util.HashMap;
import java.util.Map;

import logica.Proponente;

public class ManejadorProponente {
	
	private Map<String, Proponente> proponentesNick;
	private Map<String, Proponente> proponentesCorreo;
	private static ManejadorProponente instancia = null;
	
	private ManejadorProponente() {
		proponentesNick = new HashMap<String, Proponente>();
		proponentesCorreo = new HashMap<String, Proponente>();
	}
	
	public static ManejadorProponente getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorProponente();
		}
		return instancia;
	}
	
	public void addProponente(Proponente usr) {
		String nick = usr.getNickname();
		String correo = usr.getCorreoElectronico();
		proponentesCorreo.put(correo, usr);
		proponentesNick.put(nick, usr);
	}
	
	public Proponente getProponentePorNick(String nick) {
		return proponentesNick.get(nick);
	}
	
	public Proponente getProponentePorCorreo(String correo) {
		return proponentesCorreo.get(correo);
	}
	
	public Map<String, Proponente> getProponentesNick() {
		return proponentesNick;
	}	
	
	public Map<String, Proponente> getProponentesCorreo() {
		return proponentesCorreo;
	}
	
}
