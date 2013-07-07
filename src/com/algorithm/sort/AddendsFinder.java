package com.algorithm.sort;
/**
 * 来自算法导论2.3.7 请给出一个运行时间为O（n*lgn）的算法， 使之能再给定一个由n个整数构成的集合S和另一个整数x时，
 * 判断出S中是否存在有两个其和等于x的元素
 * 
 * @author lgrcyanny
 * 
 */
public class AddendsFinder {
	private int[] dataSet = null;

	public AddendsFinder(int[] data) {
		if (data != null) {
			this.dataSet = data;
		}
	}

	public Addends findAddends(int sum) {
		Addends res = null;
		MergeSort mySort = new MergeSort(dataSet);
		mySort.sort();
		BinarySearch mySearch = new BinarySearch(dataSet);
		int m, n;
		for (int i = 0; i < dataSet.length; i++) {
			m = dataSet[i];
			n = sum - m;
			int index = mySearch.search(n, i + 1, dataSet.length - 1);
			if (index > 0) {
				res = new Addends(m, n);
				break;
			}
		}
		return res;
	}

	public class Addends {
		private int m, n;

		public Addends(int m, int n) {
			this.m = m;
			this.n = n;
		}

		public int getM() {
			return m;
		}

		public int getN() {
			return n;
		}

	}

	public static void main(String[] args) {
		int[] data = { 3, 41, 52, 26, 38, 57, 9, 49, 17, 18, 89, 1, 23, 24, 24 };
		AddendsFinder myFinder = new AddendsFinder(data);
		long startTime = System.currentTimeMillis();
		Addends res = myFinder.findAddends(27);
		long endTime = System.currentTimeMillis();
		System.out.println("Total time is : " + (endTime - startTime));
		if (res != null) {
			System.out.println("Results is here: m = " + res.getM() + ", n = "
					+ res.getN());
		} else {
			System.out.println("Null");
		}
	}
}
