package MyGIS;

import Geom.Point3D;
/**
 * This class represents a Packman 
 * @author David Tover
 *
 */
public class Packman {
	private Point3D p;
	private double speed;
	private static int size= 0;
	private int id;
	private double radius;
	private long timeStamp;
	private Path path;
	private Point3D startingPoint;


	
	public Packman() {
		
	}
	/**
	 * This is constructor to build a new Packman
	 * @param lat input the latitude for the coordinates of the Packman
	 * @param lon input the longitude for the coordinates of the Packman
	 * @param alt input the altitude for the coordinates of the Packman
	 * @param Speed input the speed you want your Packman to run
	 * @param Radius input the radius of the Packman tht it could eat a Fruit
	 */
	public Packman(double lat, double lon, double alt, double Speed, double Radius) {
		p = new Point3D(lat,lon,alt);
		this.speed = Speed;
		this.id = size;
		this.size++;
		this.radius = Radius;
		path = new Path();
		startingPoint = new Point3D(p);
		timeStamp = 0;
	}
	/**
	 * This is a constructor to build a Packman from another Packman
	 * @param other Input a Packman 
	 */
	public Packman(Packman other) {
		this.p = other.getP();
		this.speed = other.getSpeed();
		this.size = other.getsize();
		this.radius = other.getRadius();
		timeStamp = other.getTimeStamp();
		this.path = other.getPath();
		startingPoint = other.getStartingPoint();
		
	}

	public long getTimeStamp() {
		return timeStamp;
	}
	public Path getPath() {
		return path;
	}


	public Point3D getP() {
		return p;
	}

	public void setP(Point3D p) {
		this.p = p;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getsize() {
		return size;
	}
	
	public double getRadius() {
		return radius;
	}
	public int getId() {
		return id;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public Point3D getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(Point3D startingPoint) {
		this.startingPoint = startingPoint;
	}
	public String toString() {
		return(" Packman: "+ p+" Speed: "+ speed+ " ID: "+id +" "+ timeStamp + " "+path);
	}
}
