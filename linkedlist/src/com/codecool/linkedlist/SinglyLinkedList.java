package com.codecool.linkedlist;

public class SinglyLinkedList<T> {
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int length;

    SinglyLinkedList() {
        head = tail = null;
        length = 0;
    }

    SinglyLinkedList(T data) {
        this();
        append(data);
    }

    SinglyLinkedList(T[] dataArray) {
        this();
        append(dataArray);
    }

    int size() {
        return length;
    }

    void append(T data) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(data);
        if (head == null) head = tail = node;
        else {
            tail.setNext(node);
            tail = tail.next();
        }
        length++;
    }

    void append(T[] dataArray) {
        for (T data : dataArray) append(data);
    }

    private SinglyLinkedListNode<T> traverseTo(int index) {
        if (index < 0) throw new IllegalArgumentException();
        else if (index >= length) throw new ArrayIndexOutOfBoundsException(index);

        SinglyLinkedListNode<T> pointer = head;
        for (int i = 0; i < index; i++) pointer = pointer.next();

        return pointer;
    }

    T getItemAt(int index) {
        return traverseTo(index).get();
    }

    void setItemAt(T newData, int index) {
        traverseTo(index).set(newData);
    }

    void insert(T data, int index) {
        SinglyLinkedListNode<T> pointer = traverseTo(index - 1);
        SinglyLinkedListNode<T> next = pointer.next();
        pointer.setNext(new SinglyLinkedListNode<>(data));
        pointer.next().setNext(next);
        length++;
    }

    void remove(int index) {
        if (index == 0) head = head.next();
        else {
            SinglyLinkedListNode<T> pointer = traverseTo(index - 1);
            pointer.setNext(pointer.next().next());
        }
        length--;
    }

    boolean remove(T data) {
        int index = find(data);
        if (index < 0) return false;
        remove(index);
        return true;
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
