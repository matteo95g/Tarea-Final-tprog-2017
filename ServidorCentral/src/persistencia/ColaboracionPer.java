package persistencia;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ColaboracionPer {
	
	@Id
	@GeneratedValue
	private String id;
	private Integer monto;
	private String nickColaborador;
	@Temporal(TemporalType.DATE)
	private Calendar fechaColaboracion;
    private String tipoRetorno;
    private String idPropuesta;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getMonto() {
		return monto;
	}
	public void setMonto(Integer monto) {
		this.monto = monto;
	}
	public String getNickColaborador() {
		return nickColaborador;
	}
	public void setNickColaborador(String nickColaborador) {
		this.nickColaborador = nickColaborador;
	}
	public Calendar getFechaColaboracion() {
		return fechaColaboracion;
	}
	public void setFechaColaboracion(Calendar fechaColaboracion) {
		this.fechaColaboracion = fechaColaboracion;
	}
	public String getTipoRetorno() {
		return tipoRetorno;
	}
	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}
	public String getIdPropuesta() {
		return idPropuesta;
	}
	public void setIdPropuesta(String idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	@Override
	public String toString() {
		return "Colaborador: " + nickColaborador + " Monto: " + monto + ", Retorno: "
				+ tipoRetorno ;
	}
    
    
    
}
