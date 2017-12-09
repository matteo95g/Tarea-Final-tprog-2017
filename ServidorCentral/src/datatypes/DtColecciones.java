package datatypes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtColecciones {
	private List<DtPropuesta> propuestas = new ArrayList<DtPropuesta>();
	private List<DtProponente> proponentes = new ArrayList<DtProponente>();
	private List<DtColaborador> colaboradores = new ArrayList<DtColaborador>();
	private List<DtUsuario> usuarios = new ArrayList<DtUsuario>();
	private List<DtComentario> comentarios = new ArrayList<DtComentario>();
	private List<DtCategoria> categorias = new ArrayList<DtCategoria>();
	private List<DtColaboracion> colaboraciones = new ArrayList<DtColaboracion>();
	private List<Retorno> retornos = new ArrayList<Retorno>();
	
	private List<String> lstString = new ArrayList<String>();
	private List<Integer> lstInteger = new ArrayList<Integer>();
	
	public DtColecciones() {
		
	}

	public List<DtPropuesta> getPropuestas() {
		return propuestas;
	}

	public void setPropuestas(List<DtPropuesta> propuestas) {
		this.propuestas = propuestas;
	}

	public List<DtProponente> getProponentes() {
		return proponentes;
	}

	public void setProponentes(List<DtProponente> proponentes) {
		this.proponentes = proponentes;
	}

	public List<DtColaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<DtColaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<DtUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<DtUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<DtComentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<DtComentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<DtCategoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<DtCategoria> categorias) {
		this.categorias = categorias;
	}

	public List<DtColaboracion> getColaboraciones() {
		return colaboraciones;
	}

	public void setColaboraciones(List<DtColaboracion> colaboraciones) {
		this.colaboraciones = colaboraciones;
	}

	public List<Retorno> getRetornos() {
		return retornos;
	}

	public void setRetornos(List<Retorno> retornos) {
		this.retornos = retornos;
	}

	public List<String> getLstString() {
		return lstString;
	}

	public void setLstString(List<String> lstString) {
		this.lstString = lstString;
	}

	public List<Integer> getLstInteger() {
		return lstInteger;
	}

	public void setLstInteger(List<Integer> lstInteger) {
		this.lstInteger = lstInteger;
	}	

}
