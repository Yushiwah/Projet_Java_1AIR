package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SImage extends Shape{
	private Point loc;
	private BufferedImage image;
	int width;
	int height;
	
	public SImage(String url, Point loc){
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.loc = loc;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public SImage(String url, Point loc, int height, int width){
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.loc = loc;
		this.width = width;
		this.height = height;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(String url) {
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Point getLoc() {
		return loc;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setLoc(Point point) {
		loc = point;
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
		shapevisitor.visitImage(this);
	}
}