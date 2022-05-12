package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.MinesweeperAttributes;

public class SMinesweeper extends Shape{

	private ArrayList<ArrayList<SImage>> tableImage;
	private ArrayList<ArrayList<Integer>> tableInt;
	private ArrayList<Shape> counter;
	private Point loc;
	private int n;
	private int height = 20;
	private int width = 20;
	private boolean win = false;
	private boolean lose = false;
	private int numberOfMine;
	private int numberOfMineLeft;
	
	public SMinesweeper(Point loc, int n, int numberOfMine) {
		this.loc = loc;
		tableImage = new ArrayList<ArrayList<SImage>>();
		tableInt = new ArrayList<ArrayList<Integer>>();
		counter = new ArrayList<Shape>();
		this.numberOfMine = numberOfMine;
		numberOfMineLeft = numberOfMine;
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
		bombPlacer(n);
		for(int i = 0; i < n; i++) {
			listImage = new ArrayList<SImage>();
			for(int j = 0; j < n; j++) {
				int valueOfTable = this.tableInt.get(i).get(j);
				if(valueOfTable >= 10) {
					listImage.add(new SImage("mine.gif", new Point(width*i + loc.x, height*j + loc.y), width, height));
					listImage.get(j).addAttributes(new MinesweeperAttributes());
				}
				else {
					listImage.add(new SImage(valueOfTable + ".png", new Point(width*i + loc.x, height*j + loc.y), width, height));
					listImage.get(j).addAttributes(new MinesweeperAttributes());
				}
			}
			tableImage.add(listImage);
		}
		SRectangle rect = new SRectangle(new Point(loc.x, loc.y-30), width*n, 30);
        rect.addAttributes(new ColorAttributes(true, true, new Color(211, 211, 211), Color.BLACK));
        this.counter.add(rect);
        
        SImage flag = new SImage("drapeau.png", new Point(width*n/2-25 + loc.x,loc.y-26));
        this.counter.add(flag);
        
        SText text = new SText(new Point(0, 0), "" + numberOfMineLeft);
        text.translate(loc.x + width*n/2, loc.y - (int) text.getBounds().getHeight()*2+1);
        text.addAttributes(new ColorAttributes(true, false, new Color(211, 211, 211), Color.BLACK));
        text.setSize(19);
        this.counter.add(text);
	}
	
	public Iterator<ArrayList<SImage>> iterator() {
		return tableImage.iterator();
	}
	
	public Iterator<Shape> counterIterator() {
		return counter.iterator();
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
			SImage img = new SImage("win.png", new Point(this.width*n/2 - 101, this.width*n/2 - 51));
			counter.add(img);
		}
	}
	
	public void lose() {
		lose = true;
		SImage img = new SImage("lose.png", new Point(this.width*n/2 - 126, this.width*n/2 - 51), 252, 142);
		counter.add(img);
	}
	
	public void toggleFlagged(SImage image) {
		MinesweeperAttributes attribute = (MinesweeperAttributes) image.getAttributes(MinesweeperAttributes.id);
		if(attribute.isFlagged()) {
			numberOfMineLeft += 1;
		}
		else {
			numberOfMineLeft -= 1;
		}
		attribute.toggleFlagged();
		((SText)counter.get(2)).setText("" + numberOfMineLeft);
	}
	
	public int randomNumber(int max) {
		Random random = new Random();
		return random.nextInt(0, max);
	}
	
	public void bombPlacer(int n){
		int i = 0;
		while( i < numberOfMine ) {
			int random1 = randomNumber(n);
			int random2 = randomNumber(n);
			if(tableInt.get(random1).get(random2) < 10) {
				tableInt.get(random1).set(random2, 10);
				i++;
				for(int k = Math.max(0, random1 - 1) ; k <= Math.min( random1 + 1, n - 1) ; k++) {
					for(int j = Math.max(0, random2 - 1); j <= Math.min(random2 + 1, n - 1); j++) {
						int pdk = tableInt.get(k).get(j);
						tableInt.get(k).set(j, pdk + 1);
					}
				}
			}
		}
	}
	
	public boolean findZero(SImage s) {
        Point loc = s.getLoc();
        int x = (loc.x - this.getLoc().x)/this.width;
        int y = (loc.y - this.getLoc().y)/this.height;
        for(int i = Math.max(0, x - 1) ; i <= Math.min( x + 1, n - 1) ; i++) {
            for(int j = Math.max(0, y - 1); j <= Math.min(y + 1, n - 1); j++) {
            	MinesweeperAttributes attributes = (MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id);
                if(tableImage.get(i).get(j).getName().equals("0.png") && !attributes.isFlagged()) {
                    attributes.toggleDiscovered();
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
            	MinesweeperAttributes attributes = (MinesweeperAttributes) tableImage.get(i).get(j).getAttributes(MinesweeperAttributes.id);
                if(!attributes.isDiscovered() && !attributes.isFlagged() && tableImage.get(i).get(j).getName().equals("0.png")) {
                	attributes.toggleDiscovered();
                	zeroChain(tableImage.get(i).get(j), i, j);
                }
                if(!attributes.isDiscovered() && !attributes.isFlagged()) {
                	attributes.toggleDiscovered();
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
	}
	
	public void zoom(int x, int y) {
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
	
	public int getNumber() {
		return n;
	}

	public Rectangle getBounds() {
		return new Rectangle(loc.x, loc.y, Math.max(0, n*width), Math.max(0, n*height));
	}

	public void accept(ShapeVisitor shapevisitor) {
		shapevisitor.visitMinesweeper(this);
	}
}