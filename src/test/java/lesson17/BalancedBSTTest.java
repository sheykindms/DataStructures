package lesson17;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalancedBSTTest {

    private final BalancedBST bst = new BalancedBST();

    @Test
    void generateTree() {
        bst.GenerateTree(new int[]{2,3,4,5,6,7,8});
        assertEquals(5, bst.Root.NodeKey);
        assertEquals(3, bst.Root.LeftChild.NodeKey);
        assertEquals(2, bst.Root.LeftChild.LeftChild.NodeKey);
        assertEquals(4, bst.Root.LeftChild.RightChild.NodeKey);
        assertEquals(7, bst.Root.RightChild.NodeKey);
        assertEquals(6, bst.Root.RightChild.LeftChild.NodeKey);
        assertEquals(8, bst.Root.RightChild.RightChild.NodeKey);
    }

    @Test
    void isBalanced() {
        bst.GenerateTree(new int[]{2,3,4,5,6,7,8});
        assertTrue(bst.IsBalanced(bst.Root));
        bst.GenerateTree(new int[]{2,3,4,5,6,7,8,9,10,11,12});
        assertTrue(bst.IsBalanced(bst.Root));
    }
}