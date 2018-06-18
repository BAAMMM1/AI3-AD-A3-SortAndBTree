package btree;

import quicksort.SchluesselWertPaar;

/**
 * Implementierung eines konkreten B-Baum, es werden alle Methoden des abstrakten BBaum geerbt und um die zu
 * implementierenden erweitert.
 *
 * @author Christian
 */

public class BBaumLoesung<T extends Comparable<T>, U> extends BBaum<T, U> {


    /**
     * Gibt die maximale Kapazität eines Knoten an innerhalbt des Baumes an
     */
    private final int nodeCapacity;

    /**
     * Erstellt einen BBaum für die übergebene Ordnung. Die Ordnung beeinflusst die maximale Anzahl an
     * Schlüsselwertpaaren eines Knoten.
     *
     * @param order Ordnung
     */
    public BBaumLoesung(int order) {
        super(order);
        this.nodeCapacity = 2 * order - 1;
    }


    /**
     * Fügt das Element in den Baum ein.
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

        // Wurzelknoten falls null neuanlegen
        if (this.wurzel == null) this.wurzel = new BBaumKnoten<>();

        // 1. Starten beim Wurzelknoten
        this.addSchluesselWertPaar(this.wurzel, new SchluesselWertPaar<>(schluessel, wert));

    }

    /**
     * Diese Methode fügt dem BBaum ein SchluesselWertPaar im richtigen Knoten an der richtigen Position hinzu. Dabei
     * wird der zum Schlüssel passende Blattknoten rekursiv ermittelt und anschließend wird das Schlüsselwertpaar
     * hinzugefügt. Daraufhin erfolgt eine rekursive Prüfung ob der Blattknoten und seine darüber liegenden Knoten
     * übergelaufen sind.
     *
     * @param node  Aktueller Knoten des Rekusrsionsschritt.
     * @param toAdd SchlüsselWertPaar welches hinzugefügt werden soll
     */
    private void addSchluesselWertPaar(BBaumKnoten<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        // 2 falls der Knoten ein Blattknoten ist:
        if (node.istBlattknoten()) {

            // 2.1 Einfügen an richtiger Position zwischen den vorhandenen Schlüssel
            node.addSchluesselWertPaar(this.getRightPosition(node, toAdd.getSchluessel()), toAdd);

            // 2.2 falls dann zu viele Schlüssel im Blattknoten sind
            // Aufspalten des Knotens in zwei Knoten, ein Schlüssel in den Elternknoten
            if (node.getAnzahlSchluesselWertPaare() > this.nodeCapacity) this.splitNode(node);

        } else {
            // 3. ansonsten:
            // rekursiver Abstieg in den zum einfügenden Schlüssel passenden Kindknoten
            // -> Nächster Kind knoten muss der richtige Knoten sein -> Richtigen Kindknoten bestimmen
            this.addSchluesselWertPaar(this.getMatchingChildNode(node, toAdd.getSchluessel()), toAdd);
        }

    }


    /**
     * Methode zum ermittelt des zum Schlüssel passenden Kindknotens. Hierbei wird die Frage gestellt, in welchen
     * Kindknoten der Schlüssel einzufügen ist.
     *
     * @param node Knoten der kein Blattknoten darstellt
     * @param key  Schlüssel der in ein Blatt eingefügt werden soll.
     * @return passenden Kindknoten
     */
    private BBaumKnoten<T, U> getMatchingChildNode(BBaumKnoten<T, U> node, T key) {

        int index = 0;
        while (index < node.getAnzahlKinder() && node.getSchluesselWertPaar(index).getSchluessel().compareTo(key) < 0) {
            index += 1;
        }

        return node.getKind(index);
    }

    /**
     * Ermittelt die Position für den Schlüssel im Knoten.
     *
     * @param node Knoten der ein Blattknoten darstellt
     * @param key Schlüssel für den ein Platz im Kindknoten gesucht werden soll
     * @return  Index für den übergebenen Schlüssel im Blattknoten
     */
    private int getRightPosition(BBaumKnoten<T, U> node, T key) {

        int index = 0;
        while (index < node.getAnzahlSchluesselWertPaare() && node.getSchluesselWertPaar(index).getSchluessel().compareTo(key) < 0) {
            index += 1;
        }

        return index;
    }

    /**
     * Trennt einen übergebenen Knoten in zwei neue Knoten und fügt den mittleren Schlüssel in seinen Elternknoten ein.
     * Anschließend wird rekursiv überprüft ob der Elternknoten überläuft. Falls der zu teilende Knoten die Wurzel ist,
     * wirf eine neue Wurzel angelegt, die alte Wurzel wird das Kind von der neuen Wurzel.
     *
     * @param toSplit Knoten der in zwei neue Knoten aufgespalten werden soll.
     */
    private void splitNode(BBaumKnoten<T, U> toSplit) {

        int median = toSplit.getAnzahlSchluesselWertPaare() / 2;

        BBaumKnoten<T, U> parentNode;

        // Besonderheit wenn der Knoten die Wurzel ist, dann hat dieser Knoten keinen parentNode.
        if (toSplit != this.wurzel) {
            // Dieser Knoten ist nicht die Wurzel, d.h. er hat einen Elternknoten
            parentNode = toSplit.getElternknoten();
        } else {
            // Dieser Knoten ist die Wurzel, also ist die Wurzel übergelaufen. Es muss eine neue Wurzel erstellt werden
            this.wurzel = new BBaumKnoten<>();
            parentNode = this.wurzel;
        }

        // Hier bei werden im Elternknoten die richtigen Kinder links und rechts vom Schlüssel gesetzt
        parentNode.schluesselUndKindEinfuegen(
                toSplit.subNode(0, median),
                toSplit.getSchluesselWertPaar(median),
                toSplit.subNode(median + 1, toSplit.getAnzahlSchluesselWertPaare()));

        // 2.3 rekursives Prüfen ob Elternknoten zu viele Schlüssel hat.
        if (parentNode.getAnzahlSchluesselWertPaare() > this.nodeCapacity) splitNode(toSplit.getElternknoten());

    }


}
