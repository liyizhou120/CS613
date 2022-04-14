package com.cs61b.week2;

public class IntLIst {
	public int first; 
	public IntLIst rest; 
	
	public IntLIst(int f, IntLIst r) {
		first = f; 
		rest = r ; 
	}
	
	
	//using recursion method to find size 
	public int size() {
		if(rest == null) {
			return 1; 
		}
		return 1 + this.rest.size();
	}
	
	//Size of linked list using iterative method 
	public int iterativeSize() {
		IntLIst p = this; 
		int totalSize = 0; 
		while (p != null) {
			totalSize += 1; 
			p = p.rest; 
		}
		return totalSize;
	}
	
	
	//get method to return the ith item of this IntList
	public int get (int i) {
		//base case 
		if(i == 0) {
			return first; 
		}
		return rest.get(i - 1);
	}

	public static void main(String[] args) {
		IntLIst L = new IntLIst(1,null);
		L = new IntLIst(10, L);
		L = new IntLIst(100,L);
		
 
		System.out.println(L.size());
		System.out.println(L.get(2));
		
	}

}
