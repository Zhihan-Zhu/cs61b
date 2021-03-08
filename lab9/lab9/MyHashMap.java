package lab9;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author Your name here
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

    public MyHashMap(int n) {
        buckets = new ArrayMap[n];
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
        if (size == 0) {
            return null;
        }
        ArrayMap<K, V> bucket = buckets[hash(key)];
        return bucket.get(key);
    }

    /* resize the hash table to have the given number of buckets, */
    /* rehashing all of the keys. */
    private void resize(int n) {
        MyHashMap<K, V> temp = new MyHashMap(n);
        for (int i = 0; i < buckets.length; i += 1) {
//            for (K key : buckets[i].keySet()) {
            for (K key : buckets[i]) {  //use iterator in ArrayMap
                temp.put(key, buckets[i].get(key));
            }
        }
        this.buckets = temp.buckets;
        this.size = temp.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int i = hash(key);
        if (!buckets[i].containsKey(key)) {
            size += 1;
        }
        buckets[hash(key)].put(key, value);
        if (loadFactor() > MAX_LF) {
            resize(buckets.length * 2);
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
        Set<K> keyset = new HashSet<>();
        for (int i = 0; i < buckets.length; i += 1) {
            for (K key : buckets[i]) {  //use iterator in ArrayMap
                keyset.add(key);
            }
        }
        return keyset;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
//        throw new UnsupportedOperationException();
        return keySet().iterator();
    }

//    public static void main(String[] args) {
//        MyHashMap<String, Integer> studentIDs = new MyHashMap<>();
//        studentIDs.put("sarah", 12345);
//        studentIDs.put("alan", 345);
//        for (String key : studentIDs) {
//            System.out.println(key);
//        }
//    }
}
