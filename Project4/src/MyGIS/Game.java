package MyGIS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//import FileFormat.FromCsv;
import Geom.Point3D;
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
	private double time;
	public Game() {
		this.ALF = new ArrayList<Fruit>();
		this.ALP = new ArrayList<Packman>();
		time = 0;
		
	}
	/**
	 * This is constuctor that can receive a csv File and convert it to a game
	 * @param csvFile is the path of the file that you wanto be in the game
	 */
//	public Game(String csvFile) {
//		FromCsv fc = new FromCsv(csvFile); 
//		ALF = fc.getALF();
//		ALP = fc.getALP();
//		time = 0;
//		
//	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public Game(ArrayList<Packman> alp , ArrayList<Fruit> alf) {
		this.ALF = new ArrayList<Fruit>(alf);
		this.ALP = new ArrayList<Packman>(alp);
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
	public Iterator<Fruit> iteratorFruit(){
		return ALF.iterator();
	}
	public Iterator<Packman> iteratorPackman(){
		return ALP.iterator();
	}
   public String toString() {
	   String s = "";
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
