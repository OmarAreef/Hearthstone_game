package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class view2 extends JFrame {
	
	public view2 (){
		super();
		this.setSize(1600, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel Back = new JLabel () ;
		Back.setIcon(new ImageIcon ("Images/Background.jpg"));
		Back.setOpaque(true);
		JPanel stuff = new JPanel ();
		stuff.setOpaque(false);
		stuff.setPreferredSize(new Dimension (1600,900));
	 JButton	currHero = new JButton();
		currHero.setPreferredSize(new Dimension(200, 225));
		stuff.add(currHero);
		
		JLayeredPane l = new JLayeredPane() ;
		l.setVisible(true);
		
		l.add(Back, 1);
		l.add(stuff,0);
		this.getContentPane().add(l);
		//this.getContentPane().add(stuff);
		  Back.setSize(Back.getPreferredSize());
	       
          stuff.setBounds(0, 0,1600, 900);
l.revalidate();
		
		l.repaint();
		this.revalidate();
		
		this.repaint();
	}

	public static void main(String[] args) {
view2 v = new view2();
	}

}
