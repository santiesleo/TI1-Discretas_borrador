package dataStructures.Hash;

import dataStructures.ComparableNode;
import exception.HashException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A hash table implementation using open addressing and linear probing collision resolution.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class Hash<K extends Comparable<K>, V> implements IHash<K, V> {

    private int size; // Size maximum of the hash table
    private final ArrayList<ComparableNode<K, V>> bucketArray; // The hash table itself
    private final ComparableNode<K, V> deleted; // Markup for when an element is removed

    /**
     * Constructs an empty hash table with the specified size.
     * @param size The number of elements the hash table can store.
     */
    public Hash(int size) {
        this.size = 0;
        this.bucketArray = new ArrayList<>(Collections.nCopies(size, null));
        this.deleted = new ComparableNode<>(null, null);
    }

    /**
     * Inserts the specified key-value pair into the hash table.
     * @param key The key for the value.
     * @param value The value to be inserted.
     * @throws HashException if the key already exists in the hash table or if the hash table is full.
     */
    @Override
    public void insert(K key, V value) throws HashException {
        if (size() < bucketArray.size()) {
            V element = search(key);
            if (element != null) {
                throw new HashException("Hash table exception: Duplicate key.");
            }
            for (int i = 0; i < bucketArray.size(); i++) {
                int j = hash(key, i);
                if (bucketArray.get(j) == null || bucketArray.get(j).getKey().compareTo(key) == 0) {
                    if (bucketArray.get(j) == null)
                        this.size++;
                    bucketArray.set(j, new ComparableNode<>(key, value));
                    return;
                }
            }
        }
        throw new HashException("Hash table overflow: Maximum amount exceeded.");
    }

    /**
     * Searches for the specified key in the hash table and returns the value associated with it, if it exists.
     * @param key The key to search for.
     * @return The value associated with the key or null if the key is not found.
     */
    @Override
    public V search(K key) {
        boolean stop = false;
        for (int i = 0; i < bucketArray.size() && !stop; i++) {
            int index = hash(key, i);
            ComparableNode<K, V> node = bucketArray.get(index);
            if (node == null) {
                stop = true;
            } else if (node != deleted && node.getKey().equals(key))
                return bucketArray.get(index).getValue();
        }
        return null;
    }

    /**
     * Deletes the specified key and its associated value from the hash table if it exists.
     * @param key The key to be deleted.
     */
    @Override
    public void delete(K key) {
        boolean stop = false;
        for (int i = 0; i < bucketArray.size() && !stop; i++) {
            int index = hash(key, i);
            ComparableNode<K, V> node = bucketArray.get(index);
            if (node == null) {
                stop = true;
            } else if (node != deleted && node.getKey().equals(key)) {
                bucketArray.set(index, deleted);
                this.size--;
            }
        }
    }

    /**
     * Private method to calculate the hash code for a given key and index.
     * @param key The key to be hashed.
     * @param i The current index in the linear probing sequence.
     * @return The hash code for the key at the specified index.
     */
    private int hash(K key, int i) {
        return (hash(key) + i) % bucketArray.size();
    }

    /**
     * Private method to calculate the hash code for a given key.
     * @param key The key to be hashed.
     * @return The hash code for the key.
     */
    private int hash(K key) {
        int h = key.hashCode() % bucketArray.size();
        return (h < 0) ? -h : h;
    }

    /**
     * Returns the number of nodes currently stored in the table.
     * @return The size of the table.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if the table is currently empty, false otherwise.
     * @return True if the table is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

}
