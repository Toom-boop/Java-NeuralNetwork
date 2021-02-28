package com.neuralnetwork.advanced;

public class Test {
	
	public static void main(String[] args) {
		
		Tensor t = new Tensor();
		t.set(new Point(0, 0), 1);
		t.set(new Point(0, 1), 2);
		t.set(new Point(0, 2), 3);
		t.set(new Point(1, 0), 4);
		t.set(new Point(1, 1), 5);
		t.set(new Point(1, 2), 6);
		
		/*
		 * 1 2 3
		 * 4 5 6
		 */
		
		Tensor t2 = new Tensor();
		t2.set(new Point(0, 0), 1);
		t2.set(new Point(0, 1), 1);
		t2.set(new Point(1, 0), 1);
		t2.set(new Point(1, 1), 1);
		t2.set(new Point(2, 0), 1);
		t2.set(new Point(2, 1), 1);
		
		/*
		 * 1 1
		 * 1 1
		 * 1 1
		 */
		
		/*
		 * 2 3 3
		 * 5 6 6
		 * 1 1 0
		 */
		
		/*
		 * 2 3 3
		 * 5 6 6
		 * 0 0 2
		 */
		
		System.out.println(t.add(t2));
	}
}