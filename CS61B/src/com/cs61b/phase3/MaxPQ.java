package com.cs61b.phase3;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MaxPQ<Key> implements Iterable <Key>  {

	private Key[] pq; //store items at indices 1 to n  
	private int n; //number of items on priority queue 
	private Comparator <Key> comparator; //optional comparator
	
	/*
	 * Initializes an empty priority queue with the given initial capacity 
	 * @param initCapacity: the initial capacity of this priority queue
	 */
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	/*
	 * Initializes an empty priority queue
	 */
	
	public MaxPQ() {
		this(1);
	}
	
	
	@SuppressWarnings("unchecked")
	public MaxPQ(int initCapacity, Comparator<Key> comparator) {
		this.comparator = comparator; 
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	public MaxPQ(Comparator<Key> comparator) {
		this(1, comparator);
	}
	
	/*
	 * Initializes a priority queue from the array of keys 
	 * Take time proportional to the number of keys, using sink-based
	 * heap construction 
	 * @param keys: the array of keys 
	 */
	
	@SuppressWarnings("unchecked")
	public MaxPQ(Key[] keys) {
		n = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for(int i = 0; i < n; i++) {
			pq[i+1] = keys[i];
		}
		for(int k = n/2; k >= 1; k--) {
			sink(k);
		}
		assert isMaxHeap();
	}
	
	/*
	 * Returns true if this priority queue is empty.
	 * @return {@code true} if this priority queue is empty 
	 * 		   {@code false} otherwise 
	 */
	
	public boolean isEmpty() {
		return n==0; 
	}
	
	/*
	 * Returns the number of keys on this priority queue
	 * @return the number of keys on this priority queue 
	 */
	
	public int size() {
		return n;
	}
	
	
	/*
	 * Returns a largest key on this priority queue.
	 * @return a largest key on this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	
	public Key max() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue is empty");
		}
		return pq[1];
	}
	
	//resize the underlying array to have the given capacity
	private void resize(int capacity) {
		assert capacity > n;
		@SuppressWarnings("unchecked")
		Key[] temp = (Key[]) new Object[capacity];
		for(int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp; 
	}
	
	/*
	 * Adds a new key to this priority queue 
	 * @param x the new key to add to this priority queue 
	 */
	
	public void insert(Key x) {
		if(n == pq.length - 1) {
			resize(2 * pq.length);
		}
		
		pq[++n] = x; 
		swim(n);
		assert isMaxHeap();
	}
	
	/*
	 * Removes and returns a largest key on this priority queue 
	 * @returns a largest key on this priority queue 
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	
	public Key pop() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority queue is empty");
		}
		Key max = pq[1];
		swap(1, n--);
		sink(1);
		pq[n+1]=null; 
		
		if((n>0) && (n == (pq.length - 1)/4)){
			resize(pq.length / 2);
		}
		
		assert isMaxHeap();
		return max; 
	}
	
	/***************************************************************************
	 * Helper functions to restore the heap invariant.
     ***************************************************************************/
	
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2 * k;
			if(j < n && less(j, j + 1)) {
				j++;
			}
			if(!less(k,j)) {
				break;
			}
			swap(k,j);
			k = j;
		}
	}
	
	
	@SuppressWarnings("unused")
	private void swim(int k) {
		while(k > 1 && less(k/2, k)) {
			swap(k, k/2);
			k = k/2;
		}
	}

	/***************************************************************************
    * Helper functions for compares and swaps.
    ****************************************************************************/
	
	
	@SuppressWarnings("unchecked")
	private boolean less(int i, int j) {
		if(comparator == null) {
			return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}
	
	private void swap(int i, int j) {
		Key temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private boolean isMaxHeap() {
		for(int i = 1; i <= n; i++) {
			if(pq[i]==null) {
				return false; 
			}
		}
		for(int i = n+1; i < pq.length; i++) {
			if(pq[i] != null) {
				return false; 
			}
		}
		if(pq[0] != null) {
			return false; 
		}
		
		return isMaxHeapOrdered(1);
	}
	
	private boolean isMaxHeapOrdered(int k) {
		if(k > n) {
			return true; 
		}
		int left = 2 * k; 
		int right = 2 * k + 1; 
		if(left <= n && less(k, left)) {
			return false; 
		}
		if(right <= n && less(k, right)) {
			return false; 
		}
		
		return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
	}
	
	/***************************************************************************
	    * Iterator.
	    ***************************************************************************/

	    /**
	     * Returns an iterator that iterates over the keys on this priority queue
	     * in descending order.
	     * The iterator doesn't implement {@code remove()} since it's optional.
	     *
	     * @return an iterator that iterates over the keys in descending order
	     */
	
	@Override
	public Iterator<Key> iterator() {
		
		return new HeapIterator();
	}
	
	private class HeapIterator implements Iterator<Key>{
		//creates a new PQ 
		private MaxPQ<Key> copy; 
		
		//add all items to copy of heap 
		//takes linear time since already in heap order so no keys move 
		public HeapIterator() {
			if(comparator == null) {
				copy = new MaxPQ<Key>(size());
			}
			else {
				copy = new MaxPQ<Key>(size(),comparator);
			}
			for(int i = 1; i<= n; i++) {
				copy.insert(pq[i]);
			}
		}
		
		public boolean hasNext() {
			return !copy.isEmpty();
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		public Key next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return copy.pop();
		}
	}
	
}
