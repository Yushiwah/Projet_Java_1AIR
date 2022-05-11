package graphics.shapes;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.ui.TicTacToePanel;


public class TicTacToe extends MouseAdapter {
	

	private Random random = new Random();
	private ArrayList<Integer> state;

	private JOptionPane pane;
	private JFrame frame = new JFrame();
	private JPanel titlePanel = new JPanel();
	private TicTacToePanel rectPanel = new TicTacToePanel();
	private JLabel textfield = new JLabel();
	private ArrayList<SRectangle> field = new ArrayList<SRectangle>();
	
	boolean playerOne;
	
	public TicTacToe() {
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.setVisible(true);
		
		textfield.setBackground(Color.black);
		textfield.setForeground(Color.BLUE);
		textfield.setFont(new Font("Ink Free",Font.BOLD,75));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Tic-Tac-Toe");
		textfield.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0,0,800,100);
		
		rectPanel.setLayout(new GridLayout(3,3));
		rectPanel.addMouseListener(this);
		
		state = new ArrayList<Integer>();
		
		int spaceSize = 180;
		for(int i = 1; i <4 ; i++) {
			for(int j = 0; j < 3; j++) {
				SRectangle r = new SRectangle(new Point(i*spaceSize, j*spaceSize), spaceSize, spaceSize);
				r.addAttributes(new ColorAttributes(true,true,Color.white,Color.black));
				state.add(0);
				field.add(r);
			}
		}

		titlePanel.add(textfield);
		frame.add(titlePanel,BorderLayout.NORTH);
		rectPanel.addRectangle(field);
		frame.add(rectPanel);
		
		
		begin();
	}
	
	public void begin() {
		if(random.nextInt(2)==0) {
			playerOne=true;
			textfield.setText("BLUE turn");
		}
		else {
			playerOne=false;
			textfield.setText("RED turn");
		}
	}
	
	public void end() {

		int n = 0;
		for (int i = 0; i<9; i++) {
			if(state.get(i)!=0) {
				n++;
			}
		}
		if (n==9) {
			for(int j=0;j<9;j++) {
				field.get(j).addAttributes(new ColorAttributes(false,false,Color.blue,new JButton().getBackground()));;
			}
			textfield.setText("Noone wins");
			int message = JOptionPane.showConfirmDialog(pane, "Game Over. Noone wins. Would you like to play again?","Game over.",
					JOptionPane.YES_NO_OPTION);
			
			if(message == JOptionPane.YES_OPTION) resetGame();
			else System.exit(0);
		}
		
		for (int i = 0; i<3;i++) {
			if (
					(
					(state.get(3*i)==1)&&
					(state.get(1+ 3*i)==1)&&
					(state.get(2+ 3*i)==1) 
					)||
					(		
					(state.get(3*i)==2)&&
					(state.get(1+3*i)==2)&&
					(state.get(2+3*i)==2) 
					)
			)
			{
				winner(3*i,1+3*i,2+3*i);
			}
		}
		for (int i = 0; i<3;i++) {
			if (
				(
					(state.get(i)==1)&&
					(state.get(3+i)==1)&&
					(state.get(6+i)==1) 
					)|| 
					(
					(state.get(i)==2)&& 
					(state.get(3+i)==2)&&
					(state.get(6+i)==2)
					)
				)
			{
				winner(i,3+i,6+i);
			}
		}
		if(
			(
				(state.get(0)==1) &&
				(state.get(4)==1) &&
				(state.get(8)==1)
				) ||
				(state.get(0)==2) &&
				(state.get(4)==2) &&
				(state.get(8)==2)
			)
		{
			winner(0,4,8);
		}
		if(
			(
				(state.get(4)==1) &&
				(state.get(6)==1) &&
				(state.get(2)==1)
				) ||
				(state.get(4)==2) &&
				(state.get(6)==2) &&
				(state.get(2)==2)
			){
			winner(2,4,6);
		}
		

	}
	
	public void winner(int a, int b, int c) {

		field.get(a).addAttributes(new ColorAttributes(true,true,Color.green,new JButton().getBackground()));
		field.get(b).addAttributes(new ColorAttributes(true,true,Color.green,new JButton().getBackground()));
		field.get(c).addAttributes(new ColorAttributes(true,true,Color.green,new JButton().getBackground()));
		
		textfield.setForeground(Color.green);
		textfield.setText("You win");
		int message = JOptionPane.showConfirmDialog(pane, "You win !!!  Would you like to play again?","Game over.",
				JOptionPane.YES_NO_OPTION);
		
		if(message == JOptionPane.YES_OPTION) resetGame();
		else System.exit(0);
	}

	
	public void resetGame() {
		this.frame.dispose();
		new TicTacToe();
	}
	
	public boolean available (ArrayList<Integer> table, int i) {
		return (table.get(i)==0);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		Point mouse = new Point(e.getX(), e.getY());
		SRectangle box;
		Rectangle r;
		for (int i=0; i<9; i++) {
			box = field.get(i);
			r = field.get(i).getBounds();
			if (box.getBounds().contains(mouse) && available(state,i) ){
				if (playerOne) {
					rectPanel.addCroix(new Point(r.x,r.y));
					state.set(i,1);
					playerOne = !playerOne;
					textfield.setForeground(Color.red);
					textfield.setText("RED turn"); 
					field.set(i, box);
				}
				else {
					rectPanel.addCircle(new Point(r.x,r.y));				
					state.set(i,2);
					playerOne = !playerOne;
					textfield.setForeground(Color.blue);
					textfield.setText("BLUE turn");
					
				}
			}
		}
		
		rectPanel.addRectangle(field);
		
		end();
	}
}

