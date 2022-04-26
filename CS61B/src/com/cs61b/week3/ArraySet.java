package com.cs61b.week3;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


//T is a generic type variables 
public class ArraySet <T> implements Iterable <T>{
	private T[] items;
	private int size; 
	
	public ArraySet() {
		items = (T[]) new Object[100];
		size = 0; 
	}
	
	
	
	public boolean contains(T x) {
		for(int i = 0; i < size; i++) { 
			if(items[i] == null) {
				if(x == null) {
					return true;
				}
			}
			if(x.equals(items[i])) {
				return true; 
			}
		}
		return false; 
	}
	
	
	
	public void add(T x) {
		if(x == null) {
			throw new IllegalArgumentException("you can't add null to an ArraySet");
		}
		
		if(contains(x)) {
			return; 
		}
		items[size] = x; 
		size += 1; 
	}
	
	
	public int size() {
		return size; 
	}
	
	
	
	public Iterator<T> iterator(){
		return new ArraySetIterator(); 
	}
	
	
	private class ArraySetIterator implements Iterator<T>{
		private int wizPos; 
		
		public ArraySetIterator() {
			wizPos = 0;
		}
		
		public boolean hasNext() {
			return wizPos < size; 
		}
		
		public T next() {
			T returnItem = items[wizPos];
			wizPos += 1; 
			return returnItem; 
		}
	}
	
	
/*	@Override
	public String toString() {
		//String returnString = "{";
		StringBuilder returnString = new StringBuilder("{");
		for(T item: this) {
			returnString.append (item.toString());
			returnString.append( ",");
		}
		returnString.append("}");
		return returnString.toString();
	}
*/
	
	@Override
	public String toString() {
		List<String> listOfItems = new ArrayList<>();
		for(T x : this) {
			listOfItems.add(x.toString());
		}
		return String.join(", ", listOfItems);
	}
	
	@Override
	public boolean equals(Object other) {
		if(this == other) {
			return true; 
		}
		
		if(other == null) {
			return false; 
		}
		
		if(other.getClass() != this.getClass()) {
			return false; 
		}
		
		ArraySet<T> o = (ArraySet<T>) other; 
		if(o.size() != this.size()) {
			return false; 
		}
		
		for(T item: this) {
			if(!o.contains(item)) {
				return false; 
			}
		}
		return true; 
	}
	
	
	public static void main(String[] args) {
		
		//Basic ArraySet
		ArraySet<String> s = new ArraySet<>(); 
			//s.add(null);
			s.add("horse");
			s.add("fish");
			s.add("house");
			s.add("fish");
			System.out.println(s.contains("horse"));
			System.out.println(s.size());
		
		//HashSet with i:javaset operation
		Set<Integer> javaset = new HashSet<>(); 
		javaset.add(5);
		javaset.add(23);
		javaset.add(42);
		
		for(int i : javaset) {
			System.out.println(i);
		}
		
		//Iterator implementation
		ArraySet<Integer> aset = new ArraySet<>(); 
		aset.add(5);
		aset.add(23);
		aset.add(42);
		Iterator<Integer> aseer = aset.iterator(); 
		
		while(aseer.hasNext()) {
			int i = aseer.next();
			System.out.println(i);
		}
		//Works under Iterable<T> interface
		for(int i : aset) {
			System.out.println(i);
		}
		
		System.out.println(aset);
		
		
		ArraySet<Integer> aset2 = new ArraySet<>(); 
		aset2.add(5);
		aset2.add(23);
		aset2.add(42);
		System.out.println(aset.equals(aset2));
	}
}
