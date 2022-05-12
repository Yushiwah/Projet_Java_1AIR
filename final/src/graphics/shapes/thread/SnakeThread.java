package graphics.shapes.thread;

import graphics.shapes.SSnake;
import graphics.shapes.ui.SnakeController;
import graphics.ui.Controller;

public class SnakeThread extends Thread {
	private Controller s;
	private SSnake snake;
	
	public SnakeThread(Controller s, SSnake snake) {
		this.s = s;
		this.snake = snake;
	}
	
	public void run() {
		try {
            while (snake.move(((SnakeController) s).getDirection(), s.getView())) {
                Thread.sleep(100);
            }
            Thread.sleep(100);
        }
        catch (Exception e) {
            System.out.println(e);
        }
		s.getView().repaint();
	}
}
