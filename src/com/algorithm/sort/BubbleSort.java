package com.algorithm.sort;

public class BubbleSort {
	private int[] arr = {31, 41, 59, 26, 41, 58};
	
	public void sort() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(j - 1, j);
				}
			}
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
		BubbleSort mySort = new BubbleSort();
		mySort.sort();
		mySort.printRes();
	}

}
