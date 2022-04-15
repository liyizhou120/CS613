package com.cs61b.week2;

public class VengefulSLList<Item> extends SLList<Item> {
	SLList<Item> deletedItems;
	public VengefulSLList() {
		deletedItems = new SLList<Item>();
	}
	/* A constructor will also work*/
//	public VengefulSLList() {
		//**super(); 
//		deletedItems = new SLList<Item>();
//	}
	public VengefulSLList(Item x) {
		super();
		deletedItems = new SLList<Item>();
	}
	
	@Override 
	public Item removeLast() {
		Item x = super.removeLast();
		deletedItems.addLast(x);
		return x; 
	}
	
	
	//Prints deleted items 
	public void printLostItems() {
		deletedItems.print();
	}
	
	public static void main(String[] args) {
		VengefulSLList<Integer> vs1 = new VengefulSLList<Integer>();
		vs1.addLast(1);
		vs1.addLast(5);
		vs1.addLast(10);
		vs1.addLast(13);
		
		vs1.removeLast();
		vs1.removeLast();
		
		System.out.print("the fallen are:");
		vs1.printLostItems();
	}

}
