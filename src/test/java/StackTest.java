import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stack.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {
    private Stack stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testInitialStack1() {
        assertEquals(10, stack.getStack().length, "Stack length should equal 10");
    }

    @Test
    public void testInitialStack() {
        assertAll(
                () -> assertTrue(stack.isEmpty(), "Initial stack is empty"),
                () -> assertEquals(10, stack.getStack().length, "Stack length should equal 10"),
                () -> assertEquals(0, stack.getSize(), "Initial size of the stack is equal 0")
        );
    }

    @Test
    public void testPush() {
        stack.push("First");
        assertAll(
                () -> assertFalse(stack.isEmpty(), "Stack is not empty after adding an element"),
                () -> assertEquals(1, stack.getSize(), "After adding element the size of the stack is equal 1"),
                () -> assertEquals("First", stack.peek(), "The peek() method should return the most recently added element.")
        );
    }

    @Test
    public void testPeek() {
        stack.push("First");
        stack.push("Second");

        String poppedItem = stack.peek();
        assertAll(
                () -> assertEquals("Second", poppedItem, "The peek reveals the last element of the stack"),
                () -> assertEquals(2, stack.getSize(), "After peeking the last element of the stack the size is not modified")
        );
    }

    @Test
    public void testPop() {
        stack.push("First");
        stack.push("Second");
        String poppedItem = stack.pop();

        assertAll(
                () -> assertEquals("Second", poppedItem, "The pop() method should return the most recently added element"),
                () -> assertEquals(1, stack.getSize(), "The size should decrease after removing an element"),
                () -> assertEquals("First", stack.peek(), "The peek() method should return the first element.")
        );
    }

    @Test
    public void testPopEmptyStack() {
        stack.pop();
        assertTrue(stack.isError(), "Removing element from empty stack causes error");
    }

    @Test
    public void testPeekEmptyStack() {
        stack.peek();
        assertTrue(stack.isError(), "Taking element from empty stack causes error");
    }

    @Test
    public void testIsEmptyStack() {
        stack.push("First");
        stack.pop();
        assertTrue(stack.isEmpty(), "After adding and deleting an element Stack is empty");
    }

    @Test
    public void testIsNotEmptyStack() {
        stack.push("First");
        assertFalse(stack.isEmpty(), "After adding element stack is not empty");
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i <= 10; i++) {
            stack.push("element" + i);
        }

        assertAll(
                () -> assertEquals(20, stack.getStack().length, "Stack length should have additional capacity added"),
                () -> assertTrue(stack.getSize() == 11, "Size of the stack is equal 10")
        );
    }

    @Test
    public void testPushPopSequence(){
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        assertAll(
                ()-> assertEquals(3,stack.getSize(),"Size should equal 3 after adding three elements"),
                ()-> assertEquals("Third", stack.pop(),"Pop method should return the most recently added element"),
                ()-> assertEquals("Second", stack.pop(),"Pop method should return second element"),
                ()->assertEquals("First",stack.pop(), "Pop method should return first element"),
                ()->assertTrue(stack.isEmpty(),"Stack is empty after removing all the elements")
        );
    }
}
