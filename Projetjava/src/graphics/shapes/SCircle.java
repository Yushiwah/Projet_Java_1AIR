package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;

public class SCircle extends Shape{
	private Point loc;
	private int radius;
	private float realXRadius;
	private float realYRadius;
	
	public SCircle(Point loc, int radius) {
		this.loc = loc;
		this.radius = radius;
		realXRadius = radius;
		realYRadius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Point getLoc() {
		return loc;
	}
	
	public void setLoc(Point loc) {
		this.loc = loc;
	}
	
	public void translate(int dx, int dy) {
		loc.translate(dx, dy);
	}
	
	public void zoom(int x, int y) {
		realXRadius = ((float) x)/2 + realXRadius;
		realYRadius = ((float) y)/2 + realYRadius;
		radius = (int) Math.max((float) 0, Math.min(realXRadius, realYRadius));
    }
	
	public Rectangle getBounds() {
		return (new Rectangle(loc.x, loc.y, 2*radius, 2*radius));
	}
	
	public void accept(ShapeVisitor visitor) {
		visitor.visitCircle(this);
	}
}
