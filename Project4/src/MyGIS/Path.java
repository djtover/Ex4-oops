package MyGIS;

import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

/**
 * This class represents a Path which is all the Fruits that the Packman goes through
 * @author David Tover
 *
 */
public class Path {

	private ArrayList <Fruit> AL;
	private double time;
	private double weight;

	/**
	 *  AL is the ArrayList that holds the Fruit for the Path
	 *  time is the time it takes for the Packman to go through all the Fruit in the Path
	 *  weight is the amount of weight the Pacman has accumulated through the Path 
	 */
	public Path() {
		this.AL  = new ArrayList<Fruit>();
		time = 0;
		weight=0;
	}

	public ArrayList<Fruit> getAL() {
		return AL;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}

	public boolean add(Fruit f) {
	
		return AL.add(f);

	}
	public void remove(int index) {
		AL.remove(index);
	}
	/**
	 * This is a method to add another Path to this Path
	 * @param path
	 */
	public void addPath(Path path ) {
		for(int i=0;i<path.size();i++) {
			AL.add(path.getAL().get(i));
		}
	}
	
	public Iterator<Fruit> iterator(){
		return AL.iterator();
	}
	public int size() {
		return AL.size();

	}
	public void clear() {
		AL.clear();
	}


	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String toString() {
		String s = "Path: ";
		for(int i =0; i<AL.size();i++) {
			s=s+" "+ AL.get(i);
		}
		return (s+ "Time: "+time + " Weight: "+ weight);
	}
}