package paag2.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

/**
 * Futbol zelai bat: izena eta koordenatuak.
 * JAXBk eremuak erabiltzen ditu (XML); JSON-Bk getter/setter-ak (JSON).
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Zelaia {

    @XmlElement(name = "izena")
    private String izena;

    @XmlElement(name = "latitudea")
    private double latitudea;

    @XmlElement(name = "longitudea")
    private double longitudea;

    /** JAXBk eta JSON-Bk eraikitzaile hutsa behar dute. */
    public Zelaia() {
    }

    public Zelaia(String izena, double latitudea, double longitudea) {
        this.izena = izena;
        this.latitudea = latitudea;
        this.longitudea = longitudea;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public double getLatitudea() {
        return latitudea;
    }

    public void setLatitudea(double latitudea) {
        this.latitudea = latitudea;
    }

    public double getLongitudea() {
        return longitudea;
    }

    public void setLongitudea(double longitudea) {
        this.longitudea = longitudea;
    }

    @Override
    public String toString() {
        return izena + "," + latitudea + "," + longitudea;
    }
}
