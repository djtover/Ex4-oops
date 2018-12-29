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
	private BufferedImage myImage;
	private boolean isPlayer;
	private boolean playerAdded;
	private Play play1;
	private Player player1;
	private boolean isRun;
	private int x = -1;
	private int y = -1;
	private ArrayList<Packman> pointsPack = new ArrayList<Packman>();
	private ArrayList<Fruit> pointsFruit = new ArrayList<Fruit>();
	private ArrayList<Block> pointsBlock = new ArrayList<Block>();
	private ArrayList<Ghost> pointsGhost = new ArrayList<Ghost>();
	private static int c = 1;
	private boolean isResized;


	/**
	 * This is the constructor for the class
	 * @param imageName input the path of the image that you would like to put in the GUI
	 */
	public MainWindow(String imageName) 
	{
		this.addComponentListener(this);
		isRun = false;
		isResized = false;
		isPlayer = false;
		playerAdded = false;
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
		MenuItem RunMenu = new MenuItem("Run");
		MenuItem ClearMenu = new MenuItem("Clear");
		Menu menu2 = new Menu("File");
		MenuItem fromCsv = new MenuItem ("Import CSV File");



		//		this is a listener if the Packman button was clicked

		PlayerMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				isPlayer = true;
				isRun=false;
				isResized = false;
				playerAdded = false;

			}
		});

		//		this is a listener if the Run button was clicked

		RunMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//				run();

				isRun =true;
				isResized = false;
				isPlayer = false;

				repaint();
			}
		});
		//		this is a listener if the Clear button was clicked

		ClearMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//				run();
				isRun =false;
				isResized = false;
				isPlayer = false;
				playerAdded = false;

				pointsPack.clear();
				pointsFruit.clear();
				pointsBlock.clear();
				repaint();
			}
		});
		//		this is a listener if the From Csv button was clicked

		fromCsv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				isRun =false;
				isResized = false;
				isPlayer = false;
				playerAdded = false;

				pointsPack.clear();
				pointsFruit.clear();
				pointsBlock.clear();
				repaint();



				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					if (selectedFile.getAbsoluteFile().toString().endsWith(".csv")) {
						play1 = new Play(selectedFile.getAbsoluteFile().toString()); 
						FromBoard board = new FromBoard(play1.getBoard());

						updateBoard(board);


						//						isRun = false;
						//						isResized = false;
						//						isPlayer = false;
						//						playerAdd


						repaint();
					}	
					else {
						System.out.println("This is not a csv file");
					}

				}
			}
		});




		menuBar.add(menu1);
		menuBar.add(menu2);
		menu2.add(fromCsv);
		//		menu2.add(toCsv);
		//		menu2.add(toKml);
		menu1.add(PlayerMenu);
		//		menu1.add(FruitMenu);
		menu1.add(RunMenu);
		menu1.add(ClearMenu);
		this.setResizable(true);
		this.setMenuBar(menuBar);

		try {
			myImage = ImageIO.read(new File(imageName));

		} catch (IOException e) {
			e.printStackTrace();
		}		

	}
	/**
	 * This is a method thats paints on the image that will put Packmans and Fruits on the image and also paints the paths of the Packmen
	 */
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
				System.out.println(point);
				play1.setInitLocation(newPoint.x(), newPoint.y());
				player1.setP(newPoint);
				playerAdded = true;
				System.out.println(player1);
			}

		}
		if(playerAdded) {
			int ra = 25;
			Point3D drawPlayer =  m.Coords2Pixels(player1.getP());
			x = drawPlayer.ix() - (ra/2);
			y = drawPlayer.iy() - (ra/2);
			g.setColor(Color.BLUE);
			g.fillOval(x, y, ra, ra);
		}
		//		add all the Packmen
		for(int i = 0 ; i<pointsPack.size();i++) {
			int r = 30;
			Point3D pointDraw =  m.Coords2Pixels(pointsPack.get(i).getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);
			g.setColor(Color.YELLOW);
			g.fillOval(px, py, r, r);
		}
		//		add all the Fruit
		for(int i = 0 ; i<pointsFruit.size();i++) {
			int r = 10;
			Point3D pointDraw =  m.Coords2Pixels(pointsFruit.get(i).getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);
			g.setColor(Color.MAGENTA);
			g.fillOval(px, py, r, r);

		}
		for( int i=0; i<pointsBlock.size();i++) {
			Point3D p1Pix = m.Coords2Pixels(pointsBlock.get(i).getBL());
			Point3D p2Pix = m.Coords2Pixels(pointsBlock.get(i).getTR());
			int dx = Math.abs(p2Pix.ix() - p1Pix.ix());
			int dy = Math.abs(p2Pix.iy() - p1Pix.iy());
			g.setColor(Color.BLACK);
			g.fillRect(p1Pix.ix(),p2Pix.iy(),dx,dy);
		}

		for(int i = 0 ; i<pointsGhost.size();i++) {
			int r = 15;
			Point3D pointDraw =  m.Coords2Pixels(pointsGhost.get(i).getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);
			g.setColor(Color.RED);
			g.fillOval(px, py, r, r);

		}

	}


	@Override
	public void mouseClicked(MouseEvent arg) {
		//				System.out.println("mouse Clicked");

		x = arg.getX();
		y = arg.getY();
		//		System.out.println(x+","+y);
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
	private void updateBoard(FromBoard board) {
		for(int i=0;i<board.getALP().size();i++) {
			pointsPack.add(board.getALP().get(i));
		}
		for(int i=0;i<board.getALF().size();i++) {
			pointsFruit.add(board.getALF().get(i));
		}
		for(int i=0; i<board.getALB().size();i++) {
			pointsBlock.add(board.getALB().get(i));
		}
		for(int i=0; i<board.getALG().size();i++) {
			pointsGhost.add(board.getALG().get(i));
		}
		player1 = board.getM();
	}

}
