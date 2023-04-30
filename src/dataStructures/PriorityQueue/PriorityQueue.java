package dataStructures.PriorityQueue;

import dataStructures.ComparableNode;
import exception.PriorityQueueException;

import java.util.ArrayList;

/**
 * A priority queue implementation using a max heap.
 *
 * @param <K> The key type.
 * @param <V> The value type.
 */
public class PriorityQueue<K extends Comparable<K>, V> implements IPriorityQueue<K, V>, Cloneable {

	private final ArrayList<ComparableNode<K, V>> A; // Array list that holds the nodes of the max heap.

	/**
	 * Constructs an empty priority queue.
	 */
	public PriorityQueue() {
		this.A = new ArrayList<>();
	}

	/**
	 * Constructs a priority queue with the given list.
	 *
	 * @param list The list to initialize the priority queue with.
	 */
	private PriorityQueue(ArrayList<ComparableNode<K, V>> list) {
		this.A = list;
	}

	/**
	 * Returns the index of the parent of a node in the max heap.
	 *
	 * @param i The index of the node.
	 * @return The index of the parent.
	 */
	public int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Returns the index of the left child of a node in the max heap.
	 *
	 * @param i The index of the node.
	 * @return The index of the left child.
	 */
	public int left(int i) {
		return 2 * i + 1;
	}

	/**
	 * Returns the index of the right child of a node in the max heap.
	 *
	 * @param i The index of the node.
	 * @return The index of the right child.
	 */
	public int right(int i) {
		return 2 * i + 2;
	}

	/**
	 * Inserts a node with the given key and value into the priority queue.
	 *
	 * @param key   The key of the node.
	 * @param value The value of the node.
	 */
	@Override
	public void insert(K key, V value) {
		A.add(new ComparableNode<>(key, value));
		increaseKey(A.size() - 1, key);
	}

	/**
	 * Removes and returns the node with the highest priority (maximum key) from the priority queue.
	 *
	 * @return The value of the node with the highest priority.
	 * @throws PriorityQueueException if the priority queue is empty.
	 */
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

	/**
	 * Returns the value of the node with the highest priority (maximum key) in the priority queue, without removing it from the queue.
	 *
	 * @return The value of the node with the highest priority, or null if the priority queue is empty.
	 */
	@Override
	public V maximum() {
		return isEmpty() ? null : A.get(0).getValue();
	}

	/**
	 * Increases the key of the node at the given index in the priority queue.
	 *
	 * @param i   The index of the node.
	 * @param key The new key for the node.
	 * @throws PriorityQueueException if the new key is smaller than the current key.
	 */
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

	/**
	 * Maintains the max-heap property of the priority queue by recursively "heapifying" the subtree rooted at i, assuming that the left and right subtrees are already max-heaps.
	 *
	 * @param i The index of the root of the subtree to be heapified.
	 */
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

	/**
	 * Swaps two elements in the priority queue at indices i and j.
	 *
	 * @param i The index of the first element to be swapped.
	 * @param j The index of the second element to be swapped.
	 */
	private void swap(int i, int j) {
		ComparableNode<K, V> temp = A.get(i);
		A.set(i, A.get(j));
		A.set(j, temp);
	}

	/**
	 * Returns the number of elements in the priority queue.
	 *
	 * @return The number of elements in the priority queue.
	 */
	public int size() {
		return this.A.size();
	}

	/**
	 * Checks if the priority queue is empty.
	 *
	 * @return true if the priority queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Creates and returns a copy of the priority queue.
	 *
	 * @return A new PriorityQueue object that is a copy of the original priority queue.
	 */
	public PriorityQueue<K, V> clone() {
		ArrayList<ComparableNode<K, V>> copy = new ArrayList<>(this.A);
		return new PriorityQueue(copy);
	}

}