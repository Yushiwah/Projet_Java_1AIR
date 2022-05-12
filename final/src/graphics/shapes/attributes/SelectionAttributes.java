package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {
	public static final String id="selection";
	private boolean selected;

	public SelectionAttributes() {
		selected = false;
	}
	
	public SelectionAttributes clone() {
		SelectionAttributes select = new SelectionAttributes();
		select.selected = this.selected;
		return select;
	}
	
	public String getId() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void select() {
		selected = true;
	}
	
	public void unselect() {
		selected = false;
	}
	
	public void toggleSelection() {
		selected = !selected;
	}
}
