package MyGIS;

import Geom.Point3D;

public class Player {
	private Point3D p;
	private double speed;
	private double weight;
	private int id;
	private Path path;
	public Player(double lat, double lon, double alt, double Speed, double Weight) {
		p= new Point3D(lat,lon,alt);
		this.speed = Speed;
		this.weight = Weight;
		id=0;
		path = new Path();
	}
	public Player() {
		p= new Point3D(0,0,0);
		this.speed = 0;
		this.weight = 0;
		id=0;
		path =new Path();
	}
	public Player(Player other) {
		this.p = other.getP();
		this.speed = other.getSpeed();
		this.weight = other.getWeight();
		id = 0;
		path = other.getPath();
				
	}
	public Path getPath() {
		return path;
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String toString() {
		return(" Player: "+ p+" Speed: "+ speed+ " ID: "+id + " "+ path);
	}
	
}
