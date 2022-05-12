package graphics.shapes;

import java.awt.Point;
import java.awt.Rectangle;



public class SOval extends Shape {

    private int radius1;
    private int radius2;
    private float realRadius1;
    private float realRadius2;
    private Point loc;

    public SOval(Point loc, int radius1, int radius2){
        this.loc = loc;
        this.radius1 = radius1;
        this.radius2 = radius2;
        realRadius1 = radius1;
        realRadius2 = radius2;
    }

    public int getRadius1(){
        return radius1;
    }

    public int getRadius2(){
        return radius2;
    }

    public void setRadius(int radius1, int radius2){
        this.radius1 = radius1;
        this.radius2 = radius2;
    }

    public Point getLoc(){
        return loc;
    }

    public void setLoc(Point point){
        loc = point;
    }

    public void translate(int x, int y){
        loc.translate(x,y);
    }

    public void zoom(int x, int y) {
        realRadius1 += ((float) x)/2;
        realRadius2 += ((float) y)/2;
        radius1 = (int) realRadius1;
        radius2 = (int) realRadius2;
    }

    public Rectangle getBounds(){
        return new Rectangle(this.loc.x, this.loc.y, 2*radius1, 2*radius2);
    }

    public void accept(ShapeVisitor visitor){
        visitor.visitOval(this);
    }
}