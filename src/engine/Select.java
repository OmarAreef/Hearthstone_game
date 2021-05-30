package engine;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Select extends JFrame {
	

public Select() {
	super();
	this.setBounds (400,400,500,200);
	  this.setVisible(true);
	JLabel l = new JLabel ("PLEASE SELECT YOUR TARGET i.e a Minion or Hero ") ;
	this.add(l);
	
}
}
