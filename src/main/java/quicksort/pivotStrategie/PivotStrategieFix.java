package quicksort.pivotStrategie;

import quicksort.SchluesselWertPaar;

/**
 * @author ${user} on ${date}
 */
public class PivotStrategieFix implements PivotStrategie{
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        return iRechts;
    }
}
