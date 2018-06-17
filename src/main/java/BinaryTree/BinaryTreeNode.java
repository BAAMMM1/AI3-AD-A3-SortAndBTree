package BinaryTree;

import quicksort.SchluesselWertPaar;

/**
 * Ein Knoten hat höchstens zwei Nachfolger. Jeder Knoten außer die Wurzel hat genau einen Elternknoten.
 *
 * @author Chris on 11.06.2018
 */
public class BinaryTreeNode<T extends Comparable<T>, U> {

    private SchluesselWertPaar<T, U> data;
    private BinaryTreeNode<T, U> left, right;

    public BinaryTreeNode(SchluesselWertPaar<T, U> data) {
        this.data = data;
    }

    /*
    Anzahl aller fogenden Notes
     */
    public int numbersOfAllNodesFromNodes() {
        int result = 1;
        if(this.left != null) result = result + this.left.numbersOfAllNodesFromNodes();
        if(this.right != null) result = result + this.right.numbersOfAllNodesFromNodes();
        return result;
    }

    /**
     * Gibt die Höhe eines Knoten aus
     * TODO - Testen
     * @return
     */
    public int getHoehe() {
        int l = 0, r = 0;

        if (this.left != null) l = left.getHoehe() + 1;
        if (this.right != null) r = right.getHoehe() + 1;

        return Math.max(l, r);

    }

    public void setLeft(BinaryTreeNode<T, U> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode<T, U> right) {
        this.right = right;
    }


    public BinaryTreeNode<T, U> getLeft() {
        return left;
    }

    public BinaryTreeNode<T, U> getRight() {
        return right;
    }

    public SchluesselWertPaar<T, U> getData() {
        return data;
    }

    @Override
    public String toString() {
        String l;
        String r;
        if(left != null) l = "" + left.toString(); else l = "null";
        if(right != null) r = "" + right.toString(); else r = "null";


        return "Node{" +
                "data=" + data.getSchluessel() +
                ", left=" + l +
                ", right=" + r +
                '}';
    }
}
