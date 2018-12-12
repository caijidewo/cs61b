
/** LinkedListDeque.
 * @author lizhidong
 */
public class LinkedListDeque<T> {
    /** Nested Class. */
    private class IntNode {
        /** A pointer to point to the previous node. */
        private IntNode prev;
        /** Item : information to store. */
        private T item;
        /** A pointer to point to the next node. */
        private IntNode next;

        private IntNode(IntNode pren, IntNode n) {
            prev = pren;
            next = n;
        }

        private IntNode(T i, IntNode pren, IntNode n) {
            prev = pren;
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private IntNode last;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null);
        last = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        IntNode q = sentinel.next;
        IntNode p;
        if (q != null) {
            p = new IntNode(item, sentinel, q);
            q.prev = p;
        } else {
            p = new IntNode(item, sentinel, null);
        }
        sentinel.next = p;
        if (last == sentinel) {
            last = p;
        }
        size++;
    }

    public void addLast(T item) {
        IntNode p = new IntNode(item, last, null);
        last.next = p;
        last = p;
        size = size + 1;
    }

    public boolean isEmpty() {
        return last == sentinel;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (last != sentinel) {
            IntNode p = sentinel.next;
            while (p != null) {
                System.out.println(p.item);
                p = p.next;
            }
        }
    }

    public T removeFirst() {
        IntNode renode = sentinel.next;
        T X;
        if (renode != null) {
            sentinel.next = renode.next;
            X = renode.item;
            if (renode.next != null) {
                renode.next.prev = sentinel;
            } else {
                last = sentinel;
            }
            size--;
        } else {
            X = null;
        }
        return X;
    }

    public T removeLast() {
        T X;
        if (last != sentinel) {
            X = last.item;
            IntNode prelast = last.prev;
            prelast.next = null;
            last.prev = null;
            last = prelast;
            size--;
        } else {
            X = null;
        }
        return X;
    }
    public T get(int index) {
        T X = null;
        IntNode p = sentinel;
        while (index >= 0) {
            p = p.next;
            index = index - 1;
        }
        X = p.item;
        return X;
    }
    public T getRecursive(int index) {
        if (index > size - 1) {
            return getRecursive(sentinel.next, size - 1);
        } else {
            return getRecursive(sentinel.next, index);
        }
    }
    private T getRecursive(IntNode X, int index) {
        if (index <= 0) {
            return X.item;
        } else {
            index--;
            return getRecursive(X.next, index);
        }
    }
}
