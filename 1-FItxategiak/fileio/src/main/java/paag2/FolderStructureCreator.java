package paag2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FolderStructureCreator {
    public static void create() {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Non sortu nahi duzu 'karpeta_berriak'? (path absolutoa): ");
        String sarrera = sc.nextLine().trim();
 
        Path oinarria = Path.of(sarrera, "karpeta_berriak");
 
        // Sortu beharreko azpikarpeta guztien path-ak, oinarritik abiatuta.
        Path[] azpikarpetak = {
                oinarria.resolve("animaliak").resolve("arrainak"),
                oinarria.resolve("animaliak").resolve("ugaztunak"),
                oinarria.resolve("elikagaiak").resolve("barazkiak"),
                oinarria.resolve("elikagaiak").resolve("esnekiak")
        };
 
        for (Path karpeta : azpikarpetak) {
            try {
                // createDirectories: tarteko karpetak ere sortzen ditu
                // (adibidez 'animaliak' oraindik ez badago).
                Files.createDirectories(karpeta);
                System.out.println("Sortuta: " + karpeta);
            } catch (IOException e) {
                System.out.println("Errorea sortzean (" + karpeta + "): " + e.getMessage());
            }
        }
    }
}
