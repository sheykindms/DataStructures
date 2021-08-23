package lesson16;

import java.util.*;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        Arrays.sort(a);
        int[] tree = new int[a.length];
        recursive(a, tree, 0, a.length - 1, 0);
        return tree;
    }

    private static void recursive(int[] a, int[] tree, int start, int end, int index) {
        if (start > end) {
            return;
        }
        
        int mid = (start + end + 1) / 2;
        tree[index] = a[mid];

        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if (left < tree.length) {
            recursive(a, tree, start, mid - 1, left);
        }
        if (right < tree.length) {
            recursive(a, tree, mid + 1, end, right);
        }
    }

}