package synthesizer;

public class TestIterator {
    public static void main(String[] args) {
        BoundedQueue<Integer> itorable = new ArrayRingBuffer<>(10);
        for (Integer i = 0; i < 10; i++) {
            itorable.enqueue(i);
        }
        for (Integer s : itorable) {
            System.out.println(s);
        }
    }
}
