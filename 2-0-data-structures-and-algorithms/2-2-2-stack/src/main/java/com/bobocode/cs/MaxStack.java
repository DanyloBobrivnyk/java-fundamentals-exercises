package com.bobocode.cs;

public interface MaxStack<T extends Comparable<T>> {
    void push(T element);

    T pop();

    T peek();

    T popMax();

    T peekMax();

    int size();

    boolean isEmpty();
}
