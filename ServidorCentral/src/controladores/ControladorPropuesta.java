/**
 * 
 */
package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import datatypes.EstadoPropuesta;
import datatypes.Retorno;
import excepciones.PropuestaRepetidaException;
import interfaces.IControladorPropuesta;
import logica.Categoria;
import logica.Colaboracion;
import logica.Colaborador;
import logica.FechaCambio;
import logica.Proponente;
import logica.Propuesta;
import logica.Usuario;
import manejadores.ManejadorColaboracion;
import manejadores.ManejadorPropuesta;

/**
 * @author matteo
 *
 */
public class ControladorPropuesta implements IControladorPropuesta {
	
	private Configuracion Config = Configuracion.getInstancia();

	public void addPropuesta(String titulo, String descripcion, byte[] imagen, String extImg, String lugar, Calendar fecha, Integer precioEntrada,
			Integer montoNecesario, List<Retorno> retornos, Categoria categoria, Proponente proponente) throws PropuestaRepetidaException {
		
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		
		Propuesta propu = manProp.getPropuestaPorTitulo(titulo);
		if (propu != null) {
			throw new PropuestaRepetidaException("Ese titulo para la propuesta ya existe");
		}
		
		Propuesta prop;
		 
		if ((imagen != null) && (imagen.length != 0)) {
			
			String pathImagenes = Config.getPathImagenes();

			String destinoArchivo = pathImagenes + titulo + extImg;
			
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
			String nomImg = titulo + extImg;
			prop = new Propuesta(titulo, descripcion, nomImg , lugar, fecha, precioEntrada, montoNecesario, retornos,  categoria, proponente);
		} else {
			prop = new Propuesta(titulo, descripcion, null, lugar, fecha, precioEntrada, montoNecesario, retornos,  categoria, proponente);
		}
		
		manProp.addPropuesta(prop);
		if (proponente != null)
			proponente.agregarPropuesta(prop);
	}	 
		
	public boolean tituloLibre(String titulo) {
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		Propuesta newProp = manProp.getPropuestaPorTitulo(titulo);
		if (newProp == null)
			return true;
		else
			return false;
	}
	
	
	public Map<String, Propuesta> getPropuestasTitulo(){
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		Map<String, Propuesta> propuestas = manProp.getPropuestasTitulo();
		return propuestas;
	}

	public List<Propuesta> getPropuestas() {
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		Map<String, Propuesta> propuestas = manProp.getPropuestasTitulo();		
		List<Propuesta> lstProp = new ArrayList<Propuesta>(propuestas.values());		
		return lstProp;
	}
	
	public Propuesta seleccionarPropuesta(String titulo) {
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		Propuesta newProp = manProp.getPropuestaPorTitulo(titulo);
		if (newProp != null)
			return newProp;
		else
			return null;
	}

	public void modificarPropuesta(Propuesta newProp, String titulo, String descripcion, String lugar, Calendar fecha, Integer montoN, Integer montoA,
			Integer precio, File foto, Categoria categoria) {
		Configuracion Config = Configuracion.getInstancia();
		ManejadorPropuesta manProp = ManejadorPropuesta.getInstancia();
		
		String extImg = "";
		String nomImg = "";
		
		//copio la imagen a la carpeta culturarte
		
		if (foto != null) {
			String absolutePath = foto.getAbsolutePath(); 
			int index= absolutePath.lastIndexOf('.');
			if (index > 0) {
				extImg = absolutePath.substring(index+1);
			}
			String origenArchivo = foto.getAbsolutePath();
			String pathImagenes = Config.getPathImagenes(); 
			String destinoArchivo = pathImagenes + (newProp.getTitulo()) + extImg;
			copiarArchivo(origenArchivo, destinoArchivo);
			nomImg = titulo + extImg;	
		}
		Propuesta prop = manProp.getPropuestaPorTitulo(newProp.getTitulo());
		if (prop != null) {
			prop.setTitulo(titulo);
			prop.setDescripcion(descripcion);
			prop.setLugar(lugar); 
			prop.setFechaRealizacion(fecha);
			prop.setMontoNecesario(montoN); 
			prop.setMontoActual(montoA);
			prop.setPrecioEntrada(precio);
			prop.setImagen(nomImg);
			prop.setCategoria(categoria);
		}
		
	}
 

/*	public List<Propuesta> listarPropuestaPorEstado(String estado) {
		// TODO Auto-generated method stub 
		return null;
	}*/
	

	public Map<String, Colaborador> obtenerColaboradores(String titulo) {
		Map<String, Propuesta> propuestas = getPropuestasTitulo();
		Propuesta newProp = propuestas.get(titulo);
		if (newProp != null) {
			return newProp.getColaboradores();
		}else
			return null;	 
	}
	
	public Propuesta obtenerPopuesta(String titulo) {
		Map<String, Propuesta> propuestas = getPropuestasTitulo();
		Propuesta newProp = propuestas.get(titulo);
		return newProp; 
		
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
	
	
	public void modificarEstado(String titulo, EstadoPropuesta estado){
		
		FechaCambio fcActual = new FechaCambio(Calendar.getInstance(), estado);
		Propuesta prop = seleccionarPropuesta(titulo);
		if (prop != null) {
		FechaCambio fcPasada = prop.getEstado();
		prop.agregarEstadoAnterior(fcPasada);
		prop.setEstado(fcActual);
		}

	}
	
	public void cambiarEstadoProp(Propuesta pro) {
		 long dias = Configuracion.daysBetween(pro.getEstado().getFecha(), Calendar.getInstance());
	     if (dias >= 30){
	    	 if(pro.getEstado().getEstado() == EstadoPropuesta.Publicada){
	    		 pro.agregarEstadoAnterior(pro.getEstado());
	    		 FechaCambio nuevo = new FechaCambio(Calendar.getInstance(), EstadoPropuesta.NoFinanciada);
	    		 pro.setEstado(nuevo);
	    	 }else if(pro.getEstado().getEstado() == EstadoPropuesta.EnFinanciacion){
	    		 if (pro.getMontoActual() >= pro.getMontoNecesario()){
	    			 pro.agregarEstadoAnterior(pro.getEstado());
	        		 FechaCambio nuevo = new FechaCambio(Calendar.getInstance(), EstadoPropuesta.Financiada);
	        		 pro.setEstado(nuevo);
	    		 }else{
	    			 pro.agregarEstadoAnterior(pro.getEstado());
	        		 FechaCambio nuevo = new FechaCambio(Calendar.getInstance(), EstadoPropuesta.NoFinanciada);
	        		 pro.setEstado(nuevo);
	    		 }
	    	 }
	     }

	}
	
	public void eliminarPropuesta(Propuesta prop ) {
		ManejadorPropuesta manPropuesta = ManejadorPropuesta.getInstancia();
		ManejadorColaboracion manColaboracion = ManejadorColaboracion.getInstancia();
		
		//se elimina la propuesta de aquellos usuarios que la tienen como favorita
		if (prop.getSeguidores() != null) {
			Map<String, Usuario> seguidoresProp = prop.getSeguidores();
			List<Usuario> listaSeguidores = new ArrayList<Usuario>(seguidoresProp.values());
			Iterator<Usuario> iter = listaSeguidores.iterator();
			while (iter.hasNext()) {
				Usuario apuntado = iter.next();
				String titulo = prop.getTitulo();
				apuntado.eliminarFavorita(titulo);
			}
		}
		
		//se eliminan las colaboraciones, tanto de la propuesta como de los colaboradores y del manejador
		if (prop.getColaboraciones() != null) {
			Map<Integer, Colaboracion> colaboracionesProp = prop.getColaboraciones();
			List<Colaboracion> listaColaboraciones = new ArrayList<Colaboracion>(colaboracionesProp.values());
			Iterator<Colaboracion> iterColab = listaColaboraciones.iterator();
			
			Map<String, Colaborador> colaboradores = prop.getColaboradores();
			List<Colaborador> listaColaboradores = new ArrayList<Colaborador>(colaboradores.values());
			Iterator<Colaborador> iterColaborador;
			
			while (iterColab.hasNext()) {
				Colaboracion colab = iterColab.next();
				iterColaborador = listaColaboradores.iterator();
				while (iterColaborador.hasNext()) {
					Colaborador colaboradorProp = iterColaborador.next();
					colaboradorProp.getColaboraciones().remove(colab.getId());
				}			
				manColaboracion.getColaboraciones().remove(colab.getId());	
			}
		}
			/*	
		Map<String, Colaborador> colaboradoresSys = manColaborador.getColaboradoresNick();
		List<Colaborador> listaColaboradoresSys = new ArrayList<Colaborador>(colaboradoresSys.values());
		Iterator<Colaborador> iterColaboradorSys = listaColaboradoresSys.iterator();
		while (iterColaboradorSys.hasNext()) {
			Colaborador colaboradorApuntando = iterColaboradorSys.next();
			colaboradorApuntando.getFavoritas().remove(prop.getTitulo());
		}
		
		Map<String, Proponente> proponentesSys = manProponente.getProponentesNick();
		List<Proponente> listaProponentesSys = new ArrayList<Proponente>(proponentesSys.values());
		Iterator<Proponente> iterProponenteSys = listaProponentesSys.iterator();
		while (iterProponenteSys.hasNext()) {
			Proponente proponenteApuntando = iterProponenteSys.next();
			proponenteApuntando.getFavoritas().remove(prop.getTitulo());
		}*/
		
		//se elimina la propuesta del manejador
		manPropuesta.getPropuestasTitulo().remove(prop.getTitulo());
	}
}
