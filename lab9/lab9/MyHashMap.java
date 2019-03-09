package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author LMN
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;

    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        if (buckets[hash(key) % buckets.length].containsKey(key)) {
            return buckets[hash(key) % buckets.length].get(key);
        }
        return null;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (loadFactor() > MAX_LF) {
            ArrayMap<K, V>[] resizeBucket = new ArrayMap[buckets.length * 2];
            for (int i = 0; i < resizeBucket.length; i++) {
                resizeBucket[i] = new ArrayMap<>();
            }
            for (ArrayMap<K, V> aMap : buckets) {
                for (K k : aMap) {
                    resizeBucket[hash(k) % resizeBucket.length].put(k, aMap.get(k));
                }
            }
            buckets = resizeBucket;
        }
        if (!buckets[hash(key) % buckets.length].containsKey(key)) {
            size = size + 1;
        }
        buckets[hash(key) % buckets.length].put(key, value);
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
        Set<K> keyOfK = new HashSet<>();
        for (ArrayMap<K, V> map : buckets) {
            for (K key : map) {
                keyOfK.add(key);
            }
        }
        return keyOfK;
    }


    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        if (buckets[hash(key) % buckets.length].containsKey(key)) {
            return buckets[hash(key) % buckets.length].remove(key);
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        if (buckets[hash(key) % buckets.length].containsKey(key)) {
            return buckets[hash(key) % buckets.length].remove(key, value);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
