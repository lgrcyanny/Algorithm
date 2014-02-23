package com.algorithm.dynamicprogramming;

public class OptimistBinarySearshTree {
	private double[] p;
	private double[] q;
	private int n;
	private double[][] e;
	private double[][] w;
	private int[][] root;
	
	public OptimistBinarySearshTree(double[] p, double[] q, int n) {
		this.p = p;
		this.q = q;
		this.n = n;
		this.e = new double[n + 2][n + 1];
		this.w = new double[n + 2][n + 1];
		this.root = new int[n + 1][n + 1];
	}
	
	private void optimalBST() {
		for (int i = 1; i <= n + 1; i++) {
			e[i][i - 1] = q[i - 1];
			w[i][i - 1] = q[i - 1];
		}
		for (int l = 1; l <= n; l++) {
			for (int i = 1; i <= (n - l + 1); i++) {
				int j = i + l - 1;
				e[i][j] = Double.MAX_VALUE;
				w[i][j] = w[i][j - 1] + q[j] + p[j];
				for (int r = i; r <= j; r++) {
					double q = e[i][r - 1] + e[r + 1][j] + w[i][j];
					if (q < e[i][j]) {
						e[i][j] = q;
						root[i][j] = r;
					}
				}
			}
		}
		System.out.println("The optimial BST e[1][n] is " + e[1][n]);
	}
	
	private void constructBST(int[][]root, int i, int j) {
		if (i == 1 && j == n) {
			System.out.println("Root is k" + root[i][j]);
		}
		if (i < j) {
			System.out.println("k" + root[i][root[i][j] - 1] + " is left children of " + "k" + root[i][j]);
			constructBST(root, i, root[i][j] -1);
			System.out.println("k" + root[root[i][j] + 1][j] + "is right children of " + "k" + root[i][j]);
			constructBST(root, root[i][j] + 1, j);
		}
		if (i == j) {
			System.out.println("d" + (i - 1) + " is left children of k" + root[i][j]);
			System.out.println("d" + i + " is right children of k" + root[i][j]);
		}
		if (i > j) {
			System.out.println("d" + j + " is right children of k" + root[i][j]);
		}
	}
	
	public void calculate() {
		this.optimalBST();
		this.constructBST(root, 1, n);
	}
	
	public static void main(String[] args) {
		double[] p = {0, 0.15, 0.1, 0.05, 0.1, 0.2};
		double[] q = {0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
		OptimistBinarySearshTree bst = new OptimistBinarySearshTree(p, q, 5);
		bst.calculate();
	}

}
