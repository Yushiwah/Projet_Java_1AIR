package graphics.shapes.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import graphics.shapes.SImage;
import graphics.shapes.Shape;
import graphics.shapes.SMinesweeper;
import graphics.shapes.attributes.MinesweeperAttributes;
import graphics.ui.Controller;

public class MinesweeperController extends Controller{

	public MinesweeperController(Object newModel) {
		super(newModel);
	}
	
	public MinesweeperAttributes getMinesweeperAttributes(Shape shape) {
		MinesweeperAttributes attribute = (MinesweeperAttributes) shape.getAttributes(MinesweeperAttributes.id);
        if(attribute == null) {
            attribute = new MinesweeperAttributes();
            shape.addAttributes(attribute);
        }
        return attribute;
	}

    public void mouseClicked(MouseEvent e) {
    	if(!((SMinesweeper) this.getModel()).getLose() && !((SMinesweeper) this.getModel()).getWin()) {
	    	SImage shape = this.getTarget(e.getPoint());
	    	if(shape != null && !this.getMinesweeperAttributes(shape).isDiscovered()) {
		    	if(e.getButton() == MouseEvent.BUTTON3) {
		    		((SMinesweeper)this.getModel()).toggleFlagged(shape);
		    	}
		    	else if(e.getButton() == MouseEvent.BUTTON1) {
		    		if(!this.getMinesweeperAttributes(shape).isFlagged()) {
		    			if(!((SMinesweeper) this.getModel()).findZero(shape)) {
		    				this.getMinesweeperAttributes(shape).toggleDiscovered();
		    			}
		    		}
		    	}
		    	((SMinesweeper)this.getModel()).win();
	    	}
	    	this.getView().repaint();
    	}
    }

	public SImage getTarget(Point point) {
		Iterator<ArrayList<SImage>> it = ((SMinesweeper) this.getModel()).iterator();
		SImage image;
		Iterator<SImage> arrayIn;
		while(it.hasNext()) {
			arrayIn = it.next().iterator();
			while(arrayIn.hasNext()) {
				image = arrayIn.next();
				if(image.getBounds().contains(point)) {
					return image;
				}
			}
		}
		return null;
	}

}