package graphics.shapes.ui;

import java.awt.event.KeyEvent;

import graphics.shapes.SField;
import graphics.shapes.thread.SnakeThread;
import graphics.ui.Controller;

public class SnakeController extends Controller {
	private boolean first = true;
	private int direction;
	
	public SnakeController(Object model) {
		super(model);
	}
	
	public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() >= 37 && evt.getKeyCode() <= 40){
        	if(first) {
        		direction = evt.getKeyCode() - 37;
        		first = false;
        		Thread t = new SnakeThread(this, ((SField) this.getModel()).getSnake());
        		t.start();
        	}
        	else {
        		if(direction+2 != evt.getKeyCode() - 37 && direction-2 != evt.getKeyCode() - 37) {
        			direction = evt.getKeyCode() - 37;
        		}
        	}
        }
    }
	
	public int getDirection() {
		return direction;
	}
}
