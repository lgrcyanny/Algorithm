package com.algorithm.graph;

import java.util.ArrayList;
import java.util.Iterator;

import com.algorithm.graph.GraphDFS.Vertex;

public class TopologyOrder {
	private int n;
	private ArrayList<ArrayList<Integer>> graph;
	private GraphDFS graphDFS;
	private ArrayList<Vertex> topologyOrder;
	
	public TopologyOrder(ArrayList<ArrayList<Integer>> graph) {
		this.n = graph.size();
		this.graph = graph;
		graphDFS = new GraphDFS(graph);
	}
	
	private int partition(int p, int r) {
		int x = topologyOrder.get(r).timestamp2;
		int i = p - 1;
		int j = p;
		while (j < r) {
			if (topologyOrder.get(j).timestamp2 <= x) {
				i++;
				exchange(i, j);
			}
			j++;
		}
		exchange(i + 1, r);
		return i + 1;
	}
	
	private void quickSort(int p, int r) {
		if (p < r) {
			int q = partition(p, r);
			quickSort(p, q - 1);
			quickSort(q + 1, r);
		}
	}
	
	private void exchange(int i, int j) {
		Vertex tmp = topologyOrder.get(i);
		topologyOrder.set(i, topologyOrder.get(j));
		topologyOrder.set(j, tmp);
	}
	
	public void generateTopologyOrder() {
		graphDFS.DFS();
		topologyOrder = graphDFS.getRes();
		quickSort(0, n - 1);
		for (int i = n - 1; i >= 0; i--)
		{
			Vertex v = topologyOrder.get(i);
			System.out.println(v.vertex + " : " + v.timestamp1 + " / " + v.timestamp2);
		}
	}
	
	/**
	 * 0: 1, 3
	 * 1: 2
	 * 2:
	 * 3: 2
	 * 4:
	 * 5:
	 * 6: 5, 3
	 * 7: 6, 5
	 * 8: 5
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); list.add(3); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); 
		graph.add(list);
		
		list = new ArrayList<Integer>(); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(2); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		graph.add(list);
		
		list = new ArrayList<Integer>();
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(3); list.add(5); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(5); list.add(6); 
		graph.add(list);
		
		list = new ArrayList<Integer>();
		list.add(5); 
		graph.add(list);
		
		TopologyOrder topology = new TopologyOrder(graph);
		topology.generateTopologyOrder();
	}

}
