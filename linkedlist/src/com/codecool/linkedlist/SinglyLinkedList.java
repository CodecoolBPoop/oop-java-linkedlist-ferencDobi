package com.codecool.linkedlist;

public class SinglyLinkedList<T> {
    SinglyLinkedListNode<T> head;
    SinglyLinkedListNode<T> tail;
    int length;

    void append(T data) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);
        if (head == null) head = tail = node;
        else tail.setNext(node);
    }

    void append(T[] dataArray) {
        for (T data : dataArray) append(data);
    }

    private SinglyLinkedListNode<T> traverseTo(int index) {
        if (index < 0) throw new IllegalArgumentException();
        SinglyLinkedListNode<T> pointer = head;
        for (int i = 0; i < index; i++) {
            if (!pointer.hasNext()) throw new ArrayIndexOutOfBoundsException(index);
            pointer = pointer.next();
        }
        return pointer;
    }

    T getItemAt(int index) {
        return traverseTo(index).get();
    }

    void insert(T data, int index) {
        SinglyLinkedListNode<T> pointer = traverseTo(index - 1);
        SinglyLinkedListNode<T> next = pointer.next();
        pointer.setNext(new SinglyLinkedListNode<>(data));
        pointer.next().setNext(next);
    }

    void remove(int index) {
        if (index == 0) head = head.next();
        SinglyLinkedListNode<T> pointer = traverseTo(index - 1);
        pointer.setNext(pointer.next().next());
    }

    void remove(T data) {
        remove(find(data));
    }

    int find(T data) {
        SinglyLinkedListNode<T> pointer = head;
        int i = 0;
        while (!pointer.get().equals(data)) {
            if (!pointer.hasNext()) return -1;
            pointer = pointer.next();
            i++;
        }
        return i;
    }
}
