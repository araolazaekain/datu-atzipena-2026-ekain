package paag2;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FolderListerFile {
 
    public static void listToFile() {
        // 1) Erabiltzaileak karpeta aukeratu, JFileChooser erabilita.
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Aukeratu zerrendatu nahi duzun karpeta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
 
        int emaitza = chooser.showOpenDialog(null);
 
        if (emaitza != JFileChooser.APPROVE_OPTION) {
            System.out.println("Ez da karparik aukeratu. Bertan behera utzi da.");
            return;
        }
 
        Path karpeta = chooser.getSelectedFile().toPath();
 
        // 2) Emaitza non gorde galdetu (testu-fitxategi berria).
        Scanner sc = new Scanner(System.in);
        System.out.print("Non gorde nahi duzu emaitza-fitxategia? (path absolutoa, adib. C:\\emaitzak.txt): ");
        String irteeraStr = sc.nextLine().trim();
        Path irteera = Path.of(irteeraStr);
 
        // 3) Karpetaren lehen mailako edukia irakurri eta lerroz lerro bildu.
        StringBuilder edukia = new StringBuilder();
        edukia.append("Karpeta: ").append(karpeta).append(System.lineSeparator());
        edukia.append("---").append(System.lineSeparator());
 
        try (Stream<Path> zerrenda = Files.list(karpeta)) {
            zerrenda.forEach(elementua -> {
                String mota = Files.isDirectory(elementua) ? "[Karpeta]" : "[Fitxategia]";
                edukia.append(mota).append(" ").append(elementua.getFileName())
                        .append(System.lineSeparator());
            });
        } catch (IOException e) {
            System.out.println("Errorea karpeta irakurtzean: " + e.getMessage());
            return;
        }
 
        // 4) Bildutako edukia emaitza-fitxategian idatzi.
        try {
            Files.writeString(irteera, edukia.toString());
            System.out.println("Zerrenda gorde da: " + irteera);
            JOptionPane.showMessageDialog(null, "Zerrenda gorde da:\n" + irteera);
        } catch (IOException e) {
            System.out.println("Errorea emaitza-fitxategia idaztean: " + e.getMessage());
        }
    }
}
