package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SField;
import graphics.shapes.SGif;
import graphics.shapes.SImage;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SSnake;
import graphics.shapes.STable;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;

public class Editor extends JFrame {
	ShapesView sview;
	Shape model;
	public boolean snake;
	
	public Editor() {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.sview = new ShapesView(this.model, snake);
		this.sview.setPreferredSize(new Dimension(61*16+20,41*16+20));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
	}

	
	private void buildModel() {
		snake = false;
		
		if(snake) {
			model = new SField(new Point(10,10), 16, 61, 41, 10);
			model.addAttributes(new SelectionAttributes());
			
			SSnake s = new SSnake((SField) model);
			s.addAttributes(new SelectionAttributes());
			((SField) model).setSnake(s);
		}
		
		else {
			this.model = new SCollection();
			
			SField f = new SField(new Point(10,10), 10, 75, 75, 5);
			f.addAttributes(new SelectionAttributes());
			
			SSnake s = new SSnake(f);
			s.addAttributes(new SelectionAttributes());
			f.setSnake(s);
			((SCollection) this.model).add(f);
			
			STable tabl = new STable(13);
	        tabl.addAttributes(new SelectionAttributes());
	        ((SCollection) this.model).add(tabl);
			
			SRectangle r = new SRectangle(new Point(10,10),20,30);
			r.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
			r.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(r);
			
			SImage i = new SImage("https://c.tenor.com/04hXxzZ5w6EAAAAd/yushia-nodders.gif", new Point(0,50), 50, 50);
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(i);
			
			i = new SImage(new File("src/image"), "fruitSprite.png", new Point(50,50));
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(i);
			
			SGif g = new SGif("https://c.tenor.com/04hXxzZ5w6EAAAAd/yushia-nodders.gif", new Point(50,50), 50, 50);
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(g);
			
			int xPoints[] = {0,0,9,0,0,3,12,21,24,24,15,24,24,21,12,3};
			int yPoints[] = {0,3,12,21,24,24,15,24,24,21,12,3,0,0,9,0};
			SPolygon p = new SPolygon(xPoints, yPoints, 16);
			p.translate(100, 100);
			p.addAttributes(new ColorAttributes(true,false,Color.RED,Color.GRAY));
			p.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(p);
			
			SCircle c = new SCircle(new Point(100,100),10);
			c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
			c.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(c);
			
			SText t= new SText(new Point(100,100),"hello");
			t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
			t.addAttributes(new FontAttributes());
			t.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(t);
			
			SCollection sc = new SCollection();
			sc.addAttributes(new SelectionAttributes());
			r= new SRectangle(new Point(20,30),30,30);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
	
			c = new SCircle(new Point(150,100),20);
			c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.DARK_GRAY));
			c.addAttributes(new SelectionAttributes());
			sc.add(c);
			((SCollection) this.model).add(sc);
		}
	}
	
	public static void main(String[] args) {
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}
