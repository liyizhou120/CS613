package com.cs61b.week3;

public interface DisjointSets {
	
	/* Connect two items a and b */
	public void connect(int a, int b); 
	
	/* Check if two points are connected */
	public boolean isConnected(int a, int b);
}
