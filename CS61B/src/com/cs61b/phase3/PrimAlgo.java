package com.cs61b.phase3;

import java.util.PriorityQueue;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;


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
	
	private void prim(EdgeWeightedGraph G, int s) {
		scan(G, s);
		while(!pq.isEmpty()) {
			Edge min = pq.delMin();
			int v = min.either(), w = min.other(v);
			assert marked[v]|| marked[w];
			
			if(marked[v] && marked[w]) {
				continue; 
			}
			
			double minWeight = min.weight();
			mst.enqueue(min);
			weight += minWeight;
			
			if(!marked[v]) {
				scan(G,v);
			}
			if(!marked[w]) {
				scan(G,w);
			}
			
		}
		
	}
	
	//add all edges e incident to v onto pq if the other endpoint has not yet 
	//been scanned
	private void scan(EdgeWeightedGraph G, int v) {
		assert !marked[v];
		marked[v] = true; 
		for(Edge e : G.adj(v)){
			if(!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}
	
	/**
	 * Return edges in a minimum spanning tree (or forest) 
	 * @return the edges in a minimum spanning tree (or forest) as 
	 * an iterable of edges
	 */
	
	public Iterable<Edge> edges(){
		return mst; 
	}
	
	
	/**
	 * Return the sum of edge weights in a minimum spanning tree 
	 *
	 * @return sum of edge weights in a minimum spanning tree
	 */
	
	public double weight() {
		return weight; 
	}
	
	/**
	 * Check optimality condition 
	 * @param G
	 * @return 
	 */
	
	private boolean check(EdgeWeightedGraph G) {
		//check weight 
		double totalWeight = 0.0; 
		for(Edge e : edges()) {
			totalWeight += e.weight();
		}
		
		if(Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
			System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
		}
		
		//check that it is acyclic
		UF uf = new UF(G.V());
		for(Edge e : edges()) {
			int v = e.either(), w = e.other(v); 
			if(uf.find(v) == uf.find(w)) {
				 System.err.println("It is not acyclic");
				return false; 
			}
			uf.union(v, w);
		}
		
		//check that it is a spanning forest
		for(Edge e : G.edges()) {
			int v = e.either(), w = e.other(v); 
			if(uf.find(v)!= uf.find(w)) {
				System.err.println("It is not a spanning tree");
				return false; 
			}
		}
		
		//check that it is a minimum spanning forest
		for(Edge e : edges()) {
			//all edges in MST except e
			uf = new UF(G.V());
			for(Edge f : mst) {
				int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
			}
			
			//check that e is min weight edge in crossing cut 
			for(Edge f : G.edges()) {
				int x = f.either(), y = f.other(x);
				 if (uf.find(x) != uf.find(y)) {
	                    if (f.weight() < e.weight()) {
	                        System.err.println("Edge " + f + " violates cut optimality conditions");
	                        return false;
	                    }
				 }
			}
			
		}
		
		return true; 
	}
}
