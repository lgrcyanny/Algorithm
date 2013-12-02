package com.algorithm.basicdatastructure;

import java.util.Arrays;

public class Stack {
	private int[] data;
	private int top;
	public Stack(int n) {
		this.data = new int[n];
		this.top = -1;
	}
	public void push(int val) {
		if (this.isFull()) {
			throw new StackOverflowError("Stack Full Now!");
		}
		top = top + 1;
		data[top] = val;
	}
	
	public int pop() {
		if (isEmpty()) {
			throw new StackOverflowError("Stack Empty!");
		}
		int res = data[top];
		top--;
		return res;
	}
	
	public boolean isFull() {
		return top == data.length - 1;
	}
	
	public boolean isEmpty() {
		return top == -1;
	}

	public static void main(String[] args) {
		Stack stack = new Stack(3);
		stack.push(1);
		stack.push(3);
		stack.push(5);
		int value = stack.pop();
		System.out.println("POP stack : " + value);
		stack.push(4);

	}

}
