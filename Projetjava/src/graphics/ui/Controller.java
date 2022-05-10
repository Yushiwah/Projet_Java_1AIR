package graphics.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {
	private Object model;
	private View view;	
	public int direction;

	
	public Controller(Object model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	final public View getView() {
		return view;
	}
	
	public void setModel(Object model) {
		this.model = model;
	}
	
	public Object getModel() {
		return model;
	}
	
	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	public void mouseMoved(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	}
	
	public void keyTyped(KeyEvent e) {
	}
	
	public void keyPressed(KeyEvent evt) {
	}

	public void keyReleased(KeyEvent e) {
	}
}
