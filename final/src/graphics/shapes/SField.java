package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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
	private ArrayList<Shape> score;
	private int fruitNumber;
	private int fruitPlaced;
	private int fruitEaten;
	
	public SField(Point loc, int spaceSize, int width, int height, int fruitNumber) {
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.fruitNumber = fruitNumber;
		fruitEaten = 0;
		fruitPlaced = 0;
		this.spaceSize = spaceSize;
		field = new ArrayList<SRectangle>();
		fruits = new ArrayList<SImage>();
		SRectangle rect;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				rect = new SRectangle(new Point(j*spaceSize + loc.x, i*spaceSize + loc.y), spaceSize, spaceSize);
				field.add(rect);
				if((i+j)%2 == 1) {
					rect.addAttributes(new ColorAttributes(true, false, new Color(104, 123, 65), Color.BLACK));
				}
				else {
					rect.addAttributes(new ColorAttributes(true, false, new Color(137, 179, 114), Color.BLACK));
				}
			}
		}
		score = new ArrayList<Shape>();
		rect = new SRectangle(new Point(width*spaceSize/4 + loc.x, loc.y-30), width*spaceSize/2, 30);
		SImage image = new SImage("fruitSprite.png", new Point(width*spaceSize/2 + loc.x - 29, loc.y-29), 28, 28);
		SText counter = new SText(new Point(width*spaceSize/2 + loc.x+5, loc.y-40), "" + fruitEaten);
		counter.setSize(30);
		counter.addAttributes(new ColorAttributes(false, false, Color.BLACK, Color.BLACK));
		rect.addAttributes(new ColorAttributes(true, false, new Color(104, 123, 65), Color.BLACK));
		score.add(rect);
		score.add(image);
		score.add(counter);
	}
	
	public void addFruit() {
		while(fruitNumber != fruitPlaced && fruits.size() != fruitPlaced+1 && fruits.size() < width*height) {
			int x = ((int) (Math.random()*width)) * spaceSize + loc.x; 
			int y = ((int) (Math.random()*height)) * spaceSize + loc.y;
			Point candidate = new Point(x, y);
			if(!snake.isInSnake(candidate) && !(snake.getHead().getBounds().contains(candidate))) {
				fruits.add(new SImage("fruitSprite.png",candidate, spaceSize, spaceSize));
			}
		}
		if(fruitPlaced < fruits.size()) {
			fruitPlaced++;
		}
	}
	
	public void delFruit(SImage fruit) {
		fruits.remove(fruit);
		fruitPlaced--;
		fruitEaten++;
		((SText) score.get(2)).setText("" + fruitEaten);
	}
	
	public void gameOver() {
		score.add(new SImage("gameover.png", new Point(width*spaceSize/2 + loc.x - 250, height*spaceSize/2 + loc.y - 100), 500, 60));
		SText text = new SText(new Point(0,0), "Score : " + fruitEaten);
		score.add(text);
		text.addAttributes(new ColorAttributes(true, false, new Color(104, 123, 65), Color.BLACK));
		text.setSize(50);
		text.setLoc(new Point(width*spaceSize/2 + loc.x - (int) text.getBounds().getWidth()/2, height*spaceSize/2 + loc.y - (int) text.getBounds().getHeight()/2));
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
	
	public Iterator<Shape> scoreIterator() {
		return score.iterator();
	}

	public void translate(int x, int y) {
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
