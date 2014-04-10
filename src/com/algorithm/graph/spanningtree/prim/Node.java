package com.algorithm.graph.spanningtree.prim;

public class Node {
	public Node parent = null;
	public String label = null;
	public int index = 0;
	public int key = Integer.MAX_VALUE;

	public Node() {

	}

	public Node(String label) {
		this.label = label;
	}

	public Node(String label, int index) {
		this.label = label;
		this.index = index;
	}

	@Override
	public String toString() {
		return "Node [parent=" + parent + ", label=" + label + ", index="
				+ index + ", key=" + key + "]";
	}
		
	
}
