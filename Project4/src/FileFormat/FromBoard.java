package FileFormat;

import java.util.ArrayList;

import MyGIS.Block;
import MyGIS.Fruit;
import MyGIS.Ghost;
import MyGIS.Packman;
import MyGIS.Player;
/**
 * This is a class to read from the board that comes from the Play class
 * @author David Tover
 *
 */
public class FromBoard{
	private ArrayList<Fruit> ALF;
	private ArrayList<Packman> ALP;
	private ArrayList<Block> ALB;
	private ArrayList <Ghost> ALG;
	private Player m;
	/**
	 * This consturctor is built from an ArrayList of String that has all the info
	 * @param AL
	 */
	public FromBoard(ArrayList<String> AL) {
		ALF = new ArrayList<Fruit>();
		ALP = new ArrayList<Packman>();
		ALB = new ArrayList<Block>();
		ALG = new ArrayList<Ghost>();

		String line = "";
		String cvsSplitBy = ",";

		for(int i=0;i<AL.size();i++) {
			String[] userInfo = AL.get(i).split(cvsSplitBy);
			/**
			 * Building a Block
			 */
			if(userInfo[0].equals("B")) {
				double lat1 = Double.parseDouble(userInfo[2]);
				double lon1 = Double.parseDouble(userInfo[3]);
				double alt1= Double.parseDouble(userInfo[4]);
				double lat2 = Double.parseDouble(userInfo[5]);
				double lon2 = Double.parseDouble(userInfo[6]);
				double alt2 = Double.parseDouble(userInfo[7]);
				Block bl = new Block(lat1, lon1, alt1, lat2, lon2, alt2);
				ALB.add(bl);
			}
			else {
				double lat = Double.parseDouble(userInfo[2]);
				double lon = Double.parseDouble(userInfo[3]);
				double alt = Double.parseDouble(userInfo[4]);
				/**
				 * Building a Packman
				 */
				if(userInfo[0].equals("P")) {
					double speed = Double.parseDouble(userInfo[5]);
					double radius = Double.parseDouble(userInfo[6]);

					Packman p = new Packman(lat,lon, alt, speed,radius);
					ALP.add(p);


				}
				/**
				 * Building a Fruit
				 */
				else if(userInfo[0].equals("F")) {
					double weight = Double.parseDouble(userInfo[5]); 
					Fruit f = new Fruit(lat,lon,alt,weight);	
					ALF.add(f);
				}
				/**
				 * Building a Ghost
				 */
				else if(userInfo[0].equals("G")) {
					double speed = Double.parseDouble(userInfo[5]);
					double radius = Double.parseDouble(userInfo[6]);

					Ghost g = new Ghost(lat,lon, alt, speed,radius);
					ALG.add(g);
				}
				/**
				 * Building the Player
				 */
				if(userInfo[0].equals("M")) {
					double speed = Double.parseDouble(userInfo[5]);
					double radius = Double.parseDouble(userInfo[6]);

					m = new Player(lat,lon, alt, speed,radius);
					


				}
			}
		}


	}
	public ArrayList<Fruit> getALF() {
		return ALF;
	}
	public ArrayList<Packman> getALP() {
		return ALP;
	}
	public ArrayList<Block> getALB() {
		return ALB;
	}
	public ArrayList<Ghost> getALG() {
		return ALG;
	}
	public Player getM() {
		return m;
	}
}
