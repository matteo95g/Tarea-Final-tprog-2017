
package publicador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retorno.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="retorno">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Entradas"/>
 *     &lt;enumeration value="Porcentaje"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "retorno")
@XmlEnum
public enum Retorno {

    @XmlEnumValue("Entradas")
    ENTRADAS("Entradas"),
    @XmlEnumValue("Porcentaje")
    PORCENTAJE("Porcentaje");
    private final String value;

    Retorno(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Retorno fromValue(String v) {
        for (Retorno c: Retorno.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
