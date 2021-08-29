package lesson18;

class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи
    public int lastIndex;

    public Heap() {
        HeapArray = null;
        lastIndex = 0;
    }

    public void MakeHeap(int[] a, int depth) {
        int heapLength = (int) (Math.pow(2, depth + 1) - 1);
        HeapArray = new int[heapLength];
        for (int key : a) {
            Add(key);
        }
    }

    public int GetMax() {
        if (lastIndex == 0) {
            return -1;
        }
        int root = HeapArray[0];
        HeapArray[0] = HeapArray[lastIndex - 1];
        int index = 0;
        lastIndex--;
        while (index * 2 + 2 < HeapArray.length) {
            int nextIndex = index * 2 + 2;
            if (HeapArray[index * 2 + 1] > HeapArray[index * 2 + 2]) {
                nextIndex = index * 2 + 1;
            }
            if (HeapArray[index] < HeapArray[nextIndex]) {
                int temp = HeapArray[index];
                HeapArray[index] = HeapArray[nextIndex];
                HeapArray[nextIndex] = temp;
                index = nextIndex;
            } else {
                break;
            }
        }
        return root;
    }

    public boolean Add(int key) {
        if (lastIndex == HeapArray.length) {
            return false;
        }
        HeapArray[lastIndex] = key;
        int index = lastIndex;
        int step = (index - 1) / 2;
        lastIndex++;
        while (index > 0 && HeapArray[index] > HeapArray[step]) {
            int temp = HeapArray[index];
            HeapArray[index] = HeapArray[step];
            HeapArray[step] = temp;
            index = step;
            step = (index - 1) / 2;
        }
        return true;
    }

}