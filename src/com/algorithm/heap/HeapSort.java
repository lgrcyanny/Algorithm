package com.algorithm.heap;

import com.algorithm.utils.Utils;

public class HeapSort {
	private int[] arr = {5, 13, 2, 25, 7, 17, 20, 8, 4};
	public HeapSort(int[] data) {
		if (data != null) {
			this.arr = data;
		}
	}
	
	public HeapSort() {
		
	}
	
	public void sort() {
		buildMaxHeap();
		int heapSize = arr.length - 1;
		for (int i = arr.length - 1; i > 0; i--) {
			swap(0, i);
			heapSize--;
			//recursiveMaxHeapify(0, heapSize);
			iterativeMaxHeapify(0, heapSize);
		}
	}
	
	public void printRes() {
		for (int value : arr) {
			System.out.println(value);
		}
	}
	
	private void recursiveMaxHeapify(int i, int heapSize) {
		int leftChildIndex = leftChild(i);
		int rightChildIndex = rightChild(i);
		int largestIndex = -1;
		if (leftChildIndex <= heapSize) {
			largestIndex = arr[leftChildIndex] > arr[i] ? leftChildIndex : i;
		}
		if (rightChildIndex <= heapSize) {
			largestIndex = arr[largestIndex] > arr[rightChildIndex] ? largestIndex : rightChildIndex;
		}
		if (largestIndex >= 0 && i != largestIndex) {
			swap(i, largestIndex);
			recursiveMaxHeapify(largestIndex, heapSize);
		}
	}
	
	private void iterativeMaxHeapify(int i, int heapSize) {
		int largestIndex = -1;
		for (int k = i; k <= heapSize; k = largestIndex) {
			int leftChildIndex = leftChild(k);
			int rightChildIndex = rightChild(k);
			if (leftChildIndex <= heapSize) {
				largestIndex = arr[leftChildIndex] > arr[k] ? leftChildIndex : k;
			}
			if (rightChildIndex <= heapSize) {
				largestIndex = arr[largestIndex] > arr[rightChildIndex] ? largestIndex : rightChildIndex;
			}
			if (largestIndex >= 0 && k != largestIndex) {
				swap(k, largestIndex);
			} else {
				break;
			}
		}
	}
	
	private void buildMaxHeap() {
		// heapSize is the largest index for the children
		int heapSize = arr.length - 1;
		for (int i = (arr.length / 2) - 1; i >= 0; i--) {
			recursiveMaxHeapify(i, heapSize);
		}
	}
	
	public void buildMaxHeap2() {
		int heapSize= arr.length - 1;
		for (int i = (arr.length / 2) - 1; i >= 0; i--) {
			iterativeMaxHeapify(i, heapSize);
		}
	}
	
	private int leftChild(int i) {
		return 2 * i + 1;
	}
	
	private int rightChild(int i) {
		return 2 * i + 2;
	}
	
	private void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = Utils.generateRandomTestData(1000000, 1, (int)Math.pow(10.0, 5));
		HeapSort mySort = new HeapSort(data);
		//mySort.buildMaxHeap();
		long startTime = System.currentTimeMillis();
		mySort.sort();
		long endTime = System.currentTimeMillis();
		//mySort.buildMaxHeap2();
		mySort.printRes();
		System.out.println("Sort time is " + (endTime - startTime));

	}

}
