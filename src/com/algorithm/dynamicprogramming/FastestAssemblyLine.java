package com.algorithm.dynamicprogramming;

public class FastestAssemblyLine {
	// The time of each stations on line 0 and line 1
	private int[][] a = {
		{7, 9, 3, 4, 8, 4},
		{8, 5, 6, 4, 5, 7}
	}; 
	
	// The time of transfer car from line0 to line1 or from line 1 to line 0
	private int[][] t = {
		{2, 3, 1, 3, 4},
		{2, 1, 2, 2, 1}
	}; 
	
	// The fastest time through each station f[i][j]
	private int[][] f ;
	
	// The fastest line for each path
	private int[][] l; 
	
	// The enter time
	private int[] e = {2, 4};
	
	 // The exit time
	private int[] x = {3, 2};
	
	// Number of stations of each line.
	private int n = 6;
	
	private int fastestTime;
	private int startLine;
	
	public FastestAssemblyLine (int[][] a, int[][] t, int[] e, int[] x) {
		this.a = a;
		this.t = t;
		this.e = e;
		this.x = x;
		this.f = new int[2][a[0].length];
		this.l = new int[2][a[0].length - 1];
		this.n = a[0].length;
	}
	
	public FastestAssemblyLine () {
		this.f = new int[2][n];
		this.l = new int[2][n - 1];
	}
	
	public void fastestWay () {
		f[0][0] = e[0] + a[0][0];
		f[1][0] = e[1] + a[1][0];
		for (int i = 1; i < n; i++) {
			if (f[0][i - 1] + a[0][i] <= f[1][i - 1] + t[1][i - 1] + a[0][i]) {
				f[0][i] = f[0][i - 1] + a[0][i];
				l[0][i - 1] = 0;
			} else {
				f[0][i] = f[1][i - 1] + t[1][i - 1] + a[0][i];
				l[0][i - 1] = 1;
			}
			if (f[1][i - 1] + a[1][i] <= f[0][i - 1] + t[0][i - 1] + a[1][i]) {
				f[1][i] = f[1][i - 1] + a[1][i];
				l[1][i - 1] = 1;
			} else {
				f[1][i] = f[0][i - 1] + t[0][i - 1] + a[1][i];
				l[1][i - 1] = 0;
			}
		}
		
		if (f[0][n - 1] + x[0] <= f[1][n - 1] + x[1]) {
			fastestTime = f[0][n - 1] + x[0];
			startLine = 0;
		} else {
			fastestTime = f[1][n - 1] + x[1];
			startLine = 1;
		}		
	}
	
	public void printStations(int[][] l, int line, int j) {
		if (j < 0){
			System.out.println("Line is " + line + ", Station is 0");
			return;
		}
		printStations(l, l[line][j], j - 1);
		System.out.println("Line is " + line + ", Station is " + (j + 1));
	}
	
	public void calculate () {
		this.fastestWay();
		this.printStations(l, startLine, n - 2);
	}
	
	public static void main(String[] args) {
		FastestAssemblyLine assemblyLine = new FastestAssemblyLine();
		assemblyLine.calculate();
	}

}
