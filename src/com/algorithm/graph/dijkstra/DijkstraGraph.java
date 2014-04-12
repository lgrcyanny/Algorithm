package com.algorithm.graph.dijkstra;

import java.util.PriorityQueue;
import java.util.Stack;

public class DijkstraGraph {
	public Vertex[] graph = null;

	public DijkstraGraph(Vertex[] graph) {
		this.graph = graph;
	}

	public void computShortestPath(Vertex source) {
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		source.distance = 0;
		queue.add(source);
		while (!queue.isEmpty()) {
			Vertex u = queue.remove();
			for (int i = 0; i < u.adjacencyList.length; i++) {
				Edge v = u.adjacencyList[i]; // The neighbor of vertex u
				if (v.vertex.distance > u.distance + v.weight) {
					v.vertex.distance = u.distance + v.weight;
					v.vertex.parent = u;
					queue.add(v.vertex);
				}
			}
		}
	}

	public void printShortestPath() {
		for (int i = 0; i < graph.length; i++) {
			Vertex target = graph[i];
			Stack<Vertex> path = new Stack<Vertex>();
			while (target != null) {
				path.push(target);
				target = target.parent;
			}
			printPath(path);
		}
	}

	private void printPath(Stack<Vertex> path) {
		System.out.println("=============The path is=========");
		while (!path.isEmpty()) {
			System.out.println(path.pop());
		}
	}

	public static void main(String[] args) {
		Vertex v0 = new Vertex("s");
		Vertex v1 = new Vertex("t");
		Vertex v2 = new Vertex("x");
		Vertex v3 = new Vertex("z");
		Vertex v4 = new Vertex("y");

		v0.adjacencyList = new Edge[] { new Edge(v1, 10), new Edge(v4, 5) };
		v1.adjacencyList = new Edge[] { new Edge(v2, 1), new Edge(v4, 2) };
		v2.adjacencyList = new Edge[] { new Edge(v3, 4) };
		v3.adjacencyList = new Edge[] { new Edge(v0, 7), new Edge(v2, 6) };
		v4.adjacencyList = new Edge[] { new Edge(v1, 3), new Edge(v2, 9),
				new Edge(v3, 2) };

		Vertex[] graph = { v0, v1, v2, v3 };

		DijkstraGraph dijkstraGraph = new DijkstraGraph(graph);
		dijkstraGraph.computShortestPath(v0);
		dijkstraGraph.printShortestPath(); // Each path from v0

	}

}
