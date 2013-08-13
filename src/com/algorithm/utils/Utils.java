package com.algorithm.utils;

public class Utils {
	public static void printArray(int[] data) {
		System.out.println("======Results========");
		for (int value : data) {
			System.out.println(value);
		}
		System.out.println("=======END===========");
	}
	
	public static int[] generateRandomTestData(int arraySize, int minValue, int maxValue) {
		int[] data = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			data[i] = (int) (Math.random() * (maxValue - minValue + 1));
		}
		return data;
	}
	
	public static String[] generateRandomWordsArray(int arraySize, int maxLength) {
		String[] res = new String[arraySize];
		for (int i = 0; i < arraySize; i++) {
			int randomLength = (int) (Math.random() * maxLength + 1);
			res[i] = randomString(randomLength);
		}
		return res;		
	}
	
	public static String randomString(int strlength) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strlength; i++) {
			char achar = (char)(Math.random() * 26 + 'a');
			builder.append(achar);
		}
		return builder.toString();
	}
	
	public static void printStringArray(String[] words) {
		System.out.println("======Results========");
		for (String word : words) {
			System.out.println(word);
		}
		System.out.println("=======END===========");
	}
}
