package com.algorithm.greedy;

import java.util.ArrayList;

public class ActivitySelectorGreedy {
	int n;
	ArrayList<Activity> activities;
	
	public ActivitySelectorGreedy(ArrayList<Activity> activities) {
		this.activities = activities;
	}
	
	public void recursiveActivitySelect(int i, int n) {
		int m = i + 1;
		while (m <= n && activities.get(m).s < activities.get(i).f) {
			m = m + 1;
		}
		if (m <= n) {
			recursiveActivitySelect(m, n);
			System.out.println("Activity:" + m);
		}
	}
	
	public void iterativeActivitySelect() {
		int i = 0;
		for (int m = 1; m < activities.size(); m++) {
			Activity aci = activities.get(i);
			Activity ack = activities.get(m);
			if (ack.s >= aci.f) {
				System.out.println("Activity:" + m);
				i = m;
			}
		}
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
		ActivitySelectorGreedy selector = new ActivitySelectorGreedy(activities);
		System.out.println("==========Recursive:============ ");
		selector.recursiveActivitySelect(0, activities.size() - 1);
		System.out.println("============Iterative:============== ");
		selector.iterativeActivitySelect();
	}
	

}
