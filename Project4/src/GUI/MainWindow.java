package GUI;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.awt.Desktop;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import Algo.GameAlgo;
import FileFormat.FromBoard;
import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Game;
import MyGIS.Ghost;
import MyGIS.Map;
import MyGIS.Packman;
import MyGIS.Player;
import Robot.Play;
import Geom.Point3D;
import MyCoords.MyCoords;

/**
 * This class represents the GUI of the Packman game
 * @author David Tover
 *
 */
public class MainWindow extends JFrame implements MouseListener, ComponentListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -326640337938865967L;
	/**
	 * 
	 */
	private BufferedImage myImage;
	private boolean isPlayer;
	private boolean playerAdded;
	private Play play1;
	private boolean isRunUser;
	private boolean isRunCPU;
	private int x = -1;
	private int y = -1;
	private static int c = 1;
	private boolean isResized;
	private int runTime;
	private Game game = new Game();


	/**
	 * This is the constructor for the class
	 * @param imageName input the path of the image that you would like to put in the GUI
	 */
	public MainWindow(String imageName) 
	{
		this.addComponentListener(this);
		isRunUser = false;
		isRunCPU = false;
		isResized = false;
		isPlayer = false;
		playerAdded = false;
		runTime=0;
		initGUI(imageName);		
		this.addMouseListener(this);

	}

	public BufferedImage getMyImage() {
		return myImage;
	}
	/**
	 * This is a class that initiates the GUI
	 * @param imageName input the path of the image that you would like to put in the GUI
	 */
	private void initGUI(String imageName) 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("Menu"); 
		MenuItem PlayerMenu = new MenuItem("Player");
		MenuItem RunMenu = new MenuItem("Run Manual");
		MenuItem RunCPU = new MenuItem("Run Automatic");
		MenuItem ClearMenu = new MenuItem("Clear");
		Menu menu2 = new Menu("Examples");
		MenuItem example1 = new MenuItem("Example 1");
		MenuItem example2 = new MenuItem("Example 2");
		MenuItem example3 = new MenuItem("Example 3");
		MenuItem example4 = new MenuItem("Example 4");
		MenuItem example5 = new MenuItem("Example 5");
		MenuItem example6 = new MenuItem("Example 6");
		MenuItem example7 = new MenuItem("Example 7");
		MenuItem example8 = new MenuItem("Example 8");
		MenuItem example9 = new MenuItem("Example 9");



		/**
		 * This is a listener to see if the Player button was clicked
		 */

		PlayerMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(!game.getALF().isEmpty()) {
					isPlayer = true;
					isRunUser=false;
					isRunCPU = false;
					isResized = false;
					playerAdded = false;
				}
				else {
					System.out.println("Please enter game first");
				}

			}
		});

		/**
		 * This is a listener to see if the run in Manual Mode button was clicked
		 */

		RunMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//				run();

				isRunUser =true;
				isRunCPU = false; 
				isResized = false;
				isPlayer = false;
				if(playerAdded) {
					RunUser();
				}
				else {
					System.out.println("Please enter player first");
				}

			}
		});
		/**
		 * This is a listener to see if the run in Automatic Mode button was clicked
		 */
		RunCPU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//				run();
				//				isRunCPU = true;
				isRunUser =false;
				isResized = false;
				isPlayer = false;
				if(playerAdded) {
					//					GameAlgo ga = new GameAlgo(game);
					//					ga.RunAlgo(game.getPlayer().getP());
					RunCPU();
				}
				else {
					System.out.println("Please enter player first");
				}

			}
		});
		/**
		 * this is a listener to see if the Clear button was clicked
		 */

		ClearMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				repaint();
			}
		});
		/**
		 * This is a listener to see if example 1 was clicked
		 */
		example1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example1.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 2 was clicked
		 */
		example2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example2.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 3 was clicked
		 */
		example3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example3.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 4 was clicked
		 */
		example4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example4.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 5 was clicked
		 */
		example5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example5.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 6 was clicked
		 */
		example6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				play1 = new Play("data/Ex4_OOP_example6.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 7 was clicked
		 */
		example7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example7.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 8 was clicked
		 */
		example8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example8.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		/**
		 * This is a listener to see if example 9 was clicked
		 */
		example9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example9.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		menuBar.add(menu1);
		menuBar.add(menu2);
		menu2.add(example1);
		menu2.add(example2);
		menu2.add(example3);
		menu2.add(example4);
		menu2.add(example5);
		menu2.add(example6);
		menu2.add(example7);
		menu2.add(example8);
		menu2.add(example9);
		menu1.add(PlayerMenu);
		menu1.add(RunMenu);
		menu1.add(RunCPU);
		menu1.add(ClearMenu);
		setResizable(true);
		setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File(imageName));

		} catch (IOException e) {
			e.printStackTrace();
		}		

	}
	/**
	 * This is a method thats paints on the image that will put Packmen,Fruits,Ghost on the image 
	 */
	private static int index = 0;
	public void paint(Graphics g)
	{
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png");

		g.drawImage(myImage, 0, 0, getWidth()-8,getHeight()-8, this);

		if(x!=-1 && y!=-1 )
		{
			Point3D point = new Point3D(x,y);
			Point3D newPoint=new Point3D (m.Pixels2Coords(point, getWidth(), getHeight()));
			/**
			 * This is to check if the player button was clicked and if it was then set the location of the player
			 */
			if(isPlayer) {
				play1.setInitLocation(newPoint.x(), newPoint.y());
				game.getPlayer().setP(newPoint);
				playerAdded = true;
				System.out.println(game.getPlayer());
			}

		}
		if(isRunUser) {
			Point3D point = new Point3D(x,y);
			Point3D newPoint=new Point3D (m.Pixels2Coords(point, getWidth(), getHeight()));
			double angle = m.findAngle(game.getPlayer().getP(), newPoint);
			play1.rotate(angle);
		}
		else if(isRunCPU) {
			if(game.getPlayer().getPath().getAL().size()>0) {
				if(game.getPlayer().getPath().getAL().size()>0) {
				double angle = m.findAngle(game.getPlayer().getP(), game.getPlayer().getPath().getAL().get(0).getP());
				
				
				int k = game.GhostinArea(game.getPlayer().getP());
				if(k>0) {
					angle = m.findAngle(game.getPlayer().getP(), game.getALG().get(k).getP());
					angle = (angle +135)%360;
				}

				play1.rotate(angle);
				}
			}

		}
		drawBlocks(g);

		if(playerAdded) {
			drawPlayer(g);
			drawScore(g);
			drawTimeLeft(g);
		}

		drawPackmen(g);
		drawFruit(g);
		drawGhost(g);

	}
	/**
	 * This is a method to run the game in Manual Mode 
	 */
	private void RunUser() {
		play1.start();
		play1.rotate(0);
		DrawBoard db = new DrawBoard(this);
		Thread t = new Thread(db);
		t.start();


	}
	/**
	 * This is a method to run the game in Automatic Mode
	 */
	private void RunCPU() {
		GameAlgo ga = new GameAlgo(game);
		game =  ga.RunAlgo(game.getPlayer().getP());
		isRunCPU = true;
		play1.start();
		DrawBoard db = new DrawBoard(this);
		Thread t = new Thread(db);
		t.start();		
	}
	public void setRun(boolean isRunUser) {
		this.isRunUser = isRunUser;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public Play getPlay1() {
		return play1;
	}

	public ArrayList<Fruit> getPointsFruit() {
		return game.getALF();
	}

	public int getRunTime() {
		return runTime;
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		//				System.out.println("mouse Clicked");

		x = arg.getX();
		y = arg.getY();
		repaint();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//		System.out.println("mouse entered");

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		isPlayer = false;
		isResized = true;
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * This is a method to update everyone in the game where their location is
	 * @param board is the game that was read from the Play 
	 */
	public void updateBoard(FromBoard board) {
		clear();
		for(int i=0;i<board.getALP().size();i++) {
			game.getALP().add(board.getALP().get(i));
		}
		for(int i=0;i<board.getALF().size();i++) {
			game.getALF().add(board.getALF().get(i));
		}
		for(int i=0; i<board.getALB().size();i++) {
			game.getALB().add(board.getALB().get(i));
		}
		for(int i=0; i<board.getALG().size();i++) {
			game.getALG().add(board.getALG().get(i));
		}

		game.getPlayer().setP(board.getM().getP());
		if(isRunCPU ) {
			game.getPlayer().getPath().clear();
			GameAlgo ga = new GameAlgo(game);
			game = ga.RunAlgo(game.getPlayer().getP());
		}
	}
	/**
	 * This is a method to get the time that so far have passed
	 * @param stats is a String with the statistics of the game
	 * @return the time that has passed
	 */
	public double getTime(String stats) {
		String split = ",";
		String[] userInfo = stats.split(split);
		String ans = userInfo[1];
		ans=ans.substring(11);
		return Double.parseDouble(ans);
	}
	/**
	 * This is a method to get the score that the Player has at that moment
	 * @param stats is a String with the statistics of the game
	 * @return the score of the Player
	 */
	public double getScore(String stats) {
		String split = ",";
		String[] userInfo = stats.split(split);
		String ans = userInfo[2];
		ans=ans.substring(6);
		return Double.parseDouble(ans);
	}
	/**
	 * This is a method to get how much time left in the game
	 * @param stats is a String with the statistics of the game
	 * @return the amount of time left
	 */
	public double getTimeLeft(String stats) {
		String split = ",";
		String[] userInfo = stats.split(split);
		String ans = userInfo[3];
		ans=ans.substring(11);
		return Double.parseDouble(ans);
	}
	public void clear() {
		game.clear();

	}
	private void falseEverything() {
		isRunCPU = false;
		isRunUser =false;
		isResized = false;
		isPlayer = false;
		playerAdded = false;
	}

	/**
	 * This is a method to draw all the Blocks in the game
	 * @param g
	 */
	private void drawBlocks(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png");
		for(int i=0; i<game.getALB().size();i++) {
			game.getALB().get(i).drawBlock(g, m);
		}
	}
	/**
	 * This is a method to draw all the Packmen in the game
	 * @param g
	 */
	private void drawPackmen(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 
		for(int i = 0 ; i<game.getALP().size();i++) {
			game.getALP().get(i).drawPackman(g, m);
		}
	}
	/**
	 * This is a method to draw all the Fruits in the game
	 * @param g
	 */
	private void drawFruit(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 
		for(int i = 0 ; i<game.getALF().size();i++) {
			game.getALF().get(i).drawFruit(g, m);
		}
	}
	/**
	 * This is a method to draw all the Ghosts in the game
	 * @param g
	 */
	private void drawGhost(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 
		for(int i = 0 ; i<game.getALG().size();i++) {
			game.getALG().get(i).drawGhost(g, m);

		}
	}
	/**
	 * This is a method to draw the Player in the game
	 * @param g
	 */
	private void drawPlayer(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 
		game.getPlayer().drawPlayer(g, m);
	}
	/**
	 * This is a method to draw the score of the Player in the game
	 * @param g
	 */
	private void drawScore(Graphics g) {
		double score = getScore(play1.getStatistics());
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(25f));
		g.drawString("Score: "+ score,10,getHeight()-10);
		g.setColor(Color.BLACK);
		g.drawString("Score: "+ score,9,getHeight()-9);
		g.drawString("Score: "+ score,8,getHeight()-8);
	}
	/**
	 * This is a method to draw the time left in the game
	 * @param g
	 */
	private void drawTimeLeft(Graphics g) {
		double timeLeft = getTimeLeft(play1.getStatistics())/1000;
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(25f));
		g.drawString("Time Left: "+ timeLeft,10,getHeight()-35);
		g.setColor(Color.BLACK);
		g.drawString("Time Left: "+ timeLeft,9,getHeight()-34);
		g.drawString("Time Left: "+ timeLeft,8,getHeight()-33);
	}
}
