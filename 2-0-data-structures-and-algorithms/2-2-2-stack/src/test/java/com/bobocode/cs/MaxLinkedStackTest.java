package com.bobocode.cs;

import org.junit.jupiter.api.*;
import com.bobocode.cs.exception.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaxLinkedStackTest {

    @Test
    @Order(1)
    @DisplayName("Max Stack test 1")
    void maxStackTest1() {
        var maxStack = MaxLinkedStack.of(1, 2, 40, 5, 6, 7);

        assertThat(maxStack.popMax()).isEqualTo(40);
        assertThat(maxStack.popMax()).isEqualTo(7);
        assertThat(maxStack.size()).isEqualTo(4);
        assertThat(maxStack.peek()).isEqualTo(6);
        assertThat(maxStack.peekMax()).isEqualTo(6);
    }

    @Test
    @Order(2)
    @DisplayName("Max Stack test 2")
    void maxStackTest2() {
        var maxStack = MaxLinkedStack.of(40, 1, 2, 3, 5, 6, 7);

        assertThat(maxStack).isNotNull();
        assertThat(maxStack.peekMax()).isEqualTo(40);
        assertThat(maxStack.peek()).isEqualTo(7);
        assertThat(maxStack.size()).isEqualTo(7);
    }

    @Test
    @Order(3)
    @DisplayName("Empty stack operations throw EmptyStackException")
    void emptyStackExceptionsTest() {
        var maxStack = new MaxLinkedStack<Integer>();

        assertThat(maxStack.isEmpty()).isTrue();
        assertThat(maxStack.size()).isEqualTo(0);

        assertThatThrownBy(() -> maxStack.pop())
                .isInstanceOf(EmptyStackException.class);

        assertThatThrownBy(() -> maxStack.peek())
                .isInstanceOf(EmptyStackException.class);

        assertThatThrownBy(() -> maxStack.peekMax())
                .isInstanceOf(EmptyStackException.class);

        assertThatThrownBy(() -> maxStack.popMax())
                .isInstanceOf(EmptyStackException.class);
    }

    @Test
    @Order(4)
    @DisplayName("Duplicate maximum values handling")
    void duplicateMaxTest() {
        var maxStack = MaxLinkedStack.of(5, 1, 5, 3, 5);

        assertThat(maxStack.peekMax()).isEqualTo(5);
        assertThat(maxStack.popMax()).isEqualTo(5); // Remove top 5
        assertThat(maxStack.peek()).isEqualTo(3);
        assertThat(maxStack.peekMax()).isEqualTo(5); // Still has 5s
        assertThat(maxStack.size()).isEqualTo(4);
    }

    @Test
    @Order(5)
    @DisplayName("Single element stack operations")
    void singleElementTest() {
        var maxStack = MaxLinkedStack.of(42);

        assertThat(maxStack.peek()).isEqualTo(42);
        assertThat(maxStack.peekMax()).isEqualTo(42);
        assertThat(maxStack.size()).isEqualTo(1);

        assertThat(maxStack.popMax()).isEqualTo(42);
        assertThat(maxStack.isEmpty()).isTrue();
    }

    @Test
    @Order(6)
    @DisplayName("PopMax when maximum is at bottom of stack")
    void maxAtBottomTest() {
        var maxStack = MaxLinkedStack.of(10, 1, 2, 3); // 10 is max and at bottom

        assertThat(maxStack.peekMax()).isEqualTo(10);
        assertThat(maxStack.peek()).isEqualTo(3);
        assertThat(maxStack.popMax()).isEqualTo(10);

        // Verify order is preserved after removing bottom element
        assertThat(maxStack.peek()).isEqualTo(3);
        assertThat(maxStack.peekMax()).isEqualTo(3);
        assertThat(maxStack.size()).isEqualTo(3);
    }

    @Test
    @Order(7)
    @DisplayName("Mixed pop and popMax operations")
    void mixedOperationsTest() {
        var maxStack = MaxLinkedStack.of(1, 5, 3, 8, 2);

        assertThat(maxStack.pop()).isEqualTo(2);        // Normal pop
        assertThat(maxStack.popMax()).isEqualTo(8);     // Pop max
        assertThat(maxStack.peek()).isEqualTo(3);       // Current top
        assertThat(maxStack.peekMax()).isEqualTo(5);    // Current max
        assertThat(maxStack.size()).isEqualTo(3);
    }

    @Test
    @Order(8)
    @DisplayName("String MaxStack with lexicographic ordering")
    void stringMaxStackTest() {
        var maxStack = MaxLinkedStack.of("apple", "zebra", "banana", "orange");

        assertThat(maxStack.peekMax()).isEqualTo("zebra");
        assertThat(maxStack.peek()).isEqualTo("orange");
        assertThat(maxStack.popMax()).isEqualTo("zebra");
        assertThat(maxStack.peekMax()).isEqualTo("orange");
        assertThat(maxStack.size()).isEqualTo(3);
    }

    @Test
    @Order(9)
    @DisplayName("All elements are identical")
    void allSameElementsTest() {
        var maxStack = MaxLinkedStack.of(7, 7, 7, 7);

        assertThat(maxStack.peekMax()).isEqualTo(7);
        assertThat(maxStack.popMax()).isEqualTo(7);
        assertThat(maxStack.peekMax()).isEqualTo(7);
        assertThat(maxStack.pop()).isEqualTo(7);
        assertThat(maxStack.size()).isEqualTo(2);
        assertThat(maxStack.peekMax()).isEqualTo(7);
    }

    @Test
    @Order(10)
    @DisplayName("Descending order elements - max always at bottom")
    void descendingOrderTest() {
        var maxStack = MaxLinkedStack.of(10, 8, 6, 4, 2);

        assertThat(maxStack.peekMax()).isEqualTo(10);
        assertThat(maxStack.peek()).isEqualTo(2);
        assertThat(maxStack.popMax()).isEqualTo(10); // Remove from bottom

        // After removing max from bottom, order should be preserved
        assertThat(maxStack.peek()).isEqualTo(2);
        assertThat(maxStack.peekMax()).isEqualTo(8);
        assertThat(maxStack.size()).isEqualTo(4);
    }
}