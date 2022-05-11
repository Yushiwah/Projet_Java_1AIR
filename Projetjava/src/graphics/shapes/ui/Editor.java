package graphics.shapes.ui;

import graphics.shapes.HomePage;
import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SField;
import graphics.shapes.SGif;
import graphics.shapes.SGol;
import graphics.shapes.SImage;
import graphics.shapes.SMinesweeper;
import graphics.shapes.SOval;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.SSnake;
import graphics.shapes.SText;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Editor extends JFrame {
	ShapesView sview;
	Shape model;
	
	public Editor(boolean snake, boolean minesweeper, boolean rick) {
		super("Shapes Editor");

		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				System.exit(0);
			}
		});
		
		this.buildModel(snake, minesweeper, rick);
		
		this.sview = new ShapesView(this.model, snake, minesweeper, rick);
		if(snake) {
			this.sview.setPreferredSize(new Dimension(996,706));
		}
		else if(minesweeper) {
			this.sview.setPreferredSize(new Dimension(520,550));
		}
		else if(rick) {
			this.sview.setPreferredSize(new Dimension(800,700));
		}
		else {
			this.sview.setPreferredSize(new Dimension(1200,800));
		}
		this.getContentPane().add(this.sview, java.awt.BorderLayout.CENTER);
	}

	
	private void buildModel(boolean snake, boolean minesweeper, boolean rick) {
		
		if(snake) {
			model = new SField(new Point(10,40), 16, 61, 41, 10);
			model.addAttributes(new SelectionAttributes());
			SSnake s = new SSnake((SField) model);
			s.addAttributes(new SelectionAttributes());
			((SField) model).setSnake(s);
		}
		
		else if(minesweeper) {
			model = new SMinesweeper(new Point(10,40), 25, 129);
			model.addAttributes(new SelectionAttributes());
		}
		
		else if(!rick) {
			this.model = new SCollection();
			
			SGol gol = new SGol(new Point(500,10),30, 10, 10);
			gol.addAttributes(new SelectionAttributes());
			gol.nextGeneration();
			((SCollection) this.model).add(gol);
			
			SImage i = new SImage("gol.png", new Point(500,320));
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(i);
			
	        SOval o = new SOval(new Point(500,10), 50, 20);
	        o.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
			o.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(o);
			
			SRectangle r = new SRectangle(new Point(10,10),20,30);
			r.addAttributes(new ColorAttributes(true,true,Color.BLUE,Color.RED));
			r.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(r);
			
			i = new SImage("https://c.tenor.com/04hXxzZ5w6EAAAAd/yushia-nodders.gif", new Point(0,50), 50, 50);
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(i);
			
			i = new SImage("fruitSprite.png", new Point(50,50));
			i.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(i);
			
			SGif g = new SGif("mine.gif", new Point(50,50), 50, 50);
			g.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(g);
			
			int xPoints[] = {0,0,9,0,0,3,12,21,24,24,15,24,24,21,12,3};
			int yPoints[] = {0,3,12,21,24,24,15,24,24,21,12,3,0,0,9,0};
			SPolygon p = new SPolygon(xPoints, yPoints, 16);
			p.zoom(156, 156);
			p.addAttributes(new ColorAttributes(true,false,Color.RED,Color.GRAY));
			p.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(p);
			
			int[] xs = new int [722];
			int[] ys = new int [722];
			for(int k = 0;k < 361; k++) {
				xs[k] = ((int) (Math.cos(((double)k)/180*Math.PI)*300));
				ys[k] = ((int) (Math.sin(((double)k)/180*Math.PI)*300));
			}
			for(int k = 0;k < 361; k++) {
				xs[k+361] = ((int) (Math.cos((360-(double)k)/180*Math.PI)*250));
				ys[k+361] = ((int) (Math.sin((360-(double)k)/180*Math.PI)*250));
			}
			p = new SPolygon(xs, ys, 722);
			p.zoom(-420, -420);
			p.setLoc(new Point(500,500));
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
			c = new SCircle(new Point(220,60),30);
			c.addAttributes(new ColorAttributes(true,true,Color.CYAN,Color.BLUE));
			c.addAttributes(new SelectionAttributes());
			sc.add(c);
			
			r= new SRectangle(new Point(210,120),80,200);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
			
			r= new SRectangle(new Point(190,120),20,100);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
			
			r= new SRectangle(new Point(290,120),20,100);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
			
			r= new SRectangle(new Point(210,320),20,100);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
			
			r= new SRectangle(new Point(270,320),20,100);
			r.addAttributes(new ColorAttributes(true,false,Color.MAGENTA,Color.BLUE));
			r.addAttributes(new SelectionAttributes());
			sc.add(r);
	
			
			((SCollection) this.model).add(sc);
			
			SCollection sc3 = new SCollection();
			sc3.addAttributes(new SelectionAttributes());
			SOval oval = new SOval(new Point(300, 15), 35,25);
			oval.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			oval.addAttributes(new SelectionAttributes());
			sc3.add(oval);
			SCircle circle = new SCircle(new Point(285,20),20);
			circle.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			circle.addAttributes(new SelectionAttributes());
			sc3.add(circle);
			SCircle circle2 = new SCircle(new Point(355,20),20);
			circle2.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			circle2.addAttributes(new SelectionAttributes());
			sc3.add(circle2);
			SCircle circle3 = new SCircle(new Point(340,40),15);
			circle3.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			circle3.addAttributes(new SelectionAttributes());
			sc3.add(circle3);
			SCircle circle4 = new SCircle(new Point(290,10),15);
			circle4.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			circle4.addAttributes(new SelectionAttributes());
			sc3.add(circle4);
			SOval oval2 = new SOval(new Point(280, 15), 15,25);
			oval2.addAttributes(new ColorAttributes(true,true,Color.LIGHT_GRAY,Color.LIGHT_GRAY));
			oval2.addAttributes(new SelectionAttributes());
			sc3.add(oval2);
			
			((SCollection) this.model).add(sc3);
		}
		else {
			this.model = new SCollection();
			
			SGif g = new SGif("rick.gif", new Point(0, 0), 700, 800);
			g.addAttributes(new SelectionAttributes());
			((SCollection) this.model).add(g);
		}
	}
	
	public static void main(String[] args) {
	
		
		new HomePage();
		/*

		SRAndomShapesPanel p = new SRAndomShapesPanel();
		new SRandomShapes(p) */  
	}
}
