package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import graphics.shapes.attributes.FontAttributes;

public class SText extends Shape{
	private String text;
	private Point loc;
	int width;
	int height;
	
	public SText(Point loc, String text) {
		this.text = text;
		this.loc = loc;
		addAttributes(new FontAttributes());
		Rectangle bound = ((FontAttributes) this.getAttributes(FontAttributes.id)).getBounds(text);
		width = bound.width;
		height= bound.height;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
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
		width += x;
		height += y;
		boolean ok = true;
		FontAttributes font = (FontAttributes) this.getAttributes(FontAttributes.id);
		int size = font.getSize();
		Rectangle bound;
		while(ok) {
			font.setSize(size + 1);
			bound = font.getBounds(text);
			if(bound.width <= width && bound.height <= height) {
				size++;
			}
			else {
				ok = false;
			}
		}
		ok = true;
		while(ok && size > 0) {
			font.setSize(size);
			bound = font.getBounds(text);
			if(bound.width > width || bound.height > height) {
				size--;
			}
			else {
				ok = false;
			}
		}       
    }
	
	public int getFontHeight() {
		return ((FontAttributes) this.getAttributes(FontAttributes.id)).getBounds(text).height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(loc.x - 1, loc.y, Math.max(width, 0), Math.max(height, 0));
	}
	
	public void accept(ShapeVisitor visitor) {
		visitor.visitText(this);
	}
}
