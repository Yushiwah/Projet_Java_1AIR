package graphics.shapes;

import java.awt.*;

public class SLine extends Shape {

    private Point p1;
    private Point p2;

    public SLine(int x1, int y1, int x2, int y2){
        super();
        this.p1 = new Point(x1,y1);
        this.p2 = new Point(x2,y2);
    }

    public Point getLoc(){
        return new Point(p1);
    }

    public Point getLoc2(){
        return new Point(p2);
    }

    public void setLoc(Point point){
        this.getLoc().x = point.x;
        this.getLoc().y = point.y;
    }

    public void setLoc2(Point point){
        this.getLoc2().x = point.x;
        this.getLoc2().y = point.y;
    }

    public void translate(int x, int y){
        p1.translate(x,y);
        p2.translate(x,y);
    }

    public void zoom(int x, int y){
        p2.x += x;
        p2.y += y;

    }

    public Rectangle getBounds(){
        return new Rectangle(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
    }

    public void accept(ShapeVisitor visitor){
        visitor.visitLine(this);
    }
}