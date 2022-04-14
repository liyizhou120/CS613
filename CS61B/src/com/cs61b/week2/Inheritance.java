package com.cs61b.week2;

public interface Inheritance <Item> {
	
	public void addLast(Item x); 
	
	public Item getLast(); 
	
	public Item get(int i);
	
	public int size(); 
	
	public void insert(Item x, int position);
	
	public void addFirst(Item x);
	
	public Item getFirst();
	
	default public void print() {
		for(int i = 0; i < size(); i+= 1) {
			System.out.print(get(i) + " ");
		}
		System.out.println(); 
	}
}
