package graphics.shapes.ui;

import java.awt.Graphics;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	public boolean snake;
	public boolean minesweeper;
	
	public ShapesView(Object model, boolean snake, boolean minesweeper) {
		super(model, snake, minesweeper);
		this.snake = snake;
		this.minesweeper = minesweeper;
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Shape) this.getModel()).accept(new ShapeDraftman(graphics));
		if(!snake && !minesweeper) {
			this.repaint();
		}
	}

	public Controller defaultController(Object model, boolean snake, boolean minesweeper) {
		if(snake) {
			return new SnakeController(getModel());
		}
		if(minesweeper) {
			return new MinesweeperController(getModel());
		}
        return new ShapesController(getModel());
    }
}
