package com.cs61b.phase3;

public class SelectionSort2 {
	public static void sort(int[] a) {
		int n = a.length; 
		for(int i = 0; i < n; i++) {
			int min = i; 
			for(int j = i + 1; j < n; j++) {
				if(a[j] < a[min]) {
					min = j;
				}
			}
			swap(a, i, min);
		}
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp; 
	}
	
	
	public static void main(String[] args) {
		int[] array = new int[] {5,4,3,2,1};
		sort(array);
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
