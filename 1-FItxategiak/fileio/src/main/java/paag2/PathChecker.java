package paag2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class PathChecker {

    public static void check() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Idatzi path absolutoa: ");
        String sarrera = sc.nextLine().trim();

        if (sarrera.isEmpty()) {
            System.out.println("Ez duzu ezer idatzi.");
            sc.close();
            return;
        }

        Path p = Path.of(sarrera);

        if (!p.isAbsolute()) {
            System.out.println("Kontuz: eman duzun path-a ez da absolutoa.");
        }

        if (Files.exists(p)) {
            System.out.println("Bai, existitzen da: " + p);

            if (Files.isDirectory(p)) {
                System.out.println("  -> Karpeta bat da.");
            } else if (Files.isRegularFile(p)) {
                System.out.println("  -> Fitxategi arrunt bat da.");
            } else {
                System.out.println("  -> Ez da ez karpeta ez fitxategi arrunta (link sinboliko bat, agian).");
            }
        } else {
            System.out.println("Ez, ez da existitzen: " + p);
        }

        sc.close();
    }
}