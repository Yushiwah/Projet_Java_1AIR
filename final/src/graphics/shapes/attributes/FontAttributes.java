package graphics.shapes.attributes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;


public class FontAttributes extends Attributes {
	public static final String id="font";
	public Font font;
	public Color fontColor;
	public static final Graphics2D DEFAULT_GRAPHICS = (Graphics2D) new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB).getGraphics();

	
	public FontAttributes() {
		font = new Font("dialog", Font.PLAIN, 11);
		fontColor = new Color(0);
	}
	
	public void setSize(int size) {
		font = new Font(font.getFontName(), font.getStyle(), size);
	}
	
	public int getSize() {
		return font.getSize();
	}

	public FontAttributes clone() {
		FontAttributes font = new FontAttributes();
		font.font = this.font;
		font.fontColor = fontColor;
		return font;
	}
	
	public String getId() {
		return id;
	}
	
	public Rectangle getBounds(String s) {
		s += " ";
		FontRenderContext frc = DEFAULT_GRAPHICS.getFontRenderContext();
		return font.getStringBounds(s, frc).getBounds();
	}
}
