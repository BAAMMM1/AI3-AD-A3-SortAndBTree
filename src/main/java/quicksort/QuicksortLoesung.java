package quicksort;

import quicksort.pivotStrategie.PivotStrategie;
import quicksort.pivotStrategie.PivotStrategieFix;
import quicksort.pivotStrategie.PivotStrategieMedian;
import quicksort.pivotStrategie.PivotStrategieRandom;

import java.util.Arrays;

/**
 * @author Shadai on 07.06.2018
 */
public class QuicksortLoesung<T extends Comparable<T>,U> extends Quicksort {
    private SchluesselWertPaar<T,U>[] a;
    /**
     * Konstrucktor.
     *
     * @param pivotStrategy
     */
    public QuicksortLoesung(PivotStrategie pivotStrategy) {
        super(pivotStrategy);
    }

    @Override
    public void sortiere(SchluesselWertPaar[] a) {
        this.a = a;
        qsortRange(0,a.length-1);
    }

    private void qsortRange(int iLinks, int iRechts) {
        if (iRechts > iLinks) {
            int i = iLinks;
            int j = iRechts;
            //SchluesselWertPaar<T,U> pivot = a[pivotStrategy.getIndex(a,iLinks,iRechts)];
            int pivot = pivotStrategy.getIndex(a,iLinks,iRechts);

            T pivotElement =this.a[pivot].getSchluessel();

            swap(pivot, iRechts);

            while (true) {
                while (a[i].getSchluessel().compareTo(pivotElement) < 0) {
                    i++;
                }
                while (a[j].getSchluessel().compareTo(pivotElement) >= 0 && j > 0) {
                    j--;
                }

                if (i >= j) {
                    // in Mitte getroffen
                    break;
                }
                // vertauschen
                swap(i, j);
            }
            // hier musste irechts mit pivot gewechselt werden, damit immer der pivot nach strategie getauscht wird
            // i befindet sich schon an der richtigen stelle des Pivots
            swap(i,iRechts);
            qsortRange(iLinks, i-1);
            qsortRange(i+1,iRechts);


            }

        }

    private void swap(int x, int y) {
        SchluesselWertPaar<T,U> tmp = a[x];

        a[x] = a[y];
        a[y] = tmp;
    }

    public static void main(String[] args) {
        QuicksortLoesung qs = new QuicksortLoesung(new PivotStrategieRandom());
        SchluesselWertPaar<Integer, String>[] a = new SchluesselWertPaar[5];
        a[0]= new SchluesselWertPaar<>(25, "Hallo 1");
        a[1]= new SchluesselWertPaar<>(24, "Hallo 2");
        a[2]= new SchluesselWertPaar<>(23, "Hallo 3");
        a[3]= new SchluesselWertPaar<>(22, "Hallo 4");
        a[4]= new SchluesselWertPaar<>(21, "Hallo 5");

        System.out.println("Unsortierte Liste ist: \n"+ Arrays.toString(a));
        qs.sortiere(a);

        System.out.println("Sortierte Liste ist: \n"+ Arrays.toString(a));
        // wie gebe ich nun das ergebnis aus, er führt methode aus,
        // bearbeitet this.a in seiner Methode, aber garbage collector sammelt
        // das ja nach erfolgreichem sortieren wieder ein
        // muss evtl sortiere ein SchlüsselWertPaarArray erstellen?
        // oder behält er diese Information, weil qs noch aktiv ist?
        // nur dann müsste er sich ja jeden Int, den man als Zähler erstellt hat, merken
    }
}
