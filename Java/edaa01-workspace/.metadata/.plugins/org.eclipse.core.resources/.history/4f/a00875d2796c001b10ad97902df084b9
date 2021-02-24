package mountain;

public class Side {
	private Point point1, point2;
	
	public Side(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}
	
	public Point getStart() {
		return point1;
	}
	
	public Point getEnd() {
		return point2;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Side) {
			Side side = (Side) obj;
			return side.point1.equals(point1) && side.point2.equals(point2) ||
					   side.point1.equals(point2) && side.point2.equals(point1);
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return point1.hashCode() + point2.hashCode();
	}
}
