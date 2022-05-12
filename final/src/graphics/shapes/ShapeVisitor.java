package graphics.shapes;

public interface ShapeVisitor {
	public void visitRectangle(SRectangle rect);
	public void visitCircle(SCircle circle);
	public void visitPolygon(SPolygon polygon);
	public void visitText(SText text);
	public void visitImage(SImage image);
	public void visitGif(SGif gif);
	public void visitCollection(SCollection collection);
	public void visitMinesweeper(SMinesweeper minesweeper);
	public void visitSnake(SSnake snake);
	public void visitField(SField field);
	public void visitDraw(SDraw draw);
	public void visitLine(SLine line);
	public void visitOval(SOval oval);
	public void visitArc(SArc arc);
	public void visitGol (SGol gol);
}