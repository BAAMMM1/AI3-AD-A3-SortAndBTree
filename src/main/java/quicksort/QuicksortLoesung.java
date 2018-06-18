package quicksort;

import quicksort.pivotStrategie.PivotStrategie;

/**
 * @author Shadai on 07.06.2018
 */
public class QuicksortLoesung<T extends Comparable<T>,U> extends Quicksort {
    /**
     * Auslagerung des übergeben Arrays in ein Instanzobjekt
     * wird für jeden Sortiervorgang neu gesetzt
     */
    private SchluesselWertPaar<T,U>[] a;
    /**
     * Konstrucktor: Erstellt ein Quicksort für übergebene Pivotstategie.
     * pivotStrategie darf nicht null sein.
     *
     * @param pivotStrategy
     */
    public QuicksortLoesung(PivotStrategie pivotStrategy) {
        super(pivotStrategy);
    }

    /**
     * Sortiert das Array a in-situ.
     * @param a Array mit Schlüssel-Wert-Paaren, die nach den Schlüsseln aufsteigend sortiert werden sollen.
     */
    @Override
    public void sortiere(SchluesselWertPaar[] a) {
        this.a = a;
        qsortRange(0,a.length-1);
    }

    /**
     * Hilfsmethode zum sortieren des Arrays.
     * Innerhalb der gegeben Range wird ein Pivot nach einer Strategie gewählt
     * und an richtiger Position fixiert. Anschließend wird rekrusiv
     * diese Methode so lange ausgeführt, bis alle Schlüssel innerhalb der Range
     * an der richtigen Position fixiert sind.
     * @param iLinks gibt Index der linke Grenze des Arrays an
     * @param iRechts gibt den rechten Index des Arrays an
     */
    private void qsortRange(int iLinks, int iRechts) {
        if (iRechts > iLinks) {
            int i = iLinks;
            // pivot muss isoliert betrachtet werden, daher wollen wir hier den pivot auf iRechts ausgelagern
            // und nur den Rest betrachten
            int j = iRechts -1;

            int pivot = pivotStrategy.getIndex(a,iLinks,iRechts);

            T pivotElement =this.a[pivot].getSchluessel();

            // unabhängig der Strategie wird hier das pivot nach iRechts ausgelagert
            swap(pivot, iRechts);

            while (true) {
                while (a[i].getSchluessel().compareTo(pivotElement) < 0) {
                    i++;
                }
                while (a[j].getSchluessel().compareTo(pivotElement) >= 0 && j > 0) {
                    j--;
                }
                if (i >= j) {
                    break;
                }
                swap(i, j);
            }
            // hier muss das isolierte Pivot, welches sich in iRechts befindet,
            // mit der Position von i getauscht werden.
            // Das Pivot ist anschließend an der richtigen Position i fixiert.
            swap(i,iRechts);
            qsortRange(iLinks, i-1);
            qsortRange(i+1,iRechts);
            }
        }

    /**
     * Vertauscht die Postion der Schlüsselwertpaare innerhalb des Arrays
     * @param x Entspricht dem Index des 1. Schlüsselwertpaares
     * @param y Entspricht dem Index des 2. Schlüsselwertpaares
     */
    private void swap(int x, int y) {
        SchluesselWertPaar<T,U> tmp = a[x];

        a[x] = a[y];
        a[y] = tmp;
    }

}
