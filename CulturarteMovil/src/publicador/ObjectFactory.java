
package publicador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicador package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UsuarioRepetidoException_QNAME = new QName("http://publicador/", "UsuarioRepetidoException");
    private final static QName _UsuarioYaColaboraException_QNAME = new QName("http://publicador/", "UsuarioYaColaboraException");
    private final static QName _UsuarioYaEsSeguidorException_QNAME = new QName("http://publicador/", "UsuarioYaEsSeguidorException");
    private final static QName _CategoriaRepetidaException_QNAME = new QName("http://publicador/", "CategoriaRepetidaException");
    private final static QName _IOException_QNAME = new QName("http://publicador/", "IOException");
    private final static QName _PropuestaRepetidaException_QNAME = new QName("http://publicador/", "PropuestaRepetidaException");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://publicador/", "UsuarioNoExisteException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicador
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link PropuestaRepetidaException }
     * 
     */
    public PropuestaRepetidaException createPropuestaRepetidaException() {
        return new PropuestaRepetidaException();
    }

    /**
     * Create an instance of {@link CategoriaRepetidaException }
     * 
     */
    public CategoriaRepetidaException createCategoriaRepetidaException() {
        return new CategoriaRepetidaException();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link UsuarioYaColaboraException }
     * 
     */
    public UsuarioYaColaboraException createUsuarioYaColaboraException() {
        return new UsuarioYaColaboraException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoException }
     * 
     */
    public UsuarioRepetidoException createUsuarioRepetidoException() {
        return new UsuarioRepetidoException();
    }

    /**
     * Create an instance of {@link UsuarioYaEsSeguidorException }
     * 
     */
    public UsuarioYaEsSeguidorException createUsuarioYaEsSeguidorException() {
        return new UsuarioYaEsSeguidorException();
    }

    /**
     * Create an instance of {@link DtPago }
     * 
     */
    public DtPago createDtPago() {
        return new DtPago();
    }

    /**
     * Create an instance of {@link DtPropuesta }
     * 
     */
    public DtPropuesta createDtPropuesta() {
        return new DtPropuesta();
    }

    /**
     * Create an instance of {@link DtCategoria }
     * 
     */
    public DtCategoria createDtCategoria() {
        return new DtCategoria();
    }

    /**
     * Create an instance of {@link Categoria }
     * 
     */
    public Categoria createCategoria() {
        return new Categoria();
    }

    /**
     * Create an instance of {@link DtColecciones }
     * 
     */
    public DtColecciones createDtColecciones() {
        return new DtColecciones();
    }

    /**
     * Create an instance of {@link DtColaborador }
     * 
     */
    public DtColaborador createDtColaborador() {
        return new DtColaborador();
    }

    /**
     * Create an instance of {@link DtTransferencia }
     * 
     */
    public DtTransferencia createDtTransferencia() {
        return new DtTransferencia();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtTarjeta }
     * 
     */
    public DtTarjeta createDtTarjeta() {
        return new DtTarjeta();
    }

    /**
     * Create an instance of {@link DtComentario }
     * 
     */
    public DtComentario createDtComentario() {
        return new DtComentario();
    }

    /**
     * Create an instance of {@link DtPaypal }
     * 
     */
    public DtPaypal createDtPaypal() {
        return new DtPaypal();
    }

    /**
     * Create an instance of {@link DtFechaCambio }
     * 
     */
    public DtFechaCambio createDtFechaCambio() {
        return new DtFechaCambio();
    }

    /**
     * Create an instance of {@link DtProponente }
     * 
     */
    public DtProponente createDtProponente() {
        return new DtProponente();
    }

    /**
     * Create an instance of {@link DtColaboracion }
     * 
     */
    public DtColaboracion createDtColaboracion() {
        return new DtColaboracion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "UsuarioRepetidoException")
    public JAXBElement<UsuarioRepetidoException> createUsuarioRepetidoException(UsuarioRepetidoException value) {
        return new JAXBElement<UsuarioRepetidoException>(_UsuarioRepetidoException_QNAME, UsuarioRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaColaboraException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "UsuarioYaColaboraException")
    public JAXBElement<UsuarioYaColaboraException> createUsuarioYaColaboraException(UsuarioYaColaboraException value) {
        return new JAXBElement<UsuarioYaColaboraException>(_UsuarioYaColaboraException_QNAME, UsuarioYaColaboraException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioYaEsSeguidorException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "UsuarioYaEsSeguidorException")
    public JAXBElement<UsuarioYaEsSeguidorException> createUsuarioYaEsSeguidorException(UsuarioYaEsSeguidorException value) {
        return new JAXBElement<UsuarioYaEsSeguidorException>(_UsuarioYaEsSeguidorException_QNAME, UsuarioYaEsSeguidorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "CategoriaRepetidaException")
    public JAXBElement<CategoriaRepetidaException> createCategoriaRepetidaException(CategoriaRepetidaException value) {
        return new JAXBElement<CategoriaRepetidaException>(_CategoriaRepetidaException_QNAME, CategoriaRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PropuestaRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "PropuestaRepetidaException")
    public JAXBElement<PropuestaRepetidaException> createPropuestaRepetidaException(PropuestaRepetidaException value) {
        return new JAXBElement<PropuestaRepetidaException>(_PropuestaRepetidaException_QNAME, PropuestaRepetidaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

}
