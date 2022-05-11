package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

public class SGif extends Shape{
	private Point loc;
	private Image image;
	int width;
	int height;
	
	public SGif(String url, Point loc) {
		try {
			image = new ImageIcon(new URL(url)).getImage();
		} catch (MalformedURLException e) {
			image = new ImageIcon("src/image/" + url).getImage();
		}
		this.loc = loc;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	public SGif(String url, Point loc, int height, int width) {
		try {
			image = new ImageIcon(new URL(url)).getImage();
		} catch (MalformedURLException e) {
			image = new ImageIcon("src/image/" + url).getImage();
		}
		this.loc = loc;
		this.width = width;
		this.height = height;
	}
	
	public Image getGif() {
		return image;
	}
	
	public void setGif(String url) {
		try {
			image = new ImageIcon(new URL(url)).getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public Point getLoc() {
		return new Point(loc.x, loc.y);
	}

	public void setLoc(Point point) {
		loc = point;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public void translate(int x, int y) {
		loc.translate(x, y);
	}
	
	public void zoom(int x, int y) {
		width += x;
		height += y;
	}

	public Rectangle getBounds() {
		return new Rectangle(loc.x, loc.y, Math.max(0, width), Math.max(0, height));
	}

	public void accept(ShapeVisitor shapevisitor) {
		shapevisitor.visitGif(this);
	}
}