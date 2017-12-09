package logica;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import datatypes.EstadoPropuesta;
import datatypes.Retorno;

public class Propuesta {

	
	private String titulo;
	private String descripcion;
	private String imagen;
	private String lugar;	
	private Calendar fechaRealizacion;
	private Integer precioEntrada;
	private Integer montoNecesario;
	private List<Retorno> retornos;
	private Categoria categoria;
	private Proponente proponente;
	
	private Calendar fechaIngreso;
	private Integer montoActual;
	
	private HashMap<String, Usuario> seguidores;
	private List<FechaCambio> estadosAnteriores;
	private FechaCambio estado;
	private HashMap<String, Colaborador> colaboradores;
	private HashMap<Integer, Colaboracion> colaboraciones;
	private HashMap<String, Comentario> comentarios;

	
	public Propuesta(String titulo, String descripcion, String imagen, String lugar,
			Calendar fecha, Integer precioEntrada, Integer montoNecesario, List<Retorno> retornos,
			Categoria categoria, Proponente proponente) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.lugar = lugar;
		this.fechaRealizacion = fecha;
		this.precioEntrada = precioEntrada;
		this.montoNecesario = montoNecesario; 
		this.retornos = retornos;
		this.categoria = categoria;
		this.proponente = proponente;
		this.fechaIngreso = Calendar.getInstance();
		this.fechaIngreso.set(2, fechaIngreso.get(2) + 1); 
		this.montoActual = 0;
		FechaCambio estado = new FechaCambio(fechaIngreso, EstadoPropuesta.Ingresada);
		this.estado = estado;
		this.estadosAnteriores = new LinkedList<FechaCambio>();
		this.colaboradores = new HashMap<String, Colaborador>();
		this.colaboraciones = new HashMap<Integer, Colaboracion>();
		this.comentarios = new HashMap<String, Comentario>();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public Calendar getFechaRealizacion() {
		return fechaRealizacion;
	}
	public void setFechaRealizacion(Calendar fecha) {
		this.fechaRealizacion = fecha;
	}
	
	public Integer getPrecioEntrada() {
		return precioEntrada;
	}
	public void setPrecioEntrada(int precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	
	public Integer getMontoNecesario() {
		return montoNecesario;
	}
	public void setMontoNecesario(int montoNecesario) {
		this.montoNecesario = montoNecesario;
	}
	
	public Integer getMontoActual() {
		return montoActual;
	}
	public void setMontoActual(int montoActual) {
		this.montoActual = montoActual;
	}
	
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Calendar fechaPublicacion) {
		this.fechaIngreso = fechaPublicacion;
	}
	
	public HashMap<String, Usuario> getSeguidores() {
		return seguidores;
	}
	public void setSeguidores(HashMap<String, Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	
	public void agregarSeguidor(Usuario usr) {
		if (!seguidores.containsKey(usr.getNickname())) {
			seguidores.put(usr.getNickname(), usr);
		}
	}
	
	public void eliminarSeguidor(String nick) {
		if (seguidores.containsKey(nick)){
			seguidores.remove(nick);
		}
	}
	
	public List<Retorno> getRetornos() {
		return retornos;
	}
	public void setRetornos(List<Retorno> retornos) {
		this.retornos = retornos;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<FechaCambio> getEstadosAnteriores() {
		return estadosAnteriores;
	}
	public void setEstadosAnteriores(List<FechaCambio> estadosAnteriores) {
		this.estadosAnteriores = estadosAnteriores;
	}
	
	public void agregarEstadoAnterior(FechaCambio fecha) {
		estadosAnteriores.add(fecha);
	}
	
	public FechaCambio getEstado() {
		return estado;
	}
	public void setEstado(FechaCambio estado) {
		this.estado = estado;
	}
	
	public Proponente getProponente() {
		return proponente;
	}
	public void setProponente(Proponente proponente) {
		this.proponente = proponente;
	}
	
	public HashMap<String, Colaborador> getColaboradores() {
		return colaboradores;
	}
	
//	public void setColaboradores(Map<String, Colaborador> colaboradores) {
//		this.colaboradores = colaboradores;
//	}
	
	public void agregarColaborador(Colaborador colab) {
			colaboradores.put(colab.getNickname(), colab);
	}
	
	public void eliminarColaborador(String nick) {
		colaboradores.remove(nick);
	}
	
	@Override
    public String toString() {
        return getTitulo();
    }

	public HashMap<Integer, Colaboracion> getColaboraciones() {
		return colaboraciones;
	}

    public void agregarColaboracion(Colaboracion colab){
    	colaboraciones.put(colab.getId(), colab);
    	montoActual= montoActual + colab.getMonto();
    }
	 public void eliminarColaboracion(Colaboracion colab){
		 colaboraciones.remove(colab.getId());
		 montoActual = montoActual + colab.getMonto();		 
	 }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Propuesta other = (Propuesta) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (colaboraciones == null) {
			if (other.colaboraciones != null)
				return false;
		} else if (!colaboraciones.equals(other.colaboraciones))
			return false;
		if (colaboradores == null) {
			if (other.colaboradores != null)
				return false;
		} else if (!colaboradores.equals(other.colaboradores))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estadosAnteriores == null) {
			if (other.estadosAnteriores != null)
				return false;
		} else if (!estadosAnteriores.equals(other.estadosAnteriores))
			return false;
		if (fechaRealizacion == null) {
			if (other.fechaRealizacion != null)
				return false;
		} else if (!fechaRealizacion.equals(other.fechaRealizacion))
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (montoActual == null) {
			if (other.montoActual != null)
				return false;
		} else if (!montoActual.equals(other.montoActual))
			return false;
		if (montoNecesario == null) {
			if (other.montoNecesario != null)
				return false;
		} else if (!montoNecesario.equals(other.montoNecesario))
			return false;
		if (precioEntrada == null) {
			if (other.precioEntrada != null)
				return false;
		} else if (!precioEntrada.equals(other.precioEntrada))
			return false;
		if (proponente == null) {
			if (other.proponente != null)
				return false;
		} else if (!proponente.equals(other.proponente))
			return false;
		if (retornos == null) {
			if (other.retornos != null)
				return false;
		} else if (!retornos.equals(other.retornos))
			return false;
		if (seguidores == null) {
			if (other.seguidores != null)
				return false; 
		} else if (!seguidores.equals(other.seguidores))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	public HashMap<String, Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(HashMap<String, Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void addComentario(String comentario, Colaborador colab) {
		Comentario coment = new Comentario(comentario, colab);
		getComentarios().put(colab.getNickname(), coment);
		
	}		  

}
