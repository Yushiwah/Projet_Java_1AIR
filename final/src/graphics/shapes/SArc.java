package graphics.shapes;

import java.awt.*;

public class SArc extends Shape{

    private Point loc;
    private int width;
    private int height;
    private int startAngle;
    private int arcAngle;


    public SArc(Point p, int width, int height, int startAngle, int arcAngle){
        this.loc = p;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
    }

    public void translate(int x, int y){
        this.getLoc().x += x;
        this.getLoc().y += y;
    }

    public void zoom(int x, int y){
        width += x;
        height += y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getStartAngle(){
        return startAngle;
    }

    public int getArcAngle(){
        return arcAngle;
    }

    public Point getLoc(){
        return loc;
    }

    public void setLoc(Point point){
        this.loc = point;
    }


    public Rectangle getBounds(){
        return new Rectangle(this.getLoc().x, this.getLoc().y, this.getWidth(), this.getHeight());
    }

    public void accept(ShapeVisitor visitor){
        visitor.visitArc(this);
    }

}