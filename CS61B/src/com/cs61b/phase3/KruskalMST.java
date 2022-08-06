package com.cs61b.phase3;

import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;

public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12; 
	private double weight; //weight of mst
	private MinPQ<Edge> mst = new MinPQ<Edge>(); // edges in mst
	
	/**
	 * Compute a minimum spanning tree (or forest) of an edge-weighted graph
	 * @param G the edge-weighted graph 
	 */
	
	
	public KruskalMST(EdgeWeightedGraph G) {
		//create array of edges, sorted by weight 
		Edge[] edges = new Edge[G.E()];
		int t = 0; 
		for(Edge e : G.edges()) {
			edges[t++] = e; 
		}
		Arrays.sort(edges);
		
		// run greedy algorithm 
		UnionFind uf = new UnionFind(G.V());
		for(int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
			Edge e = edges[i];
			int v = e.either(); 
			int w = e.other(v);
			
			//v - w does not create a cycle 
			if(uf.find(v) != uf.find(w)) {
				uf.union(v,w);
				mst.insert(e);
				weight += e.weight();
			}
		}
		assert check(G);
	}
	
	/**
	 * Returns the edges in a minimum spanning tree (or forest) 
	 * @param G
	 * @return the edges in a minimum spanning tree (or forest)
	 * as an iterable of edges 
	 */
	public Iterable<Edge> edges(){
		return mst; 
	}
	
	
	public double weight () {
		return weight; 
	}
	
	//check optimality conditions 
	
	
	private boolean check(EdgeWeightedGraph G) {
		
		//check total weight
		double total = 0.0;
		for(Edge e : edges()) {
			total += e.weight(); 
		}
		
		if(Math.abs(total - weight()) > FLOATING_POINT_EPSILON) { 
			System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", total, weight());
            return false;
		}
		
		//check that it is acyclic 
		UnionFind uf = new UnionFind(G.V());
		for(Edge e : edges()) {
			int v = e.either();
			int w = e.other(v);
			if(uf.find(v) == uf.find(w)) {
				System.err.println("not a tree");
				return false; 
			}
			uf.union(v, w);
		}
		
		
		//check that it is a spanning tree 
		for(Edge e : G.edges()) {
			int v = e.either();
			int w = e.other(v);
			if(uf.find(v) != uf.find(w)) {
				System.err.println("Not a spanning tree");
				return false; 
			}
			uf.union(v, w);
		}
		
		//check that it is a minimal spanning forest 
		
		for(Edge e : edges()) {
			
			//all edges in MST except e 
			
			uf = new UnionFind(G.V());
			for(Edge f : mst) {
				int x = f.either();
				int y = f.other(x);
				if(f != e) {
					uf.union(x, y);
				}
			}
			
			// check that e is min weight edge in crossing cugt 
			for(Edge f : G.edges()) {
				int x = f.either();
				int y = f.other(x);
				if(uf.find(x) != uf.find(y)) {
					if(f.weight() < e.weight()) {
						  System.err.println("Edge " + f + " violates cut optimality conditions");
	                      return false;
					}
				}
			}
		}
		return true; 
	}
	
	
	
}
