package MyGIS;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Geom.Point3D;
/**
 * This is a class that represents a Block in the packman game
 * @author David Tover
 *
 */
public class Block {

	private Point3D BL;
	private Point3D TR;
	private Point3D TL;
	private Point3D BR;
	private static int size=0;
	private int id;
	/**
	 * This is the constructor of the Block
	 * @param lat1 Latitude of bottom left corner
	 * @param lon1 Longitude of bottom left corner
	 * @param alt1 Altitude of bottom left corner
	 * @param lat2 Latitude of top right corner
	 * @param lon2 Longitude of top right corner
	 * @param alt2 Altitude of top right corner
	 */
	public Block(double lat1, double lon1,double alt1, double lat2,double lon2,double alt2) {
		this.BL = new Point3D(lat1,lon1,alt1);
		this.TR = new Point3D(lat2, lon2,alt2);
		this.TL = new Point3D(lat2,lon1,alt1);
		this.BR= new Point3D(lat1, lon2,alt2);
		this.id = size;
		size++;
	}
	/**
	 * This method checks if a certain point is in the Block
	 * @param gps the gps point you want to check
	 * @return true if it is in the Block, false if it isn't
	 */
	public boolean isInBlock(Point3D gps) {
		if(gps.y()>BL.y() && gps.x()>BL.x() && gps.y()<TR.y() && gps.x()<TR.x()) {
			return true;
		}
		return false;
	}
	/**
	 * This is a method to draw the block
	 * @param g is the graphics used to draw
	 * @param m is a map so you can convert the the points from coordinates to pixels
	 */
	public void drawBlock(Graphics g , Map m) {
		Point3D p1Pix = m.Coords2Pixels(this.getBL());
		Point3D p2Pix = m.Coords2Pixels(this.getTR());
		int dx = Math.abs(p2Pix.ix() - p1Pix.ix());
		int dy = Math.abs(p2Pix.iy() - p1Pix.iy());
		g.setColor(Color.BLACK);
		g.fillRect(p1Pix.ix(),p2Pix.iy(),dx,dy);
	}

	public int getId() {
		return id;
	}

	public Point3D getBL() {
		return BL;
	}

	public Point3D getTR() {
		return TR;
	}
	/**
	 * This is a method to get all the corners in the block
	 * @return an ArrayList of the corners of the Block
	 */
	public ArrayList<Point3D> getBlockCorners(){
		ArrayList<Point3D> corners = new ArrayList<Point3D>();
		corners.add(this.BL);
		corners.add(this.TL);
		corners.add(this.TR);
		corners.add(this.BR);
		return corners;
	}
	public String toString() {
		return "Point 1:" +BL + " Point 2: "+ TR;
	}
}
