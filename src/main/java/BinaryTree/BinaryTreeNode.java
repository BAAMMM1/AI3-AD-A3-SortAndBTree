package BinaryTree;

import quicksort.SchluesselWertPaar;

/**
 * Diese Klasse stellt einen Knoten eines einfachen Binärbaumes da. Jeder Knoten hat keinen, einen oder höchstens zwei
 * Nachfolger.
 *
 * @author Chris on 11.06.2018
 */
public class BinaryTreeNode<T extends Comparable<T>, U> {

    /**
     * Daten des Knoten
     */
    private SchluesselWertPaar<T, U> data;

    /**
     * Linker und rechter Kindknoten des Knoten
     */
    private BinaryTreeNode<T, U> left, right;

    /**
     * Konstruktor, erstellt einen neuen Knoten mit übergebenen Daten
     *
     * @param data SchlüsselWertPaar für den Knoten
     */
    public BinaryTreeNode(SchluesselWertPaar<T, U> data) {
        this.data = data;
    }

    /**
     * Ermittelt die Höhe des Knoten aus dem Maximum der Höhe des linken und rechten Teilbaumes.
     *
     * @return maximum aus linker und rechter Teilbaumhöhe.
     */
    public int getHeight() {
        int leftHeight = 0;
        int rightHeight = 0;

        if (this.left != null)
            leftHeight = left.getHeight() + 1;

        if (this.right != null)
            rightHeight = right.getHeight() + 1;

        return Math.max(leftHeight, rightHeight);
    }


    /**
     * Setzt den linken Kindknoten
     *
     * @param left Knoten der linker Kindknoten werden soll
     */
    public void setLeft(BinaryTreeNode<T, U> left) {
        this.left = left;
    }

    /**
     * Setzt den rechten Kindknoten
     *
     * @param right Knoten der rechter Kindknoten werden soll
     */
    public void setRight(BinaryTreeNode<T, U> right) {
        this.right = right;
    }


    /**
     * Gibt den linken Kindknoten zurück
     *
     * @return linker Kindknoten
     */
    public BinaryTreeNode<T, U> getLeft() {
        return left;
    }

    /**
     * Gibt den rechten Kindknoten zurück
     *
     * @return rechter Kindknoten
     */
    public BinaryTreeNode<T, U> getRight() {
        return right;
    }

    /**
     * Gibt die Datendes Knoten zurück
     *
     * @return Schlüsselwertpaar des Knotens
     */
    public SchluesselWertPaar<T, U> getData() {
        return data;
    }

    @Override
    public String toString() {
        String l;
        String r;
        if (left != null) l = "" + left.toString();
        else l = "null";
        if (right != null) r = "" + right.toString();
        else r = "null";


        return "Node{" +
                "data=" + data.getSchluessel() +
                ", left=" + l +
                ", right=" + r +
                '}';
    }
}
