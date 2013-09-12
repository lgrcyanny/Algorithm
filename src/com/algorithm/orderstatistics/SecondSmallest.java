package com.algorithm.orderstatistics;

import com.algorithm.utils.Utils;

/**
 * This Algorithm is to find the 2th minimum element in an array
 * @author lgrcyanny
 *
 */
public class SecondSmallest {
	int[] data = {};
	
	public SecondSmallest (int[] data) {
		this.data = data;
	}
	
	public int selectSecondSmallest() {
		int[] res = new int[2];
		int n = data.length;
		int i = 2;
		if (n % 2 == 0) {
			i = 2;
			if (data[0] < data[1]) {
				res[0] = data[0];
				res[1] = data[1];
			} else {
				res[0] = data[1];
				res[1] = data[0];
			}
		} else {
			i = 1;
			res[0] = data[0];
			res[1] = data[1];
		}
		for ( ;i < n; i += 2) {
			int large = getLarge(data[i], data[i + 1]);
			int small = getSmall(data[i], data[i + 1]);
			if (small < res[0]) {
				res[1] = res[0]; // Maybe res[0] is the second minimum, don't forget the tiny step
				res[0] = small;
			} else if (small < res[1]) {
				res[1] = small;
				continue;
			}
			if (large < res[1] || res[0] == res[1]) {
				res[1] = large;
			}
		}
		System.out.println("The smallest is " + res[0]);
		System.out.println("The second minimun is " + res[1]);
		return res[1];
	}
	
	private int getLarge(int a, int b) {
		return (a >= b) ? a : b;
	}
	
	private int getSmall(int a, int b) {
		return (a < b) ? a : b;
	}

	public static void main(String[] args) {
		int[] data = Utils.generateRandomTestData(100, 1, 100);
		SecondSmallest selection = new SecondSmallest(data);
		Utils.printArray(data);
		selection.selectSecondSmallest();
	}
}
