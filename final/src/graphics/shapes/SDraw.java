package graphics.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SDraw extends Shape {

    private ArrayList<Point> points;

    public SDraw(Point p1) {
        this.points = new ArrayList<Point>();
        points.add(p1);
    }

    public void addPoint(Point p1) {
        points.add(p1);
    }

    public Iterator<Point> iterator() {
        return this.points.iterator();
    }

    public void translate(int x, int y) {
        Iterator<Point> it = points.iterator();
        while (it.hasNext()) {
            it.next().translate(x, y);
        }
    }

    public Point getLoc(){
        return getBounds().getLocation();
    }

    public void setLoc(Point loc){
        Point point = getLoc();
        this.translate(loc.x - point.x, loc.y - point.y);
    }

    public void zoom(int x, int y) {

    }

    public Rectangle getBounds() {
        Iterator<Point> it = points.iterator();
        Point p1 = it.next();
        Rectangle bound = new Rectangle(p1.x, p1.y);
        Point p2;
        while (it.hasNext()) {
            p2 = it.next();
            Rectangle r = new Rectangle(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
            bound = bound.union(r);
            p1 = p2;
        }
        return bound;
    }

    public void accept(ShapeVisitor visitor){
        visitor.visitDraw(this);
    }
}