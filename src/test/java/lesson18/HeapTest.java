package lesson18;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    Heap heap;

    @BeforeEach
    void setup() {
        heap = new Heap();
        heap.makeHeap(new int[] {31, 22, 30, 17, 21, 25, 29, 10, 16, 18, 20, 14, 24, 26, 28}, 3);
    }

    @Test
    void getMax() {
        assertEquals(31, heap.getMax());
        assertEquals(30, heap.heapArray[0]);
        assertEquals(28, heap.heapArray[heap.lastIndex]);
    }
}