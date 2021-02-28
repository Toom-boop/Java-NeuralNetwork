package com.neuralnetwork.advanced;

import java.util.Iterator;

public class Size implements Iterable<Point> {
	
	private Point p;
	
	public Size(int... pos) {
		this(new Point(pos));
	}
	
	public Size(Point edge) {
		p = edge;
	}
	
	public int getDimensions() {
		return p.getDimensions();
	}
	
	public int get(int i) {
		return p.get(i);
	}
	
	public void set(int dimension, int i) {
		p.set(dimension, i);
	}
	
	public boolean contains(Point p) {
		if(p.getDimensions() != getDimensions()) return false;
		for(int i = 0; i<p.getDimensions(); i++) {
			if(p.get(i) >= get(i)) return false;
		}
		return true;
	}
	
	public boolean isEnd(Point p) {
		if(p.getDimensions() != getDimensions()) return false;
		for(int i = 0; i<p.getDimensions(); i++) {
			if(p.get(i) != get(i)-1) return false;
		}
		return true;
	}
	
	@Override
	public Iterator<Point> iterator() {
		Size size = this;
		Iterator<Point> i = new Iterator<Point>() {
			private Pointer pointer = new Pointer(size);
			
			@Override
			public boolean hasNext() {
				return pointer.hasNext();
			}
	
			@Override
			public Point next() {
				return pointer.next();
			}
		};
		return i;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o.getClass().equals(this.getClass())) {
			Size s = (Size) o;
			if(s.getDimensions() != this.getDimensions()) return false;
			for(int i = 0; i<getDimensions(); i++) {
				if(s.get(i) != this.get(i)) return false;
			}
			return true;
			
		}else {
			try {
				if((int) o != 0) return false;
				if(getDimensions() == 0) {
					return true;
				}else {
					for(int i = 0; i<getDimensions(); i++) {
						if(get(i) != 0) return false;
					}
				}
				return true;
			}catch(ClassCastException e) {
				return false;
			}
		}
	}
	
	public int[] toArray() {
		return p.toArray();
	}
	
	public Point toPoint() {
		Point result = new Point();
		for(int i = 0; i<getDimensions(); i++) {
			result.set(i, get(i) - 1);
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("(");
		boolean start = true;
		for(int i : p.toArray()) {
			if(start) {
				start = false;
			}else{
				builder.append(", ");
			}
			builder.append(i);
		}
		builder.append(")");
		return builder.toString();
	}
}