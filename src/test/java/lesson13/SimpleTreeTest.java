package lesson13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {

    private SimpleTree<Integer> tree;
    private SimpleTreeNode<Integer> initialNode;

    @BeforeEach
    void setUp() {
        initialNode= new SimpleTreeNode<>(10, null);
        tree = new SimpleTree<>(initialNode);
    }

    @Test
    void addChild() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(10, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(20, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(30, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(40, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(50, thirdNode);

        tree.addChild(null, initialNode);
        tree.addChild(initialNode, secondNode);
        tree.addChild(initialNode, thirdNode);
        tree.addChild(secondNode, fourthNode);
        tree.addChild(secondNode, fifthNode);
        tree.addChild(thirdNode, sixthNode);

        assertEquals(tree.count(), 6);
        assertEquals(tree.leafCount(), 3);
    }

    @Test
    void deleteNode() {
        SimpleTreeNode<Integer> secondNode = new SimpleTreeNode<>(10, initialNode);
        SimpleTreeNode<Integer> thirdNode = new SimpleTreeNode<>(20, initialNode);
        SimpleTreeNode<Integer> fourthNode = new SimpleTreeNode<>(30, secondNode);
        SimpleTreeNode<Integer> fifthNode = new SimpleTreeNode<>(40, secondNode);
        SimpleTreeNode<Integer> sixthNode = new SimpleTreeNode<>(50, thirdNode);

        tree.addChild(initialNode, secondNode);
        tree.addChild(initialNode, thirdNode);
        tree.addChild(secondNode, fourthNode);
        tree.addChild(secondNode, fifthNode);
        tree.addChild(thirdNode, sixthNode);

        System.out.println(secondNode);
        System.out.println(thirdNode);
        System.out.println(fourthNode);
        System.out.println(fifthNode);
        System.out.println(sixthNode);
//        tree.deleteNode(secondNode);
//
//
//        assertEquals(tree.count(), 3);
//        assertEquals(tree.leafCount(), 1);

    }

    @Test
    void getAllNodes() {
    }

    @Test
    void findNodesByValue() {
    }

    @Test
    void moveNode() {
    }

    @Test
    void leafCount() {
    }
}