package com.algorithm.sort;

public class InsertionSort {
	private int[] arr = {31, 41, 59, 26, 41, 58};
	
	public InsertionSort(int[] arr) {
		if (arr != null) {
			this.arr = arr;
		}
	}
	
	public InsertionSort() {
		
	}
	
	public void sort (boolean assending) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int j = i - 1;
			for ( ; j >= 0; j--) {
				if (assending) {
					if (arr[j] > temp) {
						arr[j + 1] = arr[j];
					} else {
						break;
					}
				} else {
					if (arr[j] < temp) {
						arr[j + 1] = arr[j];
					} else {
						break;
					}
				}
			}
			arr[j + 1] = temp;
		}
	}		
	
	public void printRes() {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}	
		System.out.println("=================");
	}

	public static void main(String[] args) {
		InsertionSort mySort = new InsertionSort();
		mySort.sort(true);
		mySort.printRes();
		mySort.sort(false);
		mySort.printRes();
	}

}
