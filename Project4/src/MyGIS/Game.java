package MyGIS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//import FileFormat.FromCsv;
import Geom.Point3D;
import MyCoords.MyCoords;
/**
 * This is class that represents the game
 * Field 1: ALF is an ArrayList that hold all of the Fruit in the Game
 * Field 2: ALP is an ArrayList that hold all of the Packman in the Game
 * Field 3: time is the amount of time it takes for all the Fruit to be eaten by the Packmen
 * @author djtov
 *
 */
public class Game {

	private ArrayList<Fruit> ALF;
	private ArrayList<Packman> ALP;
	private ArrayList<Ghost> ALG;
	private ArrayList<Block> ALB;
	private Player player;
	private double time;
	private String myImage;
	public Game() {
		this.ALF = new ArrayList<Fruit>();
		this.ALP = new ArrayList<Packman>();
		this.ALG = new ArrayList<Ghost>();
		this.ALB = new ArrayList<Block>();
		this.player = new Player();
		time = 0;
		
	}
	public Game(Game g) {
		this.ALF = g.getALF();
		this.ALP = g.getALP();
		this.ALB = g.getALB();
		this.ALG = g.getALG();
		player = g.getPlayer();
		myImage = g.getMyImage();
	}
	public void clear() {
		ALF.clear();
		ALP.clear();
		ALG.clear();
		ALB.clear();
	}
	public String getMyImage() {
		return myImage;
	}
	public ArrayList<Ghost> getALG() {
		return ALG;
	}
	public ArrayList<Block> getALB() {
		return ALB;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * This is a method to check if there is a ghost in the area
	 * @param gps1 is the point you want to see if there is a ghost near it
	 * @return the index of the ghost in the Game ArrayList of Ghosts and if it doesn't exist then return -1;
	 */
	public int GhostinArea(Point3D gps1) {
		MyCoords mc = new MyCoords();
		for(int i=0; i<this.ALG.size();i++) {
			double dist = mc.distance3d(gps1, this.getALG().get(i).getP());
			if(dist<4) {
				return i;
			}
		}
		return -1;
	}
	
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

	public ArrayList<Fruit> getALF() {
		return ALF;
	}
	public ArrayList<Packman> getALP() {
		return ALP;
	}
	public boolean addFruit(Fruit f) {
		return ALF.add(f);
	}
	public boolean addPackman(Packman p) {
		return ALP.add(p);
	}
	public boolean addBlock(Block b) {
		return ALB.add(b);
	}
	public Iterator<Fruit> iteratorFruit(){
		return ALF.iterator();
	}
	public Iterator<Packman> iteratorPackman(){
		return ALP.iterator();
	}
   public String toString() {
	   String s = "";
	   s+= player.toString()+"\n";
	   for(int i=0; i<ALF.size();i++) {
		   s=s+ (ALF.get(i).toString());
	   }
	   s+="\n";
	   for(int i=0; i<ALP.size();i++) {
		   s=s+ (ALP.get(i).toString());
	   }
	   s+= " Time: "+ time;
	   return s;
   }
}
