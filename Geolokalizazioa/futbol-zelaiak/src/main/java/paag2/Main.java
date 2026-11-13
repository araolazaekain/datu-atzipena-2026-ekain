package paag2;

import java.util.List;
import java.util.Scanner;

import paag2.model.Zelaia;
import paag2.model.Zelaiak;
import paag2.service.DistantziaKalkulatzailea;
import paag2.service.JsonKudeatzailea;
import paag2.service.XmlKudeatzailea;
import paag2.util.Sarrera;

/**
 * Futbol zelaien koordenatuak kudeatzeko kontsola aplikazioa (menua).
 */
public class Main {

    private static final String XML_FITXATEGIA = "zelaiak.xml";
    private static final String JSON_FITXATEGIA = "zelaiak.json";

    private static final XmlKudeatzailea xmlKudeatzailea = new XmlKudeatzailea(XML_FITXATEGIA);
    private static final JsonKudeatzailea jsonKudeatzailea = new JsonKudeatzailea();
    private static final DistantziaKalkulatzailea distantziaKalkulatzailea = new DistantziaKalkulatzailea();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sarrera sarrera = new Sarrera(sc);

        xmlKudeatzailea.sortuEzBadago();

        System.out.println("Ongi etorri futbol zelaien koordenatuak gordetzeko menura");

        while (true) {
            System.out.println("-----Aukeratu-----");
            System.out.println("1. Futbol zelai bat gorde");
            System.out.println("2. Futbol zelaiak ikusi");
            System.out.println("3. Kalkulatu distantzia");
            System.out.println("4. JSON-era esportatu");
            System.out.println("5. Irten");
            System.out.print("Aukera: ");

            String aukera = sc.nextLine().trim();

            switch (aukera) {
                case "1":
                    zelaiaGorde(sarrera);
                    break;
                case "2":
                    zelaiakIkusi();
                    break;
                case "3":
                    distantziaKalkulatu(sarrera);
                    break;
                case "4":
                    jsonEsportatu();
                    break;
                case "5":
                    System.out.println("Agur!");
                    sc.close();
                    return;
                default:
                    System.out.println("Aukera ez da zuzena, saiatu berriro.");
                    break;
            }
        }
    }

    private static void zelaiaGorde(Sarrera sarrera) {
        Zelaiak zelaiak = xmlKudeatzailea.irakurri();

        String izena = sarrera.irakurriTestua("Zelaiaren izena: ");

        // Gehitu aurretik, dagoeneko existitzen den egiaztatu
        if (zelaiak.badago(izena)) {
            System.out.println("\"" + izena + "\" zelaia dagoeneko existitzen da. Ez da gorde.");
            return;
        }

        double latitudea = sarrera.irakurriHamartarra("Zelaiaren latitudea: ", -90, 90);
        double longitudea = sarrera.irakurriHamartarra("Zelaiaren longitudea: ", -180, 180);

        zelaiak.gehitu(new Zelaia(izena, latitudea, longitudea));

        if (xmlKudeatzailea.idatzi(zelaiak)) {
            System.out.println("Zelaia gorde da.");
        }
    }

    private static void zelaiakIkusi() {
        List<Zelaia> zerrenda = xmlKudeatzailea.irakurri().getZelaiak();

        if (zerrenda.isEmpty()) {
            System.out.println("Ez dago zelairik gordeta.");
            return;
        }

        System.out.println("Zelaia,Latitudea,Longitudea");
        for (Zelaia zelaia : zerrenda) {
            System.out.println(zelaia);
        }
    }

    private static void distantziaKalkulatu(Sarrera sarrera) {
        List<Zelaia> zerrenda = xmlKudeatzailea.irakurri().getZelaiak();

        if (zerrenda.size() < 2) {
            System.out.println("Gutxienez bi zelai behar dira distantzia kalkulatzeko.");
            return;
        }

        for (int i = 0; i < zerrenda.size(); i++) {
            System.out.println((i + 1) + ". " + zerrenda.get(i).getIzena());
        }

        int aukera1 = sarrera.irakurriOsoa("Aukeratu lehenengo futbol zelaia: ", 1, zerrenda.size());
        int aukera2 = sarrera.irakurriOsoa("Aukeratu bigarren futbol zelaia: ", 1, zerrenda.size());

        Zelaia zelaia1 = zerrenda.get(aukera1 - 1);
        Zelaia zelaia2 = zerrenda.get(aukera2 - 1);

        double distantzia = distantziaKalkulatzailea.kalkulatu(zelaia1, zelaia2);

        System.out.println();
        System.out.println("Zelaia 1: " + zelaia1.getIzena());
        System.out.println("Zelaia 2: " + zelaia2.getIzena());
        System.out.printf("Distantzia: %.2f km%n", distantzia);
    }

    private static void jsonEsportatu() {
        Zelaiak zelaiak = xmlKudeatzailea.irakurri();

        if (jsonKudeatzailea.esportatu(zelaiak, JSON_FITXATEGIA)) {
            System.out.println("Datuak " + JSON_FITXATEGIA + " fitxategira esportatu dira.");
        }
    }
}
