package com.vivek.javacode;

import java.util.HashMap;
import java.util.Map;

public class StringCalc {
	public static void main(String args[]) {
		String str1 = "vvkuummmark";
		char strArr[] = str1.toCharArray();
		Map<Character, Integer> countMap = new HashMap<Character, Integer>();
		int count = 0;
		for (int i = 0; i < strArr.length-1; i++) {
			if (strArr[i] == strArr[i++]) {
				countMap.put(strArr[i], count++);
					System.out.println("char:"+strArr[i]+"Count:"+count);
			}
			if(i==strArr.length-1) {
				System.out.println("char:"+strArr[i]+"Count:"+count);
			}

		}
	}
}


