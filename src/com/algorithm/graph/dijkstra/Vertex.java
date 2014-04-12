package com.algorithm.graph.dijkstra;

public class Vertex implements Comparable<Vertex>{
	public String id = null;
	public Vertex parent = null;
	public Edge[] adjacencyList = null;
	public int distance = Integer.MAX_VALUE; // The min distance from source to the current vertex
	
	public Vertex() {
		
	}
	
	public Vertex(String id) {
		this.id = id;
	}

	/**
	 * Override this method for min priority queue
	 */
	@Override
	public int compareTo(Vertex o) {
		return this.distance < o.distance ? -1 : (this.distance == o.distance ? 0 : 1);
	}

	@Override
	public String toString() {
		return "Vertex [id=" + id + ", distance=" + distance + "]";
	}
	
	

}
