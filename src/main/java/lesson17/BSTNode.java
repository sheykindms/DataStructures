package lesson17;

import java.util.*;

class BSTNode {
    public int nodeKey;
    public BSTNode parent;
    public BSTNode leftChild;
    public BSTNode rightChild;
    public int Level;

    public BSTNode(int nodeKey, BSTNode parent) {
        this.nodeKey = nodeKey;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void generateTree(int[] a) {
        Arrays.sort(a);
        Root = generateRecursively(a, null, 0, a.length - 1, -1);
    }

    private BSTNode generateRecursively(int[] a, BSTNode root, int start, int end, int level) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        BSTNode bstNode = new BSTNode(a[mid], root);
        bstNode.Level = ++level;

        bstNode.leftChild = generateRecursively(a, bstNode, start, mid - 1, level);
        bstNode.rightChild = generateRecursively(a, bstNode, mid + 1, end, level);

        return bstNode;
    }

    public boolean isBalanced(BSTNode rootNode) {
        if (rootNode == null) {
            return true;
        }
        int left = height(rootNode.leftChild);
        int right = height(rootNode.rightChild);
        
        return Math.abs(left - right) <= 1
                && isBalanced(rootNode.leftChild)
                && isBalanced(rootNode.rightChild);
    }

    private int height(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }
}  