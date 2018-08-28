package com.codecool.linkedlist;

public class SinglyLinkedListNode<T> {
    private T data;
    private SinglyLinkedListNode<T> next;

    SinglyLinkedListNode(T data) {
        this.data = data;
        next = null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public SinglyLinkedListNode<T> next() {
        return next;
    }

    public void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }

    public T get() {
        return data;
    }

    public void set(T data) {
        this.data = data;
    }
}
