package com.algorithm.dynamicprogramming;

public class LongestCommonSequence {
	private String[] x;
	private int m; // Length of x
	private String[] y;
	private int n; // Length of y
	private int[][] c;

	public LongestCommonSequence(String[] x, String[] y) {
		this.x = x;
		this.y = y;
		this.m = x.length;
		this.n = y.length;
		this.c = new int[m + 1][n + 1];
	}

	public void LCS() {
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (x[i - 1].equals(y[j - 1])) {
					c[i][j] = c[i - 1][j - 1] + 1;
				} else if (c[i - 1][j] >= c[i][j - 1]) {
					c[i][j] = c[i - 1][j];
				} else {
					c[i][j] = c[i][j - 1];
				}
			}
		}
	}

	public void printLCS(String[] x, String[] y, int[][] c, int i, int j) {
		if (i > 0 && j > 0) {
			if (x[i - 1].equals(y[j - 1])) {
				printLCS(x, y, c, i - 1, j - 1);
				System.out.print(x[i - 1]);
			} else if (c[i - 1][j] >= c[i][j - 1]) {
				printLCS(x, y, c, i - 1, j);
			} else {
				printLCS(x, y, c, i, j - 1);
			}			
		}
	}
	
	public void calculate() {
		this.LCS();
		this.printLCS(x, y, c, m, n);
	}
	
	public static void main(String[] args) {
		//String[] x = {"A", "B", "C", "B", "D", "A", "B"};
		//String[] y = {"B", "D", "C", "A", "B", "A"};
		String[] x = {"1", "0", "0", "1", "0", "1", "0", "1"};
		String[] y = {"0", "1", "0", "1", "1", "0", "1", "1", "0"};
		LongestCommonSequence lcs = new LongestCommonSequence(x, y);
		lcs.calculate();
	}

}
