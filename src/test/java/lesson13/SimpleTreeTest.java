package lesson13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    private SimpleTree<Integer> tree;
    private SimpleTreeNode<Integer> initialNode;

    @BeforeEach
    void setUp() {
        initialNode = new SimpleTreeNode<>(1, null);
        tree = new SimpleTree<>(initialNode);
    }

    @Test
    void addChildAndCount() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(2, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(3, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(4, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(5, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(6, thirdNode);

        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);

        assertEquals(tree.Count(), 6);
        assertEquals(tree.LeafCount(), 3);
    }

    @Test
    void deleteNode() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(2, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(3, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(4, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(5, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(6, thirdNode);

        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);

        tree.DeleteNode(secondNode);

        assertEquals(tree.Count(), 3);
        assertEquals(tree.LeafCount(), 1);

        assertEquals(0, tree.FindNodesByValue(2).size());
        assertEquals(0, tree.FindNodesByValue(4).size());
        assertEquals(0, tree.FindNodesByValue(5).size());

        tree.DeleteNode(initialNode);

        assertEquals(tree.Count(), 0);
        assertEquals(tree.LeafCount(), 0);

    }

    @Test
    void getAllNodes() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(2, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(3, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(4, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(5, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(6, thirdNode);

        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);

        List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();

        assertTrue(allNodes.contains(initialNode));
        assertTrue(allNodes.contains(secondNode));
        assertTrue(allNodes.contains(thirdNode));
        assertTrue(allNodes.contains(fourthNode));
        assertTrue(allNodes.contains(fifthNode));
        assertTrue(allNodes.contains(sixthNode));
        assertEquals(6, allNodes.size());
    }

    @Test
    void findNodesByValue() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(2, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(3, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(4, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(5, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(6, thirdNode);
        SimpleTreeNode<Integer> sixthNode2 = new SimpleTreeNode<>(6, fifthNode);
        SimpleTreeNode<Integer> thirdNode2 = new SimpleTreeNode<>(3, thirdNode);

        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);
        tree.AddChild(fifthNode, sixthNode2);
        tree.AddChild(thirdNode, thirdNode2);

        List<SimpleTreeNode<Integer>> nodesWithSix = tree.FindNodesByValue(6);
        assertEquals(2, nodesWithSix.size());
        assertTrue(nodesWithSix.contains(sixthNode));
        assertTrue(nodesWithSix.contains(sixthNode2));

        List<SimpleTreeNode<Integer>> nodesWithThree = tree.FindNodesByValue(3);
        assertEquals(2, nodesWithThree.size());
        System.out.println(nodesWithThree.get(0) + "\n" + nodesWithThree.get(1));
        assertTrue(nodesWithThree.contains(thirdNode));
        assertTrue(nodesWithThree.contains(thirdNode2));
    }

    @Test
    void moveNode() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(2, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(3, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(4, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(5, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(6, thirdNode);

        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);

        tree.MoveNode(thirdNode, secondNode);

        assertTrue(secondNode.Children.contains(thirdNode));
        assertEquals(3, secondNode.Children.size());
        assertEquals(6, tree.Count());
        assertEquals(3, tree.LeafCount());
        assertEquals(thirdNode.Parent, secondNode);
        assertTrue(thirdNode.Children.contains(sixthNode));
        assertNotEquals(thirdNode.Parent, initialNode);
        assertFalse(initialNode.Children.contains(thirdNode));

        tree.MoveNode(sixthNode, fifthNode);

        assertEquals(3, tree.LeafCount());
        assertEquals(0, thirdNode.Children.size());
        assertTrue(fifthNode.Children.contains(sixthNode));
        assertEquals(fifthNode, sixthNode.Parent);
    }


    @Test
    void countLeavesWhenOneNode() {
        assertEquals(1, tree.LeafCount());
    }
}