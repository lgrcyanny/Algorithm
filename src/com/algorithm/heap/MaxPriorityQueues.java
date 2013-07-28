package com.algorithm.heap;

import java.util.Arrays;

public class MaxPriorityQueues {
	private int[] heap;
	private int heapSize;
	
	public MaxPriorityQueues() {
		
	}
	
	public MaxPriorityQueues(int[] data) {
		if (data != null) {
			this.heap = data;
			this.heapSize = heap.length;
			buildMaxHeap();
		}
	}	
	
	public void insert(int x) throws Exception {
		heapSize++;
		int[] newHeap = Arrays.copyOf(heap, heapSize);
		newHeap[heapSize - 1] = Integer.MIN_VALUE;
		this.heap = newHeap;
		increaseKey(heapSize - 1, x);
	}
	
	public int maxmum() {
		return heap[0];
	}
	
	public int extractMax() throws Exception {
		if (heapSize < 1) {
			throw new Exception("There is no element in heap.");
		}
		int res = heap[0];
		heap[0] = heap[heapSize - 1];
		heapSize--;
		maxHeapify(0, heapSize);
		return res;
	}
	
	public void increaseKey(int i, int key) throws Exception{
		if (heap[i] > key) {
			throw new Exception("The key is smaller than the current key " + heap[i]);
		}
		heap[i] = key;
		while (i > 0 && heap[parent(i)] < key) {
			int parentIndex = parent(i);
			heap[i] = heap[parentIndex];
			i = parentIndex;
		}
		heap[i] = key;
	}
	
	public int delete(int i) {
		int res = heap[i];
		heap[i] = heap[heapSize - 1];
		heapSize--;
		if (i > 0 && heap[i] > heap[parent(i)]) {
			int tmp = heap[i];
			while (i > 0 && heap[parent(i)] < tmp) {
				int parentIndex = parent(i);
				heap[i] = heap[parentIndex];
				i = parentIndex;
			}
			heap[i] = tmp;
		} else {
			maxHeapify(i, heapSize);
		}
		return res;
	}
	
	public int heapSize() {
		return heapSize;
	}
	
	private void maxHeapify(int i, int heapSize) {
		int childIndex = -1;
		int tmp;
		for (tmp = heap[i]; leftChild(i) < heapSize; i = childIndex) {
			childIndex = leftChild(i);
			if (childIndex != heapSize - 1 && heap[childIndex] < heap[childIndex + 1]) {
				childIndex++;
			}
			if (heap[childIndex] > tmp) {
				heap[i] = heap[childIndex];
			} else {
				break;
			}
		}
		heap[i] = tmp;
	}
	
	private int leftChild(int i) {
		return 2 * i + 1;
	}
	
	public int parent(int i) {
		return i / 2;
	}
	
	private void buildMaxHeap() {
		for (int i = (heap.length / 2) - 1; i >= 0; i--) {
			maxHeapify(i, heapSize);
		}
	}
	
	public void printHeap() {
		for (int i = 0; i < heapSize; i++) {
			System.out.println(heap[i]);
		}
	}
	
	public static void main(String[] args) throws Exception{
		int[] arr = {5, 13, 2, 25, 7, 17, 20, 8, 4};
		MaxPriorityQueues maxPriorityQueues = new MaxPriorityQueues(arr);
		maxPriorityQueues.printHeap();
		System.out.println("=========");
		
		// Extract Max
		int max = maxPriorityQueues.extractMax();
		System.out.println("The max is " + max);
		maxPriorityQueues.printHeap();
		System.out.println("=========");
		
		//Delete
		int element = maxPriorityQueues.delete(1);
		System.out.println("The deleted element is " + element);
		maxPriorityQueues.printHeap();
		System.out.println("=========");
		
		//Increase
		maxPriorityQueues.increaseKey(8, 100);
		maxPriorityQueues.printHeap();
		System.out.println("=========");
		
		//Insert
		maxPriorityQueues.insert(22);
		maxPriorityQueues.printHeap();
		System.out.println("=========");
		
		//Maxmum
		System.out.println(maxPriorityQueues.maxmum());
	}	
}
