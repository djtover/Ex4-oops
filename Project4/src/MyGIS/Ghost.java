package MyGIS;
import Geom.Point3D;

	public class Ghost {
		private Point3D p;
		private double speed;
		private double radius;
		private static int size=0;
		private int id;
		public Ghost(double lat, double lon, double alt, double Speed, double Radius) {
			p= new Point3D(lat,lon,alt);
			this.speed = Speed;
			this.radius = Radius;
			id = size;
			size++;
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


