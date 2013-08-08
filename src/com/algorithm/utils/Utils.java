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
			data[i] = (int) (Math.random() * (maxValue - minValue + 1)) + minValue;
		}
		return data;
	}
}
