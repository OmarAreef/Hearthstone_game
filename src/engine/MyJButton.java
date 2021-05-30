package engine;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextArea;

import view.view;

public class MyJButton extends JButton {

	public MyJButton (String s , int x , int y  ){
        
		this.setLayout(new BorderLayout());
		
		JTextArea a = new JTextArea ();
		float z = (float) (x/12.3) ;
	    a.setFont(a.getFont().deriveFont(z));
		a.setPreferredSize(new Dimension (x,(int)(y/1.6)));
	    a.setEditable(false);
		a.setText(s);
		this.add(a,BorderLayout.NORTH);
		a.setOpaque(false);
		this.setPreferredSize(new Dimension (x,y));
	}

}
