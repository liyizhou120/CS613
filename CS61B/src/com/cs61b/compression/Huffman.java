package com.cs61b.compression;

import edu.princeton.cs.algs4.MinPQ;


public class Huffman {
	
	public static void printCode(HuffmanCode root, String s) {
		if(root.left == null && root.right == null && Character.isLetter(root.ch)) {
			System.out.println(root.ch + " | " + s);
			return; 
		}
		
		printCode(root.left, s + "0");
		printCode(root.right, s + "1");
	}
		
	public static void main(String[] args) {
		int n = 4; 
		char[] charArray = {'A', 'B', 'C', 'D'};
		int[] charFreq = {5, 1, 6, 3}; 
		
		MinPQ<HuffmanCode> pq = new MinPQ<HuffmanCode>();
		for(int i = 0; i < n; i++) {
			HuffmanCode hc = new HuffmanCode('\0', 0, null, null);
			hc.ch = charArray[i];
			hc.freq = charFreq[i];
			hc.left = null; 
			hc.right = null;
			
			pq.insert(hc);
		}
		
		HuffmanCode root = null; 
		
		while(pq.size() > 1) {
			HuffmanCode x = pq.delMin();
			HuffmanCode y = pq.delMin();
			HuffmanCode f = new HuffmanCode('\0', 0, null, null); 
			
			f.freq = x.freq + y.freq;
			f.ch = '-'; 
			f.left = x; 
			f.right = y; 
			root = f; 
			
			pq.insert(f);
		}
		
		System.out.println(" Character  |   HuffmanCode");
		System.out.println("------------------------------");
		printCode(root, "");
	}
	
}



class HuffmanCode implements Comparable<HuffmanCode>{
	public char ch; 
	public int freq; 
	public HuffmanCode left = null; 
	public HuffmanCode right = null; 
	
	@SuppressWarnings("unused")
	HuffmanCode(char ch, int freq, HuffmanCode left, HuffmanCode right){
		this.ch = ch; 
		this.freq = freq; 
		this.left = left; 
		this.right = right; 
	}
	
	private boolean isLeaf() {
		if(left == null && right == null && Character.isLetter(ch)) {
			return left==null && right == null; 
		}
		return false; 
	}
	
	public int compareTo(HuffmanCode that) {
		return this.freq - that.freq;
	}
} 