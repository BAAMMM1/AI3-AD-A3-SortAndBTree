package quicksort.pivotStrategie;

import quicksort.QuicksortLoesung;
import quicksort.SchluesselWertPaar;

import javax.swing.text.AbstractDocument;
import java.util.Arrays;

/**
 * @author ${user} on ${date}
 */

/**
 *
 */
public class PivotStrategieMedian implements PivotStrategie{
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
       if(iLinks<0||iRechts>a.length-1) throw new IllegalArgumentException();
        //den Median des ersten, mittleren und des letzten Elementes

        int first = iLinks;
      //  System.out.println("first "+first);
        int middle = (iRechts+iLinks+1)/2;
      //  System.out.println("middle " + middle);
        int last = iRechts;
      //  System.out.println("last " +last);

        //a größer compareto Objekt -> +1
        //a genau so groß wie CO    ->  0
        //a kleiner comparetoObjekt -> -1

        int index = last;
     //   System.out.println("index Annahme = "+last);
     //   System.out.println("Werte die verglichen werden:\nFirst Schlüssel "+a[first].getSchluessel()+"\nMiddle Schlüssel "+a[middle].getSchluessel()+"\nLast Schlüssel "+ a[last].getSchluessel());

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

    public static void main(String[] args) {
        SchluesselWertPaar<Integer, String>[] a = new SchluesselWertPaar[6];
        a[0]= new SchluesselWertPaar<>(21, "Hallo 1");
        a[1]= new SchluesselWertPaar<>(22, "Hallo 2");
        a[2]= new SchluesselWertPaar<>(23, "Hallo 3");
        a[3]= new SchluesselWertPaar<>(26, "Hallo 4");
        a[4]= new SchluesselWertPaar<>(24, "Hallo 5");
        a[5]= new SchluesselWertPaar<>(25, "Hallo 6");
        PivotStrategieMedian ps = new PivotStrategieMedian();
        int iLinks = 3;
        int iRechts = 3;
        System.out.println("Index bei Median von "+iLinks+" bis "+iRechts+" = "+(iRechts-iLinks+1)+" Elemente: " + ps.getIndex(a,iLinks,iRechts));
    }

}
