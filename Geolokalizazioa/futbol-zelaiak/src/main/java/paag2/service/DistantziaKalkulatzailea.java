package paag2.service;

import paag2.model.Zelaia;

/**
 * Bi zelairen arteko distantzia kalkulatzen du Haversine formularekin.
 */
public class DistantziaKalkulatzailea {

    private static final double LURRAREN_ERRADIOA_KM = 6371.0;

    /**
     * @return bi zelaien arteko distantzia, kilometrotan
     */
    public double kalkulatu(Zelaia zelaia1, Zelaia zelaia2) {
        double lat1Rad = Math.toRadians(zelaia1.getLatitudea());
        double lat2Rad = Math.toRadians(zelaia2.getLatitudea());

        double deltaLat = Math.toRadians(zelaia2.getLatitudea() - zelaia1.getLatitudea());
        double deltaLon = Math.toRadians(zelaia2.getLongitudea() - zelaia1.getLongitudea());

        // Haversine formula
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return LURRAREN_ERRADIOA_KM * c;
    }
}
