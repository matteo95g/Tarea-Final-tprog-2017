package datatypes;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtColaborador extends DtUsuario {
	private ArrayList<Integer> colaboraciones;
	//private ArrayList<DtComentario> comentarios;
	
	public DtColaborador() {
		
	}
	
	public DtColaborador(String nickname, String nombre, String apellido, String correoElectronico, Calendar fechaNacimiento, String contra) {
		super(nickname, nombre, apellido, correoElectronico, fechaNacimiento, contra);
		this.colaboraciones = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getColaboraciones() {
		return colaboraciones;
	}
	public void setColaboraciones(ArrayList<Integer> colaboraciones) {
		this.colaboraciones = colaboraciones;
	}
}
