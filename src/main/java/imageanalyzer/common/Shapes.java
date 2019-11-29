package imageanalyzer.common;

public interface Shapes {
	static public Point point(int x,int y) {
		return new Point(x, y);
	}

	static public Quadrilateral quadrilateral(Point topleft, Point topright, Point bottomleft, Point bottomright) {
		return new Quadrilateral(topleft, topright, bottomleft, bottomright);
	}
	
}
