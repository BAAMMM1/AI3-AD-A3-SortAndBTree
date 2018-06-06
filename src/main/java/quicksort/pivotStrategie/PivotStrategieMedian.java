package quicksort.pivotStrategie;

import quicksort.SchluesselWertPaar;

/**
 * @author ${user} on ${date}
 */
public class PivotStrategieMedian implements PivotStrategie {
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {

        T first = a[iLinks].getSchluessel();
        T middle = a[(a.length / 2)].getSchluessel();
        T last = a[iRechts].getSchluessel();

        // first <= middle <= last || last <= middle <= fist
        if (((first.compareTo(middle) <= 0) && (middle.compareTo(last) <= 0)) ||
                ((last.compareTo(middle) <= 0) && (middle.compareTo(first) <= 0))) {

            return (a.length / 2);

            //first <= last <= middle || middle <= last <= fist
        } else if (((first.compareTo(last) <= 0) && (last.compareTo(middle) <= 0)) ||
                ((middle.compareTo(last) <= 0) && (last.compareTo(first) <= 0))) {

            return iRechts;

        } else {

            // middle <= first <= last || last <= first <= middle
            return iLinks;
        }

    }
}
