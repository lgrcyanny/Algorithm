package com.algorithm.graph;

import java.util.ArrayList;

/**
 * Breadth-first search a Graph
 * The Graph is represented by adjacent list
 * @author lgrcyanny
 *
 */
public class GraphBFS {
	private int n = 0;
	private ArrayList<ArrayList<Integer>> graph;
	private enum Color {
		WHITE, GREY, BLACK
	}
	private Vertex[] vertexes;
	private ArrayList<Vertex> res;
	private ArrayList<Vertex> queue;

	public GraphBFS(int n, ArrayList<ArrayList<Integer>> graph) {
		this.n = n;
		this.graph = graph;
		this.res = new ArrayList<Vertex>();
		this.queue = new ArrayList<Vertex>();
	}
	
	/**
	 * Breadth-first search root 
	 * @param root
	 */
	public void BFS(int root) {
		vertexes = new Vertex[n];
		// Initialize each vertex,
		// Each vertex has three segment: parent, d, color
		for (int i = 0; i < n; i++) {
			vertexes[i] = new Vertex(i);
		}
		
		Vertex rootv = vertexes[root];
		rootv.d = 0;
		rootv.color = Color.GREY;
		queue.add(rootv);
		
		while (!queue.isEmpty()) {
			Vertex u = queue.remove(0);
			ArrayList<Integer> list = graph.get(u.vertex);
			for (int i = 0; i < list.size(); i++) {
				Vertex temp = vertexes[list.get(i)];
				// Make sure each vertex enqueue only once
				if (temp.color == Color.WHITE) {
					temp.parent = u;
					temp.d = u.d + 1;
					temp.color = Color.GREY;
					queue.add(temp);
				}				
			}
			u.color = Color.BLACK;
			res.add(u);
		}
	}
	
	public void printRes() {
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).vertex + ":" + res.get(i).d);
		}
	}
	
	public void printShortestPath(int root, int v) {
		if (v == root) {
			System.out.println(root);
			return;
		} else if (vertexes[v].parent == null){
			System.out.println("No path form " + root + " to " + v);
		} else {
			System.out.println(v);
			printShortestPath(root, vertexes[v].parent.vertex);
		}		
	}
	
	class Vertex {
		public int vertex;
		public Vertex parent;
		// The distance from root to the vertex
		public int d;
		public Color color;
		public Vertex(int v) {
			this.vertex = v;
			parent = null;
			d = Integer.MAX_VALUE;
			color = Color.WHITE;
		}
	}
	
	/**
	 * The example adjacent list is 
	 * 0->1->4
	 * 1->0->4->2->3
	 * 2->1->3
	 * 3->1->4->2
	 * 4->0->3->1
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); list.add(4);
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(0); list.add(4); list.add(2); list.add(3);
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(1); list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(1); list.add(4); list.add(2); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(0); list.add(3); list.add(1); 
		graph.add(list);
		
		GraphBFS graphBFS = new GraphBFS(5, graph);
		graphBFS.BFS(0);
		System.out.println("BFS:");
		graphBFS.printRes();
		
		System.out.println("The shortest form 0 to 3 is");
		graphBFS.printShortestPath(0, 3);
	}

}
