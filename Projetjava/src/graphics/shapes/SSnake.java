package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.attributes.ColorAttributes;

public class SSnake extends Shape {
	private SField field;
	private ArrayList<SImage> snake;
	private int spaceSize;
	
	public SSnake(SField field) {
		this.field = field;
		spaceSize = field.spaceSize;
		snake = new ArrayList<SImage>();
		Point loc = field.getLoc();
		SImage i = new SImage("head1.png",new Point(loc.x + field.getWidth()/2*spaceSize, loc.y + field.getHeight()/2*spaceSize), spaceSize, spaceSize);
		snake.add(i);
	}
	
	public Point getLoc() {
		return getBounds().getLocation();
	}

	public void setLoc(Point point) {
		Point loc = getLoc();
		translate(point.x - loc.x, point.y - loc.y);
	}
	
	public SImage getHead() {
		return snake.get(0);
	}
	
	public boolean move(int direction) {
		Point loc = snake.get(0).getLoc();
		Point locInter = snake.get(0).getLoc();
		if(direction != 3) {
			locInter.translate(spaceSize*(direction-1), spaceSize*(direction-2)*direction);
		}
		else {
			locInter.translate(0, 1 * spaceSize);
		}
		boolean grow;
		boolean end = isInSnake(locInter) || (locInter.x < field.getLoc().x) || (locInter.y < field.getLoc().y) || (locInter.x >= field.getLoc().x + field.getWidth()*spaceSize) || (locInter.y >= field.getLoc().y + field.getHeight()*spaceSize);
		if(end) {
			return false;
		}
		snake.get(0).setLoc(locInter);
		snake.get(0).setImage("head" + direction + ".png");
		if(snake.size() < 4 || isEating()) {
			grow = true;
		}
		else {
			grow = false;
		}
		for(int i = 1; i < snake.size(); i++) {
			locInter = snake.get(i).getLoc();
			snake.get(i).setLoc(loc);
			loc = locInter;
		}
		if(grow) {
			snake.add(new SImage("tail" + 0 + ".png", loc, spaceSize, spaceSize));
			if(snake.size()%2 == 1) {
				snake.get(snake.size()-1).addAttributes(new ColorAttributes(true,true, new Color(0, 125, 0), Color.BLACK));
			}
			else {
				snake.get(snake.size()-1).addAttributes(new ColorAttributes(true,true, new Color(0, 255, 0), Color.BLACK));
			}
		}
		field.addFruit();
		return true;
	}
	
	public boolean isEating() {
		Iterator<SImage> it = field.fruitsIterator();
		Point headLoc = getHead().getLoc();
		SImage fruit;
		while(it.hasNext()) {
			fruit = it.next();
			if(fruit.getBounds().contains(headLoc)) {
				field.delFruit(fruit);
				return true;
			}
		}
		return false;
	}
	
	public boolean isInSnake(Point loc) {
		Iterator<SImage> it = iterator();
		it.next();
		while(it.hasNext()) {
			Rectangle r = it.next().getBounds();
			if(r.x == loc.x && r.y == loc.y) {
				return true;
			}
		}
		return false;
	}

	public void translate(int x, int y) {
		Iterator<SImage> it = iterator();
		while(it.hasNext()) {
			it.next().translate(x, y);
		}
	}

	public void zoom(int x, int y) {
		
	}
	
	public Iterator<SImage> iterator() {
		return snake.iterator();
	}

	public Rectangle getBounds() {
		if(snake.size() > 0) {
			Iterator<SImage> it = iterator();
			Rectangle r = it.next().getBounds();
			while(it.hasNext()) {
				r = r.union(it.next().getBounds());
			}
			return r;
		}
		return null;
	
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitSnake(this);
	}

}
