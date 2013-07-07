package com.algorithm.sort;

public class BinaryInsertionSort {
	private int[] arr = {31, 41, 59, 26, 41, 58};
	
	public BinaryInsertionSort(int[] arr) {
		if (arr != null) {
			this.arr = arr;
		}
	}
	
	public BinaryInsertionSort() {
		
	}
	
	public void sort () {
		for (int i = 1; i < arr.length; i++) {		
			binaryInsert(i);
		}
	}	
	
	private void binaryInsert(int i) {
		int temp = arr[i];
		int left = 0;
		int right = i - 1;
		while (left <= right) {
			int middle = (left + right) / 2;
			if (arr[middle] < temp) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		for (int k = i - 1; k >= left; k--) {
			arr[k + 1] = arr[k];
		}
		arr[left] = temp;
	}
	
	public void printRes() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}	
		System.out.println("=================");
	}

	public static void main(String[] args) {
		BinaryInsertionSort mySort = new BinaryInsertionSort();
		mySort.sort();
		mySort.printRes();		
	}

}
