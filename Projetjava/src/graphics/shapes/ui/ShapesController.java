package graphics.shapes.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import graphics.shapes.SCollection;
import graphics.shapes.SField;
import graphics.shapes.Shape;
import graphics.shapes.Test;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

public class ShapesController extends Controller {
	private boolean canMove = false;
	private boolean canZoom = false;
	private Point lastLocation;
	public int direction;
	private boolean first = true;
	
	public ShapesController(Object model) {
		super(model);
	}
	
	public Shape getTarget() {
		Point mousePosition = getView().getMousePosition();
		Iterator<Shape> it = ((SCollection) getModel()).iterator();
		Shape s = null;
		while(it.hasNext()) {
			s = it.next();
			if(s.getBounds().contains(mousePosition)) {
				return s;
			}
		}
		return null;
	}
	
	public void unselectAll() {
		Iterator<Shape> it = ((SCollection) getModel()).iterator();
		while(it.hasNext()) {
			((SelectionAttributes)it.next().getAttributes(SelectionAttributes.id)).unselect();
		}
	}
	
	public void translateSelected(int x, int y) {
		Iterator<Shape> it = ((SCollection) getModel()).iterator();
		while(it.hasNext()) {
			Shape s = it.next();
			if(((SelectionAttributes) s.getAttributes(SelectionAttributes.id)).isSelected()) {
				s.translate(x, y);
			}
		}
	}
	
	public void zoomSelected(int x, int y) {
		Iterator<Shape> it = ((SCollection) getModel()).iterator();
		while(it.hasNext()) {
			Shape s = it.next();
			if(((SelectionAttributes) s.getAttributes(SelectionAttributes.id)).isSelected()) {
				s.zoom(x, y);
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		Shape target = getTarget();
		if(target != null && ((SelectionAttributes) target.getAttributes(SelectionAttributes.id)).isSelected()) {
			canMove = true;
			lastLocation = e.getPoint();
		}
		else {
			Point loc = e.getPoint();
			Iterator<Shape> it = ((SCollection) getModel()).iterator();
			Rectangle bound;
			while(it.hasNext()) {
				Shape s = it.next();
				bound = s.getBounds();
				Rectangle handler = new Rectangle(bound.x + bound.width, bound.y + bound.height, ShapeDraftman.HANDLER_SIZE, ShapeDraftman.HANDLER_SIZE);
				if(handler.contains(loc) && ((SelectionAttributes) s.getAttributes(SelectionAttributes.id)).isSelected()) {
					canZoom = true;
					lastLocation = e.getPoint();
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		Shape s = getTarget();
		if(!e.isShiftDown()) {
			unselectAll();
		}
		if(s != null) {
			((SelectionAttributes) s.getAttributes(SelectionAttributes.id)).toggleSelection();
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		canMove = false;
		canZoom = false;
	}
	
	public void mouseDragged(MouseEvent e) {
		if(canMove) {
			translateSelected(e.getX() - lastLocation.x, e.getY() - lastLocation.y);
		}
		if(canZoom) {
			zoomSelected(e.getX() - lastLocation.x, e.getY() - lastLocation.y);
		}
		lastLocation = e.getPoint();
	}
	
	public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40){
        	if(first) {
        		direction = evt.getKeyCode() - 37;
        		first = false;
        		Iterator<Shape> it = ((SCollection) this.getModel()).iterator();
        		Thread t = new Test(this, ((SField) it.next()).getSnake());
        		t.start();
        	}
        	else {
        		if(direction+2 != evt.getKeyCode() - 37 && direction-2 != evt.getKeyCode() - 37) {
        			direction = evt.getKeyCode() - 37;
        		}
        	}
        }
    }
	
	
	/*public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40){
        	if(first) {
        		direction = evt.getKeyCode() - 37;
        		first = false;
        		Thread t = new Test(this, ((SField) this.getModel()).getSnake());
        		t.start();
        	}
        	else {
        		if(direction+2 != evt.getKeyCode() - 37 && direction-2 != evt.getKeyCode() - 37) {
        			direction = evt.getKeyCode() - 37;
        		}
        	}
        }
    }*/
    
}
