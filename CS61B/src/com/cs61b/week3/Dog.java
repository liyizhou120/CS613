package com.cs61b.week3;

public class Dog implements OurComparable{
	public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    public int compareTo(Object o) {
        //assume nobody is messing up and giving us
        //something that isn't a dog.
    	Dog biubiu = (Dog) o;
        return size - biubiu.size;
    }


    public void bark() {
        System.out.println(name + " says: bark");
    }
}
