package interfaces;

import java.util.List;


public interface IPersistencia {
	
	
	public List getProponentesPer();
	
	public List getPropuestasPer(String idProp);
	
	public List getColaboracionesPer(String idPropu);
	
	public void cargarCategorias();
	
	public void persistirDatos(String nickUsuario);

}
