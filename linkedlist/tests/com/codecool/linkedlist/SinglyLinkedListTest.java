package com.codecool.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

class SinglyLinkedListTest {
    private SinglyLinkedList<String> sll;
    private SinglyLinkedList<Integer> slli;

    @BeforeEach
    void setUp() {
        sll = new SinglyLinkedList<>(new String[] { "one", "two", "three" });
        slli = new SinglyLinkedList<>(new Integer[] { 6, 6, 12, 5 });
    }

    @TestFactory
    DynamicTest[] testSize() {
        return new DynamicTest[] {
                dynamicTest("testSizeForEmptyConstructor()",
                        () -> assertEquals(0, new SinglyLinkedList<Integer>().size())),
                dynamicTest("testSizeForSingleDataConstructor()",
                        () -> assertEquals(1, new SinglyLinkedList<>(Math.random()).size())),
                dynamicTest("testSizeForDataArrayConstructor()",
                        () -> assertEquals(3, sll.size())),
                dynamicTest("testSizeAfterAppending()",
                        () -> {
                    slli.append(new Integer[] { 77, 128, 99 });
                    assertEquals(7, slli.size());
                        }),
                dynamicTest("testSizeAfterRemoving()",
                        () -> {
                    slli.remove(6);
                    slli.remove(new Integer(77));
                    slli.remove(new Integer(127));
                    assertEquals(5, slli.size());
                        })
        };
    }

    @Test
    void testGetItemAt() {
        assertEquals("one", sll.getItemAt(0));
        assertEquals("three", sll.getItemAt(2));
        assertEquals(new Integer(5), slli.getItemAt(3));
    }

    @Test
    void testSetItemAt() {
        sll.setItemAt("2", 1);
        slli.setItemAt(7, 0);
        assertEquals("2", sll.getItemAt(1));
        assertEquals(new Integer(7), slli.getItemAt(0));
        assertEquals(4, slli.size());
    }

    @Test
    void testAppendOneElement() {
        slli.append(4);
        assertEquals(5, slli.size());
        assertEquals(new Integer(4), slli.getItemAt(4));
    }

    @Test
    void testAppendMultipleElements() {
        slli.append(new Integer[] { 1, 5 });
        assertEquals(6, slli.size());
        assertEquals(new Integer(5), slli.getItemAt(5));
    }

    @Test
    void testInsert() {
        slli.insert(20, 2);
        assertEquals(new Integer(20), slli.getItemAt(2));
        assertEquals(new Integer(12), slli.getItemAt(3));
    }

    @Test
    void testNegativeIndexThrowsException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> slli.getItemAt(-5));
        assertEquals("Illegal argument: negative index", exception.getMessage());
    }

    @Test
    void testOutOfBoundsIndexThrowsException() {
        Throwable exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> slli.getItemAt(5));
        assertEquals("Array index out of range: 5", exception.getMessage());
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

    @Test
    void testStream() {
        assertEquals(29, slli.stream().mapToInt(n -> n).sum());
        assertTrue(sll.stream().anyMatch(s -> s.equals("two")));
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testStreamElements(String argument) {
        assertTrue(argument.matches("one|two|three"));
    }

    private static Stream<String> stringProvider() {
        return new SinglyLinkedList<>(new String[] {"one", "two", "three"}).stream();
    }

}