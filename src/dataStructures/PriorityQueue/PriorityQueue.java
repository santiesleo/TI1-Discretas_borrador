package dataStructures.PriorityQueue;

import dataStructures.ComparableNode;
import exception.PriorityQueueException;

import java.util.ArrayList;

public class PriorityQueue<K extends Comparable<K>, V> implements IPriorityQueue<K, V> {

	private final ArrayList<ComparableNode<K, V>> A;

	public PriorityQueue() {
		this.A = new ArrayList<>();
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public int left(int i) {
		return 2 * i + 1;
	}

	public int right(int i) {
		return 2 * i + 2;
	}

	@Override
	public void insert(K key, V value) {
		A.add(new ComparableNode<>(key, value));
		increaseKey(A.size() - 1, key);
	}

	@Override
	public V extractMax() throws PriorityQueueException {
		if (isEmpty()) {
			throw new PriorityQueueException("Heap underflow.");
		}
		ComparableNode<K, V> max = A.get(0);
		A.set(0, A.get(A.size() - 1));
		A.remove(A.size() - 1);
		maxHeapify(0);
		return max.getValue();
	}

	@Override
	public V maximum() {
		return isEmpty() ? null : A.get(0).getValue();
	}

	@Override
	public void increaseKey(int i, K key) throws PriorityQueueException {
		if (key.compareTo(A.get(i).getKey()) < 0) {
			throw new PriorityQueueException("New key is smaller than current key.");
		}
		A.get(i).setKey(key);
		while (i > 0 && A.get(parent(i)).getKey().compareTo(A.get(i).getKey()) < 0) {
			swap(i, parent(i));
			i = parent(i);
		}
	}

	private void maxHeapify(int i) {
		int l = left(i), r = right(i), largest = i;
		if (l < A.size() && A.get(l).getKey().compareTo(A.get(i).getKey()) > 0) {
			largest = l;
		}
		if (r < A.size() && A.get(r).getKey().compareTo(A.get(largest).getKey()) > 0) {
			largest = r;
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest);
		}
	}

	private void swap(int i, int j) {
		ComparableNode<K, V> temp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, temp);
	}

	public int size() {
		return this.A.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

}