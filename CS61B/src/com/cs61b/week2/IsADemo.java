package com.cs61b.week2;

public class IsADemo {

	public static void main(String[] args) {
		Inheritance<String> someList = new SLList<>();
		someList.addFirst("elk");
		someList.addLast("dwell");
		someList.addLast("on");
		someList.addLast("existential");
		someList.print();
	}

}
