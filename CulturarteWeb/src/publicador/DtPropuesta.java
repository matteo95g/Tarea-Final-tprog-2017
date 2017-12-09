
package publicador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtPropuesta complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtPropuesta">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaRealizacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="precioEntrada" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="montoNecesario" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="retornos" type="{http://publicador/}retorno" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="categoria" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proponente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaIngreso" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="montoActual" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="seguidores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="estadosAnteriores" type="{http://publicador/}dtFechaCambio" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://publicador/}dtFechaCambio" minOccurs="0"/>
 *         &lt;element name="colaboradores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="colaboraciones" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comentarios" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPropuesta", propOrder = {
    "titulo",
    "descripcion",
    "imagen",
    "lugar",
    "fechaRealizacion",
    "precioEntrada",
    "montoNecesario",
    "retornos",
    "categoria",
    "proponente",
    "fechaIngreso",
    "montoActual",
    "seguidores",
    "estadosAnteriores",
    "estado",
    "colaboradores",
    "colaboraciones",
    "comentarios"
})
public class DtPropuesta {

    protected String titulo;
    protected String descripcion;
    protected String imagen;
    protected String lugar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaRealizacion;
    protected Integer precioEntrada;
    protected Integer montoNecesario;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "string")
    protected List<Retorno> retornos;
    protected String categoria;
    protected String proponente;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaIngreso;
    protected Integer montoActual;
    @XmlElement(nillable = true)
    protected List<String> seguidores;
    @XmlElement(nillable = true)
    protected List<DtFechaCambio> estadosAnteriores;
    protected DtFechaCambio estado;
    @XmlElement(nillable = true)
    protected List<String> colaboradores;
    @XmlElement(nillable = true)
    protected List<Integer> colaboraciones;
    @XmlElement(nillable = true)
    protected List<String> comentarios;

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
    }

    /**
     * Gets the value of the fechaRealizacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaRealizacion() {
        return fechaRealizacion;
    }

    /**
     * Sets the value of the fechaRealizacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaRealizacion(XMLGregorianCalendar value) {
        this.fechaRealizacion = value;
    }

    /**
     * Gets the value of the precioEntrada property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrecioEntrada() {
        return precioEntrada;
    }

    /**
     * Sets the value of the precioEntrada property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrecioEntrada(Integer value) {
        this.precioEntrada = value;
    }

    /**
     * Gets the value of the montoNecesario property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMontoNecesario() {
        return montoNecesario;
    }

    /**
     * Sets the value of the montoNecesario property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMontoNecesario(Integer value) {
        this.montoNecesario = value;
    }

    /**
     * Gets the value of the retornos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the retornos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRetornos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Retorno }
     * 
     * 
     */
    public List<Retorno> getRetornos() {
        if (retornos == null) {
            retornos = new ArrayList<Retorno>();
        }
        return this.retornos;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the proponente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProponente() {
        return proponente;
    }

    /**
     * Sets the value of the proponente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProponente(String value) {
        this.proponente = value;
    }

    /**
     * Gets the value of the fechaIngreso property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Sets the value of the fechaIngreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaIngreso(XMLGregorianCalendar value) {
        this.fechaIngreso = value;
    }

    /**
     * Gets the value of the montoActual property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMontoActual() {
        return montoActual;
    }

    /**
     * Sets the value of the montoActual property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMontoActual(Integer value) {
        this.montoActual = value;
    }

    /**
     * Gets the value of the seguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the seguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSeguidores() {
        if (seguidores == null) {
            seguidores = new ArrayList<String>();
        }
        return this.seguidores;
    }

    /**
     * Gets the value of the estadosAnteriores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estadosAnteriores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstadosAnteriores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtFechaCambio }
     * 
     * 
     */
    public List<DtFechaCambio> getEstadosAnteriores() {
        if (estadosAnteriores == null) {
            estadosAnteriores = new ArrayList<DtFechaCambio>();
        }
        return this.estadosAnteriores;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link DtFechaCambio }
     *     
     */
    public DtFechaCambio getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtFechaCambio }
     *     
     */
    public void setEstado(DtFechaCambio value) {
        this.estado = value;
    }

    /**
     * Gets the value of the colaboradores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colaboradores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColaboradores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getColaboradores() {
        if (colaboradores == null) {
            colaboradores = new ArrayList<String>();
        }
        return this.colaboradores;
    }

    /**
     * Gets the value of the colaboraciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the colaboraciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getColaboraciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getColaboraciones() {
        if (colaboraciones == null) {
            colaboraciones = new ArrayList<Integer>();
        }
        return this.colaboraciones;
    }

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<String>();
        }
        return this.comentarios;
    }

}
