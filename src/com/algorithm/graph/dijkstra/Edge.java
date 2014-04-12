package com.algorithm.graph.dijkstra;

public class Edge {
	public Vertex vertex = null;
	public int weight = 0;

	public Edge() {

	}

	public Edge(Vertex vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

}
