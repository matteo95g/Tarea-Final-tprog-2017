package interfaces;

import controladores.ControladorCategoria;
import controladores.ControladorColaboracion;
import controladores.ControladorPropuesta;
import controladores.ControladorUsuario;
import persistencia.Persistir;

public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorCategoria getIControladorCategoria() {
        return new ControladorCategoria();
    }
    
    public IControladorUsuario getIControladorUsuario() {
    	return new ControladorUsuario();
    }
    
    public IControladorPropuesta getIControladorPropuesta() {
    	return new ControladorPropuesta();
    }
    
    public IControladorColaboracion getIControladorColaboracion() {
    	return new ControladorColaboracion();
    }

    public IPersistencia getIPersistencia(){
    	return new Persistir();
    }
}
