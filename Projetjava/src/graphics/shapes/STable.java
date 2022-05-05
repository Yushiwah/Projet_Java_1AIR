package graphics.shapes;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

public class STable extends Shape{

	private ArrayList<ArrayList<SText>> tableText = new ArrayList<ArrayList<SText>>(); 
	private ArrayList<ArrayList<SRectangle>> tableRect = new ArrayList<ArrayList<SRectangle>>(); 
	private ArrayList<ArrayList<JButton>> tableButton = new ArrayList<ArrayList<JButton>>();  
	private Point loc;
	private int n;
	private int height = 20;
	private float realHeight = 20;
	private int width = 20;
	private float realWidth = 20;
	
	public STable(int n) {
		loc = new Point(0, 0);
		this.n = n;
		ArrayList<SText> listText;
		ArrayList<SRectangle> listRect;
		ArrayList<JButton> listButton;
		JButton button = new JButton("button");
		button.setBackground(Color.LIGHT_GRAY);
		for(int i = 0; i < n; i++) {
			listRect = new ArrayList<SRectangle>();
			listText = new ArrayList<SText>();
			listButton = new ArrayList<JButton>();
			for(int j = 0; j < n; j++ ) {
				listRect.add(new SRectangle(new Point(width*i, height*j), width, height));
				listText.add(new SText(new Point(width*i, height*j), "1"));
				listButton.add(new JButton());
			}
			tableRect.add(listRect);
			tableText.add(listText);
			tableButton.add(listButton);
		}
	}
	
	public Iterator<ArrayList<SRectangle>> iteratorRect() {
		return tableRect.iterator();
	}
	
	public Iterator<ArrayList<SText>> iteratorText() {
		return tableText.iterator();
	}
	
	public Iterator<ArrayList<JButton>> iteratorButton() {
		return tableButton.iterator();
	}
	
	public Point getLoc() {
		return this.loc;
	}

	public void setLoc(Point point) {
		this.loc = point;
	}

	public void translate(int x, int y) {
		loc.translate(x, y);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++ ) {
				tableRect.get(i).get(j).translate(x, y);
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