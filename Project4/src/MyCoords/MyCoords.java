package MyCoords;

import Geom.Point3D;
/**
 * This interface represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat,lon, alt points 
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @author David Tover
 *
 */
public class MyCoords implements coords_converter {
	private final int  Earth_Radius = 6371000;
	@Override
	/**
	 *  This method computes a new point which is the gps point transformed by a 3D vector (in meters)
	 *  @param gps is the point which you want to transform with a 3d vector
	 *  @param local_vector_in_meter is the vector that you want to transform with
	 */
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		// TODO Auto-generated method stub
		 double new_lat = gps.x() + toDeg(local_vector_in_meter.y()/Earth_Radius);
		 double new_lon = gps.y() + (local_vector_in_meter.x()/Earth_Radius)*(180/Math.PI)/Math.cos(gps.x()*Math.PI/180);
		 double new_z = gps.z() + local_vector_in_meter.z();
		 Point3D p = new Point3D(new_lat,new_lon,new_z); 
		 return p;
	}

	@Override
	/**
	 * This computes the 3D distance (in meters) between the two gps like points
	 * @param gps0 is gps point 1
	 * @param gps1 is gps point 2
	 */
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub

		double diffLat= gps1.x()-gps0.x();
		double diffLon = gps1.y()-gps0.y();
		double diffRadLat = toRad(diffLat);
		double diffRadLon = toRad(diffLon);
		double lonNorm = Math.cos(toRad((gps0.x())));
		double toMeterLat = (Math.sin(diffRadLat))*Earth_Radius;
		double toMeterLon = (Math.sin(diffRadLon))*lonNorm*Earth_Radius;		
		double d = Math.sqrt((toMeterLat * toMeterLat) + (toMeterLon*toMeterLon ));
		return d;
	}

	@Override
	/**
	 * computes the 3D vector (in meters) between two gps like points
	 * @param gps0 is gps point 1
	 * @param gps1 is gps point 2
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub

		double diffLat= gps1.x()-gps0.x();
		double diffLon = gps1.y()-gps0.y();
		double diffRadLat = toRad(diffLat);
		double diffRadLon = toRad(diffLon);
		double lonNorm = Math.cos(toRad((gps0.x())));
		double toMeterLat = (Math.sin(diffRadLat))*Earth_Radius;
		double toMeterLon = (Math.sin(diffRadLon))*lonNorm*Earth_Radius;
		
		double z = gps1.z()-gps0.z();
		
		Point3D v = new Point3D(toMeterLon,toMeterLat,z);
		
		

		return v;

	}

	@Override
	/**
	 * This methof computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @param gps0 is gps point 1
	 * @param gps1 is gps point 2
	 */
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		Point3D p1 = new Point3D(vector3D(gps0,gps1));
//		System.out.println(p1);

		double radX = p1.x();
		double radY = p1.y();
		double AED [] = new double [3];
		double r = Math.sqrt(Math.pow(radX, 2) + Math.pow(radY, 2) + Math.pow(p1.z(), 2));
		double inclination = Math.acos(p1.z()/r);
		double elevation = 90 - toDeg(inclination);
		double azimuth = Math.atan2(radX,radY);
		if(toDeg(azimuth)<0) {
		AED[0] = 360+toDeg(azimuth);
		}
		else {
			AED[0] = toDeg(azimuth);
		}
		
//		double azimuth = Math.atan2(radY,radX);
//			AED[0] = toDeg(azimuth);
		AED[1] = elevation;
		AED[2] = r;





		return AED;

	}

	@Override
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-430, +8848]
	 * @param p
	 */
	public boolean isValid_GPS_Point(Point3D p) {
		// TODO Auto-generated method stub
		if(p.x() > 90 || p.x() < -90)
			return false;
		if(p.y() > 180 || p.y() < -180)
			return false;
		if(p.z() < -430 || p.z()>8848)
			return false;
		return true;
	}
	private double toRad(double deg) {
		double rad = (deg*Math.PI)/180;
		return rad;

	}
	private double toDeg(double rad) {
		double deg = (rad*180)/Math.PI;
		return deg;
	}
	public static void main(String []args) {
		MyCoords mc = new MyCoords();
		Point3D p1 = new Point3D(32.103315
				,35.209039,
				670
);
		Point3D p2 = new Point3D(32.106352,
				35.205225,
				650

);
		double [] aed = mc.azimuth_elevation_dist(p1, p2);
		System.out.println(aed[0]);
		
		
	}

}
