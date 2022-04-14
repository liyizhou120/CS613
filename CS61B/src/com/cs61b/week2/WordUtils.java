package com.cs61b.week2;


public class WordUtils {
	
	public static String longest(Inheritance<String> list) {
		int maxDex = 0; 
		for(int i = 0; i < list.size(); i+= 1) {
			String LongestString = list.get(maxDex);
			String thisString = list.get(i);
			if(thisString.length() > LongestString.length()) {
				maxDex = i;
			}
		}
		return list.get(maxDex); 
		
	}
	
	
	public static void main(String[] args) {
		Alist<String> someList = new Alist<>();
		someList.addLast("elk");
		someList.addLast("are");
		someList.addLast("watching"); 
		System.out.println(longest(someList));
	}

}
