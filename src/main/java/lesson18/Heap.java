package lesson18;

class Heap {
    public int[] heapArray; // хранит неотрицательные числа-ключи
    public int lastIndex;

    public Heap() {
        heapArray = null;
        lastIndex = 0;
    }

    public void makeHeap(int[] a, int depth) {
        int heapLength = (int) (Math.pow(2, depth + 1) - 1);
        heapArray = new int[heapLength];
        for (int key : a) {
            add(key);
        }
    }

    public int getMax() {
        if (lastIndex == 0) {
            return -1;
        }
        int root = heapArray[0];
        heapArray[0] = heapArray[lastIndex - 1];
        int index = 0;
        lastIndex--;
        while (index * 2 + 2 < heapArray.length) {
            int nextIndex = index * 2 + 2;
            if (heapArray[index * 2 + 1] > heapArray[index * 2 + 2]) {
                nextIndex = index * 2 + 1;
            }
            if (heapArray[index] < heapArray[nextIndex]) {
                int temp = heapArray[index];
                heapArray[index] = heapArray[nextIndex];
                heapArray[nextIndex] = temp;
                index = nextIndex;
            } else {
                break;
            }
        }
        return root;
    }

    public boolean add(int key) {
        if (lastIndex == heapArray.length) {
            return false;
        }
        heapArray[lastIndex] = key;
        int index = lastIndex;
        int step = (index - 1) / 2;
        lastIndex++;
        while (index > 0 && heapArray[index] > heapArray[step]) {
            int temp = heapArray[index];
            heapArray[index] = heapArray[step];
            heapArray[step] = temp;
            index = step;
            step = (index - 1) / 2;
        }
        return true;
    }

}