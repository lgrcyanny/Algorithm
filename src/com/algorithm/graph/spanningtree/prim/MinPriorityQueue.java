package com.algorithm.graph.spanningtree.prim;

import java.util.ArrayList;

public class MinPriorityQueue {
	private ArrayList<Node> heap;
	
	public MinPriorityQueue() {
		heap = new ArrayList<Node>();
	}
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public Node getMin() {
		return heap.get(0);
	}
	
	public Node extractMin() {
		Node res = null;
		if (isEmpty()) {
			return res;
		}
		swap(0, heap.size() - 1);
		res = heap.remove(heap.size() - 1);
		minHeapify(0);
		return res;	
	}
	
	public void decreaseKey(int index, int key) {
		if (key > heap.get(index).key) {
			return;
		}
		heap.get(index).key = key;
		while (index > 0) {
			int parent = parent(index);
			if (heap.get(parent).key > heap.get(index).key) {
				swap(parent, index);
				index = parent;
			} else {
				break;
			}
		}
	}
	
	public void insert (Node node) {
		heap.add(node);
		int index = heap.size() - 1;		
		decreaseKey(index, node.key);
	}
	
	public int indexOf(Node node) {
		for (int i = 0; i < heap.size(); i++) {
			if (heap.get(i).label.equals(node.label)) {
				return i;
			}
		}
		return -1;
	}
	
	public void minHeapify(int index) {
		for (int left = leftChild(index); left < heap.size(); index = left) {
			if (left + 1 < heap.size() && heap.get(left + 1).key < heap.get(left).key) {
				left++;
			}
			if (heap.get(left).key < heap.get(index).key) {
				swap(left, index);
			} else {
				break;
			}
		}
	}
	
	private void swap (int i, int j) {
		Node temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	
	private int leftChild(int i) {
		return 2 * i + 1;
	}
	
	public int parent(int i) {
		int res = i % 2 == 0 ? i / 2 - 1 : i / 2; 
		return res;
	}
	
	public void print() {
		for (Node node : heap) {
			System.out.println(node.key);
		}
	}

}
