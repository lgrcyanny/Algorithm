package com.algorithm.graph.spanningtree.prim;

import java.util.ArrayList;

public class PrimMinSpanningTree {

	public ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>(); // Adjacency List
	public MinPriorityQueue queue = null;


	public PrimMinSpanningTree(ArrayList<ArrayList<Edge>> graph,
			MinPriorityQueue queue) {
		this.graph = graph;
		this.queue = queue;
	}

	public ArrayList<Node> primMST() {
		ArrayList<Node> res = new ArrayList<Node>();
		while (!queue.isEmpty()) {
			Node node = queue.extractMin();
			res.add(node);
			ArrayList<Edge> edges = graph.get(node.index);
			for (int i = 0; i < edges.size(); i++) {
				Edge edge = edges.get(i);
				int index = queue.indexOf(edge.node); // Check if the node exist in the queue
				if (index != -1 && edge.weight < edge.node.key) {
					queue.decreaseKey(index, edge.weight);
					edge.node.parent = node;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		Node n0 = new Node("a", 0);
		Node n1 = new Node("b", 1);
		Node n2 = new Node("c", 2);
		Node n3 = new Node("d", 3);
		Node n4 = new Node("e", 4);
		Node n5 = new Node("f", 5);
		Node n6 = new Node("g", 6);
		Node n7 = new Node("h", 7);
		Node n8 = new Node("i", 8);
		MinPriorityQueue queue = new MinPriorityQueue();
		n0.key = 0; // Set root key
		queue.insert(n0);
		queue.insert(n1);
		queue.insert(n2);
		queue.insert(n3);
		queue.insert(n4);
		queue.insert(n5);
		queue.insert(n6);
		queue.insert(n7);
		queue.insert(n8);
		
		ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
		ArrayList<Edge> list0 = new ArrayList<Edge>();
		list0.add(new Edge(n1, 4));
		list0.add(new Edge(n7, 8));
		
		ArrayList<Edge> list1 = new ArrayList<Edge>();
		list1.add(new Edge(n0, 4));
		list1.add(new Edge(n2, 8));
		list1.add(new Edge(n7, 11));
		
		ArrayList<Edge> list2 = new ArrayList<Edge>();
		list2.add(new Edge(n1, 8));
		list2.add(new Edge(n3, 7));
		list2.add(new Edge(n5, 4));
		list2.add(new Edge(n8, 2));
				
		ArrayList<Edge> list3 = new ArrayList<Edge>();
		list3.add(new Edge(n2, 7));
		list3.add(new Edge(n4, 9));
		list3.add(new Edge(n5, 14));
				
		ArrayList<Edge> list4 = new ArrayList<Edge>();
		list4.add(new Edge(n3, 9));
		list4.add(new Edge(n5, 10));
		
		ArrayList<Edge> list5 = new ArrayList<Edge>();
		list5.add(new Edge(n2, 4));
		list5.add(new Edge(n3, 14));
		list5.add(new Edge(n4, 10));
		list5.add(new Edge(n6, 2));
		
		ArrayList<Edge> list6 = new ArrayList<Edge>();
		list6.add(new Edge(n5, 2));
		list6.add(new Edge(n7, 1));
		list6.add(new Edge(n8, 6));
		
		ArrayList<Edge> list7 = new ArrayList<Edge>();
		list7.add(new Edge(n0, 8));
		list7.add(new Edge(n1, 11));
		list7.add(new Edge(n6, 1));
		list7.add(new Edge(n8, 7));
		
		ArrayList<Edge> list8 = new ArrayList<Edge>();
		list7.add(new Edge(n2, 2));
		list7.add(new Edge(n6, 6));
		list7.add(new Edge(n7, 7));
		
		graph.add(list0);
		graph.add(list1);
		graph.add(list2);
		graph.add(list3);
		graph.add(list4);
		graph.add(list5);
		graph.add(list6);
		graph.add(list7);
		graph.add(list8);
		
		PrimMinSpanningTree mst = new PrimMinSpanningTree(graph, queue);
		ArrayList<Node> res = mst.primMST();
		for (Node node : res) {
			System.out.println(node.toString());
		}
	}
	
	
}
