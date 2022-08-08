package com.cs61b.phase3;

public class InsertionSort {
	public static void sort(int[] arr) {
		int n = arr.length; 
		for(int i = 1; i < n; i++) {
			for(int j = i; j > 0; j--) {
				if(arr[j] < arr[j-1]) {
					swap(arr, j, j - 1);
				}
			}
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
