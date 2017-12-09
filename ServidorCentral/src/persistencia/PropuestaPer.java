package persistencia;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PropuestaPer {
	
	
	@Id
	@GeneratedValue
	private String id;
	private String titulo;
	private String lugar;
	private Integer montoNecesario;
	@Temporal(TemporalType.DATE)
	private Calendar fechaRealizacion;
	private Integer precioEntrada;

	private String tiposRetorno;
	private String idProponente;
	@OneToOne(cascade=CascadeType.PERSIST)
	private CategoriaPer categoria;
	@OneToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Collection<ColaboracionPer> colaboraciones;
	
	
	
	public String getIdProponente() {
		return idProponente;
	}
	public void setIdProponente(String idProponente) {
		this.idProponente = idProponente;
	}
	public CategoriaPer getCategoria() {
		return categoria;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public void setCategoria(CategoriaPer categoria) {
		this.categoria = categoria;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMontoNecesario() {
		return montoNecesario;
	}
	public void setMontoNecesario(Integer montoNecesario) {
		this.montoNecesario = montoNecesario;
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

	public Collection<ColaboracionPer> getColaboraciones() {
		return colaboraciones;
	}
	public void setColaboraciones(Collection<ColaboracionPer> colaboraciones) {
		this.colaboraciones = colaboraciones;
	}
	public String getTiposRetorno() {
		return tiposRetorno;
	}
	public void setTiposRetorno(String tiposRetorno) {
		this.tiposRetorno = tiposRetorno;
	}
	@Override
	public String toString() {
		return "Propuesta: "+ titulo;
	}
	
	
	
	

}
