package engine;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

public class selectHero extends JFrame implements ActionListener {
	private static Hero p1 ;
	private static Hero p2 ;
	ArrayList<JButton> heroes ;
	MyFrame text ;
	JButton h1 ; 
	JButton h2 ;
	ImageIcon im1;
	ImageIcon im2; 
	public selectHero() {
		super () ;
		heroes=new ArrayList<JButton>();
		this.setVisible(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		this.setBounds(screenWidth/4 , screenHeight/4 ,screenWidth/2 ,screenHeight/2);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("HERO SELECTION");
		
		JButton Hunter  = new JButton ("Rexaar");
		ImageIcon i = new ImageIcon ("Images/Rexxar.jpg");
		Image i1 = i.getImage().getScaledInstance(this.getWidth()/5,(int)(this.getHeight()/1.8),Image.SCALE_SMOOTH);
		i.setImage(i1);
		Hunter.setIcon(i);
		Hunter.setToolTipText("Hunter : REXAAR");
		
		JButton Mage = new JButton ("Jaina Proudmoore");
		ImageIcon i2 = new ImageIcon ("Images/Jainaa.jpg");
		Image i12 = i2.getImage().getScaledInstance(this.getWidth()/5,(int)(this.getHeight()/1.8),Image.SCALE_SMOOTH);
		i2.setImage(i12);
		Mage.setIcon(i2);
		Mage.setToolTipText("Mage :Jaina Proudmoore" );
		
		JButton Paladin  = new JButton ("Uther Lightbringer");
		ImageIcon i3 = new ImageIcon ("Images/Uther.jpg");
		Image i13 = i3.getImage().getScaledInstance(this.getWidth()/5,(int)(this.getHeight()/1.8),Image.SCALE_SMOOTH);
		i3.setImage(i13);
		Paladin.setIcon(i3);
		Paladin.setToolTipText("Paladin : Uther Lightbringer");
		
		JButton Priest = new JButton ("Anduin Wrynn");
		ImageIcon i4 = new ImageIcon ("Images/Anduin.jpg");
		Image i14 = i4.getImage().getScaledInstance(this.getWidth()/5,(int)(this.getHeight()/1.8),Image.SCALE_SMOOTH);
		i4.setImage(i14);
		Priest.setIcon(i4);
		Priest.setToolTipText("Priest : Anduin Wrynn");
		
		JButton Warlock  = new JButton ("Gul'dan");	
		ImageIcon i5 = new ImageIcon ("Images/GulDan.jpg");
		Image i15 = i5.getImage().getScaledInstance(this.getWidth()/5,(int)(this.getHeight()/1.8),Image.SCALE_DEFAULT);
		i5.setImage(i15);
		Warlock.setIcon(i5);
		Warlock.setToolTipText("Warlock : Gul'dan");
		
		heroes.add(Hunter);
		heroes.add(Mage);
		heroes.add(Paladin);
		heroes.add(Priest);
		heroes.add(Warlock);
		
		Hunter.addActionListener (this);
		Mage.addActionListener (this);
		Paladin.addActionListener (this);
		Priest.addActionListener (this);
		Warlock.addActionListener (this);
		
		

		
		
		
		this.setLayout(new GridLayout());
		this.add(Hunter);
		this.add(Mage);
		this.add(Paladin);
		this.add(Priest);
		this.add(Warlock);
		text = new MyFrame ("Select The First Hero ") ;
		text.revalidate();
		text.repaint();
		this.revalidate();
		this.repaint();
		
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton)e.getSource();
	
		int x = heroes.indexOf(b);
		if (p1 == null){ h1=b;
			switch (x) {
			case 0 : try {
					p1 = new Hunter();
					
				} catch (IOException | CloneNotSupportedException e1) {
					MyFrame f = new MyFrame (e1.getMessage());

				} break;
			case 1 : try {
					p1 = new Mage();
				} catch (IOException | CloneNotSupportedException e1) {
					MyFrame f = new MyFrame (e1.getMessage());

				}break;
			case 2 : try {
					p1 = new Paladin();
				} catch (IOException | CloneNotSupportedException e1) {
					MyFrame f = new MyFrame (e1.getMessage());

				}break;
			case 3 : try {
					p1 = new Priest();
				} catch (IOException | CloneNotSupportedException e1) {
					MyFrame f = new MyFrame (e1.getMessage());

				}break;
			case 4 : try {
					p1 = new Warlock();
				} catch (IOException | CloneNotSupportedException e1) {
					MyFrame f = new MyFrame (e1.getMessage());

				}break;
				
			}
			im1 = (ImageIcon) b.getIcon();
			text.dispose();
			text = new MyFrame ("Select The Second Hero ") ;}
			else {
				if (p1 != null && p2 == null){ h2=b;
					switch (x) {
					case 0 : try {
							p2 = new Hunter(); 	this.dispose();
						} catch (IOException | CloneNotSupportedException e1) {
							MyFrame f = new MyFrame (e1.getMessage());

						} break;
					case 1 : try {
							p2 = new Mage(); this.dispose();
						} catch (IOException | CloneNotSupportedException e1) {
							MyFrame f = new MyFrame (e1.getMessage());

						}break;
					case 2 : try {
							p2 = new Paladin(); this.dispose();
						} catch (IOException | CloneNotSupportedException e1) {
							MyFrame f = new MyFrame (e1.getMessage());

						}break;
					case 3 : try {
							p2= new Priest(); this.dispose();
						} catch (IOException | CloneNotSupportedException e1) {
							MyFrame f = new MyFrame (e1.getMessage());

						}break;
					case 4 : try {
							p2 = new Warlock(); this.dispose();
						} catch (IOException | CloneNotSupportedException e1) {
							MyFrame f = new MyFrame (e1.getMessage());
						}break;
						
					}
					im2 = (ImageIcon) b.getIcon();
					text.dispose();
					
				}
				
		}
		
	}
	public ArrayList<JButton> getHeroes() {
		return heroes;
	}



	public MyFrame getText() {
		return text;
	}



	public ImageIcon getIm1() {
		return im1;
	}



	public ImageIcon getIm2() {
		return im2;
	}



	public JButton getH1() {
		return h1;
	}



	public JButton getH2() {
		return h2;
	}



	public static void main (String[] args){
		selectHero h = new selectHero();
		
	}



	public static Hero getP1() {
		return p1;
	}



	public static Hero getP2() {
		return p2;
	}

	
}
