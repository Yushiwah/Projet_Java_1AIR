package graphics.shapes.ui;

import java.awt.Graphics;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	public boolean snake;
	
	public ShapesView(Object model, boolean snake) {
		super(model);
		this.snake = snake;
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Shape) this.getModel()).accept(new ShapeDraftman(graphics));
		if(!snake) {
			this.repaint();
		}
	}

	public Controller defaultController(Object model){
        return new ShapesController(this.getModel());
    }
}
