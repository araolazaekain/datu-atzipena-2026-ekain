package paag2.util;

import java.util.Scanner;

/**
 * Teklatutik datuak modu seguruan irakurtzeko metodoak.
 * Balio okerra sartuz gero, berriro eskatzen du programa apurtu gabe.
 */
public class Sarrera {

    private final Scanner sc;

    public Sarrera(Scanner sc) {
        this.sc = sc;
    }

    /** Hutsik ez dagoen testu bat irakurtzen du. */
    public String irakurriTestua(String mezua) {
        while (true) {
            System.out.print(mezua);
            String testua = sc.nextLine().trim();
            if (!testua.isEmpty()) {
                return testua;
            }
            System.out.println("Ezin da hutsik utzi.");
        }
    }

    /** [min, max] tarteko zenbaki hamartar bat irakurtzen du. Koma zein puntua onartzen ditu. */
    public double irakurriHamartarra(String mezua, double min, double max) {
        while (true) {
            System.out.print(mezua);
            try {
                double balioa = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                if (balioa >= min && balioa <= max) {
                    return balioa;
                }
                System.out.println("Balioa " + min + " eta " + max + " artean egon behar da.");
            } catch (NumberFormatException e) {
                System.out.println("Zenbaki bat idatzi behar duzu.");
            }
        }
    }

    /** [min, max] tarteko zenbaki oso bat irakurtzen du. */
    public int irakurriOsoa(String mezua, int min, int max) {
        while (true) {
            System.out.print(mezua);
            try {
                int balioa = Integer.parseInt(sc.nextLine().trim());
                if (balioa >= min && balioa <= max) {
                    return balioa;
                }
                System.out.println("Balioa " + min + " eta " + max + " artean egon behar da.");
            } catch (NumberFormatException e) {
                System.out.println("Zenbaki oso bat idatzi behar duzu.");
            }
        }
    }
}
