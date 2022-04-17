package com.cs61b.week3;

public class ArraySet <T> {
	private T[] items;
	private int size; 
	
	public ArraySet() {
		items = (T[]) new Object[100];
		size = 0; 
	}
	
	public boolean contains(T x) {
		for(int i = 0; i < size; i++) {
			if(x.equals(items[i])) {
				return true; 
			}
		}
		return false; 
	}
	
	
	public void add(T x) {
		if(contains(x)) {
			return; 
		}
		items[size] = x; 
		size += 1; 
	}
	
	public int size() {
		return size; 
	}
}
