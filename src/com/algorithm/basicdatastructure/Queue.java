package com.algorithm.basicdatastructure;

public class Queue {
	private int[] data;
	private int head;
	private int tail;
	public Queue(int n) {
		data = new int[n + 1];
		head = 0;
		tail = 0;
	}
	
	public void enqueue(int val) {
		if (isFull()) {
			throw new OutOfMemoryError("Queue Full");
		}
		data[tail] = val;
		if (tail == data.length - 1) {
			tail = 0;
		} else {
			tail++;
		}
	}
	
	public int dequeue() {
		if (isEmpty()) {
			throw new OutOfMemoryError("Queue empty.");
		}
		int res = data[head];
		if (head == data.length - 1) {
			head = 0;
		} else {
			head++;
		}
		return res;
	}
	
	public boolean isFull() {
		return tail + 1 == head;
	}

	public boolean isEmpty() {
		return tail == head;
	}
	
	public static void main(String[] args) {
		Queue queue = new Queue(10);
		for (int i = 1; i <= 3; i++) {
			int val = i;
			queue.enqueue(val);
		}
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
//		System.out.println("Dequeue = " + val);
//		queue.enqueue(9);
//		queue.enqueue(10);
//		queue.enqueue(11);
//		val = queue.dequeue();
//		System.out.println("Dequeue = " + val);

	}

}
