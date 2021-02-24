package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.*;

public class Mountain extends Fractal {
	private Point startPoint1;
	private Point startPoint2;
	private Point startPoint3;
	private double startDev;
	
	private Map<Side, Point> sideMiddleMap = new HashMap<>();
	
	public Mountain(double startDev, Point point1, Point point2, Point point3) {
		super();
		this.startDev = startDev;
		
		this.startPoint1 = point1;
		this.startPoint2 = point2;
		this.startPoint3 = point3;
	}
	
	public String getTitle() {
		return "Mountain Fractal";
	}

	public void draw(TurtleGraphics turtle) {
		turtle.moveTo(startPoint1.getX(), startPoint1.getY());
		
		drawTriangleWithDev(turtle, order, startDev, startPoint1, startPoint2, startPoint3);
	}
	
	private Point averagePoint(Point[] points) {
		int x = 0;
		int y = 0;
		
		for(Point p : points) {
			x += p.getX();
			y += p.getY();
		}
		
		if(points.length > 0) {
			x /= points.length;
			y /= points.length;
		}
		
		return new Point(x, y);
	}
	
	private Point center(Point pointA, Point pointB) {
		Point[] points = {pointA, pointB};
		
		return averagePoint(points);
	}
	
	private Point getRandomlyOffsetSideCenter(Side side, double dev) {
		if(sideMiddleMap.containsKey(side)) {
			return sideMiddleMap.remove(side);
		}
		
		int randomValue = (int) RandomUtilities.randFunc(dev);
		
		Point middle = center(side.getStart(), side.getEnd());
		Point offsetMiddle = new Point(middle.getX(), middle.getY() + randomValue);
		
		sideMiddleMap.put(side, offsetMiddle);
		
		return offsetMiddle;
	}
	
	private void drawTriangleWithDev(TurtleGraphics turtle, int order, double dev, Point point1, Point point2, Point point3) {
		if(order == 0) {
			turtle.moveTo(point1.getX(), point1.getY());
			turtle.penDown();
			
			turtle.forwardTo(point2.getX(), point2.getY());
			turtle.forwardTo(point3.getX(), point3.getY());
			turtle.forwardTo(point1.getX(), point1.getY());
			
			turtle.penUp();
		} else {
			Side side1 = new Side(point1, point2);
			Side side2 = new Side(point2, point3);
			Side side3 = new Side(point3, point1);
			
			Point offset1 = getRandomlyOffsetSideCenter(side1, dev);
			Point offset2 = getRandomlyOffsetSideCenter(side2, dev);
			Point offset3 = getRandomlyOffsetSideCenter(side3, dev);
			
			double newDev = dev/2;
			
			drawTriangleWithDev(turtle, order - 1, newDev, point1, offset1, offset3);
			drawTriangleWithDev(turtle, order - 1, newDev, offset1, point2, offset2);
			drawTriangleWithDev(turtle, order - 1, newDev, offset3, offset2, point3);
			
			drawTriangleWithDev(turtle, order - 1, newDev, offset1, offset2, offset3);
		}
	}
	
}
