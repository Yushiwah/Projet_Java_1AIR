package graphics.shapes;

import graphics.shapes.ui.ShapesController;

public class Test extends Thread {
	private ShapesController s;
	private SSnake snake;
	
	public Test(ShapesController s, SSnake snake) {
		this.s = s;
		this.snake = snake;
	}
	
	public void run() {
		try {
            while (snake.move(s.direction)) {
                Thread.sleep(100);
                s.getView().repaint();
            }
        }
        catch (Exception e) {
           
            System.out.println(e);
        }
	}
}
