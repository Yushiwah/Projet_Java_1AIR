package graphics.shapes.attributes;

import java.awt.Color;

public class ColorAttributes extends Attributes {
	public static final String id="color";
	public boolean filled;
	public boolean stroked;
	public Color filledColor;
	public Color strokedColor;
	
	public ColorAttributes() {
		this.filled = false;
		this.stroked = true;
		this.filledColor = Color.WHITE;
		this.strokedColor = Color.BLACK;
	}
	
	public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor) {
		this.filled = filled;
		this.stroked = stroked;
		this.filledColor = filledColor;
		this.strokedColor = strokedColor;
	}
	
	public ColorAttributes clone() {
		ColorAttributes color = new ColorAttributes();
		color.filled = filled;
		color.stroked = stroked;
		color.filledColor = filledColor;
		color.strokedColor = strokedColor;
		return color;
	}

	public String getId() {
		return id;
	}
}
