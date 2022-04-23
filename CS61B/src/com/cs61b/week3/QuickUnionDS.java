package com.cs61b.week3;

public class QuickUnionDS implements DisjointSets{
	private int[] parent; 
	
	public QuickUnionDS (int N) {
		parent = new int[N];
		for(int i = 0; i < N; i++) {
			parent[i] = -1; 
		}
	}
	
	private int findRoot(int p) {
		int r = p; 
		while(parent[r] >= 0) {
			r = parent[r];
		}
		return r; 
	}
	
	public boolean isConnected(int a, int b) {
		return findRoot(a) == findRoot(b);
	}
	
	public void connect(int a, int b) {
		int i = findRoot(a);
		int j = findRoot(b);
		parent[i] = j; 
	}
}
