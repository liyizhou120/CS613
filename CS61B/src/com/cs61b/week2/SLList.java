package com.cs61b.week2;


public class SLList <Item> implements Inheritance<Item>{
	
	//the first item if it exists is at sentinel.next
	private IntNode sentinel; 
	private int size; 
	
	//creates a empty SLList
	public SLList() {
		sentinel = new IntNode(null,null);
		size = 0; 
	}
	
	public SLList(Item x) {
		sentinel = new IntNode (null,null);	
		sentinel.next = new IntNode (x,null);
		size = 1; 
		
	}
	
	private class IntNode{
		public Item item; 
		public IntNode next; 
		
		public IntNode(Item i, IntNode n) {
			item = i; 
			next = n; 
		}
	}
	
	public void insert(Item item, int position) {
		IntNode p = sentinel; 
		while(position > 1 && p.next != null) {
			position--; 
			p = p.next; 
		}
		IntNode newNode = new IntNode(item, p.next); 
		p.next = newNode; 
	}
	
	
	public void addFirst(Item x) {
		sentinel.next = new IntNode(x, sentinel.next);
		size += 1; 
	}
	
	public Item getFirst() {
		return sentinel.next.item; 
	}
	
	public Item getLast() {
		IntNode back = getLastNode(); 
		return back.item; 
	}
	
	private IntNode getLastNode() {
		IntNode p = sentinel; 
		
		while(p.next != null) {
			p = p.next; 
		}
		
		return p; 
	}
	
	
	public Item get(int i) {
		return get(i, sentinel.next);
	}
	
	private Item get(int i, IntNode p) {
		if (i == 0) {
			return p.item;
		}
		return get(i-1, p.next);
	}
	
	
	//Nested class under SLList
	//Could have made it private 

	//Adds an item to the end of the list
	public void addLast(Item x) {
		size += 1;
		
		IntNode p = sentinel; 
		while(p.next != null){
			p = p.next; 
			
		}
		p.next = new IntNode(x, null);
	}
	
	public Item removeLast() {
		IntNode back = getLastNode();
		if(back == sentinel) {
			return null; 
		}
		
		IntNode p = sentinel; 
		
		while(p.next != back) {
			p = p.next; 
		}
		p.next = null; 
		return back.item; 
	}
	
	//Returns the size of the list that starts at IntNode p 
	//Naked recursive linked list structure 
//	private static int size (IntNode p) {
//		if(p.next == null) {
//			return 1; 
//		}
//		return 1 + size(p.next);
//	}
//	
	public int size() {
		return size;
	}
	
	@Override
	public void print() {
		System.out.println("boss does not know what he is doing");
		for(IntNode p = sentinel.next; p != null; p = p.next) {
			System.out.print(p.item + " ");
		}
	}
	

}
