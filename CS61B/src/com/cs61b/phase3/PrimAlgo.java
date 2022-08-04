package com.cs61b.phase3;

import java.util.PriorityQueue;
import edu.princeton.cs.algs4.Queue;


public class PrimAlgo {
	private static final double FLOATING_POINT_EPSILON = 1E-12;
	
	private double weight;   //total weight of MST 
	private Queue<Edge> mst; //edges in the MST  
	private boolean[] marked; //marked[v] = true iff v on tree
	private MinPQ<Edge> pq;   //edges with one endpoint in tree 
	
	/**
	 * Compute a minimum spanning tree (or forest) of an edge-weighted graph 
	 * @param G the edge-weighted graph 
	 */
	
	public PrimAlgo (EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge> ();
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				prim(G, v);
			}
		}
		assert check(G);
	}
	
	private void prim(EdgeWeightedGraph G, int size) {
		scan(G, size);
		
		
	}
	
	//add all edges e incident to v onto pq if the other endpoint has not yet 
	//been scanned
	private void scan(EdgeWeightedGraph G, int v) {
		
	}
	
	public Iterable<Edge> edges(){
		return mst; 
	}
	
	private boolean check(EdgeWeightedGraph G) {
		//check weight 
		double totalWeight = 0.0; 
		for(Edge e : edges()) {
			
		}
	}
}
