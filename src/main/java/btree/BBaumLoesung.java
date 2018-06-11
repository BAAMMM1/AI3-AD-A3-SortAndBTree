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


    private BBaumKnoten<T, U> getMatchingChildNode(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        // Bestimmen des richtien Kindknoten (In welchen Kind Knoten muss der Schlüssel eingefügrt werden)

        int index = 0;
        while (index < node.getAnzahlKinder()
                && node.getSchluesselWertPaar(index).getSchluessel().compareTo(toAdd.getSchluessel()) < 0) {
            index += 1;

        }

        return node.getKind(index);

    }

    private int getRightPosition(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        // Bestimmung des Index in einem Knoten für einen Schlüssel der in einen Knoten eingefügt werden soll
        int index = 0;
        while (index < node.getAnzahlSchluesselWertPaare()
                && node.getSchluesselWertPaar(index).getSchluessel().compareTo(toAdd.getSchluessel()) < 0) {
            index += 1;

        }

        return index;
    }

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


    // Eklusiv right
    private BBaumKnoten subNode(BBaumKnoten<T, U> node, int left, int right) {

        BBaumKnoten<T, U> newNode = new BBaumKnoten<>();

        for (int i = left; i < right; i++) {
            newNode.addSchluesselWertPaar(i - left, node.getSchluesselWertPaar(i));

        }


        return newNode;
    }



}
