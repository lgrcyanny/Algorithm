package com.algorithm.heap;

import java.util.Arrays;

public class YoungMatrix {
	// One-dimensional array represent two-dimensional matrix
	private int[] matrix;
	private int m;
	private int n;
	private int matrixSize;
	private int elementSize;
	
	public YoungMatrix(int[] data, int size, int m, int n) {
		if (data != null) {
			if (size != m * n) {
				throw new IllegalArgumentException("size is not equal to m * n");
			}
			this.matrix = Arrays.copyOf(data, size);
			for (int i = data.length; i < size; i++) {
				matrix[i] = Integer.MAX_VALUE;
			}
			Arrays.sort(matrix);
			this.m = m;
			this.n = n;
			this.matrixSize = size;
			this.elementSize = data.length;
		}
	}
	
	public int getValue(int i, int j) {
		if (i >= m || j >= n) {
			throw new IllegalArgumentException("Matrix overflow.");
		}
		return matrix[n * i + j];
	}
	
	public void setValue(int i, int j, int value) {
		if (i >= m || j >= n) {
			throw new IllegalArgumentException("Matrix overflow.");
		}
		matrix[n * i + j] = value;
	}
	
	public boolean isEmpty() {
		return getValue(0, 0) == Integer.MAX_VALUE;
	}
	
	public boolean isFull() {
		return getValue(m - 1, n - 1) < Integer.MAX_VALUE;
	}
	
	public int lastElementI() {
		return elementSize / m;
	}
	
	public int lastElementJ() {
		return elementSize % m - 1;
	}
	
	public void printMatrix() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(getValue(i, j) + ",");
			}
			System.out.println();
		}
		System.out.println("===============");
	}
	
	public int extractMin() {
		int res = getValue(0, 0);
		if (m == 1 && n == 1) {
			setValue(0, 0, Integer.MAX_VALUE);
			elementSize = 0;
			return res;
		}
		// Replace the first value of matrix by the last element 
//		matrix[0] = matrix[elementSize - 1];
//		matrix[elementSize - 1] = Integer.MAX_VALUE;
		setValue(lastElementI(), lastElementJ(), Integer.MAX_VALUE);
		elementSize--;
		
		minMatrix(0, 0);
		return res;
	}
	
	public void minMatrix(int i, int j) {
		int currentValue = getValue(i, j);
		int minI = i;
		int minJ = j;
		if (isEmpty() || elementSize == 1) {
			return;
		}
		if (i < m - 1 && getValue(i + 1, j) < currentValue) {
			minI = i + 1;
			minJ = j;
		}
		if (j < n - 1 && getValue(i, j + 1) < getValue(minI, minJ)) {
			minI = i;
			minJ = j + 1;
		}
		// Do exchange
		if (minI != i || minJ != j) {
			setValue(i, j, getValue(minI, minJ));
			setValue(minI, minJ, currentValue);
		} else {
			return;
		}
		minMatrix(minI, minJ);
	}
	
	public void decreaseKey(int i, int j, int key) {
		if (key > getValue(i, j)) {
			throw new IllegalArgumentException("key is bigger than current key.");
		}
		if (i== 0 && j == 0) {
			setValue(i, j, key);
			return;
		}
		int maxI = i;
		int maxJ = j;
		if (i > 0 && getValue(i - 1, j) > key) {
			maxI = i - 1;
			maxJ = j;
		}
		if (j > 0 && getValue(i, j - 1) > getValue(maxI, maxJ)) {
			maxI = i;
			maxJ = j - 1;
		}
		if (i != maxI || j != maxJ) {
			setValue(i, j, getValue(maxI, maxJ));
			setValue(maxI, maxJ, key);
		} else {
			setValue(i, j, key);
			return;
		}
		decreaseKey(maxI, maxJ, key);		
	}
	
	public void insert(int x) throws Exception {
		if (isFull()) {
			throw new Exception("The young matrix is full");
		}
		elementSize++;
		decreaseKey(lastElementI(), lastElementJ(), x);
	}
	
	public boolean findKey(int key) {
		boolean res = false;
		int i = 0;
		int j = n - 1;
		while (i < n && j >= 0) {
			int tmp = getValue(i, j);
			if (tmp == key) {
				res = true;
				break;
			} else if (tmp < key) {
				i++;
			} else {
				j--;
			}
		}
		return res;
	}

	public static void main(String[] args) throws Exception {
		int data[] = {9, 16, 3, 2, 4, 8, 5, 14, 12};
		YoungMatrix youngMatrix = new YoungMatrix(data, 16, 4, 4);
		youngMatrix.printMatrix();
		youngMatrix.extractMin();
		youngMatrix.printMatrix();
		youngMatrix.insert(1);
		youngMatrix.decreaseKey(1, 1, 3);
		youngMatrix.printMatrix();
		System.out.println(youngMatrix.findKey(17));
	}

}
