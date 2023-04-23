package dataStructures.Hash;

import java.util.ArrayList;

import dataStructures.ComparableNode;
import exception.HashException;

public class Hash<K extends Comparable<K>, V> implements IHash<K, V> {
    private int size;

    private final ArrayList<ComparableNode<K, V>> bucketArray;
    ComparableNode<K, V> deleted;

    public Hash(int size) {
        this.size = 0;
        bucketArray = new ArrayList<>();
        deleted = new ComparableNode<>(null, null);
        for (int i = 0; i < size; i++)
            bucketArray.add(null);
    }

    @Override
    public void insert(K key, V value) throws HashException {
        if (size() < bucketArray.size()) {
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

    private int hash(K key, int i) {
        return (hash(key) + i) % bucketArray.size();
    }

    private int hash(K key) {
        int h = key.hashCode() % bucketArray.size();
        return (h < 0) ? -h : h;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

}
