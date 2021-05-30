package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class view extends JFrame {
	private JPanel currField ;
	private JPanel OppField ;
	private JPanel CurrHand;
	private JPanel OppHand ;
	private JButton currHero ;
	private JButton OppHero ;
	private JButton end ;

	
public JButton getCurrHero() {
		return currHero;
	}

	public JButton getOppHero() {
		return OppHero;
	}

	public JButton getEnd() {
		return end;
	}

public JPanel getCurrField() {
		return currField;
	}

	public JPanel getOppField() {
		return OppField;
	}

	public JPanel getCurrHand() {
		return CurrHand;
	}

	public JPanel getOppHand() {
		return OppHand;
	}

public view (){
	
	super();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int screenHeight = screenSize.height ;
	int screenWidth = screenSize.width ; 
	this.setSize(screenWidth, screenHeight);
	screenHeight -= 90 ;
	screenWidth -= 160 ;
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("HEARTHSTONE");
	setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	currHero = new JButton();
	currHero.setLayout(new BorderLayout());
	currHero.setPreferredSize(new Dimension(screenWidth/11, screenHeight/5));
	
	OppHero = new JButton();
	OppHero.setLayout(new BorderLayout());
	OppHero.setPreferredSize(new Dimension (screenWidth/11, screenHeight/5));
	
	JPanel stuff = new JPanel ();
	stuff.setOpaque(false);
	stuff.setPreferredSize(new Dimension (screenWidth, screenHeight));
	stuff.setLayout(new GridLayout(0,1));
	

   JLabel background = new JLabel();
  ImageIcon i = new ImageIcon("Images/Background.jpg");
  Image i1 = i.getImage().getScaledInstance(screenWidth, screenHeight,Image.SCALE_DEFAULT);
  i.setImage(i1);
   
   background.setIcon(i);
	
   JPanel p= new JPanel() ;
	p.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	p.setLayout(new BorderLayout());
	//p.setBackground(Color.BLACK);
	OppHand = new JPanel();
	OppHand.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	p.add(OppHero , BorderLayout.EAST);
	p.add(OppHand,BorderLayout.CENTER);
	stuff.add(p);
	p.revalidate();
	p.repaint();
	
	
	OppField = new JPanel();
	OppField.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
   // OppField.setBackground(Color.BLUE);
	stuff.add(OppField); 
	
	JPanel x = new JPanel() ;
	x.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	x.setLayout(new BorderLayout());
	// x.setBackground(Color.RED);
	currField = new JPanel();
	currField.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	end = new JButton ("END TURN");
	
	x.add(end,BorderLayout.EAST);
	x.add(currField,BorderLayout.CENTER);
	end.setPreferredSize(new Dimension (screenWidth/12,screenHeight/40));
	stuff.add(x);
	
	stuff.add(currField);
	JPanel j = new JPanel() ;
	j.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	j.setLayout(new BorderLayout());
	
	CurrHand = new JPanel();
	CurrHand.setPreferredSize(new Dimension(screenWidth,screenHeight/4));
	j.add(currHero , BorderLayout.EAST);
	j.add(CurrHand,BorderLayout.CENTER);
	stuff.add(j);	
	stuff.setOpaque(false);
	//background.setOpaque(true);

	
	
	
	JLayeredPane layers = new JLayeredPane();
	
	       
	     //  layers.add(background, 1);
	       layers.add(stuff, 0);
	      layers.setVisible(true);	       // background.setSize(background.getPreferredSize());
	     
	             //  stuff.setBounds(0, 0,screenWidth,screenHeight);
    layers.revalidate();
    layers.repaint();
	this.getContentPane().add(stuff);
	this.revalidate();
	this.repaint();
	
	
}

public void setCurrHero(JButton currHero) {
	this.currHero = currHero;
}

public void setOppHero(JButton oppHero) {
	OppHero = oppHero;
}

public static void main (String [] args){
	view v =new view () ;
	

	
}}
