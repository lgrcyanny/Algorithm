package com.algorithm.dynamicprogramming;

public class MatrixMultiplication {
	// Matrix Multiplication sequence
	private int[] p;
	
	// The minimum cost for each chain order m[i, j]
	private int[][] m;
	
	// The partition index k for each minimum cost m[i, j]
	private int[][] s;
	
	// The number of matrix
	private int n;
	
	public MatrixMultiplication (int[] p) {
		this.p = p;
		this.n = p.length - 1;
		this.m = new int[n][n];
		this.s = new int[n][n];
	}
	
	public void matrixChainOrder () {
		// l is the length of the chain order
		for (int l = 2; l < n; l++) {
			for (int i = 0; i < (n - l + 1); i++) {
				int j = i + l - 1;
				m[i][j] = Integer.MAX_VALUE;
				for (int k = i; k <= j - 1; k++) {
					int q = m[i][k] + m[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
					if (q < m[i][j]) {
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		
	}
	
	public void printChainOrder (int[][] s, int i , int j) {
		if (i == j) {
			System.out.print("A" + i);
		} else {
			System.out.print("(");
			printChainOrder(s, i, s[i][j]);
			printChainOrder(s, s[i][j] + 1,  j);
			System.out.print(")");
		}
	}
	
	public void calculate () {
		this.matrixChainOrder();
		this.printChainOrder(s, 0, n - 1);
	}
	
	public static void main(String[] args) {
		int[] p = {30, 35, 15, 5, 10, 20, 25};
		MatrixMultiplication multiplication = new MatrixMultiplication(p);
		multiplication.calculate();
		
		System.out.println();
		
		p = new int[]{5, 10, 3, 12, 5, 50, 6};
		multiplication = new MatrixMultiplication(p);
		multiplication.calculate();
	}
	
}
