package BinaryTree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Chris on 11.06.2018
 */
public class BinaryTreeTest {

    private BinaryTree<Integer, Integer> binaryTree;

    @Before
    public void init(){
        binaryTree = new BinaryTree<Integer, Integer>();
    }

    @Test
    public void treeHeight(){

        binaryTree.einfuegen(20, 20);
        Assert.assertEquals(0, binaryTree.getTreeHeight());
        binaryTree.einfuegen(10, 10);
        Assert.assertEquals(1, binaryTree.getTreeHeight());
        binaryTree.einfuegen(30, 30);
        Assert.assertEquals(1, binaryTree.getTreeHeight());
        binaryTree.einfuegen(40, 40);
        Assert.assertEquals(2, binaryTree.getTreeHeight());
        binaryTree.einfuegen(50,50);
        Assert.assertEquals(3, binaryTree.getTreeHeight());
        binaryTree.einfuegen(15,15);
        Assert.assertEquals(3, binaryTree.getTreeHeight());
        binaryTree.einfuegen(12,12);
        Assert.assertEquals(3, binaryTree.getTreeHeight());
        binaryTree.einfuegen(11,11);
        Assert.assertEquals(4, binaryTree.getTreeHeight());

    }

    @Test
    public void isBalancedOnlyOneNodeTest(){

        binaryTree.einfuegen(20, 20);
        Assert.assertTrue(binaryTree.isHeightBalanced());

    }

    @Test
    public void isBalancedRightTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(30, 30);
        Assert.assertTrue(binaryTree.isHeightBalanced());

    }

    @Test
    public void isBalancedleftTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(10, 30);
        Assert.assertTrue(binaryTree.isHeightBalanced());

    }

    @Test
    public void isBalancedRightOutsideTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(10, 10);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(40, 40);
        Assert.assertTrue(binaryTree.isHeightBalanced());


    }

    @Test
    public void isBalancedRightInnerTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(10, 10);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(25, 25);
        Assert.assertTrue(binaryTree.isHeightBalanced());


    }

    @Test
    public void isBalancedLeftOutsideTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(10, 10);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(5, 5);
        Assert.assertTrue(binaryTree.isHeightBalanced());


    }

    @Test
    public void isBalancedLeftInnerTest(){

        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(10, 10);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(15, 15);
        Assert.assertTrue(binaryTree.isHeightBalanced());


    }

    @Test
    public void isNotBalancedRightOutsideTest(){
        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(40, 40);
        Assert.assertFalse(binaryTree.isHeightBalanced());

    }

    @Test
    public void isNotBalancedRightInnerTest(){
        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(30, 30);
        binaryTree.einfuegen(25, 25);
        Assert.assertFalse(binaryTree.isHeightBalanced());

    }

    @Test
    public void isNotBalancedLeftOutsideTest(){
        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(15, 15);
        binaryTree.einfuegen(10, 10);
        Assert.assertFalse(binaryTree.isHeightBalanced());

    }

    @Test
    public void isNotBalancedLeftInnerTest(){
        binaryTree.einfuegen(20, 20);
        binaryTree.einfuegen(15, 15);
        binaryTree.einfuegen(17, 17);
        Assert.assertFalse(binaryTree.isHeightBalanced());

    }

}
