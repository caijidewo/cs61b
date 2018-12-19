import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testDifference() {
        ArrayDequeSolution<Integer> actual = new ArrayDequeSolution<Integer>();
        StudentArrayDeque<Integer> expected = new StudentArrayDeque<Integer>();
        String message = "";
        for (Integer i = 0; i < 50; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                actual.addFirst(i);
                expected.addFirst(i);
                String value = Integer.toString(i);
                message = message + "addFirst(" + value + ")\n";
            } else if (numberBetweenZeroAndOne < 0.5) {
                actual.addLast(i);
                expected.addLast(i);
                String value = Integer.toString(i);
                message = message + "addLast(" + value + ")\n";
            } else if (numberBetweenZeroAndOne < 0.75) {
                Integer actualV = actual.removeFirst();
                Integer expectedV = expected.removeFirst();
                message = message + "removeFirst()\n";
                assertEquals(message, expectedV, actualV);
            } else {
                Integer actualV = actual.removeLast();
                Integer expectedV = expected.removeLast();
                message = message + "removeLast()\n";
                assertEquals(message, expectedV, actualV);
            }
        }
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeGold.class);
    }
}
