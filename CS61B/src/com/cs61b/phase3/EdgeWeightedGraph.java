package com.cs61b.phase3;

public class EdgeWeightedGraph {
	private final int V; 
	private int E; 
	private Bag<Edge>[] adj;
	
	/**
	 * Initializes an empty edge-weighted graph with {@code V}
	 * vertices and 0 edge 
	 * 
	 * @param V the number of vertices 
	 * @throws IllegalArgumentException if {@code V < 0} 
	 *
	 */
	
	public EdgeWeightedGraph(int V) {
		if(V < 0) {
			throw new IllegalArgumentException("Number of vertices must be non-negative");
		}
		this.V = V; 
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
	}
	
}
