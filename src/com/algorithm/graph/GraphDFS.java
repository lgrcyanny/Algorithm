package com.algorithm.graph;

import java.util.ArrayList;

/**
 * DFS search a Graph within O(E+V)
 * The graph is represented by adjacent list 
 * The vertex is represented by number 
 * @author lgrcyanny
 *
 */
public class GraphDFS {
	public static class Vertex {
		public int vertex;
		public Color color;
		public Vertex parent;
		// Time stamp for first visit
		public int timestamp1;
		// Time stamp when finish visit
		public int timestamp2;
		public Vertex(int v) {
			vertex = v;
			parent = null;
			timestamp1 = Integer.MAX_VALUE;
			timestamp2 = Integer.MAX_VALUE;
			color = Color.WHITE;
		}
	}
	
	public enum Color {
		WHITE, GREY, BLACK
	}
	
	// Number of vertex
	private int n;
	private ArrayList<ArrayList<Integer>> graph;
	private Vertex[] vertices;
	private ArrayList<Vertex> res;
	private int timer;
	private ArrayList<Vertex> stack = new ArrayList<Vertex>();
	
	public GraphDFS(ArrayList<ArrayList<Integer>> graph) {
		this.n = graph.size();
		this.graph = graph;
		this.vertices = new Vertex[n];
		this.res = new ArrayList<Vertex>();
		this.timer = 0;
	}
	
	public void DFS() {
		int i = 0;
		for (i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}
		for (i = 0; i < n; i++) {
			Vertex u = vertices[i];
			if (u.color == Color.WHITE) {
				//visitDFS(u);
				visitDFSIterative(u);
			}
		}
	}
	
	public ArrayList<Vertex> getRes() {
		return res;
	}

	private void visitDFS(Vertex u) {
		res.add(u);
		u.color = Color.GREY;
		timer++;
		u.timestamp1 = timer;
		ArrayList<Integer> adjList = graph.get(u.vertex);
		for (int i = 0; i < adjList.size(); i++) {
			Vertex v = vertices[adjList.get(i)];
			if (v.color == Color.WHITE) {
				v.parent = u;
				visitDFS(v);
			} 
		}
		u.color = Color.BLACK;
		timer++;
		u.timestamp2 = timer;
	}
	
	/**
	 * DFS with iterative method 
	 * Make use of stack
	 * @param u
	 */
	private void visitDFSIterative(Vertex u) {
		res.add(u);
		u.color = Color.GREY;
		timer++;
		u.timestamp1 = timer;
		stack.add(u);
		while(!stack.isEmpty()) {
			Vertex v = stack.remove(stack.size() - 1);
			ArrayList<Integer> adjList = graph.get(v.vertex);
			for (int i = 0; i < adjList.size(); i++) {
				Vertex temp = vertices[adjList.get(i)];
				if (temp.color == Color.WHITE) {
					temp.parent = v;
					temp.color = Color.GREY;
					timer++;
					temp.timestamp1 = timer;
					res.add(temp);
					stack.add(temp);
				}
			}
			v.color = Color.BLACK;
			timer++;
			v.timestamp2 = timer;
		}
		u.color = Color.BLACK;
		timer++;
		u.timestamp2 = timer;
	}
	
	public void printRes() {
		for (int i = 0; i < n; i++) {
			Vertex v = res.get(i);
			System.out.println(v.vertex + " : " + v.timestamp1 + " / " + v.timestamp2);
		}
	}
	
	/**
	 * The directed graph:
	 * 0->1->4
	 * 1->2->4
	 * 2->3
	 * 3->1
	 * 4->3
	 * 5->6->7
	 * 6->0->4
	 * 7->5->6
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); list.add(4); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); list.add(4); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(3);  
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(1); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(6); list.add(7); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(0); list.add(4); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(5); list.add(6); 
		graph.add(list);
		
		GraphDFS graphDFS = new GraphDFS(graph);
		graphDFS.DFS();
		graphDFS.printRes();
	}
}
