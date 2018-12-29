package MyGIS;

import Geom.Point3D;

public class Block {

	private Point3D BL;
	private Point3D TR;
	private static int size=0;
	private int id;
	
	public Block(double lat1, double lon1,double alt1, double lat2,double lon2,double alt2) {
		this.BL = new Point3D(lat1,lon1,alt1);
		this.TR = new Point3D(lat2, lon2,alt2);
		this.id = size;
		size++;
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
	public String toString() {
		return "Point 1:" +BL + " Point 2: "+ TR;
	}
}
