package com.cs61b.compression;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

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
			
		}
	}
	
	
	private static Node buildTrie(int[] freq) {
		
	}
	
	private static void writeTrie(Node x) {
		
	}
	
	private static void buildCode(String[] st, Node x, String s) {
		
	}
	
	public static void expand() {
		
	}
	
	private static Node readTrie() {
		
	}
}
