package com.cs61b.week3;
import java.util.Comparator;

public class Maximizer {
	public static Comparable max(Comparable[] item) {
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
        
        Comparator<Dog> nc =  Dog.getNameComparator();
        if (nc.compare(d1, d3) > 0) { //if d1 comes later than d3 in the alphabet
        	d1.bark(); 
        }else {
        	d3.bark();
        }
	}
}
