package graphics.shapes.ui;

import java.awt.*;
import java.util.Iterator;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.*;

public class ShapeDraftman implements ShapeVisitor {
	public final ColorAttributes DEFAULTCOLORATTRIBUTES = new ColorAttributes();
	public final FontAttributes DEFAULTFONTATTRIBUTES = new FontAttributes();
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
	
	public void visitCollection(SCollection collection) {
		Iterator<Shape> it = collection.iterator();
		while(it.hasNext()) {
			it.next().accept(this);
		}
		if(this.getSelectionAttributes(collection).isSelected()) {
			drawHandler(collection.getBounds());
		}
	}
	
	/*public void visitTable(STable table) {
        Iterator<ArrayList<SRectangle>> shapes = table.iterator();
        while(shapes.hasNext()) {
            Iterator<SRectangle> arrayIn = shapes.next().iterator();
            while(arrayIn.hasNext()) {
                arrayIn.next().accept(this);
            }
        }
        if(this.getSelectionAttributes(table).isSelected()){
            drawHandler(table.getBounds());
        }
    }*/
}
