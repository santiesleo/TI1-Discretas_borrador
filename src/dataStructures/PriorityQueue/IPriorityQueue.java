package dataStructures.PriorityQueue;

public interface IPriorityQueue<K extends Comparable<K>, V> {

	void insert(K key, V value);

	V maximum();

	V extractMax();

	void increaseKey(int i, K key);

}
