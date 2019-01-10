package MyGIS;
import java.awt.Color;
import java.awt.Graphics;

import Geom.Point3D;
/**
 * This is a class that represents a Ghost in the Packman game
 * @author David Tover
 *
 */
	public class Ghost {
		private Point3D p;
		private double speed;
		private double radius;
		private static int size=0;
		private int id;
		/**
		 * This is a constructor for the Ghost
		 * @param lat is the Latitude of the Ghost
		 * @param lon is the Longitude of the Ghost
		 * @param alt is the Altitude of the Ghost
		 * @param Speed is the Speed of the Ghost
		 * @param Radius is the radius of how far it can eat you
		 */
		public Ghost(double lat, double lon, double alt, double Speed, double Radius) {
			p= new Point3D(lat,lon,alt);
			this.speed = Speed;
			this.radius = Radius;
			id = size;
			size++;
		}
		/**
		 * This is a method to draw a Ghost
		 * @param g is the Graphics need to draw
		 * @param m is the Map need to convert from coordinates to pixels
		 */
		public void drawGhost(Graphics g , Map m) {
			int r = 15;
			Point3D pointDraw =  m.Coords2Pixels(this.getP());
			int px = pointDraw.ix() - (r/2);
			int py = pointDraw.iy() - (r/2);
			g.setColor(Color.RED);
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
		public double getSpeed() {
			return speed;
		}
		public void setSpeed(double speed) {
			this.speed = speed;
		}
		public double getRadius() {
			return radius;
		}
		public void setRadius(double radius) {
			this.radius = radius;
		}
		
	}


