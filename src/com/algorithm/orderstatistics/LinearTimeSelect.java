package com.algorithm.orderstatistics;

import java.util.Arrays;

import com.algorithm.utils.Utils;

/**
 * Select the i th smallest element in an array 
 * with specific partition method
 * @author lgrcyanny
 *
 */
public class LinearTimeSelect {
	int[] data;
	int[] medianArr;
	int rank;
	public LinearTimeSelect(int[] data, int rank) {
		if (rank < 1 || rank > data.length){
			throw new ArrayIndexOutOfBoundsException("rank out of bounds");
		}
		this.data = Arrays.copyOf(data, data.length);
		this.medianArr = Arrays.copyOf(data, data.length);
		this.rank = rank;
	}
	
	/**
	 * 
	 * @param data
	 * @param start  start index inclusive
	 * @param end    end index inclusive
	 * @param key
	 * @return
	 */
	private int indexOf(int[] data, int start, int end, int key) {
		for (int i = start; i <= end; i++) {
			if (data[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	private int partition(int[] data, int p, int r, int pivot) {
		int i = p - 1;
		int j = p;
		// 为了保证划分正确性的折中办法
		int index = indexOf(data, p, r, pivot);
		Utils.swap(data, index, r);
		while (j <= r - 1) {
			if (data[j] <= pivot) {
				i++;
				Utils.swap(data, i, j);
			}
			j++;
		}
		Utils.swap(data, i + 1, j);
		return i + 1;
	}
	
	/**
	 * Get the median value of an array
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	private int getMedianValue(int[] data, int start, int end) {
		int i, j;
		for (i = start + 1; i <= end; i++) {
			int temp = data[i];
			for (j = i; j > start && temp < data[j - 1]; j--) {
				data[j] = data[j - 1];
			}
			data[j] = temp;
		}
		//当为偶数个元素时，默认中位数都是下中位数
		int k = end - start + 1; // 元素个数
		int medianIndex = k % 2 == 0 ? k / 2 - 1 + start : k / 2 + start;
		return data[medianIndex];
	}
	
	private int[] getMedianArray(int[] data) {
		// 将数组元素划分为n/5个组，每组5个元素求中位数
		int length =  data.length % 5 == 0 ? data.length / 5 : data.length / 5 + 1;
		int[] res = new int[length];
		if (length == 1) {
			res[0] = getMedianValue(data, 0, data.length - 1);
		} else if (length > 1) {
			for (int i = 0; i < length - 1; i++) {
				int start = 5 * i;
				int end = start + 4;
				res[i] = getMedianValue(data, start, end);
			}
			res[length - 1] = getMedianValue(data, (length - 1) * 5, data.length - 1);
		}
		return res;
	}
	
	private int select(int[] data, int[] medianArr, int p, int r, int i) {
		int[] newmedianArr = getMedianArray(medianArr);
		if (newmedianArr.length > 1) {
			select(data, newmedianArr, p, r, i);
		}
		int x = newmedianArr[0];
		int q = partition(data, p, r, x);
		int k = q - p + 1;
		if (i == k) {
			return x;
		} else if (i < k) {
			newmedianArr = Arrays.copyOfRange(data, p, q);
			return select(data, newmedianArr, p, q - 1, i);
		} else {
			newmedianArr = Arrays.copyOfRange(data, q + 1, r + 1);
			return select(data, newmedianArr, q + 1, r, i - k);
		}
	}
	
	public int select() {
		return select(data, medianArr, 0, data.length - 1, rank);
	}

	public static void main(String[] args) {
		int[] data = {8, 7, 3, 4, 2, 1, 5, 18, 9, 11, 13, 6, -1};
		int rank = 2;
		LinearTimeSelect selection = new LinearTimeSelect(data, rank);
		int val = selection.select();
		System.out.println("rank = " + rank + " is " + val);
	}
}
