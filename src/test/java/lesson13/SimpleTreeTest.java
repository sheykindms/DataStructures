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
        tree.addChild(initialNode, secondNode);
        tree.addChild(initialNode, thirdNode);
        tree.addChild(secondNode, fourthNode);
        tree.addChild(secondNode, fifthNode);
        tree.addChild(thirdNode, sixthNode);
    }

    @Test
    void addChildrenThenCountNodesAndLeaves() {
        assertEquals(tree.count(), 6);
        assertEquals(tree.leafCount(), 3);
    }

    @Test
    void deleteNodeThenCountNodesAndLeaves() {
        tree.deleteNode(secondNode);

        assertEquals(tree.count(), 3, "There should be 3 nodes left in tree after deleting secondNode");
        assertEquals(tree.leafCount(), 1, "There should be 0 leaves left after deleting secondNode");

        assertEquals(0, tree.findNodesByValue(2).size(), "Second node should have been removed");
        assertEquals(0, tree.findNodesByValue(4).size(), "Fourth node should have been removed");
        assertEquals(0, tree.findNodesByValue(5).size(), "Fifth node should have been removed");

        tree.deleteNode(initialNode);

        assertEquals(tree.count(), 0, "There should be 0 nodes left in tree after deleting initial one");
        assertEquals(tree.leafCount(), 0, "There should be 0 leaves left after deleting initial node");

    }

    @Test
    void getAllNodesAndCheckTheSizeOfTree() {
        List<SimpleTreeNode<Integer>> allNodes = tree.getAllNodes();

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
        tree.addChild(fifthNode, sixthNode2);
        tree.addChild(thirdNode, thirdNode2);

        List<SimpleTreeNode<Integer>> nodesWithSix = tree.findNodesByValue(6);

        assertEquals(2, nodesWithSix.size(), "Tree should contain two nodes with value = 6");
        assertTrue(nodesWithSix.contains(sixthNode), "Tree should contain exact same sixthNode object");
        assertTrue(nodesWithSix.contains(sixthNode2), "Tree should contain exact same sixthNode2 object");

        List<SimpleTreeNode<Integer>> nodesWithThree = tree.findNodesByValue(3);

        assertEquals(2, nodesWithThree.size(), "Tree should contain two nodes with value = 3");
        assertTrue(nodesWithThree.contains(thirdNode), "Tree should contain exact same thirdNode object");
        assertTrue(nodesWithThree.contains(thirdNode2), "Tree should contain exact same thirdNode2 object");
    }

    @Test
    void moveNodeAndValidateFields() {
        tree.moveNode(thirdNode, secondNode);

        assertTrue(secondNode.children.contains(thirdNode), "Second node should have third node in children list");
        assertEquals(3, secondNode.children.size(), "Second node should have 3 children after moving");
        assertEquals(3, tree.leafCount(), "Tree should have three leaves after moving node");
        assertEquals(thirdNode.parent, secondNode, "Third node should have second node as parent");
        assertTrue(thirdNode.children.contains(sixthNode), "Third node still should have sixth node in children list");
        assertFalse(initialNode.children.contains(thirdNode), "After moving third node should be excluded from initial node list");

        tree.moveNode(sixthNode, fifthNode);

        assertEquals(3, tree.leafCount(), "After moving sixth node there should be 3 leaves left");
        assertEquals(0, thirdNode.children.size(), "Third node should have 0 elements in children list");
    }

    @Test
    void countLeavesWhenOneNode() {
        SimpleTree<Integer> oneNodeTree = new SimpleTree<>(new SimpleTreeNode<>(0, null));
        assertEquals(1, oneNodeTree.leafCount(), "When tree has one node, there should be also one leaf");
    }
}