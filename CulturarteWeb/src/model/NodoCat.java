package model;

import publicador.DtCategoria;
import java.util.List;
import java.util.LinkedList;

public class NodoCat {
	DtCategoria cat;
	List<NodoCat> hijas;
	
	public NodoCat(DtCategoria cat) {
		this.cat = cat;
		hijas = new LinkedList<NodoCat>();
	}
	public DtCategoria getCat() {
		return cat;
	}
	public void setCat(DtCategoria cat) {
		this.cat = cat;
	}
	public List<NodoCat> getHijas() {
		return hijas;
	}
	public void setHijas(List<NodoCat> hijas) {
		this.hijas = hijas;
	}
	
	

}

