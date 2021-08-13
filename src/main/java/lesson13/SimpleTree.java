package lesson13;

import java.util.*;


/**
 * Basic tree implementation
 */
public class SimpleTree<T> {
    public SimpleTreeNode<T> root;
    private int size = 0;
    public SimpleTree(SimpleTreeNode<T> root) {
        this.root = root;
        if (root != null) {
            size++;
        }
    }

    /**
     * Adds new node to SimpleTree
     *
     * @param parentNode to be added as parent to newChild
     * @param newChild   to be added to SimpleTree
     */
    public void addChild(SimpleTreeNode<T> parentNode, SimpleTreeNode<T> newChild) {
        if (parentNode == null) {
            this.root = newChild;
        } else {
            parentNode.children.add(newChild);
            newChild.parent = parentNode;
        }
        size++;
    }

    /**
     * Deletes given node with all it's children
     *
     * @param nodeToDelete to be deleted
     */
    public void deleteNode(SimpleTreeNode<T> nodeToDelete) {
        final List<SimpleTreeNode<T>> nodesToDelete = new ArrayList<>();
        getAllNodes(nodesToDelete, nodeToDelete);
        for (SimpleTreeNode<T> node : nodesToDelete) {
            node.children = new ArrayList<>();
            if (root != node) {
                node.parent.children.remove(node);
                node = null;
            } else {
                root = null;
            }
            size--;
        }
    }

    /**
     * Collects all the nodes of SimpleTree to the list
     *
     * @return list of nodes found
     */
    public List<SimpleTreeNode<T>> getAllNodes() {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        getAllNodes(nodes, root);
        return nodes;
    }

    /**
     * Collects all the nodes with given value to the list
     *
     * @param val to be found in SimpleTree
     * @return list of nodes found
     */
    public List<SimpleTreeNode<T>> findNodesByValue(T val) {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        findNodesByValue(nodes, root, val);
        return nodes;
    }

    /**
     * Moves node with all it's children as another`s node child
     *
     * @param originalNode to be moved
     * @param newParent    to be added as new parent node
     */
    public void moveNode(SimpleTreeNode<T> originalNode, SimpleTreeNode<T> newParent) {
        final SimpleTreeNode<T> parent = originalNode.parent;
        originalNode.parent = newParent;
        parent.children.remove(originalNode);
        newParent.children.add(originalNode);
    }

    /**
     * Counts the size of SimpleTree
     *
     * @return int value
     */
    public int count() {
        return size;
    }

    /**
     * Counts the number of leaves in SimpleTree
     *
     * @return int value
     */
    public int leafCount() {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        leafCount(nodes, root);
        return nodes.size();
    }

    private void leafCount(List<SimpleTreeNode<T>> nodes, SimpleTreeNode<T> root) {
        if (root == null)
            return;
        if (root.children.isEmpty()) {
            nodes.add(root);
        } else {
            List<SimpleTreeNode<T>> children = root.children;
            for (SimpleTreeNode<T> node : children) {
                leafCount(nodes, node);
            }
        }
    }

    private void getAllNodes(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root) {
        if (root == null) {
            return;
        }
        list.add(root);
        final List<SimpleTreeNode<T>> children = root.children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.children.isEmpty()) {
                getAllNodes(list, node);
            } else {
                list.add(node);
            }
        }
    }

    private void findNodesByValue(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root, T valueToFind) {
        if (root == null)
            return;
        if (root.nodeValue.equals(valueToFind)) {
            list.add(root);
        }
        final List<SimpleTreeNode<T>> children = root.children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.children.isEmpty()) {
                findNodesByValue(list, node, valueToFind);
            } else if (node.nodeValue.equals(valueToFind)) {
                list.add(node);
            }
        }
    }

}
class SimpleTreeNode<T> {
    public T nodeValue;
    public SimpleTreeNode<T> parent;
    public List<SimpleTreeNode<T>> children;

    public SimpleTreeNode(T nodeValue, SimpleTreeNode<T> parent) {
        this.nodeValue = nodeValue;
        this.parent = parent;
        children = new ArrayList<>();
    }
}
