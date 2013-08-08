package com.algorithm.lineartimesort;

import com.algorithm.utils.Utils;

/**
 * This algorithm is to sort n integer which value is 0~n^2 - 1, within O(n) time
 * Idea: treat each integer as  2-digit Integer in radix n, then radix sort 2 times, each time O(n)
 * so total time is 2*O(n + n) = O(n)
 * @author lgrcyanny
 *
 */
public class RadixSortLimitedRunTime {
	private int[] data;
	private int digits;
	private int[] count;
	private int[] tempData;
	private int n; // the amount of integers in array data
	private int radix;
	public RadixSortLimitedRunTime(int[] data) {
		this.data = data;
		this.digits = 2;
		this.n = data.length;
		this.radix = n;
		this.count = new int[n];
	}
	
	private void stableSort(int d) {
		int i;
		int index;
		tempData = new int[n];
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
			tempData[count[index] - 1] = data[i];
			count[index]--;
		}
		data = tempData;
	}
	
	public void sort() {
		for (int d = 1; d <= digits; d++) {
			stableSort(d);
		}
	}
	
	public void printRes() {
		Utils.printArray(data);
	}
	
	public static int[] generateRandomTestData(int n) {
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = (int) (Math.random() * Math.pow(n * 1.0, 2));
		}
		return data;
	}
	
	private int getValueOnDigit(int val, int d) {
		return val % (int) Math.pow(radix * 1.0, d) / (int) Math.pow(radix * 1.0, d - 1);
	}
	
	public static void main(String[] args) {
		int[] data = generateRandomTestData(1000000);
		Utils.printArray(data);
		RadixSortLimitedRunTime radixSort = new RadixSortLimitedRunTime(data);
		long startTime = System.currentTimeMillis();
		radixSort.sort();
		long endTime = System.currentTimeMillis();
		radixSort.printRes();
		System.out.println("Sorttime is " + (endTime - startTime));
	}

}
