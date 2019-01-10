package MyGIS;

import java.awt.image.BufferedImage;

//import .Cords;

import Geom.Point3D;
import MyCoords.MyCoords;
/**
 * This is a class that represents a map for this project
 * @author David Tover
 *
 */
public class Map {
	private final Point3D RT = new Point3D(32.105728,35.212416,650);
	private final Point3D RB = new Point3D(32.101898,35.212416,650);
	private final Point3D LT = new Point3D(32.105728,35.202369,650);
	private final Point3D LB = new Point3D(32.101898,35.202369,650);
	private final double diffLat = .003830;
	private final double diffLon = .010047;
	private String myImage;
	private double latPerHeight;
	private double lonPerWidth;
	private double width;
	private double height;
	/**
	 * This is a constructor for the Map
	 * @param w The Width of the Map
	 * @param h The Height of the Map
	 * @param image the path of the Image you want to put in
	 */
	public Map(double w, double h , String image) {
		width = w;
		height = h;
		myImage = image;
		lonPerWidth = diffLon/width;
		latPerHeight = diffLat/height;
	}
	/**
	 * This is a method that converts from coordinates to pixels
	 * @param p1 A Point in coordinates
	 * @return A Point in pixels
	 */
	public Point3D Coords2Pixels(Point3D gps) {


		double dx = Math.abs(gps.x()-LT.x());
		double dy = Math.abs(gps.y()-LT.y());

		double rx = dx/diffLat;
		double ry = dy/diffLon;

		int w = (int)(ry*width) +1;
		int h = (int)(rx*height)+1;

		Point3D ans = new Point3D(w,h,gps.z());
		return ans;

	}
	/**
	 * This is a method to convert from pixels to coordinates 
	 * @param p1 the Point in pixels
	 * @param w the width of the frame
	 * @param h the height of the frame
	 */
	public Point3D Pixels2Coords(Point3D p1, double w, double h) {

		double vX = LT.x()-p1.y()*latPerHeight;
		double vY = LT.y() + p1.x()*lonPerWidth;



		Point3D p2 = new Point3D(vX,vY,p1.z());

		return p2;

	}
	/**
	 * This method finds the distance between 2 pixels in meters based on the image
	 * @param p1 first pixel
	 * @param p2 second pixel
	 * @return distance between the pixels
	 */
	public double DistanceBetweenPixels(Point3D p1, Point3D p2) {
		MyCoords mc = new MyCoords();
		double dx = mc.distance3d(LT, RT);
		double dy = mc.distance3d(LT, LB);
		int y = Math.abs(p2.iy()-p1.iy());
		int x = Math.abs(p2.ix()-p1.ix());
		double ry = y/getHeight();
		double rx = x/getWidth();
		double disy = ry*dy;
		double disx = rx*dx;

		return Math.sqrt(disy*disy + disx*disx);


	}
	/**
	 * This is a method that will convert from distance in meters to distance in pixels
	 * @param dist in meters
	 * @return distance in pixels
	 */
	public int distanceInPixels(double dist) {
		MyCoords mc = new MyCoords();

		double dCoor = mc.distance3d(LT,RT);
		double dPix = getWidth();

		double r = dPix / dCoor;
		return (int)(dist*r);

	}
	/**
	 * This is a method to find the angle between 2 points
	 * @param gps1 The first point
	 * @param gps2 the second point
	 * @return
	 */
	public double findAngle(Point3D gps1, Point3D gps2) {
		MyCoords mc = new MyCoords();
		double[] aed = mc.azimuth_elevation_dist(gps1, gps2);
		return  aed[0];

	}

	public double getLatPerHeight() {
		return latPerHeight;
	}
	public double getLonPerWidth() {
		return lonPerWidth;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public String getMyImage() {
		return myImage;
	}
}
