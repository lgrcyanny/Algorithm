package com.algorithm.warshall;

import java.util.Arrays;

/**
 * Detect a set of function call relations
 * Detect if there is cycle on these relations with Warshall's Algorithm
 * that is to generate the transitive closure.
 * Input: an adjacent matrix which indicates the relationships of function calls, such as
 * [0, 0, 1, 0,
 *  0, 1, 1, 0,
 *  1, 0, 0, 1,
 *  0, 1, 0, 1]
 * Output: The transitive closure and the cycle functions
 * @author Liang Guorong
 */
public class CycleDetection {
	// The adjacent matrix, implements the 2D matrix with 1D array
	private int[] matrix;
	
	// The transitive closure records the results
	private int[] closure;
	
	// The total amount of functions or graph vertexes
	private int n;
	
	public CycleDetection(int[] matrix, int n) {
		if (matrix != null) {
			this.matrix = Arrays.copyOf(matrix, matrix.length);
		}
		this.n = n;
	}
	
	private int getValue(int[] matrix, int i, int j) {
		if (i >= n || j >= n) {
			throw new ArrayIndexOutOfBoundsException("i = " + i + ", j = " + j + 
					" out of bounds length = " + matrix.length);
		}
		return matrix[i * n + j];
	}
	
	private void setValue(int[] martrix, int i, int j, int val) {
		if (i >= n || j >= n) {
			throw new ArrayIndexOutOfBoundsException("i = " + i + ", j = " + j + 
					" out of bounds length = " + matrix.length);
		}
		this.closure[i * n + j] = val;
	}
	
	/**
	 * Generate the transitive closure with Warshall's Algorithm
	 * The core function
	 */
	public void warshallDetect() {
		closure = Arrays.copyOf(this.matrix, this.matrix.length);
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (getValue(closure, i, k) == 1 && getValue(closure, k, j) == 1) {
						setValue(closure, i, j, 1);
					}
				}
			}
		}
	}
	
	/**
	 * Check the closure, find if there is cycle function call
	 * prints the cycle function call vertex
	 */
	public void printCycleFunction() {
		for (int i = 0; i < n; i++) {
			if (getValue(closure, i, i) == 1) {
				System.out.println("function " + i + " cycle call.");
			}
		}
	}
	
	public void cycleDetect() {
		System.out.println("INPUT:");
		printMatrix(matrix);
		warshallDetect();
		System.out.println("OUTPUT:");
		System.out.println("The Transitive Closure:");
		printMatrix(closure);
		System.out.println("Cycle Functions:");
		printCycleFunction();
	}
	
	public void printMatrix(int[] data) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(this.getValue(data, i, j) + ", ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[] relations = {
			0, 1, 0, 0,
			1, 0, 1, 0,
			0, 0, 0, 1,
			0, 0, 0, 0
		};
		CycleDetection detection = new CycleDetection(relations, 4);
		detection.cycleDetect();
	}
}
