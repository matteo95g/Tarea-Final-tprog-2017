
package publicador;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtColecciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtColecciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="propuestas" type="{http://publicador/}dtPropuesta" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="proponentes" type="{http://publicador/}dtProponente" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="colaboradores" type="{http://publicador/}dtColaborador" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="usuarios" type="{http://publicador/}dtUsuario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="comentarios" type="{http://publicador/}dtComentario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="categorias" type="{http://publicador/}dtCategoria" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="colaboraciones" type="{http://publicador/}dtColaboracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="retornos" type="{http://publicador/}retorno" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lstString" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lstInteger" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtColecciones", propOrder = {
    "propuestas",
    "proponentes",
    "colaboradores",
    "usuarios",
    "comentarios",
    "categorias",
    "colaboraciones",
    "retornos",
    "lstString",
    "lstInteger"
})
public class DtColecciones {

    @XmlElement(nillable = true)
    protected List<DtPropuesta> propuestas;
    @XmlElement(nillable = true)
    protected List<DtProponente> proponentes;
    @XmlElement(nillable = true)
    protected List<DtColaborador> colaboradores;
    @XmlElement(nillable = true)
    protected List<DtUsuario> usuarios;
    @XmlElement(nillable = true)
    protected List<DtComentario> comentarios;
    @XmlElement(nillable = true)
    protected List<DtCategoria> categorias;
    @XmlElement(nillable = true)
    protected List<DtColaboracion> colaboraciones;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "string")
    protected List<Retorno> retornos;
    @XmlElement(nillable = true)
    protected List<String> lstString;
    @XmlElement(nillable = true)
    protected List<Integer> lstInteger;

    /**
     * Gets the value of the propuestas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the propuestas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPropuestas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPropuesta }
     * 
     * 
     */
    public List<DtPropuesta> getPropuestas() {
        if (propuestas == null) {
            propuestas = new ArrayList<DtPropuesta>();
        }
        return this.propuestas;
    }

    /**
     * Gets the value of the proponentes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proponentes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProponentes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtProponente }
     * 
     * 
     */
    public List<DtProponente> getProponentes() {
        if (proponentes == null) {
            proponentes = new ArrayList<DtProponente>();
        }
        return this.proponentes;
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
     * {@link DtColaborador }
     * 
     * 
     */
    public List<DtColaborador> getColaboradores() {
        if (colaboradores == null) {
            colaboradores = new ArrayList<DtColaborador>();
        }
        return this.colaboradores;
    }

    /**
     * Gets the value of the usuarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usuarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsuarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     */
    public List<DtUsuario> getUsuarios() {
        if (usuarios == null) {
            usuarios = new ArrayList<DtUsuario>();
        }
        return this.usuarios;
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
     * {@link DtComentario }
     * 
     * 
     */
    public List<DtComentario> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<DtComentario>();
        }
        return this.comentarios;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCategoria }
     * 
     * 
     */
    public List<DtCategoria> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<DtCategoria>();
        }
        return this.categorias;
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
     * {@link DtColaboracion }
     * 
     * 
     */
    public List<DtColaboracion> getColaboraciones() {
        if (colaboraciones == null) {
            colaboraciones = new ArrayList<DtColaboracion>();
        }
        return this.colaboraciones;
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
     * Gets the value of the lstString property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lstString property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLstString().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getLstString() {
        if (lstString == null) {
            lstString = new ArrayList<String>();
        }
        return this.lstString;
    }

    /**
     * Gets the value of the lstInteger property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lstInteger property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLstInteger().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getLstInteger() {
        if (lstInteger == null) {
            lstInteger = new ArrayList<Integer>();
        }
        return this.lstInteger;
    }

}
