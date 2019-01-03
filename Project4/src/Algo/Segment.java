package Algo;

import Geom.Point3D;

public class Segment {
	private Point3D point1;
	private Point3D point2;
	private boolean isVertical;
	private boolean isHorizontal;
	
	public Segment(Point3D pixel1, Point3D pixel2) {
		Point3D [] points =  orderMinMaxByX(pixel1 ,pixel2);
		
		point1 = points[0];
		point2 = points[1];
		isVertical = isVertical(point1 , point2);
		isHorizontal = isHorizontal(point1, point2);
	}
	private boolean isVertical(Point3D pixel1 , Point3D pixel2) {
		if(pixel1.x() == pixel2.x()) {
			return true;
		}
		return false;
			
	}
	private boolean isHorizontal(Point3D pixel1 , Point3D pixel2) {
		if(pixel1.y() == pixel2.y()) {
			return true;
		}
		return false;
	}
	private Point3D[] orderMinMaxByX(Point3D pixel1 , Point3D pixel2) {
		Point3D ans[]= new Point3D[2];
		if(pixel1.ix()>pixel2.ix()) {
			ans[0] = pixel2;
			ans[1] = pixel1;
		}
		else {
			ans[0] = pixel1;
			ans[1] = pixel2;
		}
		return ans;
	}
	public Point3D getPoint1() {
		return point1;
	}
	public Point3D getPoint2() {
		return point2;
	}
	public boolean getIsVertical() {
		return isVertical;
	}
	public boolean getIsHorizontal() {
		return isHorizontal;
	}
	public String toString() {
		return "Point1: " +this.point1+" Point2: "+ this.point2;
	}
}
