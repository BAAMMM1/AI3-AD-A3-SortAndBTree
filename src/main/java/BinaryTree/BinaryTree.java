package BinaryTree;

import quicksort.SchluesselWertPaar;

/**
 * Diese Klasse stellt einen einfachen Binärbaum da um die Funktion der Prüfung auf Höhenbalanciertheit zu realisieren.
 *
 * @author Chris on 11.06.2018
 */
public class BinaryTree<T extends Comparable<T>, U> {

    /**
     * Wurzelknoten des Binärenbaums
     */
    private BinaryTreeNode<T, U> root;

    /**
     * Fügt einen übergebenen Schlüssel und Wert ausgehen von der Wurzel in den Baum ein. Falls der Baum bisher leer
     * war, wird der neue Knoten die Wurzel
     *
     * @param key   Schlüssel
     * @param value Wert
     */
    public void einfuegen(T key, U value) {

        BinaryTreeNode<T, U> toAdd = new BinaryTreeNode<>(new SchluesselWertPaar<>(key, value));

        if (this.root == null)
            root = toAdd;
        else
            this.einfuegen(this.root, toAdd);

    }


    /**
     * Fügt einen übergebenen Knoten ausgehen von dem übergebenen Knoten in den Baum ein. Dabei der Schlüssel-Wert
     * genutzt um den Knoten einzusortieren.
     *
     * @param node  aktueller Knoten
     * @param toAdd Knoten der in den Baum einsortiert werden soll
     */
    private void einfuegen(BinaryTreeNode<T, U> node, BinaryTreeNode<T, U> toAdd) {

        if (toAdd.getData().getSchluessel().compareTo(node.getData().getSchluessel()) < 0) {

            // falls der neue Schlüssel kleiner als der aktuelle Knoten Schlüsselwert ist, muss der Knoten entweder
            // linker Kindknoten werden oder weiter in den linken Teilbaum hinuntersteigen

            if (node.getLeft() == null) {
                node.setLeft(toAdd);
            } else {
                this.einfuegen(node.getLeft(), toAdd);

            }

        } else {

            // falls der neue Schlüssel größer als der aktuelle Knoten Schlüsselwert ist, muss der Knoten entweder
            // rechter Kindknoten werden oder weiter in den rechten Teilbaum hinuntersteigen

            if (node.getRight() == null) {
                node.setRight(toAdd);
            } else {
                this.einfuegen(node.getRight(), toAdd);
            }

        }

    }


    /**
     * Gibt an ob der Baum vollständig höhenbalanciert ist. Ein Baum heißt vollständig ausgeglichen, wenn sich das
     * Gewicht (die Anzahl der Knoten) des linken und rechten Teilbaums jedes Knotens höchstens um 1 unterscheidet.
     *
     * @return falls balanciert true, sonst false
     */
    public boolean isHightBalanced() {

        int leftHight = 0;
        int rightHight = 0;

        if (this.root.getLeft() != null) leftHight = this.root.getLeft().getHight() + 1; // +1 wegen this.root.getLeft() weg zum left muss auch gezählt werden
        if (this.root.getRight() != null) rightHight = this.root.getRight().getHight() + 1;

        return Math.max(leftHight, rightHight) - Math.min(leftHight, rightHight) <= 1;
    }


    /**
     * Gibt die Höhe des Baumes aus.
     *
     * @return Höhe des Baumes
     */
    public int getTreeHight() {

        if (this.root == null) return 0;

        return this.root.getHight();

    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

}
