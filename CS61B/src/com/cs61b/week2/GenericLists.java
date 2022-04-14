package com.cs61b.week2;

import java.util.Arrays;

public class GenericLists <SB> {
	private class StuffNode{
		public SB item; 
		public StuffNode next; 
		
		public StuffNode(SB i, StuffNode n) {
			item = i; 
			next = n; 
		}
	}
	
	private StuffNode first; 
	private int size; 
	
	public GenericLists(SB x) {
		first = new StuffNode(x, null);
		size = 1; 
	}
	
	public void addFirst(SB x) {
		first = new StuffNode(x, first);
		size +=1; 
	}
	
	public SB getFirst() {
		return first.item; 
	}
	
	public void addLast(SB x) {
		size += 1; 
		StuffNode p = first; 
		while (p.next != null) {
			p = p.next; 
		}
		p.next = new StuffNode(x, null);
	}
	
	public int size() {
		return size; 
	}
	
	
	public static void main(String[] args) {
		GenericLists<String> s1 = new GenericLists<String> ("bones");
		int[] x,y; 
		x = new int[] {1,2,3,4,5}; 
		y = x;
		x = new int[] {-1,2,5,4,9}; 
		s1.addFirst("thug");
		System.out.println(Arrays.toString(x));
		System.out.println(Arrays.toString(y));
		
	}

}
