package lesson14;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    
    BSTNode<Integer> root = new BSTNode<Integer>(5, 5, null);
    BST<Integer> bst = new BST<Integer>(root);
    BSTNode<Integer> one;
    BSTNode<Integer> two;
    BSTNode<Integer> three;
    BSTNode<Integer> four;
    BSTNode<Integer> six;
    BSTNode<Integer> seven;

    @BeforeEach
    void setUp() {
        bst.AddKeyValue(7, 7);
        bst.AddKeyValue(6, 6);
        bst.AddKeyValue(4, 4);
        bst.AddKeyValue(3, 3);
        bst.AddKeyValue(2, 2);
        bst.AddKeyValue(1, 1);
    }

    @Test
    void findNodeByKey() {
    }

    @Test
    void addKeyValue() {
    }

    @Test
    void finMinMax() {
    }

    @Test
    void deleteNodeByKey() {
    }

    @Test
    void count() {
        assertEquals(7, bst.Count());
    }

    @Test
    void wideAllNodes() {
        ArrayList<BSTNode> nodes = bst.WideAllNodes();
        assertEquals(7 , nodes.size());
    }

    @Test
    void deepAllNodes() {
        ArrayList<BSTNode> nodes0 = bst.DeepAllNodes(0);
//        ArrayList<BSTNode> nodes1 = bst.DeepAllNodes(1);
//        ArrayList<BSTNode> nodes2 = bst.DeepAllNodes(2);
        assertEquals(7 , nodes0.size());
//        assertEquals(7 , nodes1.size());
//        assertEquals(7 , nodes2.size());
    }
}