package graphics.shapes.attributes;

public class MinesweeperAttributes extends Attributes{

    public static final String id="minesweeper";
    public boolean discovered;
    public boolean flagged;
    
    public MinesweeperAttributes(){
        flagged = false;
        discovered = false;
    }
    
    public MinesweeperAttributes clone() {
    	MinesweeperAttributes attributes =  new MinesweeperAttributes();
    	attributes.flagged = flagged;
    	attributes.discovered = discovered;
    	return attributes;
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