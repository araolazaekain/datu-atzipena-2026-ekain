package paag2.service;

import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import paag2.model.Zelaiak;

/**
 * Zelaiak JSON fitxategi batera esportatzen ditu Jakarta JSON Binding (JSON-B) erabilita.
 */
public class JsonKudeatzailea {

    /**
     * @return true ondo esportatu bada
     */
    public boolean esportatu(Zelaiak zelaiak, String jsonFitxategia) {
        JsonbConfig config = new JsonbConfig().withFormatting(true);

        try (Jsonb jsonb = JsonbBuilder.create(config);
             Writer writer = Files.newBufferedWriter(Path.of(jsonFitxategia), StandardCharsets.UTF_8)) {
            jsonb.toJson(zelaiak, writer);
            return true;
        } catch (Exception e) {
            // IOException (fitxategia), JsonbException (bihurketa) edo Jsonb.close()
            System.out.println("Errorea " + jsonFitxategia + " idaztean: " + e.getMessage());
            return false;
        }
    }
}
