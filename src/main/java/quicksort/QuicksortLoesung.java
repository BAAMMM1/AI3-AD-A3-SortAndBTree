package quicksort;

import quicksort.pivotStrategie.PivotStrategie;
import quicksort.pivotStrategie.PivotStrategieFix;
import quicksort.pivotStrategie.PivotStrategieMedian;
import quicksort.pivotStrategie.PivotStrategieRandom;

import java.util.Arrays;

/**
 * @author ${user} on ${date}
 */
public class QuicksortLoesung<T extends Comparable<T>, U> extends Quicksort<T, U> {

    public QuicksortLoesung(PivotStrategie pivotStrategy) {
        super(pivotStrategy);
    }


    @Override
    public void sortiere(SchluesselWertPaar<T, U>[] a) {

        this.sort(a, 0, a.length - 1);


    }

    public void sort(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {

        //System.out.println("ilinks: " + iLinks + " - iRechts: " + iRechts);

        if (iRechts > iLinks) {


            int i = iLinks;
            int j = iRechts - 1;

            int pivotIndex = this.pivotStrategy.getIndex(a, iLinks, iRechts);

            T pivot = a[pivotIndex].getSchluessel();

            // Was hat sich zur Standardimplementierung geändert, der Pivotwert ist nicht mehr ganz rechts.
            // Funktioniert weil der Algortihmus standardmaßig immer das pivot hatte, wenn es nicht
            //rechts ist funktioniert diese Implementierung nicht richtig
            // Pivot wird nicht betrachtet, deshlab nach rechts schieben
            swap(a, pivotIndex, iRechts);

            while (true) {

                // Wenn a[i] kleiner als pivot element, dann gehe zum nächsten element i++;
                while (a[i].getSchluessel().compareTo(pivot) < 0) {
                    i++;
                }

                // So Lange a[j] größer oder gleich dem pivot gehe einen runter
                while (a[j].getSchluessel().compareTo(pivot) >= 0 && j > 0) {
                    j--;
                }


                if (i >= j) {
                    // in der Mitte getroffen
                    break;
                }


                this.swap(a, i, j);

            }

            this.swap(a, i, iRechts); // Pivot element an die Stelle schieben, an der es die linke und recht hälfte trennt
            this.sort(a, iLinks, i - 1);
            this.sort(a, i + 1, iRechts);

        }

    }

    private void swap(SchluesselWertPaar<T, U>[] a, int i, int j) {

        SchluesselWertPaar<T, U> temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    public static void main(String[] args) {

        Quicksort<Integer, String> quicksort = new QuicksortLoesung<Integer, String>(new PivotStrategieFix());
        SchluesselWertPaar<Integer, String>[] a = new SchluesselWertPaar[8];
        a[0] = new SchluesselWertPaar<Integer, String>(6, "F");
        a[1] = new SchluesselWertPaar<Integer, String>(5, "E");
        a[2] = new SchluesselWertPaar<Integer, String>(8, "H");
        a[3] = new SchluesselWertPaar<Integer, String>(2, "B");
        a[4] = new SchluesselWertPaar<Integer, String>(1, "A");
        a[5] = new SchluesselWertPaar<Integer, String>(3, "C");
        a[6] = new SchluesselWertPaar<Integer, String>(4, "D");
        a[7] = new SchluesselWertPaar<Integer, String>(7, "G");

        System.out.println(Arrays.toString(a));

        quicksort.sortiere(a);

        System.out.println(Arrays.toString(a));

    }


}