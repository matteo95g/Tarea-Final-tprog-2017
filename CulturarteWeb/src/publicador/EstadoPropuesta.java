
package publicador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoPropuesta.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoPropuesta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Ingresada"/>
 *     &lt;enumeration value="Publicada"/>
 *     &lt;enumeration value="EnFinanciacion"/>
 *     &lt;enumeration value="Financiada"/>
 *     &lt;enumeration value="NoFinanciada"/>
 *     &lt;enumeration value="Cancelada"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "estadoPropuesta")
@XmlEnum
public enum EstadoPropuesta {

    @XmlEnumValue("Ingresada")
    INGRESADA("Ingresada"),
    @XmlEnumValue("Publicada")
    PUBLICADA("Publicada"),
    @XmlEnumValue("EnFinanciacion")
    EN_FINANCIACION("EnFinanciacion"),
    @XmlEnumValue("Financiada")
    FINANCIADA("Financiada"),
    @XmlEnumValue("NoFinanciada")
    NO_FINANCIADA("NoFinanciada"),
    @XmlEnumValue("Cancelada")
    CANCELADA("Cancelada");
    private final String value;

    EstadoPropuesta(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoPropuesta fromValue(String v) {
        for (EstadoPropuesta c: EstadoPropuesta.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
