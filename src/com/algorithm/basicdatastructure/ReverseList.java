package com.algorithm.basicdatastructure;

public class ReverseList {
	public Node head = null;
	
	public ReverseList () {
		insert(1);
		insert(2);
		insert(3);
		insert(4);
		insert(5);
		insert(6);
		insert(7);
		printList(head);
	}
	
	public void insert(int value) {
		if (head == null) {
			head = new Node(value);
		} else {
			Node n = new Node(value);
			n.next = head;
			head = n;
		}
	}
	
	public void printList(Node head) {
		Node p = head;
		if (p != null) {
			System.out.println(p.getValue());
			while (p.next != null) {
				p = p.next;
				System.out.println(p.getValue());
			}
		} else {
			System.out.println("Empty List!");
		}
		System.out.println("======");
	}
	
	public Node reverse (Node head) {
		Node p = head;
		if (p == null) {
			return null;
		} else if (p.next == null) {
			return p;
		} else if (p.next.next == null) {
			p = p.next;
			p.next = head;
			head.next = null;
			return p;
		}
		Node q = head.next;
		Node r = head.next.next;
		if (p == head) {
			p.next = null;
		}
		while (r.next != null) {
			q.next = p;
			p = q;
			q = r;
			r = r.next;
		}
		r.next = q;
		q.next = p;
		return r;
	}
	
	public Node reverse2(Node head) {
		Node p = null;
		Node q = head;
		Node r = null;
		while (true) {
			r = q.next;
			q.next = p;
			if (r == null) {
				break;
			}
			p = q;
			q = r;
		}
		return q;
	}
	
	
	public class Node{
		private int value;
		private Node next;
		public Node(int value) {
			this.value = value;
			this.next = null;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	public static void main(String[] args) {
		ReverseList list = new ReverseList();
		Node head = list.reverse2(list.head);
		list.printList(head);
	}

}
