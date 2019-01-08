package MyGIS;


import java.util.Date;

import Geom.Point3D;
/**
 * This is a class that represents a Fruit
 * @author David Tover 
 *
 */
public class Fruit {

	private Point3D p;
	private double weight;
	private static int size=0;
	private int id;
	private long timeStamp;
//	private MetaData timeFound;
	private Packman pred;	
	/**
	 * This is constructor to build a new Fruit
	 * @param lat input the latitude for the coordinates of the Fruit
	 * @param lon input the longitude for the coordinates of the Fruit
	 * @param alt input the altitude for the coordinates of the Fruit
	 * @param Weight input the weight of the Fruit
	 */
	public Fruit(double lat, double lon, double alt, double Weight) {
		p = new Point3D(lat,lon,alt);
		this.weight = Weight;
		id = size;
		this.size++;
		timeStamp= 0;
		pred = null;
	}
	public Fruit(Point3D P , double Weight) {
		p = P;
		weight = Weight;
		id = size;
		this.size++;
		timeStamp= 0;
		pred = null;
	}
	/**
	 * This is a constructor to build a Fruit from another Fruit
	 * @param other Input a Fruit 
	 */
	public Fruit(Fruit other) {
		this.p = other.getP();
		this.weight = other.getWeight();
		this.size = other.getsize();
		timeStamp = other.getTimeStamp();
//		pred = other.getPred();
	}


	public int getId() {
		return id;
	}


	public Point3D getP() {
		return p;
	}


	public void setP(Point3D p) {
		this.p = p;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public int getsize() {
		return size;
	}
	public Packman getPred() {
		return pred;
	}
	public void setPred(Packman pred) {
		this.pred = pred;
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String toString() {
		return(" Fruit: "+ p+" Weight: " +this.weight+" ID: " +id+ " Time:" +timeStamp);
	}
}
