package com.algorithm.quicksort;

public class FuzzySort {
	private Interval[] data = null;
	
	public FuzzySort(Interval[] data) {
		if (data != null) {
			this.data = data;
		}
	}
	
	private int[] partition(int p, int r) {
		Interval pivot = data[r];
		int i = p;
		int j = p;
		int k = r;
		while (j <= k) {
			if (data[j].compareTo(pivot) > 0) {
				// When current item is totally bigger than pivot, 
				// exchange them, and decrease the upper bound k by 1
				exchange(j, k);
				k--;
			} else if (data[j].compareTo(pivot) < 0) {
				// When current item is totally smaller than pivot, 
				// exchange i, j
				exchange(i, j);
				i++;
				j++;
			} else {
				// When overlap, replace the pivot by the overlap Interval
				double begin = data[j].begin > pivot.begin ? data[j].begin : pivot.begin;
				double end = data[j].end < pivot.end ? data[j].end : pivot.end;
				pivot = new Interval(begin, end);
				j++;
			}
		}
		return new int[] {i, k};
	}
	
	public void quicksort(int p, int r) {
		if (p < r) {
			int[] q = partition(p, r);
			quicksort(p, q[0] - 1);
			quicksort(q[1] + 1, r);
		}
	}
	
	public void prdoubleData() {
		for (Interval obj : data) {
			System.out.println("[" + obj.getBegin() + " , " + obj.end + "]");
		}
		System.out.println("=========");
	}
	
	private void exchange(int i, int j) {
		Interval temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	
	public static class Interval implements Comparable<Interval >{
		private double begin;
		private double end;
		public Interval(double begin, double end) {
			this.begin = begin;
			this.end = end;
		}
		public double getBegin() {
			return begin;
		}
		public void setBegin(double begin) {
			this.begin = begin;
		}
		public double getEnd() {
			return end;
		}
		public void setEnd(double end) {
			this.end = end;
		}
		@Override
		public int compareTo(Interval another) {
			if (this.begin >= another.end) {
				return 1;
			} else if (this.end <= another.begin) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public static void main(String[] args) {
		Interval[] data = new Interval[6];
		data[0] = new Interval(1, 2);
		data[1] = new Interval(0.5, 1);
		data[2] = new Interval(3, 4);
		data[3] = new Interval(7.5, 8);
		data[4] = new Interval(0.5, 8);
		data[5] = new Interval(4, 5);
//		data[6] = new Interval(4, 5);
		FuzzySort mySort = new FuzzySort(data);
		mySort.prdoubleData();	
		mySort.quicksort(0, data.length - 1);
		mySort.prdoubleData();	
	}

}
