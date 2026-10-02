package paag2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;

public class FolderLister {

    public static void list() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Zerrendatu nahi duzun karpetaren path absolutoa: ");
        String sarrera = sc.nextLine().trim();

        Path p = Path.of(sarrera);

        if (!Files.exists(p)) {
            System.out.println("Ez da existitzen: " + p);
            return;
        }

        if (!Files.isDirectory(p)) {
            System.out.println("Hori ez da karpeta bat: " + p);
            return;
        }

        // Files.list(): lehen mailako edukia soilik (ez du barruan sartzen,
        // Files.walk()-ek egingo lukeen bezala).
        try (Stream<Path> edukia = Files.list(p)) {
            edukia.forEach(elementua -> {
                String mota = Files.isDirectory(elementua) ? "[Karpeta]" : "[Fitxategia]";
                System.out.println(mota + " " + elementua.getFileName());
            });
        } catch (IOException e) {
            System.out.println("Errorea karpeta irakurtzean: " + e.getMessage());
        }
    }
}
