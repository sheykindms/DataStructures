package lesson13;

import java.util.*;

public class SimpleTreeNode<T> {
    public T nodeValue; // значение в узле
    public SimpleTreeNode<T> parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        nodeValue = val;
        this.parent = parent;
        children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SimpleTreeNode{" +
                "nodeValue=" + nodeValue +
                '}';
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> root; // корень, может быть null
    private int size = 1;

    public SimpleTree(SimpleTreeNode<T> root) {
        this.root = root;
    }

    public void addChild(SimpleTreeNode<T> parentNode, SimpleTreeNode<T> newChild) {
        parentNode.children.add(newChild);
        size++;
    }

    public void deleteNode(SimpleTreeNode<T> nodeToDelete) {
        List<SimpleTreeNode<T>> nodesToDelete = new ArrayList<>();
        getAllNodes(nodesToDelete, nodeToDelete);
        for (SimpleTreeNode<T> node : nodesToDelete) {
            node = null;
            size--;
        }
    }

    public List<SimpleTreeNode<T>> getAllNodes() {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        getAllNodes(nodes, root);
        return nodes;
    }

    public List<SimpleTreeNode<T>> findNodesByValue(T val) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        findNodesByValue(nodes, root, val);
        return nodes;
    }

    public void moveNode(SimpleTreeNode<T> originalNode, SimpleTreeNode<T> newParent) {
        SimpleTreeNode<T> tempNode = originalNode;
        deleteNode(originalNode);
        tempNode = newParent;
    }

    public int count() {
        return size;
    }

    public int leafCount() {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        leafCount(nodes, root);
        return nodes.size();
    }

    ///////////////////////
    private void leafCount(List<SimpleTreeNode<T>> nodes, SimpleTreeNode<T> root) {
        if (root == null)
            return;
        List<SimpleTreeNode<T>> children = root.children;
        for (SimpleTreeNode<T> node : children) {
            if (node.children.isEmpty()) {
                nodes.add(node);
            } else {
                leafCount(nodes, node);
            }
        }
    }

    private void getAllNodes(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root) {
        if (root == null)
            return;
        list.add(root);
        List<SimpleTreeNode<T>> children = root.children;
        for (SimpleTreeNode<T> node : children) {
            list.add(node);
            if (!node.children.isEmpty()) {
                getAllNodes(list, node);
            }
        }
    }

    private void findNodesByValue(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root, T valueToFind) {
        if (root == null)
            return;
        if (root.nodeValue.equals(valueToFind)) {
            list.add(root);
        }
        List<SimpleTreeNode<T>> children = root.children;
        for (SimpleTreeNode<T> node : children) {
            if (node.children != null) {
                findNodesByValue(list, node, valueToFind);
            }
        }
    }
}