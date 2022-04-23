package com.cs61b.week3;

public class QuickFindDS implements DisjointSets{
	private int[] id; 
	
	public void connect(int a, int b) {
		int aid = id[a];
		int bid = id[b];
		for(int i = 0; i < id.length; i++) {
			if(id[i] == aid) {
				id[i] = bid; 
			}
		}
	}
	
	public boolean isConnected(int a, int b) {
		return (id[a] == id[b]);
	}
	
}
