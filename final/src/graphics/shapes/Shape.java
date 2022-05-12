package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import java.util.TreeMap;
import graphics.shapes.attributes.Attributes;

public abstract class Shape {
	private Map<String, Attributes> attributes;
	
	public Shape() {
		attributes = new TreeMap<>();
	}
	
	public void addAttributes(Attributes attribute) {
		attributes.put(attribute.getId(), attribute);
	}
	
	public Attributes getAttributes(String attribute) {
		return attributes.get(attribute);
	}
	
	public abstract Point getLoc();
	
	public abstract void setLoc(Point point);
	
	public abstract void translate(int x, int y);
	
    public abstract void zoom(int x, int y);

	public abstract Rectangle getBounds();
	
	public abstract void accept(ShapeVisitor visitor);
}
