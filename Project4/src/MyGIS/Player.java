package MyGIS;

import Geom.Point3D;

public class Player {
	private Point3D p;
	private double speed;
	private double weight;
	private int id;
	public Player(double lat, double lon, double alt, double Speed, double Weight) {
		p= new Point3D(lat,lon,alt);
		this.speed = Speed;
		this.weight = Weight;
		id=0;
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
		return(" Packman: "+ p+" Speed: "+ speed+ " ID: "+id);
	}
	
}
