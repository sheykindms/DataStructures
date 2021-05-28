package lesson01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    LinkedList list;

    @BeforeEach
    void setUp() {
        list = new LinkedList();
    }

    void fillListWithOneElement() {
        list.addInTail(new Node(1729));
    }

    void fillListWithManyElements() {
        list.addInTail(new Node(2));
        list.addInTail(new Node(11));
        list.addInTail(new Node(17));
        list.addInTail(new Node(17));
        list.addInTail(new Node(29));
        list.addInTail(new Node(41));
    }

    @Test
    void removeFromListWithOneNode() {
        fillListWithOneElement();
        list.remove(1729);
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void removeFromListWithManyNodes() {
        fillListWithManyElements();
        list.remove(2);
        list.remove(41);
        list.remove(17);
        assertEquals(list.find(11), list.head);
        assertEquals(list.find(29), list.tail);
    }

    @Test
    void removeAllFromListWithOneNode() {
        fillListWithOneElement();
        list.removeAll(1729);
        assertNull(list.find(1729));
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void removeAllFromListWithManyNodes() {
        fillListWithManyElements();
        list.removeAll(17);
        list.removeAll(41);
        assertEquals(list.find(2), list.head);
        assertEquals(list.find(29), list.tail);
        assertNull(list.find(17));
    }

    @Test
    void removeFromListIfElementNotExists() {
        fillListWithManyElements();
        list.remove(67);
        assertEquals(list.find(2),list.head);
        assertEquals(list.find(41),list.tail);
        assertEquals(6, list.count());
    }

    @Test
    void removeAllFromListIfElementNotExists() {
        fillListWithManyElements();
        list.removeAll(67);
        assertEquals(list.find(2),list.head);
        assertEquals(list.find(41),list.tail);
        assertEquals(6, list.count());
    }

    @Test
    void removeAllFromListFromHeadAndTail() {
        fillListWithManyElements();
        list.addInTail(new Node(2));
        list.removeAll(2);
        assertEquals(list.find(11), list.head);
        assertEquals(list.find(41), list.tail);
    }

    @Test
    void removeAllFromListWithTwoElements() {
        list.addInTail(new Node(2));
        list.addInTail(new Node(2));
        list.removeAll(2);
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void clear() {
        fillListWithManyElements();
        list.clear();
        assertNull(list.head);
        assertNull(list.tail);
        assertEquals(0, list.count());
    }

    @Test
    void clearEmptyList() {
        list.clear();
        assertNull(list.head);
        assertNull(list.tail);
    }

    @Test
    void count() {
        fillListWithManyElements();
        assertEquals(6, list.count());
    }

    @Test
    void countWhenListEmpty() {
        assertEquals(0, list.count());
    }

    @Test
    void countWhenAddTwoMoreElements() {
        fillListWithOneElement();
        Node toInsertInTail = new Node(17);
        Node toInsertAfter = new Node(29);
        list.addInTail(toInsertInTail);
        list.insertAfter(toInsertInTail, toInsertAfter);
        assertEquals(list.find(1729), list.head);
        assertEquals(list.find(29), list.tail);
        assertEquals(3, list.count());
    }

    @Test
    void insertAfterLast() {
        fillListWithManyElements();
        Node toInsert = new Node(59);
        Node after = list.find(41);
        list.insertAfter(after, toInsert);
        assertEquals(toInsert, list.tail);
    }

    @Test
    void insertAfterWhenListEmpty() {
        Node toInsert = new Node(1);
        list.insertAfter(null, toInsert);
        assertEquals(toInsert, list.head);
        assertEquals(toInsert, list.tail);
    }

    @Test
    void findAll() {
        fillListWithManyElements();
        assertEquals(2, list.findAll(17).size());
        assertEquals(1, list.findAll(41).size());
        assertEquals(0, list.findAll(67).size());
    }
}