package com.cs61b.week2;


public class Alist <Item> implements Inheritance<Item>{
	private Item[] items;
	private int size; 
	
	/**Creates an empty list.*/ 
	public Alist() {
		items = (Item[]) new Object[100];
		size = 0; 
	}
	/* Inserts X into the back of the List
	 * addLast: The next item we want to add, will go into
	   position size 
	 * getLast: The item we want to return is in position size -1
	 * size: The number of items in the list should be size*/
	
	public void insert(Item x, int position) {
		Item[] newItems = (Item[]) new Object[items.length + 1];
		
		System.arraycopy(items,0,newItems,0,position);
		newItems[position] = x; 
		
		System.arraycopy(items, position, newItems, position + 1, items.length - position);
		items = newItems;
	}
	
	
	
	
	private void resize (int capacity) {
		
			Item[] a = (Item[]) new Object[capacity];
			System.arraycopy(items, 0, a, 0, size);
			items = a; 
	}
	
	public void addFirst(Item x) {
		insert(x, 0);
	}
	
	
	@Override
	
	public void addLast(Item x) {
		if(size == items.length) {
			resize(size + 1); 
		}
			items[size] = x; 
			size += 1; 
	}
	
	
	public Item getFirst() {
		return get(0);
	}

	
	/* Returns the item from the back of the list*/
	
	@Override
	
	public Item getLast() {
		return items[size - 1]; 
	}
	
	/*Gets the i-th item in the list(0 is the front)*/
	
	public Item get(int i) {
		return items[i];
	}
	
	/*returns the number of items in the list*/
	
	@Override
	public int size() {
		return size; 
	}
	
	
	
	/*Deletes item from back of the list and returns deleted item*/
	
	
	public Item removeLast() {
		 Item x = getLast(); 
		 size -= 1; 
		 return x;
	}
	

}
