package graphics.shapes;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class SPolygon extends Shape {
	private Polygon polygon;
	private ArrayList<Float> xs;
	private ArrayList<Float> ys;
	private ArrayList<Float> propx;
	private ArrayList<Float> propy;
	
	public SPolygon(int xPoints[], int yPoints[], int nPoints) {
        this.polygon = new Polygon(xPoints,yPoints,nPoints);
        
        xs = new ArrayList<Float>();
		ys = new ArrayList<Float>();
		float moyx = 0;
		float moyy = 0;
		for(int i = 0; i < nPoints; i++) {
			xs.add((float) xPoints[i]);
			ys.add((float) yPoints[i]);
			moyx += xs.get(i);
			moyx += ys.get(i);
		}
		moyx /= xs.size();
		moyy /= ys.size();
		propx = new ArrayList<Float>();
		propy = new ArrayList<Float>();
		Rectangle Bound = this.getBounds();
		for(int i = 0; i < xs.size(); i++) {
			if(Bound.width != 0) {
				propx.add((xs.get(i) - moyx)/Bound.width);
			}
			else {
				propx.add((float) 0);
			}
			if(Bound.height != 0) {
				propy.add((ys.get(i) - moyy)/Bound.height);
			}
			else {
				propy.add((float) 0);
			}
		}
    }
	
	public Point getLoc() {
		return polygon.getBounds().getLocation();
	}

	public void setLoc(Point point) {
		Point loc = getLoc();
		this.translate(point.x - loc.x, point.y - loc.y);
	}
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
	
	public void zoom(int dx, int dy) {
		if(xs.size() > 0) {
			Point startLoc= this.getLoc();
			for(int i=0; i < xs.size(); i++) {
				xs.set(i, xs.get(i) + dx*propx.get(i));
				ys.set(i, ys.get(i) + dy*propy.get(i));
			}
			int xPoints[] = polygon.xpoints;
			int yPoints[] = polygon.ypoints;
			int inX = 0;
			int inY = 0;
			for(int i = 0; i < xs.size()-1; i++) {
				if(xs.get(i) > xs.get(i+1) && propx.get(i) < propx.get(i+1)) {
					inX += 1;
				}
				if(ys.get(i) > ys.get(i+1) && propy.get(i) < propy.get(i+1)) {
					inY += 1;
				}
			}
			
			for(int i = 0; i < xs.size(); i++) {
				if(inX > 0) {
					xPoints[i] = startLoc.x;
				}
				else {
					xPoints[i] = (int) Math.floor(xs.get(i));
				}
				if(inY > 0) {
					yPoints[i] = startLoc.y;
				}
				else {
					yPoints[i] = (int) Math.floor(ys.get(i));
				}
			}
			polygon = new Polygon(xPoints, yPoints, polygon.npoints);
			Point endLoc = this.getLoc();
			this.translate(startLoc.x - endLoc.x, startLoc.y - endLoc.y);
		}
	}

	public void translate(int x, int y) {
		polygon.translate(x, y);
		for(int i = 0; i < xs.size(); i++) {
			xs.set(i, xs.get(i) + x);
			ys.set(i, ys.get(i) + y);
		}
	}
	
	public int getNbPoint() {
		return polygon.npoints;
	}
	
	public Rectangle getBounds() {
		return polygon.getBounds();
	}

	public void accept(ShapeVisitor visitor) {
		visitor.visitPolygon(this);
		
	}

}