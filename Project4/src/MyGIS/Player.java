package MyGIS;

import java.awt.Color;
import java.awt.Graphics;

import Geom.Point3D;
/**
 * This is a class that represents a Player in the Packman game
 * @author David Tover
 *
 */
public class Player {
	private Point3D p;
	private double speed;
	private double weight;
	private int id;
	private Path path;
	/**
	 * This is a constructor for the Player
	 * @param lat is the Latitude of the Player
	 * @param lon is the Longitude of the Player
	 * @param alt is the Altitude of the Player
	 * @param Speed is the Speed of the Player
	 * @param Weight is the weight the Player has accumulated
	 */
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
	/**
	 * This is a method to draw a Player
	 * @param g is the Graphics need to draw
	 * @param m is the Map needed to convert from coordinates to pixels
	 */
	public void drawPlayer(Graphics g , Map m) {
		int ra = 25;
		Point3D drawPlayer =  m.Coords2Pixels(this.getP());
		int dx = drawPlayer.ix() - (ra/2);
		int dy = drawPlayer.iy() - (ra/2);
		g.setColor(Color.BLUE);
		g.fillOval(dx, dy, ra, ra);
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
