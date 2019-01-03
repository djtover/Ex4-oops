package MyGIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Block {

	private Point3D BL;
	private Point3D TR;
	private Point3D TL;
	private Point3D BR;
	private static int size=0;
	private int id;
	
	public Block(double lat1, double lon1,double alt1, double lat2,double lon2,double alt2) {
		this.BL = new Point3D(lat1,lon1,alt1);
		this.TR = new Point3D(lat2, lon2,alt2);
		this.TL = new Point3D(lat1,lon2,alt1);
		this.BR= new Point3D(lat2, lon1,alt2);
		this.id = size;
		size++;
	}
	public boolean isInBlock(Point3D gps) {
		if(gps.y()>BL.y() && gps.x()>BL.x() && gps.y()<TR.y() && gps.x()<TR.x()) {
			return true;
		}
		return false;
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
