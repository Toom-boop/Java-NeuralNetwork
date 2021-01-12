package neuralnetwork;

public class Point {
	public int x;
	public int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o.getClass().equals(this.getClass())) {
			Point p = (Point) o;
			
			if(this.x == p.x) {
				if(this.y == p.y) {
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return x + ", " + y;
	}
}