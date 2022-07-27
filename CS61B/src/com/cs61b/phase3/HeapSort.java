package com.cs61b.phase3;
import java.util.*;



public class HeapSort {

	public static void sort(int[] elements) { 
		buildMaxHeap(elements);
		
		for(int i = elements.length - 1; i > 0; i --) {
			//Move root to end
			swap(elements, 0 , i);
			heapify(elements, 0, i);
		}
		
	}
	
	private static void buildMaxHeap(int[] elements) {
		for(int i = (int)Math.floor(elements.length / 2); i >= 0; i --) {
			heapify(elements, i, elements.length);
		}
	}
	
	private static void heapify(int[] elements, int i, int len) {
		int left = 2 * i + 1; 
		int right = 2 * i + 2;
		int largest = i; 
		
		if(left < len && elements[left] > elements[largest]) {
			largest = left; 
		}
		
		if(right < len && elements[right] > elements[largest]) {
			largest = right; 
		}
		
		if(largest != i) {
			swap(elements, i, largest);
			heapify(elements, largest, i);
		}
	}
	
	private static void swap(int[] elements, int a, int b) {
		int temp = elements[a]; 
		elements[a] = elements[b]; 
		elements[b] = temp; 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] elements = new int[] {50,37,20,2,1,21,8};
		sort(elements);
		
		System.out.println("Array: " + Arrays.toString(elements));
	}

}
