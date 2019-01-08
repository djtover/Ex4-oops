package Algo;

//import Geom.Point3D;

public class LinearEquation {
	private int x1;
	private int y1;
	private double m;
	private boolean isVertical;
	private boolean isHorizontal;

	public LinearEquation(int x , int y, double M) {
		this.x1 = x;
		this.y1 = y;
		this.m = M;
		isVertical = false;
	}
	public LinearEquation(Segment seg) {
		if(!seg.getIsHorizontal() && !seg.getIsVertical() ) {
		double dx = seg.getPoint2().x() - seg.getPoint1().x();
		double dy = seg.getPoint2().y() - seg.getPoint1().y();
		x1 = seg.getPoint1().ix();
		y1 = seg.getPoint1().iy();
		if(dx!= 0) {
		this.m = (dy/dx);
		}
		}
		else {
			if(seg.getIsHorizontal()) {
				isHorizontal = true;
			}
			else if(seg.getIsVertical()) {
				isVertical = true;
			}
		}
//		else {
//			isVertical = true;
//		}
	}
	public int f(int x) {
		if(!isVertical && !isHorizontal) {
		int ans = (int)((m*(x-x1))+y1);
		return ans;
		}
		
		return Integer.MAX_VALUE;
	}
	

	public double getX1() {
		return x1;
	}

	public double getY1() {
		return y1;
	}

	public double getM() {
		return m;
	}
}
