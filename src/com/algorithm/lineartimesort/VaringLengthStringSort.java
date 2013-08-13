package com.algorithm.lineartimesort;

import java.util.Arrays;

import com.algorithm.utils.Utils;

/**
 * Chapter 8 8-3(b) Given an array of string (in lowercase) in viriable length, sort the array within O(n)
 * 1. CountSort the first character
 * 2. For each fragement, CountSort second character
 * 3. Iterative step 2 for other position of character
 * @author lgrcyanny
 *
 */
public class VaringLengthStringSort {
	private String[] words;
	private int maxLength;
	public int[] count;
	public int countSize;
	
	public VaringLengthStringSort(String[] words, int maxLength) {
		this.words = words;
		this.maxLength = maxLength;
		this.countSize = 26;
	}
	
		
	public String[] getWords() {
		return words;
	}

	/**
	 * Count Sort array based on position pos
	 * @param original
	 * @param pos
	 */
	private String[] countSort(String[] original, int pos) {
		String[] res = new String[original.length];
		int[] tempCount = new int[countSize + 1];
		int i = 0;
		int index;
		int resIndex = 0;
		
		for (i = 0; i < original.length; i++) {
			if (pos >= original[i].length()) {
				// When there is no char on the pos, just copy to result and increment the resIndex
				res[resIndex] = original[i];
				resIndex++;
			} else {
				index = charCountIndex(original[i].charAt(pos));
				tempCount[index]++;
			}			
		}
		
		for (i = 1; i < tempCount.length; i++) {
			tempCount[i] = tempCount[i] + tempCount[i - 1];
		}
		
		for (i = original.length - 1; i >= 0; i--) {
			if (pos <= original[i].length() - 1) {
				index = charCountIndex(original[i].charAt(pos));
				// Remember maybe there is word before resIndex, so add resIndex
				res[tempCount[index] + resIndex - 1] = original[i];
				tempCount[index]--;
			}			
		}

		return res;
	}
	
	/**
	 * Generate the count of each char on pos, the function is for iterativeRadixSort, but it looks a 
	 * little silly, since has some common with countSort.
	 * @param original
	 * @param pos
	 * @return
	 */
	private int[] generateCount(String[] original, int pos) {
		// Let the tempCount.length = 27,
		// this will be convenient when generate startIndex and endIndex 
		// in function iterativeRadixSort
		int[] tempCount = new int[countSize + 1];												
		int i = 0;
		int index;
		
		for (i = 0; i < original.length; i++) {
			if (pos < original[i].length()) {
				index = charCountIndex(original[i].charAt(pos));
				tempCount[index]++;
			} 			
		}
		
		for (i = 1; i < tempCount.length; i++) {
			tempCount[i] = tempCount[i] + tempCount[i - 1];
		}
		return tempCount;
	}
	
	private String[] iterativeRadixSort(String[] original, int pos) {
		String[] res = original;
		if (pos <= maxLength - 1 && original.length > 1) {
			res = this.countSort(original, pos);
			int[] count = generateCount(original, pos);
			for (int i = 1; i < count.length; i++) {
				int start = count[i - 1];
				int end = count[i];
				// Filter the word that there is no char on the next pos
				for (int j = start; j < end; j++) {
					if (res[j].length() <= pos + 1) {
						start++;
					}
				}
				// Make sure there at least 2 elements in the fragment
				if (end - start > 1) {					
					String[] fragmentWords = Arrays.copyOfRange(res, start, end);
					fragmentWords = iterativeRadixSort(fragmentWords, pos + 1);
					copyWordsOfRange(res, fragmentWords, start, end);
				}
			}
		}
		return res;
	}
	
	public void sort() {
		this.words = this.iterativeRadixSort(this.words, 0);
	}
	
	private int charCountIndex(char achar) {
		return achar - 'a' + 1;
	}
	
	private void copyWordsOfRange(String[] des, String[] ori, int start, int end) {
		if ((end - start) == ori.length) {
			for (int i = 0; i < ori.length; i++) {
				des[start + i] = ori[i];
			}
		} else {
			throw new IndexOutOfBoundsException("start index and end index doesn't math ori.length");
		}
	}
	
	public static void main(String[] args) {
		int maxLength = 10;
		int arrSize = 200;
		//String[] words = {"pazozti", "b", "fvkjkaik", "ga", "x", "mbfg", "aps", "aseugrt", "w", "wrmy"};
		String[] words = Utils.generateRandomWordsArray(arrSize, maxLength);
		Utils.printStringArray(words);
		VaringLengthStringSort stringSort = new VaringLengthStringSort(words, maxLength);
		stringSort.sort();
		Utils.printStringArray(stringSort.getWords());		
	}

}
