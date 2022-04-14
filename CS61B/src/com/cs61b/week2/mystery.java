package com.cs61b.week2;

import java.util.Arrays;

public class mystery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[] {3, 0, 4, 6, 3};
		int ans = 2; 
		
		int result = mysterycode.mystery1(array, ans);
		System.out.println(result); 
		mysterycode.mystery2(array);
		System.out.println(Arrays.toString(array));
		
	}

}
