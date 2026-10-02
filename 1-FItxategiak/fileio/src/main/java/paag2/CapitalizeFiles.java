package paag2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CapitalizeFiles {
    public static void capitalize() {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Zein karpetatik hasi nahi duzu? (path absolutoa): ");
        String sarrera = sc.nextLine().trim();
 
        Path erroa = Path.of(sarrera);
 
        if (!Files.isDirectory(erroa)) {
            System.out.println("Hori ez da karpeta bat: " + erroa);
            return;
        }
 
        prozesatuKarpeta(erroa);
    }
 
    /**
     * Karpeta bat prozesatzen du: bertako fitxategi guztien izena aldatu,
     * eta aurkitutako azpikarpeta bakoitzarekin errekurtsiboki deitu.
     */
    private static void prozesatuKarpeta(Path karpeta) {
        // glob patroia "*": fitxategi/karpeta guztiak hartzen ditu izenari
        // begiratu gabe. Nahi izanez gero, "*.txt" bezalako patroi bat
        // erabil daiteke mota jakin batzuk soilik iragazteko.
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(karpeta, "*")) {
            for (Path elementua : stream) {
                if (Files.isDirectory(elementua)) {
                    // Azpikarpeta bada, sartu bertan errekurtsiboki.
                    prozesatuKarpeta(elementua);
                } else {
                    berrizendatuMaiuskulaz(elementua);
                }
            }
        } catch (IOException e) {
            System.out.println("Errorea karpeta irakurtzean (" + karpeta + "): " + e.getMessage());
        }
    }
 
    /**
     * Fitxategi baten izenaren lehen karakterea maiuskulaz jartzen du,
     * eta Files.move() erabiliz berrizendatzen du.
     */
    private static void berrizendatuMaiuskulaz(Path fitxategia) {
        String izena = fitxategia.getFileName().toString();
 
        if (izena.isEmpty()) {
            return;
        }
 
        // Lehen karakterea jadanik maiuskulaz badago, ez dago zer eginik.
        char lehena = izena.charAt(0);
        if (Character.isUpperCase(lehena)) {
            return;
        }
 
        String izenBerria = Character.toUpperCase(lehena) + izena.substring(1);
        Path helburua = fitxategia.resolveSibling(izenBerria);
 
        try {
            Files.move(fitxategia, helburua);
            System.out.println(izena + "  ->  " + izenBerria);
        } catch (IOException e) {
            System.out.println("Errorea berrizendatzean (" + izena + "): " + e.getMessage());
        }
    }
}
