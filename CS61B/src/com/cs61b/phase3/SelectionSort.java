package com.cs61b.phase3;

import java.util.Comparator;
import edu.princeton.cs.algs4.*;


public class SelectionSort {
	private SelectionSort(){
		
	}
	   
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] arr) {
		int n = arr.length;
		for(int i = 0; i < n; i++) {
			int min = i; 
			for(int j = i + 1; j < n; j++) {
				if(less(arr[j], arr[min])) {
					min = j; 
				}
			}
			swap(arr, i, min);
			assert isSorted(arr, 0, i);
		}
		assert isSorted(arr);
	}
	
	@SuppressWarnings("rawtypes")
	public static void sort(Object[] arr, Comparator comparator) {
		int n = arr.length; 
		for(int i = 0; i < n; i++) {
			int min = i;
			for(int j = i + 1; j < n; j++) {
				if(less(comparator, arr[j],arr[min])) {
					min = j; 
				}
			}
			swap(arr, i, min);
			assert isSorted(arr, comparator, 0, i);
		}
		assert isSorted(arr, comparator);
	}
	
	 /***************************************************************************
	    *  Helper sorting functions.
	    ***************************************************************************/
	 // is v < w ? 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean less(Comparable v, Comparable w) { 
		return v.compareTo(w) < 0; 
	}
	
	//is v < w? (using comparator)  
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean less (Comparator comparator, Object v, Object w) {
		return comparator.compare(v, w) < 0; 
	}
	
	public static void swap(Object[] arr, int i, int j) {
		Object temp = arr[i];
		arr[i] = arr[j]; 
		arr[j] = temp;
	}
	
	
	//is array sorted? Using natural order with Comparable interface
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] arr) {
		return isSorted(arr, 0, arr.length - 1);
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Comparable[] arr, int low, int high) {
		for(int i = low + 1; i <= high; i++) {
			if(less(arr[i], arr[i - 1])){
				return false; 
			}
		}
		return true; 
	}
	
	//is array sorted? Using comparator 
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Object[] arr, Comparator comparator) {
		return isSorted(arr, comparator, 0, arr.length - 1); 
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isSorted(Object[] arr, Comparator comparator, int low, int high) {
		for(int i = low + 1; i <= high; i++) {
			if(less(comparator, arr[i], arr[i - 1])) {
				return false; 
			}
		}
		return true; 
	}
	
	@SuppressWarnings("rawtypes")
	private static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			StdOut.print(a[i]);
		}
	}
	
	public static void main(String[] args) {
		String[] a = StdIn.readAllStrings();
		SelectionSort.sort(a);
		show(a);
	}
}
