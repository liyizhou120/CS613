package com.cs61b.phase3;
import edu.princeton.cs.algs4.*;

public class EdgeWeightedGraph {
	  private static final String NEWLINE = System.getProperty("line.separator");
	
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
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}
	
	/**
	 * Initializes a random edge-weighted graph with 
	 * {@code V} vertices and <em> E </em> edges. 
	 * 
	 * @param V the number of vertices
	 * @param E the number of edges 
	 * @throws IllegalArgumentException if {@code V < 0}
	 * @throws IllegalArgumentException if {@code E < 0} 
	 */
	
	public EdgeWeightedGraph(int V, int E) {
		this(V);
		if(E < 0) {
			throw new IllegalArgumentException("Number of edges must be non-negative");
		}
		
		for(int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double weight = Math.round(100 * StdRandom.uniform()) / 100;
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}
	
	public int V() {
		return V; 
	}
	
	public int E() {
		return E; 
	}
	
	private void validateVertex(int v) {
		if(v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex" + v +  "is not between 0 and " + (V-1));		
		}
	}
	
	/**
     * Adds the undirected edge {@code e} to this edge-weighted graph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless both endpoints are between {@code 0} and {@code V-1}
     */
	
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	
	/**
	 * Returns the edges incident on vertex{@code v} 
	 * @return the edges incident on vertex{@code v} as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 * 
	 */
	
	public Iterable<Edge> adj(int v){
		validateVertex(v);
		return adj[v];
	}
	
	/**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}               
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
	
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}
	
	 /**
     * Returns all edges in this edge-weighted graph.
     * To iterate over the edges in this edge-weighted graph, use foreach notation:
     * {@code for (Edge e : G.edges())}.
     *
     * @return all edges in this edge-weighted graph, as an iterable
     */
	public Iterable<Edge> edges(){
		Bag<Edge> list = new Bag<Edge>();
		for(int v = 0; v < V; v++) {
			int selfLoops = 0;
			for(Edge e : adj(v)) {
				if(e.other(v) > v) {
					list.add(e);
				}else if(e.other(v) == v){
					if(selfLoops % 2 == 0) {
						list.add(e);
						selfLoops++;
					}
				}
			}
			
		}
		return list; 
	}
	
	  /**
     * Returns a string representation of the edge-weighted graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
