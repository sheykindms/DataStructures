package lesson13;

import java.util.*;


/**
 * Basic tree implementation
 */
class SimpleTree<T> {
    public SimpleTreeNode<T> Root;
    private int size = 0;

    public SimpleTree(SimpleTreeNode<T> root) {
        this.Root = root;
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
    public void AddChild(SimpleTreeNode<T> parentNode, SimpleTreeNode<T> newChild) {
        if (parentNode == null) {
            this.Root = newChild;
        } else {
            parentNode.Children.add(newChild);
            newChild.Parent = parentNode;
        }
        size++;
    }

    /**
     * Deletes given node with all it's children
     *
     * @param nodeToDelete to be deleted
     */
    public void DeleteNode(SimpleTreeNode<T> nodeToDelete) {
        final List<SimpleTreeNode<T>> nodesToDelete = new ArrayList<>();
        GetAllNodes(nodesToDelete, nodeToDelete);
        for (SimpleTreeNode<T> node : nodesToDelete) {
            node.Children = new ArrayList<>();
            if (Root != node) {
                node.Parent.Children.remove(node);
                node = null;
            } else {
                Root = null;
            }
            size--;
        }
    }

    /**
     * Collects all the nodes of SimpleTree to the list
     *
     * @return list of nodes found
     */
    public List<SimpleTreeNode<T>> GetAllNodes() {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        GetAllNodes(nodes, Root);
        return nodes;
    }

    /**
     * Collects all the nodes with given value to the list
     *
     * @param val to be found in SimpleTree
     * @return list of nodes found
     */
    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        FindNodesByValue(nodes, Root, val);
        return nodes;
    }

    /**
     * Moves node with all it's children as another`s node child
     *
     * @param originalNode to be moved
     * @param newParent    to be added as new parent node
     */
    public void MoveNode(SimpleTreeNode<T> originalNode, SimpleTreeNode<T> newParent) {
        final SimpleTreeNode<T> parent = originalNode.Parent;
        originalNode.Parent = newParent;
        parent.Children.remove(originalNode);
        newParent.Children.add(originalNode);
    }

    /**
     * Counts the size of SimpleTree
     *
     * @return int value
     */
    public int Count() {
        return size;
    }

    /**
     * Counts the number of leaves in SimpleTree
     *
     * @return int value
     */
    public int LeafCount() {
        final List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        LeafCount(nodes, Root);
        return nodes.size();
    }

    private boolean isEvenTree(SimpleTree<T> tree) {
        return false;
    }

    private void LeafCount(List<SimpleTreeNode<T>> nodes, SimpleTreeNode<T> root) {
        if (root == null)
            return;
        if (root.Children.isEmpty()) {
            nodes.add(root);
        } else {
            List<SimpleTreeNode<T>> children = root.Children;
            for (SimpleTreeNode<T> node : children) {
                LeafCount(nodes, node);
            }
        }
    }

    private void GetAllNodes(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root) {
        if (root == null) {
            return;
        }
        list.add(root);
        final List<SimpleTreeNode<T>> children = root.Children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.Children.isEmpty()) {
                GetAllNodes(list, node);
            } else {
                list.add(node);
            }
        }
    }

    private void FindNodesByValue(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root, T valueToFind) {
        if (root == null)
            return;
        if (root.NodeValue.equals(valueToFind)) {
            list.add(root);
        }
        final List<SimpleTreeNode<T>> children = root.Children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.Children.isEmpty()) {
                FindNodesByValue(list, node, valueToFind);
            } else if (node.NodeValue.equals(valueToFind)) {
                list.add(node);
            }
        }
    }

    public ArrayList<T> EvenTrees() {
        var list = new ArrayList<T>();
        if (size % 2 != 0 || size == 0) {
            return list;
        }
        var queue = new LinkedList<SimpleTreeNode<T>>();
        queue.add(Root);
        while (!queue.isEmpty()) {
            SimpleTreeNode<T> temp = queue.poll();
            if (temp.Children != null) {
                for (var node : temp.Children) {
                    if (isEvenSubTree(node)) {
                        list.add(temp.NodeValue);
                        list.add(node.NodeValue);
                        if (node.Children != null) {
                            queue.addAll(node.Children);
                        }
                    } else {
                        queue.add(node);
                    }
                }
            }
        }
        return list;
    }

    private boolean isEvenSubTree(SimpleTreeNode<T> node) {
        if (node.Children == null) {
            return false;
        }
        int child = 0;
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            SimpleTreeNode<T> temp = queue.poll();
            child++;
            if (temp.Children != null) {
                queue.addAll(temp.Children);
            }
        }
        return child % 2 == 0;
    }

}

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T nodeValue, SimpleTreeNode<T> parent) {
        this.NodeValue = nodeValue;
        this.Parent = parent;
        Children = new ArrayList<>();
    }
}
