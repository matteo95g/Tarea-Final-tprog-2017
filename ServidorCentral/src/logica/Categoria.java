package logica;

import java.util.LinkedList;
import java.util.List;

public class Categoria {
	
	private String nombre;
	private Categoria padre;
	private List<Categoria> hijas;
	
	public Categoria(String nombre) {
		this.nombre = nombre;
		padre = null;
		hijas = null;
	}
	
	public Categoria(String nombre, Categoria padre) {
		this.nombre = nombre;
		this.padre = padre;
		hijas = null;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Categoria getPadre() {
		return padre;
	}
	public void setPadre(Categoria padre) {
		this.padre = padre;
	}
	
	public List<Categoria> getHijas() {
		return hijas;
	}
	public void setHijas(List<Categoria> hijas) {
		this.hijas = hijas;
	}
	
	public void addHija(Categoria hija) {
		if (hijas == null) {
			hijas = new LinkedList<Categoria>();
			hijas.add(hija);
		} else {
			hijas.add(hija);
		}
	}
	

	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false; 
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (hijas == null) {
			if (other.hijas != null)
				return false;
		} else if (!hijas.equals(other.hijas))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (padre == null) {
			if (other.padre != null)
				return false;
		} else if (!padre.equals(other.padre))
			return false;
		return true;
	}

	

}
