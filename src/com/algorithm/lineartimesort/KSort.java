package com.algorithm.lineartimesort;

import com.algorithm.utils.Utils;

/**
 * K sort an array of integers within O(n*lg(n/k))
 * Quick sort subset:
 * 1, 1 + k, 1 + 2k, 1 + 3k,...
 * 2, 2 + k, 2 + 2k, 2 + 3k,...
 * ...
 * i, i + k, i+ 2k, i + 3k....
 * ....
 * each subset O(n/k * lg(n/k)), there are k subsets in total, the time is O(n * lg(n/k))
 * @author lgrcyanny
 *
 */
public class KSort {
	int[] data;
	int k;
	
	public KSort(int[] data, int k) {
		this.data = data;
		this.k = k;
	}
	
	public int partition(int p, int r) {
		int pivot = data[r];
		int i = p - k;
		int j = p;
		while (j <= r - k) {
			if (data[j] <= pivot) {
				i = i + k;
				swap(i, j);				
			}
			j = j + k;
		}
		swap(i+ k, r);
		return i + k;
	}
	
	public void quickSort(int p, int r) {
		if (p < r) {
			int q = partition(p, r);
			quickSort(p, q - k);
			quickSort(q + k, r);
		}
	}
	
	public void kSort() {
		for (int i = 0; i <= k; i++) {
			quickSort(i, data.length - 1);
		}
	}
	
	private void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] data = {11, 5, 7, 8, 10, 2, 4, 6, 9, 3, 1, 13, 2, 17, 15};
		Utils.printArray(data);
		KSort myKsort = new KSort(data, 2);
		myKsort.kSort();
		Utils.printArray(data);
	}

}
