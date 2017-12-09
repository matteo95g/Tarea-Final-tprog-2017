
package publicador;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtColaboracion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtColaboracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ident" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fechaColaboracion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="retornoElegido" type="{http://publicador/}retorno" minOccurs="0"/>
 *         &lt;element name="propuesta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paga" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pago" type="{http://publicador/}dtPago" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtColaboracion", propOrder = {
    "ident",
    "monto",
    "fechaColaboracion",
    "retornoElegido",
    "propuesta",
    "usuario",
    "paga",
    "pago"
})
public class DtColaboracion {

    protected Integer ident;
    protected Integer monto;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaColaboracion;
    @XmlSchemaType(name = "string")
    protected Retorno retornoElegido;
    protected String propuesta;
    protected String usuario;
    protected boolean paga;
    protected DtPago pago;

    /**
     * Gets the value of the ident property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdent() {
        return ident;
    }

    /**
     * Sets the value of the ident property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdent(Integer value) {
        this.ident = value;
    }

    /**
     * Gets the value of the monto property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonto() {
        return monto;
    }

    /**
     * Sets the value of the monto property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonto(Integer value) {
        this.monto = value;
    }

    /**
     * Gets the value of the fechaColaboracion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaColaboracion() {
        return fechaColaboracion;
    }

    /**
     * Sets the value of the fechaColaboracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaColaboracion(XMLGregorianCalendar value) {
        this.fechaColaboracion = value;
    }

    /**
     * Gets the value of the retornoElegido property.
     * 
     * @return
     *     possible object is
     *     {@link Retorno }
     *     
     */
    public Retorno getRetornoElegido() {
        return retornoElegido;
    }

    /**
     * Sets the value of the retornoElegido property.
     * 
     * @param value
     *     allowed object is
     *     {@link Retorno }
     *     
     */
    public void setRetornoElegido(Retorno value) {
        this.retornoElegido = value;
    }

    /**
     * Gets the value of the propuesta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropuesta() {
        return propuesta;
    }

    /**
     * Sets the value of the propuesta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropuesta(String value) {
        this.propuesta = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the paga property.
     * 
     */
    public boolean isPaga() {
        return paga;
    }

    /**
     * Sets the value of the paga property.
     * 
     */
    public void setPaga(boolean value) {
        this.paga = value;
    }

    /**
     * Gets the value of the pago property.
     * 
     * @return
     *     possible object is
     *     {@link DtPago }
     *     
     */
    public DtPago getPago() {
        return pago;
    }

    /**
     * Sets the value of the pago property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPago }
     *     
     */
    public void setPago(DtPago value) {
        this.pago = value;
    }

}
