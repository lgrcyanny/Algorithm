package com.algorithm.sort;

public class MergeSort {
	private int[] arr = {3, 41, 52, 26, 38, 57, 9, 49};
	
	public MergeSort(int[] data) {
		if (data != null) {
			this.arr = data;
		}
	}
	
	public MergeSort() {
		
	}
	
	private void merge(int p, int q, int r) {
		//System.out.println(p + "," + q + "," + r);
		int n1 = q - p + 1;
		int n2 = r - q;
		int i, j, k;
		int[] leftArr = new int[n1 + 1];
		int[] rightArr = new int[n2 + 1];
		
		// Create two arrays
		for (i = 0; i < n1; i++) {
			leftArr[i] = arr[p + i];
		}
		for (j = 0; j < n2; j++) {
			// Not q + j
			rightArr[j] = arr[q + j + 1];
		}
		// Add flag to show when the array is empty
		leftArr[n1] = Integer.MAX_VALUE;
		rightArr[n2] = Integer.MAX_VALUE;
			
		// Execute merge, let k = r, so that copy the not empty array		
		for (i = 0, j = 0, k = p; k <= r; k++) {
			if (leftArr[i] <= rightArr[j]) {
				arr[k] = leftArr[i];
				i++;
			} else {
				arr[k] = rightArr[j];
				j++;
			}
		}		
	}
	
	private void mergeSort(int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(p, q);
			mergeSort(q + 1, r);
			merge(p, q, r);
		}		
	}
	
	public void sort() {
		mergeSort(0, arr.length - 1);
	}
	
	public void printRes() {
		System.out.println("Results:");
		for (int val: arr) {
			System.out.println(val);
		}		
	}
	
	public static void main(String[] args) {
		MergeSort mySort = new MergeSort();
		mySort.sort();
		mySort.printRes();

	}

}
