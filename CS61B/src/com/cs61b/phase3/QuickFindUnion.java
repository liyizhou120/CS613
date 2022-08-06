package com.cs61b.phase3;

public class QuickFindUnion {
	public class UnionFind {
		private int[] id; // parent[i] = parent of i 
		private int count; // number of components 
		
		/**
		 * Initializes an empty union-find data structure with 
		 * {@code n} elements {@code 0} through {@code n-1}. 
		 * Initially, each element is in its own set 
		 * 
		 * @param n the number of elements 
		 * @throws IllegalArgumentException if {@code n < 0} 
		 */
		
		
		public UnionFind(int n) {
			if(n < 0) {
				throw new IllegalArgumentException("n < 0");
			}
			count = n; 
			id = new int[n];
			
			for(int i = 0; i < n; i++) {
				id[i] = i; 
			}
		}
		
		 /**
	     * Merges the set containing element {@code p} with the 
	     * the set containing element {@code q}.
	     *
	     * @param  p one element
	     * @param  q the other element
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     */
		
		public void union(int p, int q) {
			validate(p);
			validate(q);
			
			int rootP = find(p);
			int rootQ = find(q);
			if(rootP == rootQ) {
				return; 
			}
			for(int i = 0; i < id.length; i++) {
				if(id[i] == rootP) {
					id[i] = rootQ; 
				}
			}
			count--; 
		}
		
		/**
		 * Returns the canonical element of the set containing element {@code p}
		 * @param p an element 
		 * @return the canonical element of the set containing p
		 * @throws IllegalArgumentException unless {@code 0 <= p < n)  
		 */
		
		public int find (int p) {
			validate(p);
			return id[p]; 
		}
		
		 /**
	     * Returns true if the two elements are in the same set.
	     *
	     * @param  p one element
	     * @param  q the other element
	     * @return {@code true} if {@code p} and {@code q} are in the same set;
	     *         {@code false} otherwise
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     */
		
		
		public boolean connected(int p, int q) {
			validate(p);
			validate(q);
			return find(p) == find(q);
		}
		
		public int count() {
			return count; 
		}
		
		
		private void validate(int p) {
			int n = id.length;
			if(p < 0 || p >= n) {
				throw new IllegalArgumentException("index " + p + "is not between 0 and " + (n - 1));
			}
		}
	}	
}
