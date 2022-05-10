package graphics.shapes.attributes;

public class MinesweeperAttributes extends Attributes{

    public static final String id="minesweeper";
    private boolean discovered;
    private boolean flagged;
    
    public MinesweeperAttributes(){
        flagged = false;
        discovered = false;
    }
    
    public boolean isFlagged() {
        return flagged;
    }
    
    public boolean isDiscovered() {
        return discovered;
    }
    
    public void toggleFlagged() {
    	flagged = !flagged;
    }

    public void toggleDiscovered() {
    	discovered = !discovered;
    }
    
    public String getId() {
        return id;
    }    
}