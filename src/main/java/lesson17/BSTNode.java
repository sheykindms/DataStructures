package lesson17;

import java.util.*;

class BSTNode {
    public int NodeKey;
    public BSTNode Parent;
    public BSTNode LeftChild;
    public BSTNode RightChild;
    public int Level;

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root;

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
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

        bstNode.LeftChild = generateRecursively(a, bstNode, start, mid - 1, level);
        bstNode.RightChild = generateRecursively(a, bstNode, mid + 1, end, level);

        return bstNode;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) {
            return true;
        }
        int left = height(root_node.LeftChild);
        int right = height(root_node.RightChild);
        
        return Math.abs(left - right) <= 1
                && IsBalanced(root_node.LeftChild)
                && IsBalanced(root_node.RightChild);
    }

    private int height(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.LeftChild), height(node.RightChild));
    }
}  