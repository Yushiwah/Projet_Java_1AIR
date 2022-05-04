package graphics.shapes;

import java.awt.*;

public class SRectangle extends Shape{
	private Rectangle rect;
	
	public SRectangle(Point loc, int width, int height) {
		rect = new Rectangle(loc.x, loc.y, width, height);
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public Point getLoc() {
		return rect.getLocation();
	}
	
	public void setLoc(Point loc) {
		rect.setLocation(loc);
	}
	
	public void translate(int dx, int dy) {
		rect.translate(dx, dy);
	}
	
	public void zoom(int x, int y){
        rect.width += x;
        rect.height += y;
    }
	
	public Rectangle getBounds() {
		return rect;
	}
	
	public void accept(ShapeVisitor visitor) {
		visitor.visitRectangle(this);
	}
}
