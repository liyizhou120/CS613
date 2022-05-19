package com.cs61b.week4;

public class EnglishToInt {
	/** Convert ith character of a String to a letter number 
	 * e.g. 'a' -> 1, 'b' -> 2 ... 'z' -> 26
	 * @param args
	 */
	
	public static int letterNum(String s, int i) {
		//TODO
		int letterAtIndex = s.charAt(i);
		if((letterAtIndex < 'a') || (letterAtIndex > 'z')) {
			throw new IllegalArgumentException();
		}
		
		return letterAtIndex - 'a' + 1;
 	}
	
	public static int englishToInt(String s) {
		//TODO 
		int letterToIntSum = 0; 
		for(int i = 0; i < s.length(); i++) {
			letterToIntSum = letterToIntSum * 27; 
			letterToIntSum = letterToIntSum + letterNum(s, i);
		}
		
		return letterToIntSum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "rinima";
		int i = englishToInt(str);
		System.out.println(i);
	}

}
