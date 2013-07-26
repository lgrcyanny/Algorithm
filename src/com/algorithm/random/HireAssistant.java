package com.algorithm.random;

public class HireAssistant {
	private int[] interviewees;
	private int[] priorities;
	private int sum;
	public HireAssistant(int sum) {
		this.sum = sum;
	}
	
	public void hireAssistant() {
		permuteInterviewees();
		int best = Integer.MIN_VALUE;
		for (int i = 0; i < sum; i++) {
			System.out.println("Interview No." + i + " quality is " + interviewees[i]);
			if (interviewees[i] > best) {
				best = interviewees[i];
				System.out.println("Hire candidate " + i + " whose quaility is " + interviewees[i]);
			}
		}
	}
	
	/**
	 * Permute interviewees, by random priorities
	 */
	private void permuteInterviewees() {
		interviewees = new int[sum];
		priorities = new int[sum];
		for (int i = 0; i < sum; i++) {
			interviewees[i] = i;
		}
		for (int i = 0; i < sum; i++) {
			priorities[i] = (int)(Math.random() * Math.pow(sum, 3.0) + 1);
		}
		System.out.println("Original interviewees");
		printArray(interviewees);
		System.out.println("Original priorities");
		printArray(priorities);
		mergeSortByPriorities(0, sum - 1);
		printArray(priorities);
		printArray(interviewees);
	}
	
	private void mergeSortByPriorities(int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSortByPriorities(p, q);
			mergeSortByPriorities(q + 1, r);
			merge(p, q, r);
		}
	}
	
	private void merge(int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] leftPrArr = new int[n1 + 1];
		int[] leftArr = new int[n1 + 1];
		int[] rightPrArr = new int[n2 + 1];
		int[] rightArr = new int[n2 + 1];
		int i, j, k;
		for (i = 0; i < n1; i++) {
			leftPrArr[i] = priorities[p + i];
			leftArr[i] = interviewees[p + i];
		}
		for (j = 0; j < n2; j++) {
			rightPrArr[j] = priorities[q + j + 1];
			rightArr[j] = interviewees[q + j + 1];
		}
		leftPrArr[n1] = Integer.MAX_VALUE;
		rightPrArr[n2] = Integer.MAX_VALUE;
		
		for (k = p, i = 0, j = 0; k <= r; k++) {
			if (leftPrArr[i] <= rightPrArr[j])  {
				interviewees[k] = leftArr[i];
				priorities[k] = leftPrArr[i];
				i++;
			} else {
				interviewees[k] = rightArr[j];
				priorities[k] = rightPrArr[j];
				j++;
			}
		}		
	}
	
	/**
	 * Generate uniform random permutation 均匀的随机排列
	 */
	public void randomInPlace() {
		interviewees = new int[sum];
		int i;
		for (i = 0; i < sum; i++) {
			interviewees[i] = i;
		}
		for (i = 0; i < sum; i++) {
			int j = (int)(Math.random() * (sum - i) + i);// random int i~ (n - 1)
			int temp = interviewees[i];
			interviewees[i] = interviewees[j];
			interviewees[j] = temp;
		}
		printArray(interviewees);		
	}	
	
	public void printArray(int[] arr) {
		for (int i : arr) {
			System.out.println(i);			
		}
		System.out.println("==========");
	}
	
	public static void main(String[] args) {
		HireAssistant hire = new HireAssistant(10);
		hire.randomInPlace();
	}
}
