package com.algorithm.hash;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Practice 11.1-4 For a large array, implement a direct address table with
 * stack
 * 
 * @author lgrcyanny
 * 
 */
public class DirectAddressTable {
	public static class Element {
		public int key = -1;
		public int value = -1;

		public Element(int key, int data) {
			this.key = key;
			this.value = data;
		}

		public Element() {

		}

	}

	protected static int[] hash = new int[10000];
	protected ArrayList<Element> stack = new ArrayList<Element>();

	public DirectAddressTable() {
		stack.add(new Element());
	}
	
	private int search(Element item) {
		int res = -1;
		int hashkey = hash[item.key];
		if (hashkey > 0 && hashkey < stack.size() && stack.get(hashkey).value == item.value) {
			res = 1;
		}
		return res;
	}

	public Element insert(Element item) throws Exception {
		if (search(item) > 0) {
			throw new Exception("Sorry, the element already exists.");
		} else {
			stack.add(item);
			hash[item.key] = stack.size() - 1;
		}		
		return item;
	}
	
	public Element directSearch(int key) {
		int position = hash[key];
		if (position > 0 && position < stack.size()) {
			return stack.get(position);
		} else {
			return null;
		}
	}
	
	public Element delete(Element item) throws Exception {
		if (search(item) > 0) {
			stack.remove(hash[item.key]);
			hash[item.key] = 0;			
		} else {
			throw new Exception("Element doesn't exist.");
		}
		return item;
	}
	
	public void print() {
		for (int i : hash) {
			if (i > 0) {
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Element e1 = new Element(1, 13);
		Element e2 = new Element(2, 15);
		Element e3 = new Element(3, 16);
		DirectAddressTable hashTable = new DirectAddressTable();
		hashTable.insert(e1);
		hashTable.insert(e2);
		hashTable.insert(e3);
		hashTable.print();
	}

}
