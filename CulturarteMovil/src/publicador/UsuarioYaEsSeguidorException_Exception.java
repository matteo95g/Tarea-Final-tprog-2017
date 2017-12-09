
package publicador;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "UsuarioYaEsSeguidorException", targetNamespace = "http://publicador/")
public class UsuarioYaEsSeguidorException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private UsuarioYaEsSeguidorException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public UsuarioYaEsSeguidorException_Exception(String message, UsuarioYaEsSeguidorException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public UsuarioYaEsSeguidorException_Exception(String message, UsuarioYaEsSeguidorException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicador.UsuarioYaEsSeguidorException
     */
    public UsuarioYaEsSeguidorException getFaultInfo() {
        return faultInfo;
    }

}