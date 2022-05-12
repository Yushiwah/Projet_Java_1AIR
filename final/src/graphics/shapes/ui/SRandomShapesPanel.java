package graphics.shapes.ui;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import graphics.shapes.Shape;

public class SRandomShapesPanel extends JPanel {
	
	private List<Shape> shapes = new LinkedList<Shape>();
	
	public void addShape(Shape s) {
		shapes.add(s);
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ShapeDraftman visitor = new ShapeDraftman(g);
		for (Shape s : shapes) {
			s.accept(visitor);
		}
	}
	
	

}
