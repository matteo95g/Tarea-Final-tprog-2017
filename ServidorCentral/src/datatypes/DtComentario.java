package datatypes;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
	public class DtComentario {
	
	private Integer idComentario;
	private String comentario;
	private Calendar fecha;
	private String colaborador;
	
	public DtComentario() {
		
	}
	
	public DtComentario(Integer idComentario,String coment, Calendar fecha, String colab) {
		this.comentario = coment;
		this.idComentario = idComentario;
		this.fecha = fecha;
		this.colaborador = colab;
	}
	
	public Integer getIdComentario() {
		return idComentario;
	}
	
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Calendar getFecha() {
		return fecha;
	}
	
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public String getColaborador() {
		return colaborador;
	}
	
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

}
