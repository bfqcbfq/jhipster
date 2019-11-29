package imageanalyzer.common;

import java.awt.image.BufferedImage;

public class Brush {
	private BufferedImage targetImage;
	private int[] pointMatrix;
	private int color;
	private int size = 0;
	private int offset = size / 2;

	static int[][] PointMatrixes = new int[20][];
	{
		PointMatrixes[1] = new int[] { 1 };
		PointMatrixes[2] = new int[] { 2, 2 };
		PointMatrixes[3] = new int[] { 3, 3, 3 };
		PointMatrixes[4] = new int[] { 2, 4, 4, 2 };
		PointMatrixes[5] = new int[] { 3, 5, 5, 5, 3 };
		PointMatrixes[6] = new int[] { 2, 4, 6, 6, 4, 2 };
		PointMatrixes[7] = new int[] { 3, 5, 7, 7, 7, 5, 3 };
		PointMatrixes[8] = new int[] { 2, 4, 6, 8, 8, 6, 4, 2 };
		PointMatrixes[9] = new int[] { 3, 5, 7, 9, 9, 9, 7, 5, 3 };
		PointMatrixes[10] = new int[] { 4, 6, 8, 10, 10, 10, 10, 8, 6, 4 };
	}

	public Brush(BufferedImage to, int size, int color) {
		super();
		this.targetImage = to;
		this.pointMatrix = PointMatrixes[size];
		this.color = color;
		this.size = size;
	}

	public void drawPoint(Point point) {
		this.drawPoint(point.getX(), point.getY());
	}

	public void drawPoint(int x, int y) {
		int width = targetImage.getWidth();
		int height = targetImage.getHeight();

		for (int yy = -offset; yy < size - offset; yy++) {
			int xWidth = pointMatrix[yy + offset];
			for (int xx = -xWidth / 2; xx < xWidth - xWidth / 2; xx++) {
				int px = x + xx;
				int py = y + yy;
				if (px >= 0 && py >= 0 && px < width && py < height) {
					targetImage.setRGB(px, py, color);
				}
			}
		}
	}

	public void drawLine(int ax, int ay, int bx, int by) {
		if (Math.abs(by - ay) < Math.abs(bx - ax)) {
			float gradient = ((float) by - ay) / (bx - ax);
			for (int x = ax; x <= bx; x++) {
				int y = ay + (int) ((x - ax) * gradient);
				drawPoint(x, y);
			}
		} else {
			float gradient = ((float) bx - ax) / (by - ay);

			for (int y = ay; y <= by; y++) {
				int x = ax + (int) ((y - ay) * gradient);

				drawPoint(x, y);
			}
		}

	}

	public void drawRect(int ax, int ay, int bx, int by) {
		drawLine(ax, ay, bx, ay); // top
		drawLine(bx, ay, bx, by); // right
		drawLine(ax, by, bx, by); // bottom
		drawLine(ax, ay, ax, by); // left
	}

	public void drawLine(LineSegment lineV) {
		this.drawLine(lineV.A.x, lineV.A.y, lineV.B.x, lineV.B.y);
	}

	public void drawRect(Point A, Point B) {
		this.drawRect(A.x, A.y, B.x, B.y);
	}

}
