package graphics.ui;

import javax.swing.JPanel;


public abstract class View extends JPanel {
	private Object model;
	private Controller controller;

	public View(Object model) {
		this.model = model;
		controller = defaultController(model);
		controller.setView(this);
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
		this.addKeyListener(controller);
	}
	
	public void setModel(Object model) {
		this.model = model;
		controller.setModel(model);
	}
	
	public Object getModel() {
		return model;
	}
	
	public Controller defaultController(Object model) {
		return new Controller(model);
	}
	
	final public Controller getController() {
		return controller;
	}
}


