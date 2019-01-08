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
import FileFormat.FromCsv;
import FileFormat.ToCsv;
import FileFormat.ToKml;
import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Game;
import MyGIS.Ghost;
import MyGIS.Map;
import MyGIS.Packman;
import MyGIS.Player;
import MyGIS.ShortestPathAlgo;
import MyGIS.Solution;
import Robot.Play;
import Geom.Point3D;

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
	//	private Player player1;
	private boolean isRunUser;
	private boolean isRunCPU;
	private int x = -1;
	private int y = -1;
	//	private ArrayList<Packman> pointsPack = new ArrayList<Packman>();
	//	private ArrayList<Fruit> pointsFruit = new ArrayList<Fruit>();
	//	private ArrayList<Block> pointsBlock = new ArrayList<Block>();
	//	private ArrayList<Ghost> pointsGhost = new ArrayList<Ghost>();
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
		//		MenuItem fromCsv = new MenuItem ("Import CSV File");
		MenuItem example1 = new MenuItem("Example 1");
		MenuItem example2 = new MenuItem("Example 2");
		MenuItem example3 = new MenuItem("Example 3");
		MenuItem example4 = new MenuItem("Example 4");
		MenuItem example5 = new MenuItem("Example 5");
		MenuItem example6 = new MenuItem("Example 6");
		MenuItem example7 = new MenuItem("Example 7");
		MenuItem example8 = new MenuItem("Example 8");
		MenuItem example9 = new MenuItem("Example 9");



		//		this is a listener if the Packman button was clicked

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

		//		this is a listener if the Run button was clicked

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
		//		this is a listener if the Clear button was clicked

		ClearMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				repaint();
			}
		});

		example1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example1.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});

		example2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example2.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});

		example3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example3.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		example4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example4.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		example5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example5.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
		example6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example6.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});

		example6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				falseEverything();
				clear();
				//				repaint();
				play1 = new Play("data/Ex4_OOP_example6.csv");
				play1.setIDs(327339701);
				FromBoard board = new FromBoard(play1.getBoard());
				updateBoard(board);
				repaint();

			}
		});
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
	 * This is a method thats paints on the image that will put Packmans and Fruits on the image and also paints the paths of the Packmen
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
			//			if it is putting down packmen on the screen then make a new packman and save it to the arraylist of packmen
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
		if(isRunCPU) {
			if(game.getPlayer().getPath().getAL().size()>0) {
				double angle = m.findAngle(game.getPlayer().getP(), game.getPlayer().getPath().getAL().get(0).getP());
				
//				System.out.println(game.getPlayer().getP()+" is source ");
//				System.out.println(game.getPlayer().getPath().getAL().get(0).getP() +" is dest");
//				System.out.println(angle + " is angle");
				play1.rotate(angle);
			}
//			System.out.println(game.getPlayer().getPath().getAL().size() +" is Path size");
//			System.out.println(game.getALF().size() + " is Fruit size");

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

	private void RunUser() {
		//		Map m = new Map(getWidth(),getHeight(),"Ariel1.png");
		play1.start();
		play1.rotate(0);
		DrawBoard db = new DrawBoard(this);
		Thread t = new Thread(db);
		t.start();


	}
	private void RunCPU() {
		GameAlgo ga = new GameAlgo(game);
		game =  ga.RunAlgo(game.getPlayer().getP());
		isRunCPU = true;
		play1.start();
		//		play1.rotate(0);
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
	public double getTime(String stats) {
		String split = ",";
		String[] userInfo = stats.split(split);
		String ans = userInfo[1];
		ans=ans.substring(11);
		return Double.parseDouble(ans);
	}
	public double getScore(String stats) {
		String split = ",";
		String[] userInfo = stats.split(split);
		String ans = userInfo[2];
		ans=ans.substring(6);
		return Double.parseDouble(ans);
	}
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
	private void drawBlocks(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png");

		for(int i=0; i<game.getALB().size();i++) {
			Point3D p1Pix = m.Coords2Pixels(game.getALB().get(i).getBL());
			Point3D p2Pix = m.Coords2Pixels(game.getALB().get(i).getTR());
			int dx = Math.abs(p2Pix.ix() - p1Pix.ix());
			int dy = Math.abs(p2Pix.iy() - p1Pix.iy());
			g.setColor(Color.BLACK);
			g.fillRect(p1Pix.ix(),p2Pix.iy(),dx,dy);
		}
	}
	private void drawPackmen(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 

		for(int i = 0 ; i<game.getALP().size();i++) {
			int r = 30;
			Point3D pointDraw =  m.Coords2Pixels(game.getALP().get(i).getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);

			g.setColor(Color.YELLOW);
			g.fillOval(px, py, r, r);
		}
	}
	private void drawFruit(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 

		for(int i = 0 ; i<game.getALF().size();i++) {
			int r = 10;
			Point3D pointDraw =  m.Coords2Pixels(game.getALF().get(i).getP());
			int px = pointDraw.ix()-(r/2);
			int py = pointDraw.iy() - (r/2);

			g.setColor(Color.MAGENTA);
			g.fillOval(px, py, r, r);
		}
	}
	private void drawGhost(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 

		for(int i = 0 ; i<game.getALG().size();i++) {
			int r = 15;
			Point3D pointDraw =  m.Coords2Pixels(game.getALG().get(i).getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);
			g.setColor(Color.RED);
			g.fillOval(px, py, r, r);

		}
	}
	private void drawPlayer(Graphics g) {
		Map m = new Map(getWidth(),getHeight(),"Ariel1.png"); 
		int ra = 25;
		Point3D drawPlayer =  m.Coords2Pixels(game.getPlayer().getP());
		int dx = drawPlayer.ix() - (ra/2);
		int dy = drawPlayer.iy() - (ra/2);
		g.setColor(Color.BLUE);
		g.fillOval(dx, dy, ra, ra);
	}
	private void drawScore(Graphics g) {
		double score = getScore(play1.getStatistics());
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(25f));
		g.drawString("Score: "+ score,10,getHeight()-10);
		g.setColor(Color.BLACK);
		g.drawString("Score: "+ score,9,getHeight()-9);
		g.drawString("Score: "+ score,8,getHeight()-8);
	}
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
