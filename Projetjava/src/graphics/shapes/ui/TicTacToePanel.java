package graphics.shapes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import graphics.shapes.SPolygon;
import graphics.shapes.SRectangle;
import graphics.shapes.attributes.ColorAttributes;

public class TicTacToePanel extends JPanel {
	
	private List<SRectangle> rects;
	private ArrayList<SPolygon> polys = new ArrayList<SPolygon>() ;
	private ArrayList<SPolygon> circle = new ArrayList<SPolygon>() ;

	public void addCroix(Point loc) {
		int xPoints[] = {0,0,9,0,0,3,12,21,24,24,15,24,24,21,12,3};
        int yPoints[] = {0,3,12,21,24,24,15,24,24,21,12,3,0,0,9,0};
        SPolygon p = new SPolygon(xPoints, yPoints, 16);
        p.addAttributes(new ColorAttributes(true,true,Color.blue,new JButton().getBackground()));
        p.translate(loc.x+2, loc.y+2);
        p.zoom(152, 152);
		this.polys.add(p);
	}
	
	public void addCircle (Point loc) {
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
        SPolygon c = new SPolygon(xs, ys, 722);
        c.zoom(-420, -420);
        c.setLoc(new Point(loc.x,loc.y));
        c.addAttributes(new ColorAttributes(true,false,Color.RED,Color.GRAY));
        this.circle.add(c);
		
	}
	
	public void addRectangle(List<SRectangle> rects) {
		this.rects = rects;
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ShapeDraftman visitor = new ShapeDraftman(g);
		for (SRectangle r : rects) {
			r.accept(visitor);
		}
		for (SPolygon c : circle) {
			c.accept(visitor);
		}
		for (SPolygon p : polys) {
			p.accept(visitor);
		}
			
	
	}

}
	

