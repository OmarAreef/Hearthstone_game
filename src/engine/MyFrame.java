package engine;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MyFrame extends JFrame{
	public MyFrame(String s ){
		super();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height ;
		int screenWidth = screenSize.width ; 
		this.setBounds(screenWidth /4 , screenHeight/4, screenWidth / 3, screenHeight/3);
		//this.setSize(500,200);
		this.setVisible(true);
		float z = screenWidth / 80 ;
	  

		JTextArea l= new JTextArea(s);
		l.setFont(l.getFont().deriveFont(z));
		l.setOpaque(false);
		this.setLayout(new BorderLayout());
		l.setEditable(false);
		this.add(l,BorderLayout.CENTER);
	}

}
