package com.algorithm.orderstatistics;

import com.algorithm.utils.Utils;

public class Quantile {
	private RandomizedSelect selector;
	private int[] data;
	private int[] res;
	private int n; // The length of array data
	private int k; // The Kth quantile
	private int t; // The amount in each k set
	
	public Quantile(int[] data, int k) {
		this.data = data;
		this.res = new int[k];
		this.k = k;
		this.n = data.length;
		if (n % k != 0) {
			throw new IllegalArgumentException("The data set can be seprated into k subsets.");
		}
		this.t = n / k;
		selector = new RandomizedSelect(data, 1);
	}
		
	public int[] getRes() {
		return res;
	}

	public void calQuantiles(int[] data, int p, int r, int k) {
		int rank = k == 1 ? k * t : k / 2 * t;
		int quantile = selector.randomSelect(p, r, rank);
		res[(rank + p) / t - 1] = quantile;
		if (k == 1) {
			return;
		}
		int q = Utils.indexOf(data, p, r, quantile);
		calQuantiles(data, p, q, k / 2);
		calQuantiles(data, q+ 1, r, k - (int)(k / 2));
	}

	public static void main(String[] args) {
		int[] data = {3, 2, 1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		int k = 5;
		Quantile quantile = new Quantile(data, k);
		quantile.calQuantiles(data, 0, data.length - 1, k);
		Utils.printArray(quantile.getRes());
	}

}
