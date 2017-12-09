package logica;

import java.util.Calendar;

public class Comentario {
	private Integer idComentario = 0;
	private String comentario;
	private Calendar fecha;
/*	Propuesta propuesta;*/
	private Colaborador colaborador;
	
	
	public Comentario(String coment, Colaborador colab) {
		this.comentario = coment;
		this.idComentario = idComentario++;
		this.fecha = Calendar.getInstance();
		this.colaborador = colab;
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
/*	public Propuesta getPropuesta() {
		return propuesta;
	}
	public void setPropuesta(Propuesta propuesta) {
		this.propuesta = propuesta;
	}*/
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public Integer getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}
	
}
