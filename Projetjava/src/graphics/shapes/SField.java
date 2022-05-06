package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.attributes.ColorAttributes;

public class SField extends Shape {
	private SSnake snake;
	private Point loc;
	private int width;
	private int height;
	public int spaceSize;
	private ArrayList<SRectangle> field;
	private ArrayList<SImage> fruits;
	private int fruitNumber;
	private int fruitPlaced;
	
	public SField(Point loc, int spaceSize, int width, int height, int fruitNumber) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.fruitNumber = fruitNumber;
		fruitPlaced = 0;
		this.spaceSize = spaceSize;
		field = new ArrayList<SRectangle>();
		fruits = new ArrayList<SImage>();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				field.add(new SRectangle(new Point(j*spaceSize + loc.x, i*spaceSize + loc.y), spaceSize, spaceSize));
			}
		}
	}
	
	public void addFruit() {
		while(fruitNumber != fruitPlaced && fruits.size() != fruitPlaced+1) {
			int x = ((int) (Math.random()*width)) * spaceSize + loc.x; 
			int y = ((int) (Math.random()*height)) * spaceSize + loc.x;
			Point candidate = new Point(x, y);
			if(!snake.isInSnake(candidate) && !(snake.getHead().getBounds().contains(candidate))) {
				fruits.add(new SImage(new File("src/image"), "fruitSprite.png",candidate));
				fruits.get(fruits.size()-1).addAttributes(new ColorAttributes(true, true, Color.ORANGE, Color.BLACK));
			}
		}
		if(fruitPlaced < fruits.size()) {
			fruitPlaced++;
		}
	}
	
	public void delFruit(SImage fruit) {
		fruits.remove(fruit);
		fruitPlaced--;
	}
	
	public Point getLoc() {
		return new Point(loc.x, loc.y);
	}

	public void setLoc(Point point) {
		translate(point.x - loc.x, point.y - loc.y);
	}
	
	public SSnake getSnake() {
		return snake;
	}

	public void setSnake(SSnake snake) {
		this.snake = snake;
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
	
	public Iterator<SRectangle> iterator() {
		return field.iterator();
	}
	
	public Iterator<SImage> fruitsIterator() {
		return fruits.iterator();
	}

	public void translate(int x, int y) {
		loc.translate(x, y);
		Iterator<SRectangle> it = iterator();
		Iterator<SImage> fruitsIt = fruits.iterator();
		while(it.hasNext()) {
			it.next().translate(x, y);
		}
		while(fruitsIt.hasNext()) {
			fruitsIt.next().translate(x, y);
		}
		snake.translate(x, y);
	}

	public void zoom(int x, int y) {
		
	}

	public Rectangle getBounds() {
		return new Rectangle(loc.x, loc.y, width*spaceSize, height*spaceSize);
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitField(this);
	}
}
