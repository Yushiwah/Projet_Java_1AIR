package graphics.shapes.ui;

import java.awt.Graphics;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	public ShapesView(Object model) {
		super(model);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Shape) this.getModel()).accept(new ShapeDraftman(graphics));
		this.repaint();
	}

	public Controller defaultController(Object model){
        return new ShapesController(this.getModel());
    }
}
