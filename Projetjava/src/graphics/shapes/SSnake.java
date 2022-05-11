package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import graphics.ui.View;

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
	
	public boolean move(int direction, View view) {
		Point loc = snake.get(0).getLoc();
		Point locAfter = snake.get(0).getLoc();
		Point locBefore;
		if(direction != 3) {
			locAfter.translate(spaceSize*(direction-1), spaceSize*(direction-2)*direction);
		}
		else {
			locAfter.translate(0, 1 * spaceSize);
		}
		boolean grow;
		boolean end = isInSnake(locAfter) || (locAfter.x < field.getLoc().x) || (locAfter.y < field.getLoc().y) || (locAfter.x >= field.getLoc().x + field.getWidth()*spaceSize) || (locAfter.y >= field.getLoc().y + field.getHeight()*spaceSize);
		if(end) {
			field.gameOver();
			return false;
		}
		snake.get(0).setLoc(locAfter);
		snake.get(0).setImage("head" + direction + ".png");
		grow = snake.size() < 4 || isEating();
		locBefore = locAfter;
		int snakePart;
		for(int i = 1; i < snake.size(); i++) {
			snakePart = 0;
			locAfter = snake.get(i).getLoc();
			
			if(loc.x > locBefore.x) {
				snakePart += 1;
			}
			else if(loc.x < locBefore.x) {
				snakePart += 2;
			}
			else if(loc.y > locBefore.y) {
				snakePart += 3;
			}
			else {
				snakePart += 5;
			}
			if(loc.x > locAfter.x) {
				snakePart += 1;
			}
			else if(loc.x < locAfter.x) {
				snakePart += 2;
			}
			else if(loc.y > locAfter.y) {
				snakePart += 3;
			}
			else {
				snakePart += 5;
			}
			snake.get(i).setLoc(loc);
			snake.get(i).setImage("snake" + snakePart + ".png");
			locBefore = loc;
			loc = locAfter;
		}
		if(!grow) {
			loc = locBefore;
			locBefore = snake.get(snake.size()-2).getLoc();
		}
		if(loc.x > locBefore.x) {
			snakePart = 0;
		}
		else if(loc.x < locBefore.x) {
			snakePart = 2;
		}
		else if(loc.y > locBefore.y) {
			snakePart = 1;
		}
		else {
			snakePart = 3;
		}
		if(grow) {
			snake.add(new SImage("tail" + snakePart + ".png", loc, spaceSize, spaceSize));
		}
		else {
			snake.get(snake.size()-1).setImage("tail" + snakePart + ".png");
		}
		field.addFruit();
		view.repaint();
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
