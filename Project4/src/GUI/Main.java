package GUI;

import javax.swing.JFrame;


public class Main 
{
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow("Ariel1.png");
		window.setVisible(true);
		window.setSize(window.getMyImage().getWidth()-200,window.getMyImage().getHeight()+100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

