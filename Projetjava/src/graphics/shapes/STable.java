package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import graphics.shapes.attributes.MinesweeperAttributes;

public class STable extends Shape{

	private ArrayList<ArrayList<SImage>> tableImage = new ArrayList<ArrayList<SImage>>();
	private ArrayList<ArrayList<Integer>> tableInt = new ArrayList<ArrayList<Integer>>();  
	private Point loc;
	private int n;
	private int height = 20;
	private float realHeight = 20;
	private int width = 20;
	private float realWidth = 20;
	private boolean win = false;
	private boolean lose = false;
	private int numberOfMine;
	
	public STable(int n, int numberOfMine) {
		this.numberOfMine = numberOfMine;
		loc = new Point(0, 0);
		this.n = n;
		ArrayList<SImage> listImage;
		ArrayList<Integer> listInt;
		for(int i = 0; i < n; i++) {
			listInt = new ArrayList<Integer>();
			for(int j = 0; j < n; j++ ) {
				listInt.add(0);
			}
			tableInt.add(listInt);
		}
		bombPlacer(tableInt, n);
		for(int i = 0; i < n; i++) {
			listImage = new ArrayList<SImage>();
			for(int j = 0; j < n; j++) {
				int valueOfTable = this.tableInt.get(i).get(j);
				if(valueOfTable >= 10) {
					listImage.add(new SImage("mine.gif", new Point(height*i, width*j), width, height));
					listImage.get(j).addAttributes(new MinesweeperAttributes());
				}
				else {
					listImage.add(new SImage(valueOfTable + ".png", new Point(height*i, width*j), width, height));
					listImage.get(j).addAttributes(new MinesweeperAttributes());
				}
			}
			tableImage.add(listImage);
		}
	}
	
	
	public Iterator<ArrayList<SImage>> iterator() {
		return tableImage.iterator();
	}
	
	public void win() {
		Iterator<ArrayList<SImage>> shapes = this.iterator();
		int count = 0;
		while(shapes.hasNext()) {
			Iterator<SImage> arrayIn = shapes.next().iterator();
			while(arrayIn.hasNext()) {
				if(((MinesweeperAttributes) arrayIn.next().getAttributes(MinesweeperAttributes.id)).isDiscovered()){
					count++;
				}
			}
		}
		if(count == (n*n - numberOfMine)) {
			win = true;
		}
	}
	
	public void lose() {
		lose = true;
	}
	
	public int randomNumber(int max) {
		Random random = new Random();
		return random.nextInt(0, max);
	}
	
	public void bombPlacer( ArrayList<ArrayList<Integer>> table, int n){
		int i = 0;
		while( i < numberOfMine ) {
			int random1 = randomNumber(n);
			int random2 = randomNumber(n);
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
	
	public boolean findZero(SImage s) {
    	System.out.println("test1");
        Point loc = s.getLoc();
        int x = (loc.x - this.getLoc().x)/this.width;
        int y = (loc.y - this.getLoc().y)/this.height;
        for(int i = Math.max(0, x - 1) ; i <= Math.min( x + 1, n - 1) ; i++) {
            for(int j = Math.max(0, y - 1); j <= Math.min(y + 1, n - 1); j++) {
                if(tableImage.get(i).get(j).getName().equals("0.png")) {
                    ((MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id)).toggleDiscovered();
                    zeroChain(tableImage.get(i).get(j), i, j);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void zeroChain(SImage s, int x, int y) {
        for(int i = Math.max(0, x - 1) ; i <= Math.min( x + 1, n - 1) ; i++) {
            for(int j = Math.max(0, y - 1); j <= Math.min(y + 1, n - 1); j++) {
                if(!((MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id)).isDiscovered() && tableImage.get(i).get(j).getName().equals("0.png")) {
            		((MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id)).toggleDiscovered();
                	zeroChain(tableImage.get(i).get(j), i, j);
                }
                if(!((MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id)).isDiscovered()) {
            		((MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id)).toggleDiscovered();
            	}
            }
        }
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
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public boolean getWin() {
		return win;
	}
	
	public boolean getLose() {
		return lose;
	}
}