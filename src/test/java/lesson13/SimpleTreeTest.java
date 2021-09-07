package lesson13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {
    private SimpleTree<Integer> tree;
    private SimpleTreeNode<Integer> initialNode;
    private SimpleTreeNode<Integer> secondNode;
    private SimpleTreeNode<Integer> thirdNode;
    private SimpleTreeNode<Integer> fourthNode;
    private SimpleTreeNode<Integer> fifthNode;
    private SimpleTreeNode<Integer> sixthNode;

    @BeforeEach
    void setUp() {
        initialNode = new SimpleTreeNode<>(1, null);
        tree = new SimpleTree<>(initialNode);

        secondNode = new SimpleTreeNode<>(2, initialNode);
        thirdNode = new SimpleTreeNode<>(3, initialNode);
        fourthNode = new SimpleTreeNode<>(4, secondNode);
        fifthNode = new SimpleTreeNode<>(5, secondNode);
        sixthNode = new SimpleTreeNode<>(6, thirdNode);
        tree.AddChild(initialNode, secondNode);
        tree.AddChild(initialNode, thirdNode);
        tree.AddChild(secondNode, fourthNode);
        tree.AddChild(secondNode, fifthNode);
        tree.AddChild(thirdNode, sixthNode);
    }

    @Test
    void addChildrenThenCountNodesAndLeaves() {
        assertEquals(tree.Count(), 6);
        assertEquals(tree.LeafCount(), 3);
    }

    @Test
    void deleteNodeThenCountNodesAndLeaves() {
        tree.DeleteNode(secondNode);

        assertEquals(tree.Count(), 3, "There should be 3 nodes left in tree after deleting secondNode");
        assertEquals(tree.LeafCount(), 1, "There should be 0 leaves left after deleting secondNode");

        assertEquals(0, tree.FindNodesByValue(2).size(), "Second node should have been removed");
        assertEquals(0, tree.FindNodesByValue(4).size(), "Fourth node should have been removed");
        assertEquals(0, tree.FindNodesByValue(5).size(), "Fifth node should have been removed");

        tree.DeleteNode(initialNode);

        assertEquals(tree.Count(), 0, "There should be 0 nodes left in tree after deleting initial one");
        assertEquals(tree.LeafCount(), 0, "There should be 0 leaves left after deleting initial node");

    }

    @Test
    void getAllNodesAndCheckTheSizeOfTree() {
        List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();

        assertTrue(allNodes.contains(initialNode));
        assertTrue(allNodes.contains(secondNode));
        assertTrue(allNodes.contains(thirdNode));
        assertTrue(allNodes.contains(fourthNode));
        assertTrue(allNodes.contains(fifthNode));
        assertTrue(allNodes.contains(sixthNode));
        assertEquals(6, allNodes.size(), "Method should return all the 6 nodes");
    }

    @Test
    void addTwoMoreNodesAndFindAllWithGivenValue() {
        SimpleTreeNode<Integer> sixthNode2 = new SimpleTreeNode<>(6, fifthNode);
        SimpleTreeNode<Integer> thirdNode2 = new SimpleTreeNode<>(3, thirdNode);
        tree.AddChild(fifthNode, sixthNode2);
        tree.AddChild(thirdNode, thirdNode2);

        List<SimpleTreeNode<Integer>> nodesWithSix = tree.FindNodesByValue(6);

        assertEquals(2, nodesWithSix.size(), "Tree should contain two nodes with value = 6");
        assertTrue(nodesWithSix.contains(sixthNode), "Tree should contain exact same sixthNode object");
        assertTrue(nodesWithSix.contains(sixthNode2), "Tree should contain exact same sixthNode2 object");

        List<SimpleTreeNode<Integer>> nodesWithThree = tree.FindNodesByValue(3);

        assertEquals(2, nodesWithThree.size(), "Tree should contain two nodes with value = 3");
        assertTrue(nodesWithThree.contains(thirdNode), "Tree should contain exact same thirdNode object");
        assertTrue(nodesWithThree.contains(thirdNode2), "Tree should contain exact same thirdNode2 object");
    }

    @Test
    void moveNodeAndValidateFields() {
        tree.MoveNode(thirdNode, secondNode);

        assertTrue(secondNode.Children.contains(thirdNode), "Second node should have third node in children list");
        assertEquals(3, secondNode.Children.size(), "Second node should have 3 children after moving");
        assertEquals(3, tree.LeafCount(), "Tree should have three leaves after moving node");
        assertEquals(thirdNode.Parent, secondNode, "Third node should have second node as parent");
        assertTrue(thirdNode.Children.contains(sixthNode), "Third node still should have sixth node in children list");
        assertFalse(initialNode.Children.contains(thirdNode), "After moving third node should be excluded from initial node list");

        tree.MoveNode(sixthNode, fifthNode);

        assertEquals(3, tree.LeafCount(), "After moving sixth node there should be 3 leaves left");
        assertEquals(0, thirdNode.Children.size(), "Third node should have 0 elements in children list");
    }

    @Test
    void countLeavesWhenOneNode() {
        SimpleTree<Integer> oneNodeTree = new SimpleTree<>(new SimpleTreeNode<>(0, null));
        assertEquals(1, oneNodeTree.LeafCount(), "When tree has one node, there should be also one leaf");
    }
}