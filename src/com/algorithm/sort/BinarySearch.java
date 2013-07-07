package com.algorithm.sort;

public class BinarySearch {
	private int[] arr;
	
	public BinarySearch(int[] arr) {
		this.arr = arr;
	}
	
	/**
	 * Binary Search int x, return the index if find
	 * @param x
	 * @param start the start index
	 * @param end   the end index , such as arr.length - 1
	 * @return
	 */
	public int search(int x, int start, int end) {
		int res = -1;
		int left = start;
		int right = end;
		int middle = (left + right) / 2;
		while (left <= right) {
			if (arr[middle] == x) {
				res = middle;
				break;
			} else if (arr[middle] < x) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = (left + right) / 2;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] testArr = {1, 2, 4, 5, 7, 9, 11, 13};
		BinarySearch mySearch = new BinarySearch(testArr);
		System.out.println(mySearch.search(13, 0, testArr.length - 1));
	}

}
