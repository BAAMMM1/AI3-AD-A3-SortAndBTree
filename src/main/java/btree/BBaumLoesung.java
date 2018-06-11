package btree;

import quicksort.SchluesselWertPaar;

/**
 * Implementierung eines konkreten B-Baum, es werden alle Methoden des abstakten BBaum geerbt und um die zu
 * implementierenden erweitert.
 *
 * @author Christian
 */

public class BBaumLoesung<T extends Comparable<T>, U> extends BBaum<T, U> {

    private int maxKeys;

    public BBaumLoesung(int i) {
        super(i);
        this.maxKeys = 2 * i - 1;
    }


    /**
     * Fügt das Element in den Knoten ein.
     *
     * @param schluessel Schlüssel des Elementes, das in den aktuellen Knoten eingefügt werden soll
     * @param wert       Wert des Elementes.
     */
    @Override
    public void einfuegen(T schluessel, U wert) {

        // 0. Preconditions
        if (schluessel == null || wert == null) throw new NullPointerException();

        // Prüfen ob der Schlüssel bereits vorhanden.
        if (this.findeWertZuSchluessel(schluessel) != null) throw new IllegalArgumentException("key already exists");

        // 1. Starten beim Wurzelknoten (Neuanlegen, falls null)
        if (this.wurzel == null) this.wurzel = new BBaumKnoten<T, U>();

        this.addSchluesselWertPaar(this.wurzel, new SchluesselWertPaar<T, U>(schluessel, wert));

    }

    /**
     * Diese Methode fügt dem BBaum ein SchluesselWertPaar an der richtigen Position hinzu.
     * Dabei wird der zum Schlüssel passende Blattknoten rekursiv ermittelt.
     *
     * @param node Aktueller Knoten des Rekusrsionsschritt.
     * @param toAdd SchlüsselWertPaar
     */
    private void addSchluesselWertPaar(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {


        // 2 falls der Knoten ein Blattknoten ist:
        if (node.istBlattknoten()) {

            // 2.1 Einfügen an richtiger Position zwischen den vorhandenen Schlüssel
            node.addSchluesselWertPaar(this.getRightPosition(node, toAdd), toAdd);

            // 2.2 falls dann zu viele Schlüssel im Blattknoten sind
            // Aufspalten des Knotens in zwei Knoten, ein Schlüssel in den Elternknoten
            if (node.getAnzahlSchluesselWertPaare() > this.maxKeys) this.splitNode(node);


        } else {
            // 3. ansonsten:
            // rekursiver Abstieg in den zum einfügenden Schlüssel passenden Kindknoten
            // -> Nächster Kind knoten muss der richtige Knoten sein -> Richtigen Kindknoten bestimmen
            this.addSchluesselWertPaar(this.getMatchingChildNode(node, toAdd), toAdd);
        }


    }


    /**
     * Methode zum ermittelt des zum Schlüssel passenden Kindknotens.
     *
     * @param node
     * @param toAdd
     * @return
     */
    private BBaumKnoten<T, U> getMatchingChildNode(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        // Frage: In welchen Kind Knoten muss der Schlüssel eingefügt werden?

        int index = 0;
        while (index < node.getAnzahlKinder()
                && node.getSchluesselWertPaar(index).getSchluessel().compareTo(toAdd.getSchluessel()) < 0) {
            index += 1;

        }

        return node.getKind(index);

    }

    /**
     * Ermittelt die Position für den Schlüssel im Knoten.
     *
     * @param node
     * @param toAdd
     * @return
     */
    private int getRightPosition(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        int index = 0;
        while (index < node.getAnzahlSchluesselWertPaare()
                && node.getSchluesselWertPaar(index).getSchluessel().compareTo(toAdd.getSchluessel()) < 0) {
            index += 1;

        }

        return index;
    }

    /**
     * Trennt einen übergebenen Knoten in zwei neue Knoten und fügt den mittleren Schlüssel in seinen Elternknoten ein.
     * Anschließend wird rekursiv überprüft ob der Elternknoten überläuft.
     *
     * @param node
     */
    private void splitNode(BBaumKnoten<T, U> node) {

        // Teilen: Knoten ab der Mitte in zwei neue Knoten, Referenz in den Eltern Knoten
        // Schauen ob Elternknoten überlaufen ist

        int mid = node.getAnzahlSchluesselWertPaare() / 2;

        BBaumKnoten<T, U> left = this.subNode(node, 0, mid);
        BBaumKnoten<T, U> right = this.subNode(node, mid + 1, node.getAnzahlSchluesselWertPaare());

        SchluesselWertPaar<T, U> medianKeyPair = node.getSchluesselWertPaar(mid);


        if (node != this.wurzel) { // Dieser Knoten ist nicht die wuzel
            BBaumKnoten<T, U> elternKnoten = node.getElternknoten();

            // Hier bei werden im Elternknoten die richtigen Kinder gesetzt
            elternKnoten.schluesselUndKindEinfuegen(left, medianKeyPair, right);

            // 2.3 rekursives Prüfen ob Elternknoten zu viele Schlüssel hat.
            if (elternKnoten.getAnzahlSchluesselWertPaare() > this.maxKeys) splitNode(node.getElternknoten());


        } else {
            // Dann ist der Knoten die Wurzel die übergelaufen ist, neue Wurzel muss nicht geprüft werden ob sie nun überläuft
            // Wie in schluesselUndKindEinfuegen
            this.wurzel = new BBaumKnoten<>(medianKeyPair);
            this.wurzel.setKind(0, left);
            this.wurzel.setKind(1, right);

        }


    }


    /**
     * Erstellt einen Sub-Knoten für eine übergebene Range und gibt diesen zurück.
     *
     * @param node
     * @param rangeStart
     * @param rangeEnd
     * @return
     */
    // Eklusiv right
    private BBaumKnoten subNode(BBaumKnoten<T, U> node, int rangeStart, int rangeEnd) {

        BBaumKnoten<T, U> subNode = new BBaumKnoten<>();

        for (int i = rangeStart; i < rangeEnd; i++) {
            subNode.addSchluesselWertPaar(i - rangeStart, node.getSchluesselWertPaar(i));

        }


        return subNode;
    }



}
