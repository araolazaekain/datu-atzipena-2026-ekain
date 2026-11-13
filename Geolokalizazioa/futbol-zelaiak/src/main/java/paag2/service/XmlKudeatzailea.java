package paag2.service;

import java.io.File;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import paag2.model.Zelaiak;

/**
 * XML fitxategia irakurri eta idazten du JAXB erabilita.
 */
public class XmlKudeatzailea {

    private final String fitxategia;

    public XmlKudeatzailea(String fitxategia) {
        this.fitxategia = fitxategia;
    }

    /** Fitxategia existitzen ez bada, hutsik sortzen du. */
    public void sortuEzBadago() {
        if (!new File(fitxategia).exists()) {
            idatzi(new Zelaiak());
        }
    }

    /**
     * @return fitxategiko zelaiak; errorea badago, zerrenda hutsa
     */
    public Zelaiak irakurri() {
        try {
            JAXBContext context = JAXBContext.newInstance(Zelaiak.class);
            return (Zelaiak) context.createUnmarshaller().unmarshal(new File(fitxategia));
        } catch (JAXBException e) {
            System.out.println("Errorea " + fitxategia + " irakurtzean: " + e.getMessage());
            return new Zelaiak();
        }
    }

    /**
     * @return true ondo idatzi bada
     */
    public boolean idatzi(Zelaiak zelaiak) {
        try {
            JAXBContext context = JAXBContext.newInstance(Zelaiak.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(zelaiak, new File(fitxategia));
            return true;
        } catch (JAXBException e) {
            System.out.println("Errorea " + fitxategia + " idaztean: " + e.getMessage());
            return false;
        }
    }
}
