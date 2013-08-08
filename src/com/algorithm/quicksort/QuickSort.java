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
	
	private int partitionHoare(int p, int r) {
		int i = p;
		int j = r;
		int pivot = data[p];
		while (i != j) {
			while (i < j && data[j] > pivot) j--;
			if (i < j) {
				data[i] = data[j];
				i++;
			}
			while (i < j && data[i] < pivot) i++;
			if (i < j) {
				data[j] = data[i];
				j--;
			}
		}
		data[i] = pivot;
		return i;
	}
	
	private int randomPartition(int p, int r) {
		int pivot = (int) Math.random() * (r - p + 1) + p;
		swap(pivot, data[r]);
		return partition(p, r);
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
	
	public void quickSortRandom(int p, int r, boolean assending) {
		if (p < r) {
			int q;
			if (assending) {
				q = randomPartition(p, r);
			} else {
				q = partitionForReversedOrder(p, r);
			}
			quickSort(p, q - 1, assending);
			quickSort(q + 1, r, assending);
		}
	}
	
	public void quickSortHoare(int p, int r) {
		if (p < r) {
			int pivot = partitionHoare(p, r);
			quickSortHoare(p, pivot - 1);
			quickSortHoare(pivot + 1, r);
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
		//mySort.quickSortRandom(0, data.length - 1, true);
		mySort.quickSortHoare(0, data.length - 1);
		mySort.printData();
	}

}
