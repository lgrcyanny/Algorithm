package com.algorithm.quicksort;

public class QuickSort {
	private int[] data = null;
	
	public QuickSort(int[] data) {
		if (data != null) {
			this.data = data;
		}
	}
	
	private int partition(int p, int r) {
		int pivot = data[r];
		int i = p - 1;
		int j = p;
		while (j <= r - 1) {
			if (data[j] <= pivot) {
				i++;
				swap(i, j);
			}
			j++;
		}
		swap(i + 1, j);
		return i + 1;
	}
	
	private int partitionForReversedOrder(int p, int r) {
		int pivot = data[r];
		int i = p - 1;
		int j = p;
		while (j <= r - 1) {
			if (data[j] >= pivot) {
				i++;
				swap(i, j);
			}
			j++;
		}
		swap(i + 1, j);
		return i + 1;
	}
	
	private void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public void quickSort(int p, int r, boolean assending) {
		if (p < r) {
			int q;
			if (assending) {
				q = partition(p, r);
			} else {
				q = partitionForReversedOrder(p, r);
			}
			quickSort(p, q - 1, assending);
			quickSort(q + 1, r, assending);
		}
	}
	
	public void printData() {
		for (int val : data) {
			System.out.println(val);
		}
		System.out.println("===========");
	}
	
	public static void main(String[] args) {
		int[] data = {13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11};
		QuickSort mySort = new QuickSort(data);
		mySort.quickSort(0, data.length - 1, true);
		mySort.printData();
	}

}
