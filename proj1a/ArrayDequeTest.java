import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            input.addFirst(i);
        }
        int actual1, expected1, actual2, expected2;
        actual1 = input.get(1);
        expected1 = 3;
        assertEquals(expected1, actual1);
        actual2 = input.get(3);
        expected2 = 1;
        assertEquals(expected2, actual2);
    }
    @Test
    public void testLast() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            input.addLast(i);
        }
        int actual1, expected1, actual2, expected2;
        actual1 = input.get(1);
        expected1 = 1;
        assertEquals(expected1, actual1);
        actual2 = input.get(4);
        expected2 = 4;
        assertEquals(expected2, actual2);
    }
    @Test
    public void testRemoveFirstAndLast() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addFirst(0);
        input.addFirst(1);
        input.addFirst(2);
        input.addFirst(3);
        input.addFirst(4);
        input.addFirst(5);
        input.addFirst(6);
        input.addFirst(7);
        input.addFirst(8);
        input.addFirst(9);
        int actual = input.removeLast();
        assertEquals(0, actual);
    }
    @Test
    public void testSize() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 88; i++) {
            input.addFirst(i);
        }
        int actualsize1 = input.size();
        int expectedsize1 = 88;
        assertEquals(expectedsize1, actualsize1);
        int actualsize2, expectedsize2;
        input.removeLast();
        actualsize2 = input.size();
        expectedsize2 = actualsize1 - 1;
        assertEquals(expectedsize2, actualsize2);
        int actuallsize3, expectedsize3;
        for (int i = 0; i < 4; i++) {
            input.removeFirst();
        }
        actuallsize3 = input.size();
        expectedsize3 = actualsize2 - 4;
        assertEquals(expectedsize3, actuallsize3);
    }
    @Test
    public void testIsEmptyandIsFull() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        boolean actual1, expected1;
        expected1 = true;
        actual1 = input.isEmpty();
        assertEquals(expected1, actual1);
        for (int i = 0; i < 6; i++) {
            input.addFirst(i);
        }
    }
    @Test
    public void testExeicise() {
        ArrayDeque<Integer> input3 = new ArrayDeque<>();
        ArrayDeque<Integer> input4 = input3;
        assertEquals(input3, input4);
    }
    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            lld1.addLast(i);
        }
        Integer actual = lld1.getRecursive(0);
        Integer expected = 0;
        assertEquals(expected, actual);
    }
    @Test
    public void testIterative() {
        LinkedListDeque<Integer> input = new LinkedListDeque<>();
        input.addFirst(0);
        int actual = input.get(0);
        assertEquals(0, actual);
    }
    @Test
    public void testGet() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 4; i++) {
            input.addLast(i);
        }
        int actual = input.get(0);
        int expected = 0;
        assertEquals(expected, actual);
    }
    @Test
    public void testResize() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            input.addLast(i);
        }
        for (int i = 0; i < 3; i++) {
            input.removeFirst();
        }
    }
    @Test
    public void testBoundAddFirst() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addLast(0);
        input.addLast(1);
        int getZero = input.get(0);
        input.removeFirst();
        input.removeFirst();
        input.addLast(5);
        input.addFirst(6);
        input.addLast(7);
        input.addLast(8);
        int getTwo = input.get(2);
        input.addFirst(10);
        input.addFirst(11);
        input.addFirst(12);
    }
    @Test
    public void testRemoveLastAndResize() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        for (int i = 0; i < 99; i++) {
            input.addLast(i);
        }
        for (int i = 0; i < 80; i++) {
            input.removeLast();
        }
    }
}
