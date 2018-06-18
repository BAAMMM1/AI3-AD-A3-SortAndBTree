package quicksort.pivotStrategie;

import quicksort.SchluesselWertPaar;

/**
 * @author Shadai on 07.06.2018
 */
public class PivotStrategieFix implements PivotStrategie {
    @Override
    public <T extends Comparable<T>, U> int getIndex(SchluesselWertPaar<T, U>[] a, int iLinks, int iRechts) {
        // Fixe Position soll erste Position sein im Array
        return iLinks;
    }
}
