package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import graphics.shapes.SArc;
import graphics.shapes.SCircle;
import graphics.shapes.SOval;
import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;


public class SRandomShapes extends MouseAdapter {
	
	private Random random = new Random();
	private SRandomShapesPanel panel;
	private JFrame frame = new JFrame();

	
	public SRandomShapes (SRandomShapesPanel p) {
		panel = p;
		frame.setContentPane(this.panel);
		panel.addMouseListener(this);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void mousePressed(MouseEvent e) {
		
		/* Initialize randomly the red, green and blue intensity */ 
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		
		int index1 = 1+ random.nextInt(20);
		int[] xPoint = new int [index1];
		int[] yPoint = new int [index1];
		
		for (int i = 0; i<index1;i++) {
			int x1 = random.nextInt(20);
			xPoint[i]=x1;
		}
		for (int i = 0; i<index1;i++) {
			int y1 = random.nextInt(20);
			yPoint[i]=y1;
		}
		
		int x = random.nextInt(100);
		int y = random.nextInt(100);
		int index = random.nextInt(5);
		

		
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		Point p = new Point(e.getX()-10, e.getY()-10);
		SCircle s = new SCircle (p,x);
		s.addAttributes(new ColorAttributes(true,true,new Color (r,g,b),new Color (r,g,b)));
		SRectangle re = new SRectangle(p,x,y);
		re.addAttributes(new ColorAttributes(true,false,new Color (r,g,b),new Color (r,g,b)));
		SOval o = new SOval(p,x,y);
		o.addAttributes(new ColorAttributes(true,true,new Color (r,g,b),new Color (r,g,b)));
        SArc a = new SArc(p,r,g,b,r);
        a.addAttributes(new ColorAttributes(true,true,new Color (r,g,b),new Color (r,g,b)));
        SPolygon poly = new SPolygon(xPoint,yPoint,index1);
        poly.setLoc(p);
        poly.zoom(r, g);
		poly.addAttributes(new ColorAttributes(true,true,new Color (r,g,b),new Color (r,g,b)));
		
		shapes.add(s);
		shapes.add(re);
		shapes.add(o);
		shapes.add(a);
		shapes.add(poly);
		
		
		Shape form = shapes.get(index);
		
		panel.addShape(form);
	}
	
	
	
	
	
	
	
	
	
}
