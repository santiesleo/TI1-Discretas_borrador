package dataStructures;

public class ComparableNode<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private ComparableNode<K, V> next;
    private ComparableNode<K, V> prev;

    public ComparableNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public ComparableNode<K, V> getNext() {
        return next;
    }

    public void setNext(ComparableNode<K, V> next) {
        this.next = next;
    }

    public ComparableNode<K, V> getPrev() {
        return prev;
    }

    public void setPrev(ComparableNode<K, V> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return key.toString() + ": " + value.toString();
    }

}
