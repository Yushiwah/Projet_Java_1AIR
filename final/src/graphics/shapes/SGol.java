package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import graphics.shapes.attributes.ColorAttributes;


public class SGol extends Shape {

	private Point loc;
	private int width;
	private int height;
	private int spaceSize;
	private ArrayList<SRectangle> field = new ArrayList<SRectangle>();
	private ArrayList<ArrayList<Integer>> state;
	private ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();

	public SGol(Point loc, int spaceSize, int width, int height) {
		
		ArrayList<Integer> line;
		this.loc = loc;
		this.width = width;
		this.height = height;
		this.spaceSize = spaceSize;
		state = new ArrayList<ArrayList<Integer>>(); 
		Random random = new Random();
		
		for(int i = 0; i < width; i++) {
			line = new ArrayList<Integer>();
			for(int j = 0; j < height; j++) {
				SRectangle r = new SRectangle(new Point(i*spaceSize + loc.x, j*spaceSize + loc.y), spaceSize, spaceSize);
				if (random.nextInt(3)==0) {
					r.addAttributes(new ColorAttributes(true,false,Color.BLACK,Color.BLACK));
					line.add(1);
				}
				else {
					r.addAttributes(new ColorAttributes(true,true,Color.WHITE,Color.BLACK));
					line.add(0);
				}
				field.add(r);
			}
			state.add(line);
		}
			for (int k = 0; k < state.size(); k++) {
				line = new ArrayList<Integer>();
				for (int l = 0; l <state.get(0).size(); l++) {
					line.add(0);
				}
				neighbors.add(line);
			}
		 
	}
	
	public void neighbor () {
		
		for (int x = 0; x<width; x++) {
			for (int y=0; y<height; y++) {
				neighbors.get(x).set(y, 0);
				for(int i = Math.max(0, x-1) ; i <= Math.min( x+1, width-1) ; i++) {
					for(int j = Math.max(0, y-1); j <= Math.min(y+1, height-1); j++) {
							neighbors.get(x).set(y, neighbors.get(x).get(y) + state.get(i).get(j) );
					}
				}
				neighbors.get(x).set(y, neighbors.get(x).get(y) - state.get(x).get(y) );
			}
		}
	}
	
	
	public void nextGeneration () {
		
		neighbor();
		for (int i = 0; i<width; i++) {
			for (int j = 0; j<height ; j++) {
				SRectangle r = new SRectangle(new Point(i*spaceSize + loc.x, j*spaceSize + loc.y), spaceSize, spaceSize);
				int n = neighbors.get(i).get(j);
				if (n==3 || (n == 2 && state.get(i).get(j)==1)) {
					state.get(i).set(j, 1);
					r.addAttributes(new ColorAttributes(true,false,Color.BLACK,Color.BLACK));
				}
				else {
					state.get(i).set(j, 0);
					r.addAttributes(new ColorAttributes(true,true,Color.WHITE,Color.BLACK));
				}
				field.set(i*height + j,r);
			}
			
		}
	}

	public Point getLoc() {
		return new Point(loc.x, loc.y);
	}

	public void setLoc(Point point) {
		translate(point.x - loc.x, point.y - loc.y);
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
	
	public Iterator <SRectangle> iterator() {
		return field.iterator();
	}


	public void translate(int x, int y) {
		loc.translate(x, y);
		Iterator<SRectangle> it = iterator();
		while(it.hasNext()) {
			it.next().translate(x, y);
		}
		
	}

	public void zoom(int x, int y) {
	}

	public Rectangle getBounds() {
		return new Rectangle(loc.x, loc.y, width*spaceSize, height*spaceSize);
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitGol(this);
	}
}