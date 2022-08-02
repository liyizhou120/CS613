package com.cs61b.phase3;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPQ<Key> implements Iterable<Key> {
	private Key[] pq; //store items at indices 1 to n    
	private int n;    // number of items on priority queue 
	private Comparator<Key> comparator; 
	
	
	/** 
	 * Initializes an empty priority queue with the given initial capacity
	 * @param initCapacity: the initial capacity of this priority queue 
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public MinPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}
	
	/**
	 * Initializes an empty priority queue 
	 */
	
	public MinPQ() {
		this(1);
	}
	
	/**
	 * Initializes an empty priority queue with the given initial capacity 
	 * using the given comparator 
	 * 
	 * @param initCapacity: the initial capacity of this priority queue 
	 * @param comparator the order in which to compare the keys 
	 */
	
	@SuppressWarnings("unchecked")
	public MinPQ(int initCapacity, Comparator<Key> comparator) {
		this.comparator = comparator;
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0; 
	}
	
	 /**
	  * Initializes an empty priority queue using the given comparator 
	  * 
	  * @param comparator the order in which to compare the keys 
	  */
	
	public MinPQ(Comparator<Key> comparator) {
		this(1, comparator);  
	}
	
	/**
	 * Initializes a priority queue from the array of keys 
	 * Takes time proportional to the number of keys, using sink-based heap construction
	 */
	
	@SuppressWarnings("unchecked")
	public MinPQ(Key[] keys) {
		n = keys.length; 
		pq = (Key[]) new Object[n + 1];
		for(int i = 0; i < n; i++) {
			pq[i+1] = keys[i];
		}
		for(int k = n/2; k >= 1; k--) {
			swim(k);
		}
		assert isMinHeap();
	}
	
	public boolean isEmpty() {
		return n == 0; 
	}
	
	
	private void swim(int k) {
		while(k > 1 && greater(k/2,k)) {
			swap(k,k/2);
			k = k / 2 ; 
		}
	}
	
	private void sink(int k) {
		while(2*k <= n) {
			int j = 2*k; 
			if(j < n && greater(j, j+1)) {
				j++;
			}
			if(!greater(k,j)) {
				break;
			}
			swap(k,j);
			k = j;
		}
	}
	
	
	private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }
	
	
	public int size() {
		return n; 
	}
	
	
	public Key min() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority Queue underflow");
		}
		return pq[1];
	}
	
	
	private void resize(int capacity) {
		assert capacity > n;
		@SuppressWarnings("unchecked")
		Key[] temp = (Key[]) new Object[capacity];
		for(int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp; //update the array 
	}
	
	
	
	public void insert(Key x) {
		if(n == pq.length - 1) {
			resize(pq.length * 2);
		}
		pq[++n] = x; 
		swim(n);
		assert isMinHeap();
	}
	
	public Key delMin() {
		if(isEmpty()) {
			throw new NoSuchElementException("Priority Queue underflow");
		}
	
		Key min = pq[1];
		swap(1, n--);
		
		
		
		return min; 
	}
	
	
	@SuppressWarnings("unchecked")
	private boolean greater(int i, int j) {
		if(comparator == null) {
			return((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}
	
	
	
	
	private boolean isMinHeap() {
		for(int i = 1; i <= n; i++) {
			if(pq[i] == null) {
				return false; 
			}
		}
		for(int i = n + 1; i < pq.length; i++) {
			if(pq[i] != null) {
				return false; 
			}
		}
		if(pq[0] != null) {
			return false; 
		}
		return isMinHeapOrdered(1);
	}
	
	
	
	private boolean isMinHeapOrdered(int k) {
		if(k > n) {
			return true; 
		}
		int left = 2 * k; 
		int right = 2 * k + 1; 
		if(left <= n && greater(k, left)) {
			return false;
		}
		if(right <= n && greater(k, right)) {
			return false; 
		}
		
		return isMinHeapOrdered(left) && isMinHeapOrdered(right);
		
	}
}
