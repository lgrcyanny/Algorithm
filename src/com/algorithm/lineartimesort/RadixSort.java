package com.algorithm.lineartimesort;

import com.algorithm.utils.Utils;

public class RadixSort {
	private int[] data;
	private int[] results;
	private int[] count;
	private int digits;
	
	public RadixSort(int[] data, int digits) {
		this.data = data;
		this.digits = digits;
		this.count = new int[10];
	}
	
	public void printRes() {
		Utils.printArray(data);
	}
	
	private void stableCountSort(int d) {
		int i;
		int index;
		results = new int[data.length];
		
		for (i = 0; i < count.length; i++) {
			count[i] = 0;
		}
		
		for (i = 0; i < data.length; i++) {
			index = getValueOnDigit(data[i], d);
			count[index]++;
		}
		
		for (i = 1; i < count.length; i++) {
			count[i] = count[i] + count[i - 1];
		}
		
		for (i = data.length - 1; i >= 0; i--) {
			index = getValueOnDigit(data[i], d);
			results[count[index] - 1] = data[i];
			count[index]--;
		}
		data = results;
	}
	
	public void sort() {
		for (int d = 1; d <= digits; d++) {
			stableCountSort(d);
		}
	}
	
	/**
	 * Get the value on d digit of Interger val, such as getValueOnDigit(233, 1) will get 3 on the 
	 * single-digit
	 * @param val
	 * @param d
	 * @return
	 */
	private int getValueOnDigit(int val, int d) {
		return val % (int) Math.pow(10.0, d) / (int) Math.pow(10.0, d - 1);
	}
	

	public static void main(String[] args) {
		int[] data = {329, 457, 657, 839, 436, 720, 355};
		//int[] data = Utils.generateRandomTestData(1000000, 1, (int)Math.pow(10.0, 5));
		RadixSort mySort = new RadixSort(data, 5);
		long startTime = System.currentTimeMillis();
		mySort.sort();
		long endTime = System.currentTimeMillis();
		mySort.printRes();
		System.out.println("Sort time is " + (endTime - startTime));
	}

}
