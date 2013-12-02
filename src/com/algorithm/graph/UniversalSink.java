package com.algorithm.graph;

import java.util.ArrayList;

/**
 * Find possible UniversalSink by adjacent matrix within O(V)
 *  @author lgrcyanny
 *
 */
public class UniversalSink {
	private int vertexCount;
	private int edgeCount;
	private int[] graph;
	public int universalSink = -1;
	
	public UniversalSink(int v, int e, int[] graph) {
		this.vertexCount = v;
		this.edgeCount = e;
		this.graph = graph;
	}
	
	public int getValue (int i, int j) {
		return graph[i * vertexCount + j];
	}
	
	public boolean checkSink() {
		int i = 0;
		int j = 0;
		while (j < vertexCount) {
			if (getValue(i, j) == 0) {
				j++;
			} else if (i == j) {
				i++;
				j++;
			} else {
				i = j;
			}
		}
		if (i >= vertexCount) {
			return false;
		} else {
			// 所有纵向为1, 除了汇点
			for (int k = 0; k < vertexCount && k != i; k++) {
				int m = getValue(k, i);
				if (m != 1) {
					return false;
				}
			}
			
			// 汇点的所有横向为0
			for (int k = 0; k < vertexCount; k++) {
				if (getValue(i, k) != 0) {
					return false;
				}
			}
		}
		universalSink = i;
		return true;
	}
	
	public static void main(String[] args) {
		int[] graph = {
				0, 0, 1, 1,
				0, 0, 1, 1,
				1, 0, 1, 1,
				0, 0, 0, 0
		};
		UniversalSink universalSink = new UniversalSink(4, 4, graph);
		if (universalSink.checkSink()) {
			System.out.println("Universal Sink is :" + universalSink.universalSink);
		}
	}
	
}
