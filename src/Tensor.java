import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Tensor {
	
	private Map<Point, Double> tensor;
	
	private Size size;
	
	public Tensor() {
		this(new Size(0));
	}
	
	public Tensor(int... size) {
		this(new Size(size));
	}
	
	public Tensor(Size size) {
		tensor = new HashMap<Point, Double>();
		this.size = size;
	}
	
	public double get(int... p) {
		return get(new Point(p));
	}
	
	public double get(Point p) {
		this.fitSize(p);
		for(Entry<Point, Double> e : tensor.entrySet()) {
			if(e.getKey().equals(p)) {
				return e.getValue();
			}
		}
		return 0;
	}
	
	public double set(Point p, double e) {
		this.fitSize(p);
		for(Entry<Point, Double> entry : tensor.entrySet()) {
			if(entry.getKey().equals(p)) {
				tensor.put(entry.getKey(), e);
				return e;
			}
		}
		tensor.put(p, e);
		return e;
	}
	
	public Tensor add(Tensor b) {
		this.fitSize(b.size.toPoint());
		Size size = new Size(this.size.toArray());
		
		Tensor result = new Tensor();
		for(Point p : size) {
			result.set(new Point(p.toArray()), this.get(p) + b.get(p));
		}
		return result;
	}
	
	public Tensor subtract(Tensor b) {
		this.fitSize(b.size.toPoint());
		Size size = new Size(this.size.toArray());
		
		Tensor result = new Tensor();
		for(Point p : size) {
			result.set(new Point(p.toArray()), this.get(p) - b.get(p));
		}
		return result;
	}
	
	public Tensor multiply(Tensor b) {
		this.fitSize(b.size.toPoint());
		Size size = new Size(this.size.toArray());
		
		Tensor result = new Tensor();
		for(Point p : size) {
			result.set(new Point(p.toArray()), this.get(p) * b.get(p));
		}
		return result;
	}
	
	public Tensor divide(Tensor b) {
		this.fitSize(b.size.toPoint());
		Size size = new Size(this.size.toArray());
		
		Tensor result = new Tensor();
		for(Point p : size) {
			result.set(new Point(p.toArray()), this.get(p) / b.get(p));
		}
		return result;
	}
	
	public Tensor transpose() {
		Size size = new Size(this.size.toArray());
		
		Tensor result = new Tensor();
		for(Point p : size) {
			result.set(new Point(p.toArray()), this.get(p.transpose()));
		}
		return result;
	}
	
	public Size size() {
		return size;
	}
	
	private void fitSize(Point p) {
		Size result = p.toSize();
		int dimensions = result.getDimensions();
		
		if(size.getDimensions() > dimensions) dimensions = size.getDimensions();
		
		for(int i = 0; i<dimensions; i++) {
			if(size.get(i) > result.get(i)) result.set(i, size.get(i));
		}
		size = result;
	}
	
	public double toDouble() {
		if(size.getDimensions() == 2 && size.get(0) == 1 && size.get(0) == 1) {
			return this.get(0, 0);
		}else {
			throw new IllegalArgumentException("Size is not 1, 1 !");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{");
		boolean start = true;
		for(Point p : size) {
			if(start) {
				start = false;
			}else {
				builder.append(", ");
			}
			builder.append(get(p));
		}
		builder.append("}");
		return builder.toString();
	}
}
