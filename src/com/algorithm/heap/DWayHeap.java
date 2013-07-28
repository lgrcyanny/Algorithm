package com.algorithm.heap;

import java.util.Arrays;

public class DWayHeap {
	private int d;
	private int[] heap;
	private int heapSize;
	
	public DWayHeap(int[] data, int d) {
		if (data != null) {
			this.d = d;
			this.heap = data;
			this.heapSize = data.length;
			buildMaxHeap();
		}
	}
	
	private void buildMaxHeap() {
		for (int i = parent(heapSize - 1); i >= 0; i--) {
			maxHeapify(i, heapSize);
		}
	}
	
	public int extractMax() {
		int res = heap[0];
		heap[0] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(0, heapSize);
		return res;
	}
	
	public void insert(int x) throws Exception{
		heapSize++;
		int[] newHeap = Arrays.copyOf(heap, heapSize);
		heap = newHeap;
		heap[heapSize - 1] = Integer.MIN_VALUE;
		increaseKey(heapSize - 1, x);
	}
	
	public void increaseKey(int i, int key) throws Exception {
		if (key < heap[i]) {
			throw new Exception("Key is smaller than current key.");
		}
		int parentIndex;
		for (; i > 0; i = parentIndex) {
			parentIndex = parent(i);
			if (key > heap[parentIndex]) {
				heap[i] = heap[parentIndex];
			} else {
				break;
			}
		}
		heap[i] = key;
	}
	
	private void maxHeapify(int i, int heapSize) {
		int[] childrenIndexes;
		int largestIndex;
		int tmp;
		for (tmp = heap[i]; i <= heapSize - 1; i = largestIndex) {
			childrenIndexes = childrenIndexes(i);
			largestIndex = childrenIndexes[0];
			for (int j = 1; j < d; j++) {
				if (largestIndex <= heapSize - 1 && childrenIndexes[j] <= heapSize - 1) {
					largestIndex = heap[largestIndex] < heap[childrenIndexes[j]] ? childrenIndexes[j] : largestIndex;
				} else {
					break;
				}
			}
			if (largestIndex <= heapSize - 1 && heap[largestIndex] > tmp) {
				heap[i] = heap[largestIndex];
			} else {
				break;
			}
		}
		heap[i] = tmp;
	}
	
	private int[] childrenIndexes(int i) {
		int[] res = new int[d];
		for (int j = 0; j < d; j++) {
			res[j] = d * i + j + 1;
		}
		return res;
	}
	
	private int parent(int i) {
		return (i - 1) / d;
	}
	
	public void printRes() {
		for (int i = 0; i < heapSize; i++) {
			System.out.println(heap[i]);
		}
		System.out.println("==========");
	}

	public static void main(String[] args) throws Exception{
		int[] data = {7, 8, 9, 5, 24, 21, 5, 2, 1, 25, 9, 14, 15};
		DWayHeap heap = new DWayHeap(data, 3);
		heap.insert(30);
		heap.extractMax();
		heap.increaseKey(3, 70);
		heap.printRes();
	}

}
