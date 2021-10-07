package lesson14;

import java.util.*;

class BSTNode<T> {
    public int nodeKey;
    public T nodeValue;
    public BSTNode<T> parent;
    public BSTNode<T> leftChild;
    public BSTNode<T> rightChild;

    public BSTNode(int nodeKey, T nodeValue, BSTNode<T> parent) {
        this.nodeKey = nodeKey;
        this.nodeValue = nodeValue;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> node;
    public boolean nodeHasKey;
    public boolean toLeft;

    public BSTFind() {
        node = null;
    }
}

class BST<T> {
    BSTNode<T> root;
    private int size;

    public BST(BSTNode<T> node) {
        root = node;
        size++;
    }

    public BSTFind<T> findNodeByKey(int key) {
        BSTFind<T> desiredNode = new BSTFind<>();
        BSTNode<T> currentNode = root;
        BSTNode<T> temp = currentNode;
        while (currentNode != null) {
            if (currentNode.nodeKey == key) {
                desiredNode.node = currentNode;
                desiredNode.nodeHasKey = true;
                return desiredNode;
            } else if (currentNode.nodeKey > key) {
                temp = currentNode;
                currentNode = currentNode.leftChild;
            } else {
                temp = currentNode;
                currentNode = currentNode.rightChild;
            }
        }
        desiredNode.node = temp;
        desiredNode.nodeHasKey = false;
        desiredNode.toLeft = temp.nodeKey > key;
        return desiredNode;
    }

    public boolean addKeyValue(int key, T val) {
        BSTNode<T> bstNode = new BSTNode<>(key, val, null);
        if (root == null) {
            root = bstNode;
            size++;
            return true;
        }
        BSTFind<T> bstFind = findNodeByKey(key);
        if (bstFind.nodeHasKey) {
            return false;
        }
        bstNode.parent = bstFind.node;
        if (bstFind.toLeft) {
            bstFind.node.leftChild = bstNode;
        } else {
            bstFind.node.rightChild = bstNode;
        }
        size++;
        return true;
    }

    public BSTNode<T> finMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> currentNode = FromNode;
        BSTNode<T> desiredNode = currentNode;
        if (FindMax) {
            while (currentNode != null) {
                desiredNode = currentNode;
                currentNode = currentNode.rightChild;
            }
        } else {
            while (currentNode != null) {
                desiredNode = currentNode;
                currentNode = currentNode.leftChild;
            }
        }
        return desiredNode;
    }

    public boolean deleteNodeByKey(int key) {
        BSTFind<T> bstNodeFound = findNodeByKey(key);
        if (!bstNodeFound.nodeHasKey) {
            return false;
        }
        BSTNode<T> bstNodeToDelete = bstNodeFound.node;
        if (bstNodeToDelete.rightChild == null) {
            if (bstNodeToDelete.parent == null) {
                root = bstNodeToDelete.leftChild;
            } else {
                if (bstNodeToDelete.parent.leftChild == bstNodeToDelete) {
                    bstNodeToDelete.parent.leftChild = bstNodeToDelete.leftChild;
                } else {
                    bstNodeToDelete.parent.rightChild = bstNodeToDelete.leftChild;
                }
                if (bstNodeToDelete.leftChild != null) {
                    bstNodeToDelete.leftChild.parent = bstNodeToDelete.parent;
                }
            }
        } else {
            BSTNode<T> maxRightCandidate = bstNodeToDelete.rightChild;
            while (maxRightCandidate.leftChild != null) {
                maxRightCandidate = maxRightCandidate.leftChild;
            }
            if (maxRightCandidate.rightChild != null) {
                maxRightCandidate.parent.leftChild = maxRightCandidate.rightChild;
            } else {
                maxRightCandidate.parent.leftChild = null;
            }
            bstNodeToDelete.nodeKey = maxRightCandidate.nodeKey;
            bstNodeToDelete.nodeValue = maxRightCandidate.nodeValue;
        }
        size--;
        return true;
    }

    public int count() {
        return size;
    }

    public ArrayList<BSTNode<T>> wideAllNodes() {
        if (root == null) {
            return new ArrayList<>();
        }
        var nodes = new ArrayList<BSTNode<T>>();
        var queue = new LinkedList<BSTNode<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            nodes.add(node);
            if (node.leftChild != null) {
                queue.add(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild);
            }
        }
        return nodes;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<BSTNode> nodes = new ArrayList<>();
        if (order == 0) {
            inorder(nodes, root);
        }
        if (order == 1) {
            postorder(nodes, root);
        }
        if (order == 2) {
            preorder(nodes, root);
        }
        return nodes;
    }

    private void preorder(ArrayList<BSTNode> nodes, BSTNode<T> node) {
        nodes.add(node);
        if (node.leftChild != null) {
            preorder(nodes, node.leftChild);
        }
        if (node.rightChild != null) {
            preorder(nodes, node.rightChild);
        }
    }

    private void postorder(ArrayList<BSTNode> nodes, BSTNode<T> node) {
        if (node.leftChild != null) {
            postorder(nodes, node.leftChild);
        }
        if (node.rightChild != null) {
            postorder(nodes, node.rightChild);
        }
        nodes.add(node);
    }

    private void inorder(ArrayList<BSTNode> nodes, BSTNode<T> node) {
        if (node.leftChild != null) {
            inorder(nodes, node.leftChild);
        }
        nodes.add(node);
        if (node.rightChild != null) {
            inorder(nodes, node.rightChild);
        }
    }
}