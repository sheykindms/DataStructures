package lesson13;

import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        this.Parent = parent;
        Children = new ArrayList<>();
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root;
    private int size = 0;

    public SimpleTree(SimpleTreeNode<T> root) {
        this.Root = root;
        if (root != null) {
            size++;
        }
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode == null) {
            this.Root = NewChild;
        } else {
            ParentNode.Children.add(NewChild);
            NewChild.Parent = ParentNode;
        }
        size++;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        List<SimpleTreeNode<T>> nodesToDelete = new ArrayList<>();
        GetAllNodes(nodesToDelete, NodeToDelete);
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

    public List<SimpleTreeNode<T>> GetAllNodes() {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        GetAllNodes(nodes, Root);
        return nodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        FindNodesByValue(nodes, Root, val);
        return nodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        SimpleTreeNode<T> parent = OriginalNode.Parent;
        OriginalNode.Parent = NewParent;
        parent.Children.remove(OriginalNode);
        NewParent.Children.add(OriginalNode);
    }

    public int Count() {
        return size;
    }

    public int LeafCount() {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        LeafCount(nodes, Root);
        return nodes.size();
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
        System.out.println(root);
        List<SimpleTreeNode<T>> children = root.Children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.Children.isEmpty()) {
                GetAllNodes(list, node);
            } else {
                list.add(node);
                System.out.println(node);
            }
        }
    }

    private void FindNodesByValue(List<SimpleTreeNode<T>> list, SimpleTreeNode<T> root, T valueToFind) {
        if (root == null)
            return;
        if (root.NodeValue.equals(valueToFind)) {
            list.add(root);
        }
        List<SimpleTreeNode<T>> children = root.Children;
        for (SimpleTreeNode<T> node : children) {
            if (!node.Children.isEmpty()) {
                FindNodesByValue(list, node, valueToFind);
            } else if (node.NodeValue.equals(valueToFind)) {
                list.add(node);
            }
        }
    }
}