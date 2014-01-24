package com.algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Generate Strong Connected Component(SCC) of a graph, which is represented by linked list
 * SCC is made by twice DFS
 * First DFS G
 * Second DFS GT
 * @author lgrcyanny
 *
 */
public class GraphSCC {
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
	private ArrayList<ArrayList<Integer>> graphT;
	private Vertex[] vertices1;
	private Vertex[] vertices2;
	private ArrayList<Vertex> res1;
	private ArrayList<Vertex> res2;
	private int timer;
	
	public GraphSCC(ArrayList<ArrayList<Integer>> graph) {
		this.n = graph.size();
		this.graph = graph;
		this.graphT = new ArrayList<ArrayList<Integer>>();
		this.res1 = new ArrayList<Vertex>();
		this.res2 = new ArrayList<Vertex>();
		this.vertices1 = new Vertex[n];
		this.vertices2 = new Vertex[n];
	}
	
	public void generateSCC() {
		this.DFS4Graph();
		this.sortVerticesByTimestamp2(res1);
		System.out.println("First DFS for graph :");
		this.printRes(res1);
		this.reverseGraph();
		this.DFS4GraphT();
		System.out.println("Secondly DFS for graphT :");
		this.printRes(res2);
	}
	
	
	/**
	 * Reverse graph to generate graphT
	 */
	private void reverseGraph() {
		for (int i = 0; i < n; i++) {
			graphT.add(new ArrayList<Integer>());
		}		
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> list = graph.get(i);
			for (int j = 0; j < list.size(); j++) {
				int vertex = list.get(j);
				graphT.get(vertex).add(i);
			}
		}
	}

	private void sortVerticesByTimestamp2(ArrayList<Vertex> vertices) {
		Collections.sort(vertices, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				if (o1.timestamp2 < o2.timestamp2) {
					return 1;
				} else if (o1.timestamp2 > o2.timestamp2) {
					return -1;
				}
				return 0;
			}
		});
	}
	
	private void DFS4Graph() {
		timer = 0;
		int i = 0;
		for (i = 0; i < n; i++) {
			vertices1[i] = new Vertex(i);
		}
		for (i = 0; i < n; i++) {
			Vertex u = vertices1[i];
			if (u.color == Color.WHITE) {
				visitDFS4Graph(u);
			}
		}
	}
	
	private void visitDFS4Graph(Vertex u) {
		res1.add(u);
		u.color = Color.GREY;
		timer++;
		u.timestamp1 = timer;
		ArrayList<Integer> adjList = graph.get(u.vertex);
		for (int i = 0; i < adjList.size(); i++) {
			Vertex v = vertices1[adjList.get(i)];
			if (v.color == Color.WHITE) {
				v.parent = u;
				visitDFS4Graph(v);
			} 
		}
		u.color = Color.BLACK;
		timer++;
		u.timestamp2 = timer;
	}
	
	private void DFS4GraphT() {
		timer = 0;
		int i = 0;
		for (i = 0; i < n; i++) {
			vertices2[i] = new Vertex(i);
		}
		for (i = 0; i < n; i++) {
			// Visit DFS in order of decreasing timestamp2 in res1
			int index = res1.get(i).vertex;
			Vertex u = vertices2[index];
			if (u.color == Color.WHITE) {
				visitDFS4GraphT(u);
			}
		}
	}
	
	private void visitDFS4GraphT(Vertex u) {
		res2.add(u);
		u.color = Color.GREY;
		timer++;
		u.timestamp1 = timer;
		ArrayList<Integer> adjList = graphT.get(u.vertex);
		for (int i = 0; i < adjList.size(); i++) {
			Vertex v = vertices2[adjList.get(i)];
			if (v.color == Color.WHITE) {
				v.parent = u;
				visitDFS4GraphT(v);
			} 
		}
		u.color = Color.BLACK;
		timer++;
		u.timestamp2 = timer;
	}
	
	public void printRes(ArrayList<Vertex> res) {
		for (int i = 0; i < n; i++) {
			Vertex v = res.get(i);
			System.out.println(v.vertex + " : " + v.timestamp1 + " / " + v.timestamp2);
		}
	}
	
	/**
	 * The directed graph:
	 * 0->1->4
	 * 1->2->3
	 * 2->1
	 * 3->3
	 * 4->0->3
	 * 5->2->6
	 * 6->2->7
	 * 7->5
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); list.add(4); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(1);  
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(0); list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); list.add(6); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); list.add(7); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(5); 
		graph.add(list);
		
		GraphSCC graphSCC = new GraphSCC(graph);
		graphSCC.generateSCC();
		
	}

}
