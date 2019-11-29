package imageanalyzer.common;

public class LineSegment {
	@Override
	public String toString() {
		return "[" + A + " ~ " + B + "]";
	}

	Point A;

	public LineSegment(Point a, Point b) {
		super();
		A = a;
		B = b;
	}

	public static LineSegment line(int ax, int ay, int bx, int by) {
		return new LineSegment(new Point(ax, ay), new Point(bx, by));
	}

	Point B;

	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}

}
