package BinaryTree;

import quicksort.SchluesselWertPaar;

/**
 * @author Chris on 11.06.2018
 */
public class BinaryTree<T extends Comparable<T>, U> {

    BinaryTreeNode<T, U> root;

    public BinaryTree() {

    }

    public void einfuegen(T key, U value) {

        SchluesselWertPaar<T, U> toAdd = new SchluesselWertPaar<T, U>(key, value);

        if (this.root == null) root = new BinaryTreeNode<T, U>(toAdd);
        else this.einfuegen(this.root, toAdd);


    }


    public void einfuegen(BinaryTreeNode<T, U> node, SchluesselWertPaar<T, U> toAdd) {

        if (toAdd.getSchluessel().compareTo(node.data.getSchluessel()) < 0) {
            if (node.getLeft() == null) {
                BinaryTreeNode<T, U> left = new BinaryTreeNode<T, U>(toAdd);
                left.setParent(node);
                node.setLeft(left);
            } else {
                this.einfuegen(node.getLeft(), toAdd);

            }
        } else {
            if (node.getRight() == null) {
                BinaryTreeNode<T, U> right = new BinaryTreeNode<T, U>(toAdd);
                right.setParent(node);
                node.setRight(right);
            } else {
                this.einfuegen(node.getRight(), toAdd);
            }

        }


    }


    /**
     * Ein Baum heißt vollständig ausgeglichen, wenn sich das Gewicht (die Anzahl der Knoten) des linken und rechten
     * Teilbaums jedes Knotens höchstens um 1 unterscheidet.
     *
     * @return
     */
    public boolean isHightBalanced() {
        if (this.root == null) {
            return true;
        }
        return this.root.isBalanced();
    }


    public int numberOfAllNodes() {
        if (this.root == null) {
            return 0;
        } else {
            return this.root.numbersOfAllNodesFromNodes();
        }

    }

    public int getHoehe() {
        if (this.root == null) {
            return 0;
        } else {
            return this.root.getHoehe();
        }

    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    public static void main(String[] args) {
        BinaryTree<Integer, Integer> binaryTree = new BinaryTree<Integer, Integer>();

        System.out.println(binaryTree);
        binaryTree.einfuegen(2,2);
        System.out.println(binaryTree);
        binaryTree.einfuegen(1,1);
        System.out.println(binaryTree);
        binaryTree.einfuegen(3,3);
        System.out.println(binaryTree);
        binaryTree.einfuegen(4,4);
        System.out.println(binaryTree);
        //binaryTree.einfuegen(5,5);
        System.out.println(binaryTree);
        System.out.println(binaryTree.numberOfAllNodes());
        System.out.println(binaryTree.getHoehe());
        System.out.println(binaryTree.isHightBalanced());



    }
}
