package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class SCollection extends Shape {
	private ArrayList<Shape> shapes;
	private ArrayList<Float> dxs;
	private ArrayList<Float> dys;
	private ArrayList<Float> zxs;
	private ArrayList<Float> zys;
	private ArrayList<Float> propx;
	private ArrayList<Float> propy;
	
	public SCollection() {
		shapes = new ArrayList<Shape>();
		dxs = new ArrayList<Float>();
		dys = new ArrayList<Float>();
		zxs = new ArrayList<Float>();
		zys = new ArrayList<Float>();
		propx = new ArrayList<Float>();
		propy = new ArrayList<Float>();
	}
	
	public Iterator<Shape> iterator() {
		return shapes.iterator();
	}
	
	public void add(Shape shape) {
		shapes.add(shape);
		dxs.add((float) 0);
		dys.add((float) 0);
		zxs.add((float) 0);
		zys.add((float) 0);
		propx.add(null);
		propy.add(null);
		proportion();
	}
	
	public Point getLoc() {
		if(shapes.size() > 	0) {
			return this.getBounds().getLocation();
		}
		return null;
	}
	
	public void setLoc(Point loc) {
		Point corner = this.getLoc();
		this.translate(loc.x - corner.x, loc.y - corner.y);
	}
	
	public void proportion() {
		float moyx = 0;
		float moyy = 0;
		double maxx = shapes.get(0).getBounds().getCenterX();
		double minx = shapes.get(0).getBounds().getCenterX();
		double maxy = shapes.get(0).getBounds().getCenterY();
		double miny = shapes.get(0).getBounds().getCenterY();
		Iterator<Shape> it = iterator();
		while(it.hasNext()) {
			Rectangle rect = it.next().getBounds();
			moyx += rect.getCenterX();
			moyy += rect.getCenterY();
			if(rect.getCenterX() < minx) {
				minx = rect.getCenterX();
			}
			else if(rect.getCenterX() > maxx) {
				maxx = rect.getCenterX();
			}
			if(rect.getCenterY() < miny) {
				miny = rect.getCenterY();
			}
			else if(rect.getCenterY() > maxy) {
				maxy = rect.getCenterY();
			}
		}
		moyx /= propx.size();
		moyy /= propy.size();
		for(int i = 0; i < dxs.size(); i++) {
			Rectangle r = shapes.get(i).getBounds();
			if(maxx != minx) {
					propx.set(i, ((float) r.getCenterX() - moyx)/(float) (maxx-minx));
			}
			if(maxy != miny) {
				propy.set(i, ( (float)r.getCenterY() - moyy)/(float) (maxy-miny));
			}
		}
	}
	
	public void translate(int x, int y) {
		Iterator<Shape> it = this.iterator();
		while(it.hasNext()) {
			it.next().translate(x, y);
		}
	}
	
	public void zoom(int x, int y){
		Point startLoc = getLoc();
		for(int i = 0; i < dxs.size(); i++) {
			zxs.set(i, zxs.get(i) + ((float) x) * Math.abs(propx.get(i)));
			zys.set(i, zys.get(i) + ((float) y) * Math.abs(propy.get(i)));
			dxs.set(i, dxs.get(i) + ((float) x) * propx.get(i)/2);
			dys.set(i, dys.get(i) + ((float) y) * propy.get(i)/2);
		}
        Iterator<Shape> it = iterator();
        int i = 0;
        while(it.hasNext()){
        	Shape s = it.next();
            s.zoom((int) Math.floor(zxs.get(i)),(int) Math.floor(zys.get(i)));
            s.translate((int) Math.floor(dxs.get(i)),(int) Math.floor(dys.get(i)));
            zxs.set(i, zxs.get(i) - (int) Math.floor(zxs.get(i)));
            zys.set(i, zys.get(i) - (int) Math.floor(zys.get(i)));
            dxs.set(i, dxs.get(i) - (int) Math.floor(dxs.get(i)));
            dys.set(i, dys.get(i) - (int) Math.floor(dys.get(i)));
            i++;
        }
        Point endLoc = getLoc();
        translate(startLoc.x - endLoc.x, startLoc.y - endLoc.y);
    }
	
	public Rectangle getBounds() {
		if(shapes.size() > 0) {
			Iterator<Shape> it = this.iterator();
			Rectangle rect = it.next().getBounds();
			while(it.hasNext()) {
				rect = rect.union(it.next().getBounds());
			}
			return rect;
		}
		return null;
	}
	
	public void accept(ShapeVisitor visitor) {
		visitor.visitCollection(this);
	}
}
