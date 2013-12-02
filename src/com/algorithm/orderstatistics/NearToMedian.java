package com.algorithm.orderstatistics;

import com.algorithm.utils.Utils;

/**
 * 在给定一个有n个不同数字的集合S以及一个正整数k<=n，确定出S中最接近其中位数的K个数
 * 1. 求A数组的中位数m
 * 2. 新建一个数组B，保存A中每个数与m的差的绝对值
 * 3. 在B中求出第k小的数t
 * 4. 遍历A，输出A中与M的差的绝对值小于t的数
 * @author lgrcyanny
 *
 */
public class NearToMedian {
	private RandomizedSelect selector;
	private int[] data;
	private int[] B;
	private int k;
	private int[] res;
	
	public NearToMedian(int[] data, int k) {
		selector = new RandomizedSelect(data, data.length / 2);
		this.data = data;
		this.k = k;
		this.B = new int[data.length];
		this.res = new int[k];
	}
	
	public int[] getRes() {
		return res;
	}
	
	public void process() {
		int median = selector.select();
		int i, j;
		for (i = 0; i < data.length; i++) {
			B[i] = Math.abs(data[i] - median);
		}
		selector.setData(B);
		selector.setRank(k);
		int kth = selector.select();
		for (i = 0, j = 0; i < data.length; i++) {
			if (j < res.length && Math.abs(data[i] - median) <= kth) {
				res[j] = data[i];
				j++;
			}
		}
	}

	public static void main(String[] args) {
		int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int k = 4;
		NearToMedian processor = new NearToMedian(data, k);
		processor.process();
		Utils.printArray(processor.getRes());
	}

}
