package com.algorithm.orderstatistics;

/**
 * Randomized select ith smallest element in an array
 * @author lgrcyanny
 *
 */
public class RandomizedSelect {
	int[] data;
	int rank;
	public RandomizedSelect(int[] data, int rank) {
		if (rank < 1 || rank > data.length){
			throw new ArrayIndexOutOfBoundsException("rank out of bounds");
		}
		this.data = data;
		this.rank = rank;
	}
	
	
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	private int partition(int p, int r) {
		int i = p - 1;
		int j = p;
		int pivot = data[r];
		while(j <= r - 1) {
			if (data[j] <= pivot) {
				i++;
				swap(i, j);
			}
			j++;
		}
		swap(r, i + 1);
		return i + 1;
	}
	
	private int randomizedPartition(int p, int r) {
		int pivotIndex = (int)(Math.random() * (r - p) + p);
		swap(pivotIndex, r);
		return partition(p, r);
	}
	
	private void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	
	public int randomSelect(int p, int r, int i) {
		if (p == r) {
			return data[p];
		}
		int q = randomizedPartition(p, r);
		int k = q - p + 1;
		if (i == k) {
			return data[q];
		} else if (i < k) {
			return randomSelect(p, q - 1, i);
		} else {
			return randomSelect(q + 1, r, i - k);
		}
	}
	
	public int iterativeSelect() {
		int p = 0;
		int r = data.length - 1;
		int q = 0;
		int i = rank;
		int k = 0;
		while (p != r) {
			q = randomizedPartition(p, r);
			k = q - p + 1;
			if (i == k) {
				break;
			} else if (i < k) {
				r = q - 1;
			} else {
				p = q + 1;
				i = i - k;
			}
		}	
		if (p == r) {
			return data[p];
		} else {
			return data[q];
		}
	}
	
	public int select() {
		return randomSelect(0, data.length - 1, rank);
	}

	public static void main(String[] args) {
		int[] data = {1, 3, 6, 2, 5, 11, 8, 7, 0, -1};
		int rank = 3;
		RandomizedSelect selection = new RandomizedSelect(data, rank);
		System.out.println("The rank = " + rank + " of the array is " + selection.iterativeSelect());
	}

}
