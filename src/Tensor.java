package te.neuralnetwork;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tensor {
	
private Map<Point, Double> tensor;
	
	private Point size;
	
	public Tensor() {
		this(0, 0);
	}
	
	public Tensor(Point p) {
		this(p.x, p.y);
	}
	
	public Tensor(int x, int y) {
		tensor = new HashMap<Point, Double>();
		size = new Point(x, y);
	}
	
	public double get(Point p) {
		this.fixSize(p);
		for(Entry<Point, Double> e : tensor.entrySet()) {
			if(e.getKey().equals(p)) {
				return e.getValue();
			}
		}
		return 0;
	}
	
	public double get(int x, int y) {
		return this.get(new Point(x, y));
	}
	
	public double set(Point p, double e) {
		this.fixSize(p);
		for(Entry<Point, Double> entry : tensor.entrySet()) {
			if(entry.getKey().equals(p)) {
				tensor.put(entry.getKey(), e);
				return e;
			}
		}
		tensor.put(p, e);
		return e;
	}
	
	public double set(int x, int y, double e) {
		return this.set(new Point(x, y), e);
	}
	
	public Tensor add(Tensor b) {
		Point size = new Point(this.size.x, this.size.y);
		this.fixSize(b.size);
		
		
		Tensor result = new Tensor();
		for(int i = 0; i<size.x; i++) {
			for(int l = 0; l<size.y; l++) {
				result.set(i, l, this.get(i, l) + b.get(i, l));
			}
		}
		return result;
	}
	
	public Tensor subtract(Tensor b) {
		Point size = new Point(this.size.x, this.size.y);
		this.fixSize(b.size);
		
		
		Tensor result = new Tensor();
		for(int i = 0; i<size.x; i++) {
			for(int l = 0; l<size.y; l++) {
				result.set(i, l, this.get(i, l) - b.get(i, l));
			}
		}
		return result;
	}
	
	public Tensor multiply(Tensor b) {
		Point size = new Point(this.size.x, this.size.y);
		this.fixSize(b.size);
		
		
		Tensor result = new Tensor();
		for(int i = 0; i<size.x; i++) {
			for(int l = 0; l<size.y; l++) {
				result.set(i, l, this.get(i, l) * b.get(i, l));
			}
		}
		return result;
	}
	
	public Tensor divide(Tensor b) {
		Point size = new Point(this.size.x, this.size.y);
		this.fixSize(b.size);
		
		
		Tensor result = new Tensor();
		for(int i = 0; i<size.x; i++) {
			for(int l = 0; l<size.y; l++) {
				result.set(i, l, this.get(i, l) / b.get(i, l));
			}
		}
		return result;
	}
	
	public Tensor transpose() {
		Tensor result = new Tensor();
		
		for(int i = 0; i<=size.x; i++) {
			for(int l = 0; l<=size.y; l++) {
				result.set(l, i, this.get(i, l));
			}
		}
		return result;
	}
	
	public Point size() {
		return size;
	}
	
	private void fixSize(Point p) {
		if(size.x < p.x+1) {
			size.x = p.x+1;
		}
		if(size.y < p.y+1) {
			size.y = p.y+1;
		}
	}
	
	public double toDouble() {
		if(size.x == 1 && size.y == 1) {
			return this.get(0, 0);
		}else {
			throw new IllegalArgumentException("Size is not 1, 1 !");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{");
		for(int i = 0; i<size.x; i++) {
			if(i != 0) {
				builder.append(", ");
			}
			builder.append("{");
			for(int l = 0; l<size.y; l++) {
				if(l != 0) {
					builder.append(", ");
				}
				builder.append(this.get(i, l));
			}
			builder.append("}");
		}
		builder.append("}");
		return builder.toString();
	}
}