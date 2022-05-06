package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;

public class STable extends Shape{

	private ArrayList<ArrayList<SImage>> tableImage = new ArrayList<ArrayList<SImage>>(); 
	private ArrayList<ArrayList<SRectangle>> tableRect = new ArrayList<ArrayList<SRectangle>>(); 
	private ArrayList<ArrayList<Integer>> tableInt = new ArrayList<ArrayList<Integer>>();  
	private Point loc;
	private int n;
	private int height = 26;
	private float realHeight = 26;
	private int width = 26;
	private float realWidth = 26;
	
	public STable(int n) {
		loc = new Point(0, 0);
		this.n = n;
		ArrayList<SImage> listImage;
		ArrayList<SRectangle> listRect;
		ArrayList<Integer> listInt;
		for(int i = 0; i < n; i++) {
			listRect = new ArrayList<SRectangle>();
			listImage = new ArrayList<SImage>();
			listInt = new ArrayList<Integer>();
			for(int j = 0; j < n; j++ ) {
				listRect.add(new SRectangle(new Point(width*i, height*j), width, height));
				listInt.add(0);
			}
			tableRect.add(listRect);
			tableInt.add(listInt);
		}
		bombPlacer(tableInt, n);
		for(int i = 0; i < n; i++) {
			listInt = new ArrayList<Integer>();
			listImage = new ArrayList<SImage>();
			for(int j = 0; j < n; j++) {
				int valueOfTable = this.tableInt.get(i).get(j);
				if(valueOfTable >= 10) {
					listImage.add(new SImage(new File("src/image"), "mine.gif", new Point(height*i, width*j)));
				}
				else {
					listImage.add(new SImage(new File("src/image"), valueOfTable + ".png", new Point(height*i, width*j)));
				}
			}
			tableImage.add(listImage);
		}
	}
	
	public Iterator<ArrayList<SRectangle>> iteratorRect() {
		return tableRect.iterator();
	}
	
	public Iterator<ArrayList<SImage>> iteratorImage() {
		return tableImage.iterator();
	}
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(0, 12);
	}
	
	public void bombPlacer( ArrayList<ArrayList<Integer>> table, int n){
		int i = 0;
		while( i < 10 ) {
			int random1 = randomNumber();
			int random2 = randomNumber();
			if(table.get(random1).get(random2) < 10) {
				table.get(random1).set(random2, 10);
				i++;
				for(int k = Math.max(0, random1 - 1) ; k <= Math.min( random1 + 1, n - 1) ; k++) {
					for(int j = Math.max(0, random2 - 1); j <= Math.min(random2 + 1, n - 1); j++) {
						int pdk = table.get(k).get(j);
						table.get(k).set(j, pdk + 1);
					}
				}
			}
		}
		this.tableInt = table;
	}
	
	public Point getLoc() {
		return new Point(loc.x, loc.y);
	}

	public void setLoc(Point point) {
		this.loc = point;
	}

	public void translate(int x, int y) {
		loc.translate(x, y);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++ ) {
				tableRect.get(i).get(j).translate(x, y);
				tableImage.get(i).get(j).translate(x, y);
			}
		}
	}
	
	public void zoom(int x, int y) {
		realWidth += ((float) x) / ((float) n);
		realHeight += ((float) y) / ((float) n);
		int dx;
		int dy;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++ ) {
				dx = (int) realWidth - width;
				dy = (int) realHeight - height;
				tableRect.get(i).get(j).zoom(dx, dy);
				tableRect.get(i).get(j).translate(dx*i, dy*j);
				tableImage.get(i).get(j).zoom(dx, dy);
				tableImage.get(i).get(j).translate(dx*i, dy*j);
			}
		}
		width = (int) realWidth;
		height = (int) realHeight;
		
	}

	public Rectangle getBounds() {
		return new Rectangle(loc.x, loc.y, Math.max(0, n*width), Math.max(0, n*height));
	}

	public void accept(ShapeVisitor shapevisitor) {
		shapevisitor.visitTable(this);
	}
}