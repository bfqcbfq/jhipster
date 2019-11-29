package imageanalyzer;

import static imageanalyzer.common.Colors.colorrange;
import static imageanalyzer.common.Shapes.point;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import imageanalyzer.common.Brush;
import imageanalyzer.common.ColorRange;
import imageanalyzer.common.LineSegment;
import imageanalyzer.common.Point;

public class ComputeOne extends StartColorDistribute {

	public static void main(String[] args) throws FileNotFoundException, IOException {

//		String fileName = "SKM_C454e19111215260_0001.tif1574391211279__adjustDataRect.bmp";// .tif";

		String fileName = "SKM_C454e19111215260_0038.tif_result.bmp";

		new ComputeOne().compute(fileName);

//		new FindSmallRect().minus(fileName, nameto);

//		new FindSmallRect().spread("SKM_C454e19112111130.tif_result.bmp");
	}

//
	public void minus(String fileNameFrom, String fileNameTo) throws IOException, FileNotFoundException {
		File file = new File(fileNameFrom);
		this.fileName = fileNameFrom;
		File fileTo = new File(fileNameTo);

		BufferedImage imageFrom = (BufferedImage) ImageIO.read(file);
		BufferedImage imageMask = (BufferedImage) ImageIO.read(fileTo);
		imageMask = spread(imageMask, colorrange(1, 90), 10);
		imageMask = spread(imageMask, colorrange(90, 229), 8);
		printColorDistribution("spread", imageMask);

		BufferedImage target = minus(imageFrom, imageMask, colorrange(1, 229));
		printColorDistribution("minus", target);
	}

	public void plus(String fileNameFrom, String fileNameTo) throws IOException, FileNotFoundException {
		File file = new File(fileNameFrom);
		this.fileName = fileNameFrom;
		File fileTo = new File(fileNameTo);
//		this.fileName = fileNameFrom;

		BufferedImage imageFrom = (BufferedImage) ImageIO.read(file);
		BufferedImage imageTo = (BufferedImage) ImageIO.read(fileTo);

		imageTo = spread(imageTo, colorrange(1, 229), 3);
		printColorDistribution("spread", imageTo);

		BufferedImage target = plus(imageFrom, imageTo, colorrange(1, 229));
		printColorDistribution("target", target);

	}

	public void spread(String fileNameTo) throws IOException, FileNotFoundException {
		this.fileName = fileNameTo;
		File fileTo = new File(fileNameTo);
//		this.fileName = fileNameFrom;

		BufferedImage imageTo = (BufferedImage) ImageIO.read(fileTo);
		BufferedImage target = spread(imageTo, colorrange(1, 229), 3);
		printColorDistribution("spread", target);

	}

	public void compute(String fileName) throws IOException, FileNotFoundException {
		File file = new File(fileName);
		this.fileName = fileName;

		BufferedImage lastStep = null;
		{
			BufferedImage step0 = (BufferedImage) ImageIO.read(file);
			lastStep = step0;
		}

//
//		int width = lastStep.getWidth();
//		int height = lastStep.getHeight();

//		printColorDistribution("0- cleargray", lastStep);

		// 260,1885

//		lastStep = findout(lastStep, new Point(200, 500), colorrange(100, 229), (short) 3, (short) 50, 50);

		lastStep = computerRect(lastStep);

	}

	protected BufferedImage computerRect(BufferedImage lastStep) throws IOException, FileNotFoundException {
		
		List<String> str= new ArrayList<>();
		
		int offsetX = 10;
		int offsetY = 10;
		int gate = 100;
		{
			int width = 58;
			int x1 = 260;
			int x2 = 1832;
			int x3 = 3403;
			boolean[] q1 = new boolean[6];
			boolean[] q2 = new boolean[6];
			boolean[] q3 = new boolean[6];
			
			int i = 0;
			q1[i++] = countPointOfRect(lastStep, point(x1, 1880), offsetX, offsetY, width, gate);
			q1[i++] = countPointOfRect(lastStep, point(x2, 1880), offsetX, offsetY, width, gate);
			q1[i++] = countPointOfRect(lastStep, point(x3, 1880), offsetX, offsetY, width, gate);

			q1[i++] = countPointOfRect(lastStep, point(x1, 1980), offsetX, offsetY, width, gate);
			q1[i++] = countPointOfRect(lastStep, point(x2, 1980), offsetX, offsetY, width, gate);
			q1[i++] = countPointOfRect(lastStep, point(x3, 1980), offsetX, offsetY, width, gate);

			i = 0;
			q2[i++] = countPointOfRect(lastStep, point(x1, 2590), offsetX, offsetY, width, gate);
			q2[i++] = countPointOfRect(lastStep, point(x2, 2590), offsetX, offsetY, width, gate);
			q2[i++] = countPointOfRect(lastStep, point(x3, 2590), offsetX, offsetY, width, gate);

			q2[i++] = countPointOfRect(lastStep, point(x1, 2690), offsetX, offsetY, width, gate);
			q2[i++] = countPointOfRect(lastStep, point(x2, 2690), offsetX, offsetY, width, gate);
			q2[i++] = countPointOfRect(lastStep, point(x3, 2690), offsetX, offsetY, width, gate);


			i = 0;
			q3[i++] =countPointOfRect(lastStep, point(x1, 3294), offsetX, offsetY, width, gate);
			q3[i++] =countPointOfRect(lastStep, point(x2, 3294), offsetX, offsetY, width, gate);
			q3[i++] =countPointOfRect(lastStep, point(x3, 3294), offsetX, offsetY, width, gate);

			q3[i++] =countPointOfRect(lastStep, point(x1, 3390), offsetX, offsetY, width, gate);
			q3[i++] =countPointOfRect(lastStep, point(x2, 3390), offsetX, offsetY, width, gate);
			q3[i++] =countPointOfRect(lastStep, point(x3, 3390), offsetX, offsetY, width, gate);

			System.out.println(q1);
			System.out.println(q2);
			System.out.println(q3);
		}
		
		

		// 5116
		// 5222

		{
			// 1834

			int ystep = 5222 - 5116;
			int x1 = 260;
			int y = 5113;
			int width = 50;
			countPointOfRect(lastStep, point(x1, y), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x1, y += ystep), offsetX, offsetY, width, gate);
		}
		{
			// 1834

			int ystep = 5222 - 5116;
			int x2 = 2558;
			int y = 5113;
			int width = 50;
			countPointOfRect(lastStep, point(x2, y), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
			countPointOfRect(lastStep, point(x2, y += ystep), offsetX, offsetY, width, gate);
		}

		{
			// 1834
			int width = 58;
			int x = 260;
			countPointOfRect(lastStep, point(x, 6235), offsetX, offsetY, width, gate);
		}
		{
			// 1834
			int width = 58;
			int x = 1827;
			countPointOfRect(lastStep, point(x, 6235), offsetX, offsetY, width, gate);
		}

		printColorDistribution("countPointOfRange", lastStep);
		return lastStep;
	}

	private boolean countPointOfRect(BufferedImage lastStep, Point startPoint, int offsetX, int offsetY, int width, int gate)
			throws IOException, FileNotFoundException {

		Point a = startPoint.offfset(-offsetX, -offsetY);
		Point b = startPoint.offfset(width).offfset(offsetX, offsetY);

		int cnt1 = countPointOfRange(lastStep, a, b, colorrange(1, 229));

		Brush red = new Brush(lastStep, 1, RED_COLOR);
		red.drawRect(a, b);

		Brush red10 = new Brush(lastStep, 10, RED_COLOR);
		if (cnt1 > gate) {
			red10.drawRect(a, b);
		}

		System.out.println(cnt1);
		return cnt1 > gate;
	}

	public BufferedImage findout(BufferedImage imageFrom, Point point, ColorRange colorRange, short lineWidth, short maxOffset, int lineMinLength)
			throws IOException, FileNotFoundException {
		int width = imageFrom.getWidth();
		int height = imageFrom.getHeight();

		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Brush red3Brush = new Brush(imageTo, 3, RED_COLOR);
		Graphics2D g2d = imageTo.createGraphics();
		AffineTransform trans = new AffineTransform();
		g2d.drawImage(imageFrom, null, null);

		Point startLeft = new Point(252, 1910);
//		super.expectedPointCount = 
		LineSegment lineLeft = matchVerticalLine(imageFrom, colorRange, startLeft, lineWidth, lineMinLength, maxOffset, imageFrom);
		red3Brush.drawLine(lineLeft);

		Point startTop = new Point(lineLeft.getA().getX() + lineWidth * 2, lineLeft.getA().getY() - lineWidth * 2);

		LineSegment lineTop = matchHorizonLine(imageFrom, colorRange, startTop, lineWidth, lineMinLength, maxOffset, imageFrom);
		red3Brush.drawLine(lineTop);

		Point startRight = new Point(lineTop.getB().getX() - lineWidth, lineTop.getB().getY() + lineWidth * 2);
		LineSegment lineRight = matchVerticalLine(imageFrom, colorRange, startRight, lineWidth, lineMinLength, maxOffset, imageFrom);

		Point startBottom = new Point(lineLeft.getB().getX() + lineWidth * 2, lineLeft.getB().getY() - lineWidth * 2);
		LineSegment lineBottom = matchHorizonLine(imageFrom, colorRange, startBottom, lineWidth, lineMinLength, maxOffset, imageFrom);
//
		Point actualTopLeft = getCrossPoint(lineTop, lineLeft, true);
//		Point expectedTopLeft = new Point(160, 520);
//		BufferedImage imageTo = null;
//		{
//			int width = imageFrom.getWidth();
//			int height = imageFrom.getHeight();
//			imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g2d = imageTo.createGraphics();
//			AffineTransform trans = new AffineTransform();
//			g2d.setTransform(trans);
//
//			double gradientTop = ((double) lineTop.getB().getY() - lineTop.getA().getY()) / (lineTop.getB().getX() - lineTop.getA().getX());
//			double gradientLeft = ((double) lineLeft.getB().getX() - lineLeft.getA().getX()) / (lineLeft.getB().getY() - lineLeft.getA().getY());
//			double rotateLeft = Math.atan(gradientLeft);
//
//			g2d.rotate(rotateLeft, actualTopLeft.getX(), actualTopLeft.getY());
//			g2d.shear(0, -gradientLeft - gradientTop);
//			g2d.translate(expectedTopLeft.getX() - actualTopLeft.getX(), expectedTopLeft.getY() - actualTopLeft.getY());
//
//			g2d.drawImage(imageFrom, null, null);
//
//			if (debug) {
//				Brush brushRawReact = new Brush(imageTo, 3, RED_COLOR);
//				brushRawReact.drawLine(lineLeft);
//				brushRawReact.drawLine(lineTop);
//				brushRawReact.drawLine(lineRight);
//				brushRawReact.drawLine(lineBottom);
//
////				Point topleft = getCrossPoint(lineTop, lineLeft, true);
//				Point rightbottom = getCrossPoint(lineRight, lineBottom, true);
//
//				Brush brushRule = new Brush(imageTo, 3, GREEN_COLOR);
//				brushRule.drawRect(expectedTopLeft, rightbottom);
//			}
//
////			Brush redBrush = new Brush(newImg, 10, RED_COLOR);
////			redBrush.drawRect(computedStartPoint.getX(), computedStartPoint.getY(), lineTop.getB().getX(), lineLeft.getB().getY());
//		}
//		imageTo = filter_colorConvert(imageTo, 0, 0, color(0xFF, 0xFF, 0xFF));

		return imageTo;

	}

}
