package lesson15;

class aBST {
    public Integer[] Tree;
    private final int depth;

    public aBST(int depth) {
        int numberOfNodes = (int) (Math.pow(2, depth + 1) - 1);
        Tree = new Integer[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            Tree[i] = null;
        }
        this.depth = depth;
    }

    public Integer findKeyIndex(int key) {
        int currentDepth = 0;
        int index = 0;
        while (currentDepth <= depth) {
            if (Tree[index] == null) {
                return -index;
            } else if (Tree[index] == key) {
                return index;
            }
            if (key < Tree[index]) {
                index = index * 2 + 1;
            } else {
                index = index * 2 + 2;
            }
            currentDepth++;
        }
        return null;
    }

    public int addKey(int key) {
        Integer index = findKeyIndex(key);
        if (index == null) {
            return -1;
        }
        Tree[-index] = key;
        return -index;
    }

}