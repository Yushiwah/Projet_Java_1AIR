package graphics.shapes.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.*;

public class ShapeDraftman implements ShapeVisitor {
	public final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	public final FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
	public final MinesweeperAttributes MINESWEEPERATTRIBUTES = new MinesweeperAttributes();
	public final SelectionAttributes DEFAULTSELECTIONATTRIBUTES = new SelectionAttributes();
	public final static int HANDLER_SIZE = 5;
	private Graphics graphics;
	
	public ShapeDraftman(Graphics graphics) {
		this.graphics = graphics;
	}
	
	public void drawHandler(Rectangle rect) {
		graphics.setColor(Color.WHITE);
		graphics.fillRect(rect.x-HANDLER_SIZE, rect.y-HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);
		graphics.fillRect(rect.x + rect.width, rect.y + rect.height, HANDLER_SIZE, HANDLER_SIZE);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(rect.x-HANDLER_SIZE, rect.y-HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);
		graphics.drawRect(rect.x + rect.width, rect.y + rect.height, HANDLER_SIZE, HANDLER_SIZE);
	}
	
	public ColorAttributes getColorAttributes(Shape s) {
		ColorAttributes attribute = (ColorAttributes) s.getAttributes(ColorAttributes.id);
		if(attribute == null) {
			attribute = DEFAULTCOLORATTRIBUTES;
			s.addAttributes(attribute.clone());
		}
		return attribute;
	}
	
	public FontAttributes getFontAttributes(Shape s) {
		FontAttributes attribute = (FontAttributes) s.getAttributes(FontAttributes.id);
		if(attribute == null) {
			attribute = DEFAULTFONTATTRIBUTES;
			s.addAttributes(attribute.clone());
		}
		return attribute;
	}
	
	public MinesweeperAttributes getMinesweeperAttributes(Shape s) {
		MinesweeperAttributes attribute = (MinesweeperAttributes) s.getAttributes(MinesweeperAttributes.id);
		if(attribute == null) {
			attribute = MINESWEEPERATTRIBUTES;
			s.addAttributes(attribute.clone());
		}
		return attribute;
	}
	
	public SelectionAttributes getSelectionAttributes(Shape s) {
		SelectionAttributes attribute = (SelectionAttributes) s.getAttributes(SelectionAttributes.id);
		if(attribute == null) {
			attribute = DEFAULTSELECTIONATTRIBUTES;
			s.addAttributes(attribute.clone());
		}
		return attribute;
	}
	
	public void visitRectangle(SRectangle rect) {
		Rectangle r = rect.getRect();
		ColorAttributes color = this.getColorAttributes(rect);
		if(color.filled) {
			graphics.setColor(color.filledColor);
			graphics.fillRect(r.x, r.y, r.width, r.height);
		}
		if(color.stroked) {
			graphics.setColor(color.strokedColor);
			graphics.drawRect(r.x, r.y, r.width, r.height);
		}
		if(this.getSelectionAttributes(rect).isSelected()) {
			drawHandler(r);
		}
	}


	public void visitCircle(SCircle circle) {
		Point loc = circle.getLoc();
		int r = circle.getRadius();
		ColorAttributes color = this.getColorAttributes(circle);
		if(color.filled) {
			graphics.setColor(color.filledColor);
			graphics.fillOval(loc.x, loc.y, 2*r, 2*r);
		}
		if(color.stroked) {
			graphics.setColor(color.strokedColor);
			graphics.drawOval(loc.x, loc.y, 2*r, 2*r);
		}
		if(this.getSelectionAttributes(circle).isSelected()) {
			drawHandler(circle.getBounds());
		}
	}
	
	public void visitOval(SOval oval) {
	    Point loc = oval.getLoc();
	    int r1 = oval.getRadius1();
	    int r2 = oval.getRadius2();
	    ColorAttributes colorAttributes = getColorAttributes(oval);
	
	    if (colorAttributes.stroked) {
	        graphics.setColor(colorAttributes.strokedColor);
	        graphics.drawOval(loc.x, loc.y, 2 * r1, 2 * r2);
	    }
	    if (colorAttributes.filled) {
	        graphics.setColor(colorAttributes.filledColor);
	        graphics.fillOval(loc.x, loc.y, 2*r1, 2*r2);
	    }
	    
	    if(getSelectionAttributes(oval).isSelected()) {
	        drawHandler(oval.getBounds());
	    }
	}
	
	public void visitArc(SArc arc) {
        ColorAttributes colorAttributes = this.getColorAttributes(arc);
        if (colorAttributes.stroked) {
            graphics.setColor(colorAttributes.strokedColor);
            graphics.drawArc(arc.getLoc().x, arc.getLoc().y, arc.getWidth(), arc.getHeight(), arc.getStartAngle(),arc.getArcAngle());
        }
        if (colorAttributes.filled) {
            graphics.setColor(colorAttributes.filledColor);
            graphics.fillArc(arc.getLoc().x, arc.getLoc().y, arc.getWidth(), arc.getHeight(), arc.getStartAngle(),arc.getArcAngle());
        }
        if(this.getSelectionAttributes(arc).isSelected()) {
            drawHandler(arc.getBounds());
        }
    }
		
	public void visitPolygon(SPolygon polygon) {
		Rectangle rect = polygon.getBounds();
		ColorAttributes color = this.getColorAttributes(polygon);
		Polygon poly = polygon.getPolygon();
		if(color.filled) {
			graphics.setColor(color.filledColor);
			graphics.fillPolygon(poly.xpoints, poly.ypoints, poly.npoints);
		}
		if(color.stroked) {
			graphics.setColor(color.strokedColor);
			graphics.drawPolygon(poly.xpoints, poly.ypoints, poly.npoints);
		}
		if(this.getSelectionAttributes(polygon).isSelected()) {
			drawHandler(rect);
		}
	}
	
	public void visitText(SText text) {
		Point loc = text.getLoc();
		ColorAttributes color = this.getColorAttributes(text);
		FontAttributes font = this.getFontAttributes(text);
		Rectangle rect = text.getBounds();
		if(color.filled) {
			graphics.setColor(color.filledColor);
			graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
		if(color.stroked) {
			graphics.setColor(color.strokedColor);
			graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
		graphics.setFont(font.font);
		graphics.setColor(font.fontColor);
		graphics.drawString(text.getText(), loc.x, loc.y + text.getFontHeight() - 2);
		if(this.getSelectionAttributes(text).isSelected()) {
			drawHandler(rect);
		}
	}
	
	public void visitImage(SImage image) {
		Point loc = image.getLoc();
		Rectangle rect = image.getBounds();
		graphics.drawImage(image.getImage(), loc.x, loc.y, Math.max(0, image.getWidth()), Math.max(0, image.getHeight()), null);
		if(this.getSelectionAttributes(image).isSelected()) {
			drawHandler(rect);
		}
	}
    
    public void visitGif(SGif gif){
        Point loc = gif.getLoc();
        Rectangle rect = gif.getBounds();
        graphics.drawImage(gif.getGif(), loc.x, loc.y, Math.max(0, gif.getWidth()), Math.max(0, gif.getHeight()), null);
        if(this.getSelectionAttributes(gif).isSelected()){
        	drawHandler(rect);
        }
    }
    
    public void visitGol(SGol gol) {
		Iterator <SRectangle> it = gol.iterator();
		while (it.hasNext()) {
			it.next().accept(this);
		}
        if(getSelectionAttributes(gol).isSelected()){
            drawHandler(gol.getBounds());
        }
	}
	
	public void visitCollection(SCollection collection) {
		Iterator<Shape> it = collection.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
		if(this.getSelectionAttributes(collection).isSelected()) {
			drawHandler(collection.getBounds());
		}
	}
	
	public void visitMinesweeper(SMinesweeper minesweeper) {
        Iterator<ArrayList<SImage>> shapes = minesweeper.iterator();
        while(shapes.hasNext()) {
            Iterator<SImage> arrayIn = shapes.next().iterator();
            while(arrayIn.hasNext()) {
                SImage image = arrayIn.next();
                if(((MinesweeperAttributes) image.getAttributes(MinesweeperAttributes.id)).isDiscovered()) {
                	if(image.getName().equals("mine.gif")) {
                		minesweeper.lose();
                    }
                    else {
                        image.accept(this);
                    }                }
                else if(((MinesweeperAttributes) image.getAttributes(MinesweeperAttributes.id)).isFlagged()) {
                    (new SImage("drapeau.png", image.getLoc(), minesweeper.getWidth(), minesweeper.getHeight())).accept(this);
                }
                else {
                    Point loc = image.getLoc();
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(loc.x, loc.y, minesweeper.getWidth(), minesweeper.getHeight());
                }
            }
        }
        if(minesweeper.getWin() || minesweeper.getLose()) {
        	shapes = minesweeper.iterator();
            while(shapes.hasNext()) {
                Iterator<SImage> arrayIn = shapes.next().iterator();
                while(arrayIn.hasNext()) {
                	SImage image = arrayIn.next();
                	if(minesweeper.getLose()) {
	                	if(image.getName().equals("mine.gif") && !getMinesweeperAttributes(image).isFlagged()) {
	                		image.accept(this);
	                	}
	                	else if(!image.getName().equals("mine.gif") && getMinesweeperAttributes(image).isFlagged()) {
	                		SImage cross = new SImage("croix.png", image.getLoc(), minesweeper.getWidth(), minesweeper.getHeight());
	                		cross.accept(this);
	                	}
                	}
                	else {
                		if(image.getName().equals("mine.gif")) {
                			image.translate(0, -1);
                			SImage sadMine = new SImage("mine_triste.png", image.getLoc(), minesweeper.getWidth(), minesweeper.getHeight()+1);
	                		sadMine.accept(this);
	                		image.translate(0, 1);
                		}
                	}
                }
            }
        }
        Iterator<Shape> it = minesweeper.counterIterator();
        while(it.hasNext()) {
        	it.next().accept(this);
        }
        if(this.getSelectionAttributes(minesweeper).isSelected()){
            this.drawHandler(minesweeper.getBounds());
        }
    }

	public void visitSnake(SSnake snake) {
		Iterator<SImage> it = snake.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
		if(getSelectionAttributes(snake).isSelected()){
        	drawHandler(snake.getBounds());
        }
	}
	
	public void visitDraw(SDraw draw) {
        ColorAttributes colorAttributes = this.getColorAttributes(draw);
        SelectionAttributes selectionAttributes = this.getSelectionAttributes(draw);
        Iterator<Point> it = draw.iterator();
        Point p1 = it.next();
        Point p2;
        while(it.hasNext()){
            p2 = it.next();
            SLine line = new SLine(p1.x,p1.y,p2.x,p2.y);
            line.addAttributes(new ColorAttributes(false, true,Color.BLACK,colorAttributes.strokedColor));
            line.accept(this);
            p1 = p2;
        }

        if (selectionAttributes.isSelected()) {
            drawHandler(draw.getBounds());
        }
    }
	
	public void visitLine(SLine line) {
        Point p1 = line.getLoc();
        Point p2 = line.getLoc2();
        ColorAttributes colorAttributes = this.getColorAttributes(line);

        if (colorAttributes.stroked) {
            graphics.setColor(colorAttributes.strokedColor);
            graphics.drawLine(p1.x,p1.y, p2.x, p2.y);
        }

        if (colorAttributes.filled) {
            graphics.setColor(colorAttributes.strokedColor);
            graphics.drawLine(p1.x,p1.y, p2.x, p2.y);
        }

        if (this.getSelectionAttributes(line).isSelected()) {
            drawHandler(line.getBounds());	
        }
	}

	public void visitField(SField field) {
		
		Iterator<SRectangle> it = field.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
		Iterator<SImage> fruitIt = field.fruitsIterator();
		while(fruitIt.hasNext()) {
			fruitIt.next().accept(this);
		}
		
		Iterator<Shape> scoreIt = field.scoreIterator();
		while(scoreIt.hasNext()) {
			scoreIt.next().accept(this);
		}
		field.getSnake().accept(this);
		if(getSelectionAttributes(field).isSelected()){
        	drawHandler(field.getBounds());
        }
	}
}
