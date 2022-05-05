package graphics.shapes;

public interface ShapeVisitor {
	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitPolygon(SPolygon polygon);
	public void visitText(SText text);
	public void visitImage(SImage image);
	public void visitGif(SGif gif);
	public void visitCollection(SCollection collection);
	public void visitTable(STable table);
}