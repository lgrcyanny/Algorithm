package com.algorithm.greedy;

import java.util.ArrayList;


public class ActivitySelectedDynamicProgram {
	// Record the activity number between c[i, j]
	public int[][] c;
	// Record the compatible activity between s[i, j]
	public int[][] s;
	// The number of activities
	int n;
	ArrayList<Activity> activities;
	
	public ActivitySelectedDynamicProgram(ArrayList<Activity> activities) {
		this.activities = activities;
		this.n = activities.size();
		c = new int[n][n];
		s = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					c[i][i] = 1;
				}
				s[i][j] = -1;
			}
		}
		c[0][0] = 0;
		c[n - 1][n - 1] = 0;
	}
	
	public void activitySelector() {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (i > j) {
					c[i][j] = 0;
				} else {
					for (int k = i + 1; k < j; k++) {
						Activity aci = activities.get(i);
						Activity acj = activities.get(j);
						Activity ack = activities.get(k);
						if (ack.s >= aci.f && ack.f <= acj.s) {
							int q = c[i][k] + c[k][j] + 1;
							if (q > c[i][j]) {
								c[i][j] = q;
								s[i][j] = k;
								System.out.println("s[" + i + "]" + "[" + j + "] = " + k);
							}
						}
					}
				}
			}
		}
		System.out.println(c[0][n - 1]);
	}
	
	public void printSelectedActivity(int[][] s, int i, int j) {
		if (s[i][j] >= 0) {
			int k = s[i][j];
			printSelectedActivity(s, i, k);
			printSelectedActivity(s, k, j);
			System.out.println("Activity: " + k);
		}
	}
	
	public void calculate() {
		this.activitySelector();
		this.printSelectedActivity(s, 0, n - 1);
	}
	
	public static void main(String[] args) {
		ArrayList<Activity> activities = new ArrayList<Activity>();
		activities.add(new Activity(-1, 0)); // Virtual Activity
		activities.add(new Activity(1, 4));
		activities.add(new Activity(3, 5));
		activities.add(new Activity(0, 6));
		activities.add(new Activity(5, 7));
		activities.add(new Activity(3, 8));
		activities.add(new Activity(5, 9));
		activities.add(new Activity(6, 10));
		activities.add(new Activity(8, 11));
		activities.add(new Activity(8, 12));
		activities.add(new Activity(2, 13));
		activities.add(new Activity(12, 14));
		activities.add(new Activity(Integer.MAX_VALUE, Integer.MAX_VALUE)); // Virtual Activity
		ActivitySelectedDynamicProgram activitySelector = new ActivitySelectedDynamicProgram(activities);
		activitySelector.calculate();;		
	}

}
