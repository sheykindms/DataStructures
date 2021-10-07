package lesson17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {

    private final BalancedBST bst = new BalancedBST();

    @Test
    void generateTree() {
        bst.generateTree(new int[]{2,3,4,5,6,7,8});
        assertEquals(5, bst.Root.nodeKey);
        assertEquals(3, bst.Root.leftChild.nodeKey);
        assertEquals(2, bst.Root.leftChild.leftChild.nodeKey);
        assertEquals(4, bst.Root.leftChild.rightChild.nodeKey);
        assertEquals(7, bst.Root.rightChild.nodeKey);
        assertEquals(6, bst.Root.rightChild.leftChild.nodeKey);
        assertEquals(8, bst.Root.rightChild.rightChild.nodeKey);
    }

    @Test
    void isBalanced() {
        bst.generateTree(new int[]{2,3,4,5,6,7,8});
        assertTrue(bst.isBalanced(bst.Root));
        bst.generateTree(new int[]{2,3,4,5,6,7,8,9,10,11,12});
        assertTrue(bst.isBalanced(bst.Root));
    }
}