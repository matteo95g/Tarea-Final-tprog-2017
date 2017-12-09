package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaEsSeguidorException;
import interfaces.Fabrica;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Proponente;
import logica.Propuesta;
import logica.Usuario;
import logica.Registros;
import manejadores.ManejadorColaborador;
import manejadores.ManejadorProponente;
import manejadores.ManejadorRegistros;
import persistencia.Persistir;

public class ControladorUsuario implements IControladorUsuario{

	public ControladorUsuario() {
	}
	
	public void agregarUsuario(String nick, String nombre, String apellido, String correo, int dia, int mes, int anio,
		byte[] imagen, String extImg, String perfil, String direccion, String biografia, String web, String contra) throws UsuarioRepetidoException {
		Configuracion Config = Configuracion.getInstancia();
		
		Usuario userNick = getUsuarioPorNick(nick);
		Usuario userCorreo = getUsuarioPorCorreo(correo);
		
		String extImagen = null;
		
		if (userCorreo != null) {
			throw new UsuarioRepetidoException("Este correo ya se encuentra registrado a nombre de " + userCorreo.getNombre() + " " + userCorreo.getApellido());
		}
		
		if (userNick != null) {
			throw new UsuarioRepetidoException("Este nick ya se encuentra ocupado");
		}
		
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(anio, mes, dia);
		
		if (imagen.length != 0) {
			
			extImagen = extImg;
			
			String pathImagenes = Config.getPathImagenes();

			String destinoArchivo = pathImagenes + nick + extImg;
			
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(destinoArchivo);
				fos.write(imagen);
				fos.close();
			} catch (FileNotFoundException e) {
				return;
			} catch (IOException e) {
				return;
			}
			
			/*String absolutePath = imagen.getAbsolutePath();
			int i = absolutePath.lastIndexOf('.');
			if (i > 0) {
				extImg = "." + absolutePath.substring(i+1);
			}
			String origenArchivo = imagen.getAbsolutePath();*/
			/*if (!origenArchivo.equals(destinoArchivo)) {
				copiarArchivo(origenArchivo, destinoArchivo);
			}*/
		}
		
		if (perfil.equals("Colaborador")) {
			agregarColaborador(nick, nombre, apellido, correo, fecha, contra, extImagen);
//			Colaborador nuevoCol = new Colaborador(nick, nombre, apellido, correo, fecha, contra);
//			if (imagen.length != 0) {
//				nombreImg = nick + extImg;
//				nuevoCol.setImagen(nombreImg);
//			}
//			manColaborador.addColaborador(nuevoCol);
		} else {
			agregarProponente(nick, nombre, apellido, correo, fecha, contra, direccion, extImagen, biografia, web);
//			Proponente nuevoProp = new Proponente(nick, nombre, apellido, correo, fecha, contra, direccion);
//			if (imagen.length != 0) {
//				nombreImg = nick + extImg;
//				nuevoProp.setImagen(nombreImg);
//			}
//			if (!biografia.equals("")) {
//				nuevoProp.setBiografia(biografia);
//			}
//			if (!web.equals("")) {
//				nuevoProp.setSitioWeb(web);
//			}
//			manProponente.addProponente(nuevoProp);
		}
		
	}
	
	private void agregarColaborador(String nick, String nombre, String apellido, String correo, Calendar fecha, String contra, String extImg ){
		
		ManejadorColaborador manColaborador = ManejadorColaborador.getInstancia();
		
		Colaborador nuevoCol = new Colaborador(nick, nombre, apellido, correo, fecha, contra);
		String	nombreImg = null;
		if ((extImg != null) && (extImg != "")) {
		    nombreImg = nick + extImg;
			nuevoCol.setImagen(nombreImg);
		}
		manColaborador.addColaborador(nuevoCol);
	}
	
	
	private void agregarProponente(String nick, String nombre, String apellido, String correo, Calendar fecha, String contra, String direccion, String extImg, String biografia, String web) {
		
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		Proponente nuevoProp = new Proponente(nick, nombre, apellido, correo, fecha, contra, direccion);
		String	nombreImg = null;
		if ((extImg != null) && (extImg != "")) {
		    nombreImg = nick + extImg;
			nuevoProp.setImagen(nombreImg);
		}
		if (!biografia.equals("")) {
			nuevoProp.setBiografia(biografia);
		}
		if (!web.equals("")) {
			nuevoProp.setSitioWeb(web);
		}
		
		manProponente.addProponente(nuevoProp);
		
	}
	
	
	
	public boolean nickLibre(String nick) {
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		Colaborador col = manCategoria.getColaboradorPorNick(nick);
		Proponente prop = manPropuesta.getProponentePorNick(nick);
		if ((col == null) && (prop == null)) {
			return true;
		}
		return false;
	}
	
	public void copiarArchivo(String sourceFile, String destinationFile) {
		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream input = new FileInputStream(inFile);
			FileOutputStream output = new FileOutputStream(outFile);

			int var;
			while ( (var = input.read()) != -1)
				output.write(var);

			input.close();
			output.close();
		} catch(IOException e) {
			return;
		}
	}
	
	public Map<String, Proponente> getProponentes() {
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		Map<String, Proponente> proponentes = (HashMap<String, Proponente>) manPropuesta.getProponentesNick();
		return proponentes;
	}
	
	public Map<String, Colaborador> getColaboradores() {
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();
		Map<String, Colaborador> colaboradores = (Map<String, Colaborador>) manCategoria.getColaboradoresNick();
		return colaboradores;
	}
	
	public void seguirUsuario(String seguidor, String seguido) throws UsuarioYaEsSeguidorException, UsuarioNoExisteException {
		Usuario uSeguidor = getUsuarioPorNick(seguidor);
		Usuario uSeguido = getUsuarioPorNick(seguido);
		
		
		
		if (uSeguidor == null) {
			throw new UsuarioNoExisteException("El usuario " + seguidor + " no existe");
		}
		
		if (uSeguido == null) {
			throw new UsuarioNoExisteException("El usuario " + seguido + " no existe");
		}
		
		int tipo1 = tipoUsuario(seguidor);
		int tipo2 = tipoUsuario(seguido);
		
		Map<String, Usuario> seguidores = getSeguidos(seguidor);
		
		if (seguidores.containsKey(seguido)) {
			throw new UsuarioYaEsSeguidorException("El usuario " + seguidor + " ya sigue a " + seguido);
		} else {
		auxSeguirUsr(seguidor, seguido, tipo1, tipo2);
		}
		
		
		
		
	}
	
	public Map<String, Usuario> getSeguidores(String usuario) {
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();
		Usuario usuP = manPropuesta.getProponentePorNick(usuario);
		Usuario usuC = manCategoria.getColaboradorPorNick(usuario);
		if (usuP != null) {
			return usuP.getSeguidores();
		} else {
			return usuC.getSeguidores();
		}	
	}
	
	public Map<String, Usuario> getSeguidos(String usuario) {
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();
		Usuario usuP = manPropuesta.getProponentePorNick(usuario);
		Usuario usuC = manCategoria.getColaboradorPorNick(usuario);
		if (usuP != null) {
			return usuP.getSeguidos();
		} else {
			return usuC.getSeguidos();
		}	
	}
	
	public void dejarSeguirUsuario(String seguidor, String seguido) throws UsuarioNoExisteException {
		
		Usuario uSeguidor = getUsuarioPorNick(seguidor);
		Usuario uSeguido = getUsuarioPorNick(seguido);
	
		if (uSeguidor == null) {
			throw new UsuarioNoExisteException("El usuario " + seguidor + " no existe");
		}
		if (uSeguido == null) {
			throw new UsuarioNoExisteException("El usuario " + seguido + " no existe");
		}
		
		int tipo1 = tipoUsuario(seguidor);
		int tipo2 = tipoUsuario(seguido);
		
		auxDejarSeguir(seguidor, seguido, tipo1, tipo2);
		
				
	}
	
	public Usuario getUsuarioPorNick(String nick){
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		ManejadorColaborador manColaborador = ManejadorColaborador.getInstancia();
		Proponente propUsr = manPropuesta.getProponentePorNick(nick);
		Colaborador colUsr = manColaborador.getColaboradorPorNick(nick);
		if (propUsr != null) {
			return propUsr;
		} else if (colUsr != null) {
			return colUsr;
		} else {
			return null;
		}
	}
	
	public Usuario getUsuarioPorCorreo(String correo){
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		ManejadorColaborador manColaborador= ManejadorColaborador.getInstancia();
		Proponente propUsr = manProponente.getProponentePorCorreo(correo);
		Colaborador colUsr = manColaborador.getColaboradorPorCorreo(correo);
		if (propUsr != null) {
			return propUsr;
		} else if (colUsr != null) {
			return colUsr;
		} else {
			return null;
		}
	}
	
	private int tipoUsuario(String usuario) { //0:null   -  1:Proponente   -   2:Colaborador
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		ManejadorColaborador manColaborador = ManejadorColaborador.getInstancia();
		Proponente propUsr = manPropuesta.getProponentePorNick(usuario);
		Colaborador colUsr = manColaborador.getColaboradorPorNick(usuario);
		
		if (propUsr != null) {
			return 1;
		} else if (colUsr != null) {
			return 2;
		} else {
			return 0;
		}
	    
	}
	
	private void auxDejarSeguir(String seguidor, String seguido, int tipo1, int tipo2) {
		
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();

		Usuario uSeguidor = null, uSeguido = null;
		
		if (tipo1 == 1) {
			uSeguidor = manProponente.getProponentePorNick(seguidor);
		} else {
			uSeguidor = manCategoria.getColaboradorPorNick(seguidor);
		}
		
		if (tipo2 == 1){
			uSeguido = manProponente.getProponentePorNick(seguido);
		} else {
			uSeguido = manCategoria.getColaboradorPorNick(seguido);
		}
		
		uSeguido.eliminarSeguidor(seguidor);
		uSeguidor.eliminarSeguido(seguido);
		
	
	}
	
	
	private void auxSeguirUsr(String seguidor, String seguido, int tipo1, int tipo2) {
		
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		ManejadorColaborador manCategoria = ManejadorColaborador.getInstancia();

		Usuario uSeguidor = null, uSeguido = null;
		
		if (tipo1 == 1) {
			uSeguidor = manProponente.getProponentePorNick(seguidor);
		} else {
			uSeguidor = manCategoria.getColaboradorPorNick(seguidor);
		}
		
		if (tipo2 == 1){
			uSeguido = manProponente.getProponentePorNick(seguido);
		} else {
			uSeguido = manCategoria.getColaboradorPorNick(seguido);
		}
		
		uSeguidor.agregarSeguido(uSeguido);
		uSeguido.agregarSeguidor(uSeguidor);
		
	}
	
	public boolean existeUsuarioPorNick(String nick) {
		ManejadorProponente manPropuesta = ManejadorProponente.getInstancia();
		ManejadorColaborador manColaborador = ManejadorColaborador.getInstancia();
		Proponente propUsr = manPropuesta.getProponentePorNick(nick);
		Colaborador colUsr = manColaborador.getColaboradorPorNick(nick);
		if (propUsr != null) {
			return true;
		} else if (colUsr != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean existeUsuarioPorCorreo(String correo) {
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		ManejadorColaborador manColaborador= ManejadorColaborador.getInstancia();
		Proponente propUsr = manProponente.getProponentePorCorreo(correo);
		Colaborador colUsr = manColaborador.getColaboradorPorCorreo(correo);
		if (propUsr != null) {
			return true;
		} else if (colUsr != null) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void agregarRegistro(String dirIP, String dirURL, String navegador, String sistOP){
		
		ManejadorRegistros manRegistro = ManejadorRegistros.getInstancia();
		Calendar fecha = Calendar.getInstance();
		Registros registro = new Registros();
		registro.setDirIp(dirIP);
		registro.setDirURL(dirURL);
		registro.setFecha(fecha);
		registro.setNavegador(navegador);
		registro.setSistOp(sistOP);
			
		manRegistro.addRegistro(registro);
		
	}
	
	
	public List<Registros> getRegistros(){
		ManejadorRegistros manRegistro = ManejadorRegistros.getInstancia();
		return manRegistro.getRegistros();
	}
	
	
	public void borrarProponente(String nickProp) {
		Fabrica fabrica = Fabrica.getInstance();
		IControladorPropuesta iContPro = fabrica.getIControladorPropuesta();
		
		Persistir persistencia = new Persistir();
		persistencia.persistirDatos(nickProp);
		ManejadorProponente manProponente = ManejadorProponente.getInstancia();
		Proponente usr = manProponente.getProponentePorNick(nickProp);
		
		usr.setFavoritas(null);
		
		//se elimina el usuario como seguido de sus seguidores
		Map<String, Usuario> seguidoresBorrar = usr.getSeguidores();
		List<Usuario> listaSeguidores = new ArrayList<Usuario>(seguidoresBorrar.values());
		Iterator<Usuario> iter = listaSeguidores.iterator();
		while (iter.hasNext()) {
			Usuario apuntado = iter.next();
			apuntado.eliminarSeguido(nickProp);
		}
		
		// se elimina el usuario como seguidor de sus seguidos
		Map<String, Usuario> seguidosBorrar = usr.getSeguidos();
		List<Usuario> listaSeguidos = new ArrayList<Usuario>(seguidosBorrar.values());
		Iterator<Usuario> iterador = listaSeguidos.iterator();
		while (iterador.hasNext()) {
			Usuario apuntado = iterador.next();
			apuntado.eliminarSeguidor(nickProp);
			
		}
		
		//se eliminan las propuestas
		Map<String, Propuesta> propuestasBorrar = usr.getPropuestas();
		List<Propuesta> listaPropuestas = new ArrayList<Propuesta>(propuestasBorrar.values());
		Iterator<Propuesta> iterProp = listaPropuestas.iterator();
		while (iterProp.hasNext()) {
			Propuesta propApuntada = iterProp.next();
			iContPro.eliminarPropuesta(propApuntada);
		}
		//se elimina el usuario del manejador
		manProponente.getProponentesNick().remove(nickProp);
		manProponente.getProponentesCorreo().remove(usr.getCorreoElectronico());
	}
	
	

	
}