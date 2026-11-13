package paag2.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Zelaien zerrenda. XMLren erro elementua da: {@code <zelaiak>}.
 */
@XmlRootElement(name = "zelaiak")
@XmlAccessorType(XmlAccessType.FIELD)
public class Zelaiak {

    // XMLn: <zelaia> elementu bat zelai bakoitzeko.
    // JSONen: "zelaiak" array-a (getter-aren izenetik hartzen du).
    @XmlElement(name = "zelaia")
    private List<Zelaia> zelaiak = new ArrayList<>();

    public Zelaiak() {
    }

    public List<Zelaia> getZelaiak() {
        return zelaiak;
    }

    public void setZelaiak(List<Zelaia> zelaiak) {
        this.zelaiak = zelaiak;
    }

    /**
     * Izen bereko zelairik dagoen egiaztatzen du (maiuskulak/minuskulak kontuan hartu gabe).
     *
     * @param izena bilatu nahi den zelaiaren izena
     * @return true izen hori duen zelai bat badago
     */
    public boolean badago(String izena) {
        for (Zelaia zelaia : zelaiak) {
            if (zelaia.getIzena().equalsIgnoreCase(izena.trim())) {
                return true;
            }
        }
        return false;
    }

    public void gehitu(Zelaia zelaia) {
        zelaiak.add(zelaia);
    }
}
