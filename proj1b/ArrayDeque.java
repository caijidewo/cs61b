public class ArrayDeque<T> implements Deque<T> {
    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double usageRate;
    private static final double USAGEFACTOR = 0.25;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        usageRate = (double) (size + 2) / array.length;
    }
    private int minusOne(int index) {
        int indexOfMinusOne;
        if (index == 0) {
            indexOfMinusOne = array.length - 1;
        } else {
            indexOfMinusOne = index - 1;
        }
        return indexOfMinusOne;
    }
    private int plusOne(int index) {
        int indexOfPlusOne;
        if (index == (array.length - 1)) {
            indexOfPlusOne = 0;
        } else {
            indexOfPlusOne = index + 1;
        }
        return indexOfPlusOne;
    }
    public void addFirst(T item) {
        if (isFull()) {
            T[] resizeArray = (T[]) new Object[array.length * 2];
            int copyIndex = 1;
            int i = (nextFirst + 1) % array.length;
            for (; i != nextLast; i = (i + 1) % array.length) {
                resizeArray[copyIndex++] = array[i];
            }
            nextFirst = 0;
            nextLast = array.length - 1;
            array = resizeArray;
            usageRate = (double) (size + 2) / array.length;
        }
        array[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size = size + 1;
        usageRate = (double) (size + 2) / array.length;
    }
    public void addLast(T item) {
        if (isFull()) {
            T[] resizeArray = (T[]) new Object[array.length * 2];
            int copyIndex = 1;
            int i = (nextFirst + 1) % array.length;
            for (; i != nextLast; i = (i + 1) % array.length) {
                resizeArray[copyIndex++] = array[i];
            }
            nextFirst = 0;
            nextLast = array.length - 1;
            array = resizeArray;
            usageRate = (double) (size + 2) / array.length;
        }
        array[nextLast] = item;
        nextLast = plusOne(nextLast);
        size = size + 1;
        usageRate = (double) (size + 2) / array.length;
    }
    @Override
    public boolean isEmpty() {
        return (nextFirst + 1) % array.length == nextLast;
    }
    private boolean isFull() {
        return (nextLast + 1) % array.length == nextFirst;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (!isEmpty()) {
            for (int i = nextFirst + 1; i != nextLast; i = plusOne(i)) {
                System.out.println(array[i]);
            }
        }
    }
    public T removeFirst() {
        T removeItem = null;
        if (usageRate < USAGEFACTOR && array.length > 16) {
            T[] resizeArray = (T[]) new Object[array.length / 2];
            int copyIndex = 1;
            int i = (nextFirst + 1) % array.length;
            for (; i != nextLast; i = (i + 1) % array.length) {
                resizeArray[copyIndex++] = array[i];
            }
            nextFirst = 0;
            nextLast = size + 1;
            array = resizeArray;
            usageRate = (double) (size + 2) / array.length;
        }
        if (!isEmpty()) {
            removeItem = array[plusOne(nextFirst)];
            array[plusOne(nextFirst)] = null;
            nextFirst = plusOne(nextFirst);
            size = size - 1;
            usageRate = (double) (size + 2) / array.length;
        }
        return removeItem;
    }
    public T removeLast() {
        T removeItem = null;
        if (usageRate < USAGEFACTOR && array.length > 16) {
            T[] resizeArray = (T[]) new Object[array.length / 2];
            int copyIndex = 1;
            int i = (nextFirst + 1) % array.length;
            for (; i != nextLast; i = (i + 1) % array.length) {
                resizeArray[copyIndex++] = array[i];
            }
            nextFirst = 0;
            nextLast = size + 1;
            array = resizeArray;
            usageRate = (double) (size + 2) / array.length;
        }
        if (!isEmpty()) {
            removeItem = array[minusOne(nextLast)];
            array[minusOne(nextLast)] = null;
            nextLast = minusOne(nextLast);
            size = size - 1;
            usageRate = (double) (size + 2) / array.length;
        }
        return removeItem;
    }
    public T get(int index) {
        T getItem = null;
        if (!isEmpty()) {
            getItem = array[(nextFirst + index + 1) % array.length];
        }
        return getItem;
    }
}
