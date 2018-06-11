package quicksort;

//muss angepasst werden!!!

import org.junit.Test;
import quicksort.pivotStrategie.PivotStrategieFix;
import quicksort.pivotStrategie.PivotStrategieRandom;

import static org.junit.Assert.assertTrue;

/**
 * Testklasse für das Quicksort-Verfahren.
 */
public class TestQuicksortRandom {

    /**
     * Verschiedene Konstanzen für die Generierung von Testdaten in der Methode generierDaten().
     */
    private enum DatenGenerierung {
        IN_REIHENFOLGE, ZUFALL, RUECKWAERTS
    }

    /**
     * Mit dieser Instanz wird getestet.
     */
    // TODO: Legen Sie hier eine Instanz Ihrer Implementierung an.
    Quicksort<Integer, String> qs =
            new QuicksortLoesung<>(new PivotStrategieRandom()); // TODO - <>

    private SchluesselWertPaar<Integer, String>[] generiereDaten(int anzahl, DatenGenerierung datenGenerierung) {
        SchluesselWertPaar<Integer, String>[] daten = new SchluesselWertPaar[anzahl];
        for (int i = 0; i < daten.length; i++) {
            switch (datenGenerierung) {
                case IN_REIHENFOLGE:
                    daten[i] = new SchluesselWertPaar<Integer, String>(i, i + "");
                    break;
                case ZUFALL:
                    int wert = (int) (Math.random() * anzahl * 10);
                    daten[i] = new SchluesselWertPaar<Integer, String>(wert, wert + "");
                    break;
                case RUECKWAERTS:
                    daten[i] = new SchluesselWertPaar<Integer, String>(anzahl - i, (anzahl - i) + "");
                    break;
            }
        }
        return daten;
    }

    @Test
    public void testRueckwaerts() {
        SchluesselWertPaar<Integer, String>[] daten = generiereDaten(10, DatenGenerierung.RUECKWAERTS);
        ausgeben("vorher: ", daten);

        qs.sortiere(daten);
        ausgeben("nachher: ", daten);                   ///
        for (int i = 0; i < daten.length - 1; i++) {
            assertTrue("Daten sind nicht korrekt sortiert", daten[i].getSchluessel().compareTo(daten[i + 1].getSchluessel()) <= 0);
        }

        ausgeben("nachher: ", daten);
    }

    @Test
    public void testZufall() {
        SchluesselWertPaar<Integer, String>[] daten = generiereDaten(10, DatenGenerierung.ZUFALL);
        ausgeben("vorher: ", daten);

        qs.sortiere(daten);
        ausgeben("nachher: ", daten);               ///
        for (int i = 0; i < daten.length - 1; i++) {
            assertTrue("Daten sind nicht korrekt sortiert", daten[i].getSchluessel().compareTo(daten[i + 1].getSchluessel()) <= 0);
        }

        ausgeben("nachher: ", daten);
    }

    @Test
    public void testInReihenfolge() {
        SchluesselWertPaar<Integer, String>[] daten = generiereDaten(10, DatenGenerierung.IN_REIHENFOLGE);
        ausgeben("vorher: ", daten);

        qs.sortiere(daten);
        ausgeben("nachher: ", daten);           ///
        for (int i = 0; i < daten.length - 1; i++) {
            assertTrue("Daten sind nicht korrekt sortiert", daten[i].getSchluessel().compareTo(daten[i + 1].getSchluessel()) <= 0);
        }

        ausgeben("nachher: ", daten);
    }

    /**
     * Hilfsmethode zur Ausgabe eines Arrays auf der Konsole.
     *
     * @param nachricht Diese Nachricht wird mit ausgegeben.
     * @param daten     Array
     */
    public static <T extends Comparable<T>, U> void ausgeben(String nachricht, SchluesselWertPaar<T, U>[] daten) {
        System.out.print(nachricht + ": ");
        for (SchluesselWertPaar<T, U> element : daten) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
