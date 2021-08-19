package lesson14;

import java.util.*;

class BSTNode<T> {
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> Node;
    public boolean NodeHasKey;
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root;
    private int size;

    public BST(BSTNode<T> node) {
        Root = node;
        size++;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> desiredNode = new BSTFind<>();
        BSTNode<T> currentNode = Root;
        BSTNode<T> temp = currentNode;
        while (currentNode != null) {
            if (currentNode.NodeKey == key) {
                desiredNode.Node = currentNode;
                desiredNode.NodeHasKey = true;
                return desiredNode;
            } else if (currentNode.NodeKey > key) {
                temp = currentNode;
                currentNode = currentNode.LeftChild;
            } else {
                temp = currentNode;
                currentNode = currentNode.RightChild;
            }
        }
        desiredNode.Node = temp;
        desiredNode.NodeHasKey = false;
        desiredNode.ToLeft = temp.NodeKey > key;
        return desiredNode;
    }

    public boolean AddKeyValue(int key, T val) {
        BSTNode<T> bstNode = new BSTNode<>(key, val, null);
        if (Root == null) {
            Root = bstNode;
            size++;
            return true;
        }
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (bstFind.NodeHasKey) {
            return false;
        }
        bstNode.Parent = bstFind.Node;
        if (bstFind.ToLeft) {
            bstFind.Node.LeftChild = bstNode;
        } else {
            bstFind.Node.RightChild = bstNode;
        }
        size++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> currentNode = FromNode;
        BSTNode<T> desiredNode = currentNode;
        if (FindMax) {
            while (currentNode != null) {
                desiredNode = currentNode;
                currentNode = currentNode.RightChild;
            }
        } else {
            while (currentNode != null) {
                desiredNode = currentNode;
                currentNode = currentNode.LeftChild;
            }
        }
        return desiredNode;
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> bstNodeFound = FindNodeByKey(key);
        if (!bstNodeFound.NodeHasKey) {
            return false;
        }
        BSTNode<T> bstNodeToDelete = bstNodeFound.Node;
        if (bstNodeToDelete.RightChild == null) {
            if (bstNodeToDelete.Parent == null) {
                Root = bstNodeToDelete.LeftChild;
            } else {
                if (bstNodeToDelete.Parent.LeftChild == bstNodeToDelete) {
                    bstNodeToDelete.Parent.LeftChild = bstNodeToDelete.LeftChild;
                } else {
                    bstNodeToDelete.Parent.RightChild = bstNodeToDelete.LeftChild;
                }
                if (bstNodeToDelete.LeftChild != null) {
                    bstNodeToDelete.LeftChild.Parent = bstNodeToDelete.Parent;
                }
            }
        } else {
            BSTNode<T> maxRightCandidate = bstNodeToDelete.RightChild;
            while (maxRightCandidate.LeftChild != null) {
                maxRightCandidate = maxRightCandidate.LeftChild;
            }
            if (maxRightCandidate.RightChild != null) {
                maxRightCandidate.Parent.LeftChild = maxRightCandidate.RightChild;
            } else {
                maxRightCandidate.Parent.LeftChild = null;
            }
            bstNodeToDelete.NodeKey = maxRightCandidate.NodeKey;
            bstNodeToDelete.NodeValue = maxRightCandidate.NodeValue;
        }
        size--;
        return true;
    }

    public int Count() {
        return size;
    }

    public ArrayList<BSTNode<T>> WideAllNodes() {
        if (Root == null) {
            return new ArrayList<>();
        }
        ArrayList<BSTNode<T>> nodes = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(Root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            nodes.add(node);
            if (node.LeftChild != null) {
                queue.add(node.LeftChild);
            }
            if (node.RightChild != null) {
                queue.add(node.RightChild);
            }
        }
        return nodes;
    }

    public ArrayList<BSTNode<T>> DeepAllNodes(int order) {
        if (Root == null) {
            return new ArrayList<>();
        }
        ArrayList<BSTNode<T>> nodes = new ArrayList<>();
        if (order == 0) {
            inorder(nodes, Root);
        }
        if (order == 1) {
            postorder(nodes, Root);
        }
        if (order == 2) {
            preorder(nodes, Root);
        }
        return nodes;
    }

    private void preorder(ArrayList<BSTNode<T>> nodes, BSTNode<T> node) {
        nodes.add(node);
        if (node.LeftChild != null) {
            preorder(nodes, Root.LeftChild);
        }
        if (node.RightChild != null) {
            preorder(nodes, Root.RightChild);
        }
    }

    private void postorder(ArrayList<BSTNode<T>> nodes, BSTNode<T> node) {
        if (node.LeftChild != null) {
            postorder(nodes, Root.LeftChild);
        }
        if (node.RightChild != null) {
            postorder(nodes, Root.RightChild);
        }
        nodes.add(node);
    }

    private void inorder(ArrayList<BSTNode<T>> nodes, BSTNode<T> node) {
        if (node.LeftChild != null) {
            inorder(nodes, Root.LeftChild);
        }
        nodes.add(node);
        if (node.RightChild != null) {
            inorder(nodes, Root.RightChild);
        }
    }
}