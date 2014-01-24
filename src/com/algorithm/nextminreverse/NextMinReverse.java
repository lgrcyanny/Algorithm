package com.algorithm.nextminreverse;

public class NextMinReverse {
	private final int[] bigint;
	private int[] reverse;
	
	public NextMinReverse(int[] bigint) {
		this.bigint = bigint;
	}
	
	public int[] calculate() {
		if (isCarry()) {
			reverse = new int[bigint.length + 1];
			reverse[0] = 1;
			reverse[reverse.length - 1] = 1;
		} else if (isOdd()){
			reverse = calculateOddReverse();
		} else {
			reverse = calculateEvenReverse();
		}
		
		return reverse;
	}
	
	private int[] calculateOddReverse() {
		int[] res = new int[bigint.length];
		int middle = bigint.length / 2;
		for (int i = 0; i <= middle; i++) {
			res[i] = bigint[i];
			res[bigint.length - i - 1] = bigint[i];
		}
		if (!isBiggerThanOriginal(bigint, res)) {
			res[middle]++;
		}		
		return res;
	}
	
	private int[] calculateEvenReverse() {
		int[] res = new int[bigint.length];
		int middle = bigint.length / 2 - 1;
		for (int i = 0; i <= middle; i++) {
			res[i] = bigint[i];
			res[bigint.length - i - 1] = bigint[i];
		}
		while(!isBiggerThanOriginal(bigint, res) && middle >= 0) {
			res[middle]++;
			res[middle + 1]++;
			middle--;
		}
		return res;
	}
	
	private boolean isOdd() {
		return bigint.length % 2 > 0 ? true : false;
	}
	
	private boolean isBiggerThanOriginal (int[] original, int[] reverse) {
		boolean res = false;
		for (int i = 0; i < reverse.length; i++) {
			if (reverse[i] > original[i] && (i == 0 || i > 0 && (reverse[i - 1] >= original[i - 1]))) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	private boolean isCarry () {
		for (int i = 0; i < bigint.length; i++) {
			if (bigint[i] != 9) {
				return false;
			}
		}
		return true;
	}
	
	public static void printBigInt(int[] arr) {
		for (int val : arr) {
			System.out.print(val);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		NextMinReverse nmr = new NextMinReverse(new int[]{9, 9, 9, 9});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{1, 2, 3, 4, 5});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{3, 4, 7, 9, 1});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{1, 2, 3, 4, 5});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{1, 2, 3, 4, 5, 6});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{8, 8, 8, 8});
		printBigInt(nmr.calculate());	
		
		nmr = new NextMinReverse(new int[]{2, 4, 8, 0});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{1, 0, 0, 0});
		printBigInt(nmr.calculate());
		
		nmr = new NextMinReverse(new int[]{9, 1, 0, 1, 1});
		printBigInt(nmr.calculate());
	}
}
