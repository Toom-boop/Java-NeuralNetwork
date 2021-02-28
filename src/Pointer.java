public class Pointer {
	
	int i = 0;
	int dimension;
	Size size;
	
	Point p;
	
	Pointer child;
	
	public Pointer(Size size) {
		this(0, size);
	}
	
	private Pointer(int dimension, Size size) {
		this.size = size;
		this.dimension = dimension;
		this.p = new Point();
	}
	
	public boolean hasNext() {
		return i<size.get(dimension) && !size.isEnd(p);
	}
	
	public Point next() {
		if(hasNext()) {
			p.set(dimension, i);
			if(dimension<size.getDimensions()-1) {
				if(child == null) {
					child = new Pointer(dimension+1, size);
				}
				if(child.hasNext()) {
					p = child.next();
				}else {
					child.i = 0;
					p.set(dimension+1, 0);
					i++;
					return next();
				}
			}else {
				i++;
			}
		}
		return p;
	}
}
