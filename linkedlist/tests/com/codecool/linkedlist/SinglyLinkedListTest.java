package com.codecool.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    private SinglyLinkedList<String> sll;
    private SinglyLinkedList<Integer> slli;

    @BeforeEach
    void setUp() {
        sll = new SinglyLinkedList<>(new String[] { "one", "two", "three" });
        slli = new SinglyLinkedList<>(new Integer[] { 6, 6, 12, 5 });
    }

    @Test
    void testSize() {
        assertEquals(3, sll.size());
    }

    @Test
    void testGetItemAt() {
        assertEquals("one", sll.getItemAt(0));
        assertEquals("three", sll.getItemAt(2));
        assertEquals(new Integer(5), slli.getItemAt(3));
    }

    @Test
    void testAppend() {
        slli = new SinglyLinkedList<>(3);
        slli.append(4);
        slli.append(new Integer[] { 1, 5 });
        assertEquals(4, slli.size());
        assertEquals(new Integer(1), slli.getItemAt(2));
    }

    @Test
    void testInsert() {
        slli.insert(20, 2);
        assertEquals(new Integer(20), slli.getItemAt(2));
        assertEquals(new Integer(12), slli.getItemAt(3));
    }

    @Test
    void testNegativeIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> slli.getItemAt(-5));
    }

    @Test
    void testOutOfBoundsThrowsException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> slli.getItemAt(5));
    }

    @Test
    void testFind() {
        assertEquals(2, slli.find(12));
        assertEquals(0, slli.find(6));
        assertEquals(3, slli.find(5));
    }

    @Test
    void testRemoveByIndex() {
        slli.remove(2);
        assertEquals(new Integer(5), slli.getItemAt(2));
    }

    @Test
    void testRemoveData() {
        sll.remove("two");
        assertEquals("three", sll.getItemAt(1));
        assertFalse(sll.remove("four"));
        assertTrue(slli.remove(new Integer(6)));
        assertEquals(new Integer(6), slli.getItemAt(0));
        assertEquals(new Integer(12), slli.getItemAt(1));
    }
}