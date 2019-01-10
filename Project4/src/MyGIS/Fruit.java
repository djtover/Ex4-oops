package MyGIS;


import java.awt.Color;
import java.awt.Graphics;
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
	}
	public Fruit(Point3D P , double Weight) {
		p = P;
		weight = Weight;
		id = size;
		this.size++;
		timeStamp= 0;
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
	}
	/**
	 * This is a method to draw a Fruit
	 * @param g is the graphics to draw
	 * @param m is a Map to convert the points from coordinates to pixels
	 */
	public void drawFruit(Graphics g , Map m) {
		int r = 10;
		Point3D pointDraw =  m.Coords2Pixels(this.getP());
		int px = pointDraw.ix()-(r/2);
		int py = pointDraw.iy() - (r/2);

		g.setColor(Color.MAGENTA);
		g.fillOval(px, py, r, r);
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
