package engine;

import java.awt.Dimension;
import java.awt.Toolkit;

public class MyFrame2 extends MyFrame {

	public MyFrame2(String s) {
		super(s);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = screenSize.height ;
		int screenWidth = screenSize.width ; 
		this.setSize(new Dimension (screenWidth/4 , screenHeight/4));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
