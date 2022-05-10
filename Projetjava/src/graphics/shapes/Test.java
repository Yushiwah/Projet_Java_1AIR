package graphics.shapes;

import graphics.ui.Controller;

public class Test extends Thread {
	private Controller s;
	private SSnake snake;
	
	public Test(Controller s, SSnake snake) {
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
