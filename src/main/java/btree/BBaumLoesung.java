package btree;

/**
 * Implementierung eines konkreten B-Baum, es werden alle Methoden des abstakten BBaum geerbt und um
 * die zu implementierenden erweitert.
 *
 * @author Christian 
 */

public class BBaumLoesung<T extends Comparable<T>, U> extends BBaum<T, U> {

    public BBaumLoesung(int i) {
        super(i);
    }

    @Override
    public void einfuegen(T schluessel, U wert) {

    }

}
