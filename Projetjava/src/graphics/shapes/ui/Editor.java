package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SGif;
import graphics.shapes.SImage;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JFrame;

public class Editor extends JFrame {
	ShapesView sview;
	SCollection model;
	
	public Editor() {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		
		this.buildModel();
		
		this.sview = new ShapesView(this.model);
		this.sview.setPreferredSize(new Dimension(1000,800));
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
	}

	
	private void buildModel() {
		this.model = new SCollection();
		this.model.addAttributes(new SelectionAttributes());
		
		SRectangle r = new SRectangle(new Point(10,10),20,30);
		r.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
		r.addAttributes(new SelectionAttributes());
		this.model.add(r);
		
		SImage i = new SImage("https://c.tenor.com/04hXxzZ5w6EAAAAd/yushia-nodders.gif", new Point(50,50));
		i.addAttributes(new SelectionAttributes());
		this.model.add(i);
		
		SGif g = new SGif("https://c.tenor.com/04hXxzZ5w6EAAAAd/yushia-nodders.gif", new Point(50,50));
		i.addAttributes(new SelectionAttributes());
		this.model.add(g);
		
		int xPoints[] = {10, 25, 10, 25, 5, 45, 58, 56};
		int yPoints[] = {10, 25, 25, 15, 50, 50, 20, 250};
		SPolygon p = new SPolygon(xPoints, yPoints, 8);
		p.translate(100, 100);
		p.addAttributes(new ColorAttributes(true,true,Color.GREEN,Color.GRAY));
		p.addAttributes(new SelectionAttributes());
		this.model.add(p);
		
		SCircle c = new SCircle(new Point(100,100),10);
		c.addAttributes(new ColorAttributes(false,true,Color.BLUE,Color.BLUE));
		c.addAttributes(new SelectionAttributes());
		this.model.add(c);
		
		SText t= new SText(new Point(100,100),"hello");
		t.addAttributes(new ColorAttributes(true,true,Color.YELLOW,Color.BLUE));
		t.addAttributes(new SelectionAttributes());
		this.model.add(t);
		
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
		this.model.add(sc);
	}
	
	public static void main(String[] args) {
		Editor self = new Editor();
		self.pack();
		self.setVisible(true);
	}
}
