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
		
	}
	
	@Override
	public Iterator<Key> iterator() {
		
		return null;
	}
	
	
	
}
