package datatypes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPropuesta {
	
	private String titulo;
	private String descripcion;
	private String imagen;
	private String lugar;	
	private Calendar fechaRealizacion;
	private Integer precioEntrada;
	private Integer montoNecesario;
	private List<Retorno> retornos = new ArrayList<Retorno>();
	private String categoria;
	private String proponente;	
	private Calendar fechaIngreso;
	private Integer montoActual;	
	private ArrayList<String> seguidores = new ArrayList<String>();	
	private ArrayList<DtFechaCambio> estadosAnteriores = new ArrayList<DtFechaCambio>();
	private DtFechaCambio estado;	
	private ArrayList<String> colaboradores = new ArrayList<String>();
	private ArrayList<Integer> colaboraciones = new ArrayList<Integer>();
	private ArrayList<String> comentarios = new ArrayList<String>();
	
	public DtPropuesta(){}
	
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
	
	public void setFechaRealizacion(Calendar fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	public Integer getPrecioEntrada() {
		return precioEntrada;
	}
	
	public void setPrecioEntrada(Integer precioEntrada) {
		this.precioEntrada = precioEntrada;
	}
	
	public Integer getMontoNecesario() {
		return montoNecesario;
	}
	
	public void setMontoNecesario(Integer montoNecesario) {
		this.montoNecesario = montoNecesario;
	}
	
	public List<Retorno> getRetornos() {
		return retornos;
	}
	
	public void setRetornos(List<Retorno> retornos) {
		this.retornos = retornos;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getProponente() {
		return proponente;
	}
	
	public void setProponente(String proponente) {
		this.proponente = proponente;
	}
	
	public Calendar getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Calendar fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	public Integer getMontoActual() {
		return montoActual;
	}
	
	public void setMontoActual(Integer montoActual) {
		this.montoActual = montoActual;
	}
	
	public ArrayList<String> getSeguidores() {
		return seguidores;
	}
	
	public void setSeguidores(ArrayList<String> seguidores) {
		this.seguidores = seguidores;
	}
	
	public ArrayList<DtFechaCambio> getEstadosAnteriores() {
		return estadosAnteriores;
	}
	
	public void setEstadosAnteriores(ArrayList<DtFechaCambio> estadosAnteriores) {
		this.estadosAnteriores = estadosAnteriores;
	}
	
	public DtFechaCambio getEstado() {
		return estado;
	}
	
	public void setEstado(DtFechaCambio estado) {
		this.estado = estado;
	}
	
	public ArrayList<String> getColaboradores() {
		return colaboradores;
	}
	
	public void setColaboradores(ArrayList<String> colaboradores) {
		this.colaboradores = colaboradores;
	}
	
	public ArrayList<Integer> getColaboraciones() {
		return colaboraciones;
	}
	
	public void setColaboraciones(ArrayList<Integer> claboraciones) {
		this.colaboraciones = claboraciones;
	}
	
	public ArrayList<String> getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(ArrayList<String> comentarios) {
		this.comentarios = comentarios;
	}

}
