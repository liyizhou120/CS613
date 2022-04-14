package com.cs61b.week2;

public class RotatingSLList <Item> extends SLList<Item>{
	
	public void RotateRight() {
		Item x = removeLast(); 
		addFirst(x);
	}
	
	
	
	public static void main(String[] args) {
		RotatingSLList<Integer> rsl = new RotatingSLList<>();
		rsl.addLast(10);
		rsl.addLast(11);
		rsl.addLast(12);
		rsl.addLast(13);

		rsl.RotateRight(); 
		rsl.print();
	}

}
