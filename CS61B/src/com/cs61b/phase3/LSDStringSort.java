package com.cs61b.phase3;

import java.util.Arrays;

public class LSDStringSort {
	public static void sort(String[] arr, int W) {
		int N = arr.length;
		int R = 256; 
		String[] aux = new String[N];
		
		for(int d = W - 1; d >= 0; d--) {
			int[] count = new int[R + 1];
			for(int i = 0; i < N; i++) {
				count[(int)arr[i].charAt(d) + 1]++;
			}
			
			for(int r = 0; r < R; r++) {
				count[r + 1] += count[r];
			}
			
			for(int i = 0; i < N; i++) {
				aux[(int)count[arr[i].charAt(d)]++] = arr[i];
			}
			
			for(int i = 0; i < N; i++) {
				arr[i] = aux[i];
			}
			
		}
	}
		
		
	public static void main(String[] args) {	
		String[] a = new String[]{"4PGC938", "2IYE230","3CIO720", "1ICK750"};
		sort(a,7);
		System.out.println(Arrays.toString(a));
	}
}
