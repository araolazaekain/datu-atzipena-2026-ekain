package paag2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class AnimaliaCreator {
    public static void create() {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Non dago 'karpeta_berriak'? (path absolutoa): ");
        String oinarriaStr = sc.nextLine().trim();
 
        System.out.print("Zer zoaz deskribatzera? (adib. ugaztunak, arrainak): ");
        String kategoria = sc.nextLine().trim();
 
        System.out.print("Zein? ");
        String izena = sc.nextLine().trim();
 
        System.out.print("Nolakoa da? ");
        String edukia = sc.nextLine();
 
        // karpeta_berriak/animaliak/<kategoria>/
        Path helburuKarpeta = Path.of(oinarriaStr, "karpeta_berriak", "animaliak", kategoria);
 
        if (!Files.exists(helburuKarpeta)) {
            System.out.println("Ez da existitzen karpeta hori: " + helburuKarpeta);
            System.out.println("Ziurtatu lehenago 'karpeta_berriak' egitura sortu duzula (FolderStructureCreator).");
            return;
        }
 
        Path fitxategia = helburuKarpeta.resolve(izena + ".txt");
 
        try {
            Files.writeString(fitxategia, edukia);
            System.out.println("Fitxategia sortuta: " + fitxategia);
        } catch (IOException e) {
            System.out.println("Errorea fitxategia sortzean: " + e.getMessage());
        }
    }
}
