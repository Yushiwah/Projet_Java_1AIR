package graphics.shapes;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SImage extends Shape{
	private Point loc;
	private BufferedImage image;
	private String name;
	private int width;
	private int height;
	
	public SImage(String url, Point loc) {
		name = url;
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			try {
				image = ImageIO.read(new File("src/image", url));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		this.loc = loc;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public SImage(String url, Point loc, int width, int height) {
		name = url;
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			try {
				image = ImageIO.read(new File("src/image", url));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
			try {
				image = ImageIO.read(new File("src/image", url));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Point getLoc() {
		return new Point(loc.x, loc.y);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String getName() {
		return name;
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