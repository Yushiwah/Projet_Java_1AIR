package graphics.shapes.ui;

import java.awt.Graphics;

import graphics.shapes.SCollection;
import graphics.shapes.SGol;
import graphics.shapes.Shape;
import graphics.ui.Controller;
import graphics.ui.View;

public class ShapesView extends View {
	public boolean snake;
	public boolean minesweeper;
	public boolean rick;
	int counter;
	
	public ShapesView(Object model, boolean snake, boolean minesweeper, boolean rick) {
		super(model, snake, minesweeper);
		this.snake = snake;
		this.minesweeper = minesweeper;
		this.rick = rick;
		counter = 0;
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		((Shape) this.getModel()).accept(new ShapeDraftman(graphics));
		if(!snake && !minesweeper) {
			counter++;
			if(!rick) {
				try {
		        	Thread.sleep(5);
		        }
		        catch (Exception e) {
		        	System.out.println(e);
		        }
				if(counter == 50) {
					((SGol) ((SCollection) this.getModel()).iterator().next()).nextGeneration();
					counter = 0;
				}
			}
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
