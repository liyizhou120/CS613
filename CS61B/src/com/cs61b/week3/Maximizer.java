package com.cs61b.week3;

public class Maximizer {
	public static OurComparable max(OurComparable[] item) {
		int maxDex = 0; 
		for(int i = 0; i < item.length; i+= 1) {
			int cmp = item[i].compareTo(item[maxDex]);
			if(cmp > 0) {
				maxDex = i;
			}
		}
		return item[maxDex];
	}
	
	public static void main(String[] args) {
		Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};

        Dog d = (Dog) max(dogs);
        d.bark();
	}
}
