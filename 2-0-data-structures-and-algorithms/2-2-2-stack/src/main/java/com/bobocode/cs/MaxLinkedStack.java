package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import java.util.stream.Stream;

public class MaxLinkedStack<T extends Comparable<T>> implements MaxStack<T> {

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        public static <T> Node<T> valueOf(T element) {
            return new Node<>(element);
        }

        public Node(T element) {
            this.element = element;
        }
    }

    private LinkedStack<T> stack;
    private LinkedStack<T> maxStack;


    public MaxLinkedStack() {
        this.stack = new LinkedStack<>();
        this.maxStack = new LinkedStack<>();
    }

    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T extends Comparable<T>> MaxLinkedStack<T> of(T... elements) {
        MaxLinkedStack<T> maxLinkedStack = new MaxLinkedStack<>();
        Stream.of(elements).forEach(maxLinkedStack::push);
        return maxLinkedStack;
    }

    /**
     * Push element x onto stack
     * Time: O(1), Space: O(1)
     */
    @Override
    public void push(T element) {
        stack.push(element);

        if (maxStack.isEmpty() || element.compareTo(maxStack.peek()) >= 0) {
            maxStack.push(element);
        }
    }

    /**
     * Time: O(1), Space: O(1)
     * */
    @Override
    public T pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        T top = stack.pop();

        if (!maxStack.isEmpty() && maxStack.peek().equals(top)) {
            maxStack.pop();
        }


        return top;
    }

    @Override
    public T peek() {
        return stack.peek();
    }

    /**
     * Time: O(n), Space: O(n)
     * */
    @Override
    public T popMax() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        T max = maxStack.peek();
        LinkedStack<T> tmp = new LinkedStack<>();

        while (!stack.peek().equals(max)) {
            tmp.push(stack.pop());
        }

        stack.pop();
        maxStack.pop();

        while (!tmp.isEmpty()){
            push(tmp.pop());
        }

        return max;
    }

    @Override
    public T peekMax() {
        return maxStack.peek();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
