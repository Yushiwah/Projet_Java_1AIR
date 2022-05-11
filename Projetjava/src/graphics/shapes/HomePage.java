package graphics.shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import graphics.shapes.ui.Editor;
import graphics.shapes.ui.SRandomShapes;
import graphics.shapes.ui.SRandomShapesPanel; 



	public class HomePage extends JFrame implements ActionListener{
		
		private JButton[] games = new JButton[6];
		
		public HomePage(){
		  
		    this.setTitle("Homepage");
		    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		    this.setSize(500, 500);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setLayout(new GridLayout(2,3,10,10));
			
			//-------------- 1 Snake]  JButton
			games[0]  = new JButton("Snake");
			games[0].addActionListener(this);
			games[0] .setFocusable(false);
			//1.1 Style for the text in the JButton 
			games[0] .setFont(new Font("Helvetica", Font.BOLD, 25));
			games[0] .setForeground(Color.CYAN);
			games[0] .setHorizontalTextPosition(SwingConstants.CENTER);
			games[0] .setVerticalTextPosition(JButton.BOTTOM);
			games[0] .setIconTextGap(-110);
			//1.2 Image in the JButton
			ImageIcon icon = new ImageIcon("src/image/snake.jpeg");
			Image image = icon.getImage();
			Image modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[0] .setIcon(icon);
			this.add(games[0] );
			
			//-------------- 2 GameOfLife JButton
			games[2] = new JButton("Random Shapes");
			games[2] .setFocusable(false);
			games[2].addActionListener(this);
			games[2] .setBackground(Color.YELLOW);
			games[2].setFont(new Font("Helvetica", Font.BOLD, 25));
			this.add(games[2] );
			//2.1 Image in the JButton
			icon = new ImageIcon("src/image/randomshapes.png");
			
			image = icon.getImage();
			modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[2].setIcon(icon);
			games[2] .setIconTextGap(-50);
			games[2].setHorizontalTextPosition(SwingConstants.CENTER);
			
			//--------------- 3 Base Java Project JButton
			games[3] = new JButton("Java Project");
			icon = new ImageIcon("src/image/javaproject.png");
			image = icon.getImage();
			modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[3].setIcon(icon);
			games[3].addActionListener(this);
			games[3].setFocusable(false);
			games[3].setBackground(Color.WHITE);
			games[3].setFont(new Font("Helvetica", Font.BOLD, 25));
			games[3] .setIconTextGap(-50);
			games[3].setHorizontalTextPosition(SwingConstants.CENTER);
			this.add(games[3] );
			
			//--------------- 4 Minesweeper JButton
			games[4] = new JButton("Démineur");
			games[4].addActionListener(this);
			games[4].setFocusable(false);
			games[4].setBackground(Color.RED);
			this.add(games[4]);
			//4.1 Style for the text in the JButton 
			games[4].setFont(new Font("Helvetica", Font.BOLD, 25));
			games[4].setForeground(Color.orange);
			games[4].setHorizontalTextPosition(SwingConstants.CENTER);
			games[4].setVerticalTextPosition(JButton.BOTTOM);
			
			//4.2 Image in the JButton
			icon = new ImageIcon("src/image/demineur.png");
			image = icon.getImage();
			modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[4] .setIconTextGap(-50);
			games[4].setIcon(icon);
			
			//--------------- 5 Surprise JButton
			games[5] = new JButton("");
			games[5].setFocusable(false);
			games[5].addActionListener(this);
			games[5].setBackground(Color.BLUE);
			games[5].setForeground(Color.WHITE);
			this.add(games[5] );
			
			//5.1 Image in the JButton
			icon = new ImageIcon("src/image/surprise.jpg");
			image = icon.getImage();
			modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[5].setIcon(icon);
			
			//--------------- 6 Tic Tac Toe JButton
			games[1] = new JButton("Tic Tac Toe");
			games[1].setBackground(Color.LIGHT_GRAY);
			games[1].addActionListener(this);
			games[1].setFocusable(false);
			
			//6.1 Style for the text in the JButton 
			games[1].setFont(new Font("", Font.BOLD, 35));
			games[1].setForeground(Color.orange);
			games[1].setHorizontalTextPosition(SwingConstants.CENTER);
			games[1].setVerticalTextPosition(JButton.BOTTOM);
			games[1].setIconTextGap(-130);
			//6.2 Image in the JButton
			icon = new ImageIcon("src/image/ttt.png");
			image = icon.getImage();
			modified = image.getScaledInstance(450, 320, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(modified);
			games[1].setIcon(icon);
			
			this.add(games[1]);
			
			this.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==games[0]) {
				System.out.println("yo");
				this.dispose();
				Editor self = new Editor(true, false, false);
				self.pack();
				self.setVisible(true);
			}
			
			if (e.getSource()==games[1]) {
				this.dispose();
				new TicTacToe();
			}
			
			if (e.getSource()==games[2]) {
				this.dispose();
				SRandomShapesPanel p = new SRandomShapesPanel();
				new SRandomShapes(p);
			}
			
			if (e.getSource()==games[3]) {
				this.dispose();
				Editor self = new Editor(false, false, false);
				self.pack();
				self.setVisible(true);
			}
			
			if (e.getSource()==games[4]) {
				this.dispose();
				Editor self = new Editor(false, true, false);
				self.pack();
				self.setVisible(true);
			}
			
			if (e.getSource()==games[5]) {
				this.dispose();
				Editor self = new Editor(false, false, true);
				self.pack();
				self.setVisible(true);
			}
		}      
	}
