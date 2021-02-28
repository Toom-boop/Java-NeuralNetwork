public class Point {
	
	private int[] pos;
	
	public Point(int... pos) {
		this.pos = pos.clone();
	}
	
	public void set(int dimension, int i) {
		if(dimension+1 > pos.length) {
			int[] pos = new int[dimension+1];
			for(int l = 0; l<this.pos.length; l++) {
				pos[l] = this.pos[l];
			}
			this.pos = pos;
		}
		pos[dimension] = i;
	}
	
	public int get(int dimension) {
		if(dimension+1 > pos.length) {
			int[] pos = new int[dimension+1];
			for(int l = 0; l<this.pos.length; l++) {
				pos[l] = this.pos[l];
			}
			this.pos = pos;
		}
		return pos[dimension];
	}
	
	public int getDimensions() {
		return pos.length;
	}
	
	public Point transpose() {
		int[] pos = this.pos;
		for(int i = this.pos.length-1; i>0; i--) {
			pos[i] = this.pos[i];
		}
		return new Point(pos);
	}
	
	public int[] toArray() {
		return pos;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o != null && o.getClass().equals(this.getClass())) {
			Point p = (Point) o;
			if(p.getDimensions() != this.getDimensions()) return false;
			for(int i = 0; i<pos.length; i++) {
				if(p.get(i) != this.get(i)) {
					//System.err.print(p + " != " + this + "i: " + i + "    ");
					return false;
				}
			}
			return true;
			
		}else {
			try {
				if((int) o != 0) return false;
				if(getDimensions() == 0) {
					return true;
				}else {
					for(int i = 0; i<pos.length; i++) {
						if(get(i) != 0) return false;
					}
				}
				return true;
			}catch(ClassCastException e) {
				return false;
			}
		}
	}
	
	public Size toSize() {
		Size result = new Size();
		for(int i = 0; i<getDimensions(); i++) {
			result.set(i, get(i) + 1);
		}
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("(");
		boolean start = true;
		for(int i : pos) {
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
