package com.ivision.app.demo;

public class LocationAnalyse {

	public static void main(String[] args) {

	}

	boolean Intersection(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
			double dbX, double dbY) {
		double a = x2 - x1;
		double b = x3 - x4;
		double c = y2 - y1;
		double d = y3 - y4;
		double g = x3 - x1;
		double h = y3 - y1;
		double f = a * d - b * c; // 行列式
		/*
		 * if(abs(f)<1.0e-06) // delta=0，表示两线段重合或平行:delta<=(1e-6) && delta>=-(1e-6) {
		 * //Inverse matrix cannot be computed. return false; }
		 */
		double t = (d * g - b * h) / f;
		double s = (-c * g + a * h) / f;
		if ((0 > t) || (t > 1)) {
			// tow line do not intersect.
			return false;
		}
		if ((0 > s) || (s > 1)) {
			// tow line do not intersect.
			return false;
		}

		dbX = x1 + t * (x2 - x1);
		dbY = y1 + t * (y2 - y1);
		return true;
	}

}
