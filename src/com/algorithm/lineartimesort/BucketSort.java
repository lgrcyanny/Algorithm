package com.algorithm.lineartimesort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.algorithm.utils.Utils;

/**
 * Bucket Sort, Suppose the input data is 0~n-1 (n=100), the input distribution is homogeneous
 * @author lgrcyanny
 *
 */
public class BucketSort {
	private int[] data;
	private int[] results;
	private ArrayList<ArrayList<Integer>> bucket;
	private int bucketSize;
	
	public BucketSort(int[] data) {
		this.data = data;
		this.bucketSize = 10;
		bucket = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < bucketSize; i++) {
			bucket.add(new ArrayList<Integer>(10));
		}
		results = new int[data.length];
	}
	
	public int[] getResults() {
		return results;
	}

	private int partition(int p, int r, ArrayList<Integer> arr) {
		int pivot = arr.get(r);
		int i = p - 1;
		int j = p;
		for (; j <= r - 1; j++) {
			if (arr.get(j) <= pivot) {
				i++;
				swap(i, j, arr);
			}
		}
		swap(i + 1, r, arr);
		return i + 1;
	}
	
	private void quickSort(int p, int r, ArrayList<Integer> arr) {
		if (p < r) {
			int q = partition(p, r, arr);
			quickSort(p, q - 1, arr);
			quickSort(q + 1, r, arr);
		}
	}
	
	private void swap(int i, int j, ArrayList<Integer> arr) {
		int temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}
	
	private int getBucketIndex(int val) {
		return val / 10;
	}
	
	public void bucketSort() {
		for (int i = 0; i < data.length; i++) {
			int index = getBucketIndex(data[i]);
			bucket.get(index).add(data[i]);			
		}
		
		for (int i = 0; i < bucket.size(); i++) {
			//Collections.sort(bucket.get(i));
			if (bucket.get(i).size() > 1) {
				quickSort(0, bucket.get(i).size() - 1, bucket.get(i));
			}
		}
		
		for (int i = 0, j = 0; i < bucket.size(); i++) {
			if (bucket.get(i).size() > 0) {
				Iterator<Integer> iterator = bucket.get(i).iterator();
				while (iterator.hasNext()) {
					results[j] = iterator.next();	
					j++;
				}
			}
		}
	}

	
	public static void main(String[] args) {
		int[] data = Utils.generateRandomTestData(10, 0, 99);
		Utils.printArray(data);
		BucketSort mySort = new BucketSort(data);
		mySort.bucketSort();
		Utils.printArray(mySort.getResults());
	}

}
