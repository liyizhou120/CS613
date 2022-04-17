package com.cs61b.week3;
import java.util.Comparator;

public class Dog implements Comparable<Dog>{
	public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public int compareTo(Dog biubiu) {
        //assume nobody is messing up and giving us
        //something that isn't a dog.
        return size - biubiu.size;
    }


    public void bark() {
        System.out.println(name + " says: bark");
    }
    
    private static class NameComparator implements Comparator<Dog>{
    	public int compare(Dog dog1, Dog dog2) {
    		return dog1.name.compareTo(dog2.name);
    	}
    }
    
    public static Comparator<Dog> getNameComparator(){
    	return new NameComparator();
    }
}
