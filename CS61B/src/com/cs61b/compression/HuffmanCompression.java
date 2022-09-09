package com.cs61b.compression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.MinPQ;

public class HuffmanCompression {
	private static final int R = 256; 
	
	// Do not instantiate (Why?) 
	private HuffmanCompression() {}
	
	private static class Node implements Comparable<Node> {
		private final char ch; 
		private final int freq; 
		private final Node left; 
		private final Node right; 
		
		@SuppressWarnings("unused")
		Node(char ch, int freq, Node left, Node right){
			this.ch = ch; 
			this.freq = freq;
			this.left = left;
			this.right = right; 
		}
		
		//check if the node a leaf node 
		private boolean isLeaf() {
			assert ((left == null) && (right == null) || (left != null) && (right != null));
			
			return(left == null) && (right == null);
		}
		
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}
	
	/**
	 * Reads a sequence of 8-bit bytes from standard input; compresses them using 
	 * Huffman codes with an 8-bit alphabet; and writes the results 
	 * to standard output 
	 */
	
	public static void compress() {
		//read the input 
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		int[] freq = new int[R];
		
		for(int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}
		
		//build Huffman trie 
		Node root = buildTrie(freq);
		
		//build Huffman code table 
		String[] st = new String[R];
		buildCode(st, root, "");
		
		//print trie for decoder 
		writeTrie(root);
		
		//print number of bytes in original uncompressed message 
		BinaryStdOut.write(input.length);
		
		//use Huffman code to encode input 
		for(int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for(int j = 0; j < code.length(); j++) {
				if(code.charAt(j) == '0') {
					BinaryStdOut.write(false);
				}
				else if (code.charAt(j) == '1') {
					BinaryStdOut.write(true);
				}
				else {
					throw new IllegalStateException("Illegal state");
				}
			}
		}
		
		BinaryStdOut.close();
	}
	
	
	private static Node buildTrie(int[] freq) {
		//Find two nodes with smallest freq and merge them with a new parent 
		MinPQ<Node> pq = new MinPQ<>(); 
		
//		char[] charArray = new char[R];
//		Node parent = new Node('\0', 0, null, null);
		
		// My method 
//		for(int i = 0; i < freq.length; i++) {
//			if(freq[i] != 0) {
//				pq.insert(new Node(charArray[freq[i]], freq[i], null, null));
//			}
//		}
		
		//insert freq Node into the MinPQ 
		for(char c = 0; c < R; c++) {
			if(freq[c] > 0) {
				pq.insert(new Node(c, freq[c], null, null));
			}
		}
		
	
		while(pq.size() > 1) {
			Node leftNode = pq.delMin();
			Node rightNode = pq.delMin();
			
			int freqSum = leftNode.freq + rightNode.freq;
					
			Node parent = new Node('\0', freqSum, leftNode, rightNode);
			pq.insert(parent);
		}
		
		return pq.delMin(); 
	}
	
	private static void writeTrie(Node x) {
		if(x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch, 8);
			return; 
		}
		
		//Current bit is 0 
		BinaryStdOut.write(false);
		
		//Preorder traversal 
		writeTrie(x.left);
		writeTrie(x.right);
	}
	
	
	//make a lookup table from symbols and their encodings
	private static void buildCode(String[] st, Node x, String s) {
		if(!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1'); 
		}else {
			st[x.ch] = s;
		}
	}
	
	public static void expand() {
		//read in Huffman trie from input stream
		Node root = readTrie(); 
		
		//number of bytes to write 
		int length = BinaryStdIn.readInt();
		
		//decode using Huffman Trie 
		for(int i = 0; i < length; i++) {
			Node x = root; 
			while(!x.isLeaf()) {
				boolean bit = BinaryStdIn.readBoolean();
				if(bit) {
					x = x.right;
				}
				else {
					x = x.left; 
				}
			}
			BinaryStdOut.write(x.ch, 8);
		}
		BinaryStdOut.close();
	}
	
	private static Node readTrie() {
		boolean isLeaf = BinaryStdIn.readBoolean();
		if(isLeaf) {
			return new Node(BinaryStdIn.readChar(), -1, null, null);
		}
		else {
			return new Node('\0', -1, readTrie(), readTrie()); 
		}
	}
	
	public static void main(String[] args) {
		if(args[0].equals("-")) {
			compress();
		}
		else if(args[0].equals("+")) {
			expand();
		}
		else throw new IllegalStateException("Illegal command line argument");
	}
}
