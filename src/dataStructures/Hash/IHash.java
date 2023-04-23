package dataStructures.Hash;

public interface IHash<K extends Comparable<K>, V> {

    public void insert(K key, V value) throws Exception;

    public V search(K key);

    public void delete(K key);

}
