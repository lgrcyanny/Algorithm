package com.algorithm.lineartimesort;

import java.util.Arrays;
import com.algorithm.utils.Utils;

/**
 * Chapter8, 8-3, Given an array with numbers, which is in different length, the algorithm
 * is to sort the array within O(n).
 * First, count sort by the length of numbers, put them in different bucket
 * Second, radix sort each buket
 * Last, merge the bucket
 * @author lgrcyanny
 *
 */
public class VaringLengthNumberSort {
	private int[] data;
	private int maxDigits;
	private int arraySize;
	private int[] count;
	
	public VaringLengthNumberSort(int[] data, int maxDigits) {
		this.data = data;
		this.arraySize = data.length;
		this.maxDigits = maxDigits;
	}
	
	public int[] getData () {
		return this.data;
	}
	
	private void countSort() {
		int[] temp = new int[arraySize];
		int[] tempCount = new int[maxDigits + 1];
		int i;
		int index;
		
		for (i = 0; i < tempCount.length; i++) {
			tempCount[i] = 0;
		}
		
		for (i = 0; i < arraySize; i++) {
			index = getDigits(data[i]);
			tempCount[index]++;
		}
		
		for (i = 1; i < tempCount.length; i++) {
			tempCount[i] = tempCount[i] + tempCount[i - 1];
		}
		
		// Store the count for later usage on function sort.
		this.count = Arrays.copyOf(tempCount, tempCount.length);
		
		for (i = arraySize - 1; i >= 0; i--) {
			index = getDigits(data[i]);
			temp[tempCount[index] - 1] = data[i];
			tempCount[index]--;
		}
		data = temp;		
	}
	
	private int getDigits(int number) {
		int res = 0;
		while (number > 0) {
			res++;
			number = number / 10;
		}
		return res;
	}
	
	/**
	 * Get the value on the dth digit
	 * @param number
	 * @param d  The digit
	 * @return
	 */
	private int getValueOnDigit(int number, int d) {
		return number % (int) Math.pow(10.0, d) / (int) Math.pow(10.0, d - 1);
	}
	
	/**
	 * The radix subroutine, make use of CountSort to sort numbers by digit 
	 * @param arr
	 * @param digit
	 * @return
	 */
	private int[] stableSort(int[] arr, int digit) {
		int[] tempRes = new int[arr.length];
		int[] tempCount = new int[10]; // Initial each element is zero
		int i;
		int index;
		for (i = 0; i < arr.length; i++) {
			index = getValueOnDigit(arr[i], digit);
			tempCount[index]++;
		}
		
		for (i = 1; i < tempCount.length; i++) {
			tempCount[i] += tempCount[i - 1];
		}
		
		for (i = arr.length - 1; i >= 0; i--) {
			index = getValueOnDigit(arr[i], digit);
			tempRes[tempCount[index] - 1] = arr[i];
			tempCount[index]--;
		}
		return tempRes;		
	}
	
	private int[] radixSort(int[] arr, int digits) {
		int[] tempRes = arr;
		for (int i = 1; i <= digits; i++) {
			tempRes = stableSort(tempRes, i);
		}
		return tempRes;
	}
	
	private void copyDataOfRange(int[] tempRes, int start, int end) {
		if (tempRes.length == (end - start)) {
			for (int i = 0; i < tempRes.length; i++) {
				data[start + i] = tempRes[i];
			}
		} else {
			throw new IndexOutOfBoundsException("tempRes length " + tempRes.length 
					+ " doesn't match the startIndex " + start + "and endIndex " + end);
		}
	}
	
	public void sort() {
		this.countSort();
		for (int i = 1; i < count.length; i++) {
			// Select a fragment of numbers with i digits 
			int startIndex = count[i - 1];
			int endIndex = count[i];
			if (startIndex < endIndex) {
				int[] tempArr = Arrays.copyOfRange(data, startIndex, endIndex);
				tempArr = this.radixSort(tempArr, i);
				this.copyDataOfRange(tempArr, startIndex, endIndex);
			}
		}
	}
	
	public static void main(String[] args) {
		//int[] data = {123, 9, 3, 4, 67, 23, 1234, 65478, 2390, 98910};
		int[] data = Utils.generateRandomTestData(1000000, 1, (int)Math.pow(10.0, 5));
		Utils.printArray(data);
		VaringLengthNumberSort mySort = new VaringLengthNumberSort(data, 5);
		long startTime = System.currentTimeMillis();
		mySort.sort();
		long endTime = System.currentTimeMillis();
		Utils.printArray(mySort.getData());
		System.out.println("Sort time is " + (endTime - startTime));
	}

}
