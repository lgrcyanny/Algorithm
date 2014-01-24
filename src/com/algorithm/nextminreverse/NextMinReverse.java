package com.algorithm.nextminreverse;

public class NextMinReverse {
	private int[] bigint;
	private int[] reverse;
	
	public NextMinReverse(int[] bigint) {
		this.bigint = bigint;
	}
	
	public int[] calculate() {
		if (bigint.length == 0) {
			return null;
		}
		if (isCarry()) {
			reverse = new int[bigint.length + 1];
			reverse[0] = 1;
			reverse[reverse.length - 1] = 1;
		} else {
			int length = bigint.length;
			reverse = new int[length];
			if (length == 1) {
				reverse[0] = bigint[0] + 1;
			} else {
				reverse = makeReverse(bigint);
				if (!isBiggerThanOriginal(bigint, reverse)) {
					bigint = incrementMiddle(bigint);
					reverse = makeReverse(bigint);
				}				
			}			
		}		
		return reverse;
	}
	
	private int[] makeReverse(int[] bigint) {
		int length = bigint.length;
		int[] res = new int[length];		
		int middle = getMiddleIndex(bigint);
		for (int i = 0; i <= middle; i++) {
			res[i] = bigint[i];
			res[bigint.length - i - 1] = bigint[i];
		}
		return res;
	}
	
	private int[] incrementMiddle(int[] bigint) {
		int middle = getMiddleIndex(bigint);
		bigint[middle]++;
		for (int i = middle; i > 0; i--) {
			if (bigint[i] == 10) {
				bigint[i] = 0;
				bigint[i - 1]++;
			}
		}
		return bigint;
	}
	
	private int getMiddleIndex(int[] bigint) {
		int middle = 0;
		int length = bigint.length;
		if (isOdd()) {
			middle = length / 2;
		} else {
			middle = length / 2 - 1;
		}
		return middle;
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
		if (arr == null) {
			System.out.print("Empty Value.");
		} else {
			for (int val : arr) {
				System.out.print(val);
			}
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
		
		nmr = new NextMinReverse(new int[]{4, 9, 4});
		printBigInt(nmr.calculate());
	}
}
