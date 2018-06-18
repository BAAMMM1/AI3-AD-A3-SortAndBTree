package quicksort.pivotStrategie;

import quicksort.SchluesselWertPaar;

/**
 * @author Shadai on 07.06.2018
 */
public class PivotStrategieMedian implements PivotStrategie{
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
       if(iLinks<0||iRechts>a.length-1) throw new IllegalArgumentException();
        //den Median des ersten, mittleren und des letzten Elementes

        int first = iLinks;
        int middle = (iRechts+iLinks+1)/2;
        int last = iRechts;

        // Wir gehen erstmal davon aus das Median das letzte Element ist
        int index = last;

        if(a[first].getSchluessel().compareTo(a[middle].getSchluessel())== 1)                           //  MF-Fälle
            if(a[middle].getSchluessel().compareTo(a[last].getSchluessel())==1) index = middle;         //L MF
            else if (a[last].getSchluessel().compareTo(a[first].getSchluessel())==1) index = first;     //  MF L
            else index = last;                                                                          // MLF
        else                                                                                            //  FM-Fälle
            if(a[first].getSchluessel().compareTo(a[last].getSchluessel())==1) index = first;           //L FM
            else if (a[last].getSchluessel().compareTo(a[middle].getSchluessel())==1) index = middle;   //  FM L
            else index = last;                                                                          // FLM

        return index;
    }
}
