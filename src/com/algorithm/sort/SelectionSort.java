package com.algorithm.sort;

public class SelectionSort {
	private int[] arr = {31, 41, 59, 26, 41, 58};
	
	public void sort() {
		for (int i = 0; i < arr.length; i++) {
			int val = arr[i];
			int key = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < val) {
					val = arr[j];
					key = j;
				}
			}
			swap(i, key);
		}
	}
	
	private void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public void printRes() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}	
		System.out.println("=================");
	}
	
	public static void main(String[] args) {
		SelectionSort mySort = new SelectionSort();
		mySort.sort();
		mySort.printRes();
	}

}
