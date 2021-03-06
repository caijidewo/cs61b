package lab9;

//import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author LMN
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) < 0) {
            return getHelper(key, p.right);
        } else if (p.key.compareTo(key) > 0) {
            return getHelper(key, p.left);
        } else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        if (p.key.compareTo(key) < 0) {
            p.right = putHelper(key, value, p.right);
        } else if (p.key.compareTo(key) > 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = putHelper(key, value, root);
        } else {
            putHelper(key, value, root);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> setOfK = new HashSet<>();
        midTraverse(root, setOfK);
        return setOfK;
    }

    private void midTraverse(Node tree, Set<K> setOfK) {
        if (tree == null) {
            return;
        }
        setOfK.add(tree.key);
        if (tree.left != null) {
            midTraverse(tree.left, setOfK);
        }
        if (tree.right != null) {
            midTraverse(tree.right, setOfK);
        }
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V deleteNode = find(key, root);
        root = removeHelper(key, root);
        if (deleteNode != null) {
            size = size - 1;
        }
        return deleteNode;
    }
    private V find(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) < 0) {
            return find(key, p.right);
        } else if (p.key.compareTo(key) > 0) {
            return find(key, p.left);
        } else {
            return p.value;
        }
    }

    private Node removeHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (p.key.compareTo(key) < 0) {
            p.right = removeHelper(key, p.right);
        } else if (p.key.compareTo(key) > 0) {
            p.left = removeHelper(key, p.left);
        } else {
            if (p.left != null && p.right != null) {
                Node tmp = findRightMin(p.right);
                p.key = tmp.key;
                p.value = tmp.value;
                p.right = removeHelper(p.key, p.right);
            } else {
                if (p.left == null) {
                    p = p.right;
                } else {
                    p = p.left;
                }
            }
        }
        return p;
    }


    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V removeValue = find(key, root);
        if (removeValue == value) {
            return remove(key);
        } else {
            return null;
        }
    }

    private Node findRightMin(Node p) {
        if (p == null) {
            return null;
        }
        if (p.left == null) {
            return p;
        }
        return findRightMin(p.left);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
