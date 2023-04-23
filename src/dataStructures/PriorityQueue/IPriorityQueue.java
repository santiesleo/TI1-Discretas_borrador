package dataStructures.PriorityQueue;

import exception.PriorityQueueException;

public interface IPriorityQueue<K extends Comparable<K>, V> {

	void insert(K key, V value);

	V maximum();

	V extractMax() throws PriorityQueueException;

	void increaseKey(int i, K key) throws PriorityQueueException;

}
