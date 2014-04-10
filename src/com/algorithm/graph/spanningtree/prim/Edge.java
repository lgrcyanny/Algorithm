package com.algorithm.graph.spanningtree.prim;

public class Edge {
	public Node node;
	public int weight = 0;

	public Edge() {

	}

	public Edge(Node node, int weight) {
		this.node = node;
		this.weight = weight;
	}

}
