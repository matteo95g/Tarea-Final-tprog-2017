package datatypes;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.Categoria;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtCategoria {
	private String nombre;
	private ArrayList<DtCategoria> hijas;
	
	public DtCategoria() {
		
	}
	
	public DtCategoria(String nombre) {
		this.nombre = nombre;
		hijas = null;
	}
	
	public DtCategoria(String nombre, DtCategoria padre) {
		this.nombre = nombre;
		hijas = null;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<DtCategoria> getHijas() {
		return hijas;
	}
	public void setHijas(ArrayList<DtCategoria> hijas) {
		this.hijas = hijas;
	}
	
	public void addHija(DtCategoria hija) {
		if (hijas == null) {
			hijas = new ArrayList<DtCategoria>();
			hijas.add(hija);
		} else {
			hijas.add(hija);
		}
	}
}
