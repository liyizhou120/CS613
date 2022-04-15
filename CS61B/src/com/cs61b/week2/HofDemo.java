package com.cs61b.week2;

//Demonstrate higher order function in Java
public class HofDemo {
	public static int doTwice(IntUnaryFunction f, int x) {
		return f.apply(f.apply(x));
	}
	
	public static void main(String[] args) {
		IntUnaryFunction tenX = new Tenx();
		System.out.println(doTwice(tenX, 2));
	}
}
