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
            int j = iRechts -1;             //pivot muss isoliert betrachtet werden, daher haben wir hier den pivot auf iRechts ausgelagert
            //SchluesselWertPaar<T,U> pivot = a[pivotStrategy.getIndex(a,iLinks,iRechts)];
//            int pivot = pivotStrategy.getIndex(a,iLinks,iRechts);
            int pivot = pivotStrategy.getIndex(a,i,j);

            T pivotElement =this.a[pivot].getSchluessel();
            swap(pivot, iRechts);
//                      P
//                1 2 3 4 5 6
//            System.out.println("Pivot Schlüssel   " + a[pivot].getSchluessel()+" und Pivot Wert   "+ a[pivot].getWert()+"  vor Swap");
//            System.out.println("iRechts Schlüssel " + a[iRechts].getSchluessel()+" und iRechts Wert "+ a[iRechts].getWert()+"  vor Swap");
        //
//            System.out.println("Pivot Schlüssel   " + a[pivot].getSchluessel()+" und Pivot Wert   "+ a[pivot].getWert()+" nach Swap");
//            System.out.println("iRechts Schlüssel " + a[iRechts].getSchluessel()+" und iRechts Wert "+ a[iRechts].getWert()+" nach Swap");
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
                // vertauschen
               // System.out.println("Schlusselwert von i :"+a[i].getSchluessel()+"\nSchlüsselwert von j : "+a[j].getSchluessel());
                swap(i, j);
            }


            // hier musste irechts mit pivot gewechselt werden, damit immer der pivot nach strategie getauscht wird
            // i befindet sich schon an der richtigen stelle des Pivots
            //ausgelagerter Pivot wird nun an der Position von i fixiert
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
        QuicksortLoesung qs = new QuicksortLoesung(new PivotStrategieFix());
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
