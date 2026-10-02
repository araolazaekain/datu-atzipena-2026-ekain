package paag2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        System.out.println("Ruta erlatiboen jatorria: "+new File("").getAbsolutePath());

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream("iostreams2/xanadu.txt");
            out = new FileOutputStream("iostreams2/outagain.txt");
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }finally{}
    }
}