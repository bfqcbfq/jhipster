package imageanalyzer;

import static imageanalyzer.common.Colors.color;
import static imageanalyzer.common.Colors.colorrange;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageanalyzer.common.Brush;
import imageanalyzer.common.ColorRange;
import imageanalyzer.common.ImageAnalyzerCommon;
import imageanalyzer.common.LineSegment;
import imageanalyzer.common.Point;

public class StartColorDistribute extends ImageAnalyzerCommon {

	public static void main(String[] args) throws FileNotFoundException, IOException {
//		File file = new File("images\\back2.png");

//		String fileName = "3580.tif";// .tif";
//		String fileName = "9210.png";// .tif";
//		String fileName = "1111.png";// .tif";

//		String fileName = "4400-04.tif";// .tif"; 包括两者

//		String fileName = "4400-02.tif";// .tif";字体等
//		String fileName = "4400-99.tif";// .tif"; // 选择框
		String fileName = "4400.tif";// .tif";
//		new StartColorDistribute().compute(fileName);

	}

	public BufferedImage parse(File file) throws IOException, FileNotFoundException {
//		File file = new File(fileName);
		this.fileName = file.getName();

		BufferedImage lastStep = null;
		{
			BufferedImage step0 = (BufferedImage) ImageIO.read(file);
			lastStep = step0;
		}

		super.debug = false;

		lastStep = filterColor(lastStep);
		printColorDistribution("filterColor", lastStep);

		lastStep = adjustDataRect(lastStep, new Point(200, 500), colorrange(0, 100), (short) 10, (short) 200, 200);
		printColorDistribution("adjustDataRect", lastStep);

		lastStep = filter_colorConvert(lastStep, colorrange(31, 250), color(0xFF));

		return lastStep;
	}

	protected BufferedImage filterColor(BufferedImage lastStep) throws IOException, FileNotFoundException {
		int width = lastStep.getWidth();
		int height = lastStep.getHeight();

		// 把头上的一条彩色条去掉
//		lastStep = filter_ClearRange(lastStep, 0, width, 0, 480);

		// 计算数学平均的色彩分布
		printColorDistributionAfterRedArithmeticMean(lastStep);

		// 过滤所有超过229的色彩
		lastStep = filter_ColorRange(lastStep, 0, 229);

		// 如果亮灰色周围没有其他更深的颜色，则更新成白色
		lastStep = filter_ClearAloneLightGray(lastStep, 5, colorrange(163, 229), color(0xFF)); // OK
//		printColorDistribution("1- cleargray", lastStep);

		// 101
		// 如果是0-37 则更新成字体颜色
		lastStep = filter_colorConvert(lastStep, colorrange(0, 37), color(1, 0xFF, 0xFF));
//		printColorDistribution("2- 0-60 to 1", lastStep);

		lastStep = filter_AverageInMatrix(lastStep, 3, colorrange(64, 229), colorrange(0, 229));// OK
		lastStep = filter_AverageInMatrix(lastStep, 5, colorrange(64, 229), colorrange(0, 229));// OK
		lastStep = filter_AverageInMatrix(lastStep, 10, colorrange(64, 229), colorrange(0, 229));// OK
//		printColorDistribution("3- Average", lastStep);

		// TODO 如果靠近字体颜色的灰色，则更新成字体颜色 正在处理这个，还不够完善
		lastStep = filter_upgradeWhenNear(lastStep, 10, colorrange(61, 101), colorrange(0, 61), color(2, 0, 0xFF), 4);
		lastStep = filter_colorConvert(lastStep, colorrange(0, 67), color(1, 0, 0xFF));
//		printColorDistribution("4- nearBlack 61-109", lastStep);
//		
//		// TODO 如果靠近字体颜色的灰色，则更新成字体颜色 正在处理这个，还不够完善
		lastStep = filter_upgradeWhenNear(lastStep, 10, colorrange(61, 127), colorrange(0, 10), color(3, 0, 0xFF), 4);
		lastStep = filter_upgradeWhenNear(lastStep, 10, colorrange(61, 127), colorrange(0, 10), color(3, 0, 0xFF), 4);
		lastStep = filter_upgradeWhenNear(lastStep, 10, colorrange(61, 229), colorrange(0, 10), color(3, 0, 0xFF), 4);
//		printColorDistribution("5- nearBlack 61-229", lastStep);

		lastStep = filter_colorConvert(lastStep, colorrange(68, 229), color(140, 0xFF, 0xFF));
//		printColorDistribution("6- color 109-169", lastStep);

		lastStep = filter_upgradeWhenNear(lastStep, 3, colorrange(0, 229), colorrange(109, 169), color(140, 0xDD, 0xFF), 1);
		lastStep = filter_upgradeWhenNear(lastStep, 3, colorrange(61, 229), colorrange(109, 169), color(140, 0xDD, 0xFF), 1);
//		lastStep = filter_colorConvert(lastStep,  61, 229, color(140, 0xFF, 0xFF));
//		printColorDistribution("7- color 61-229", lastStep);
//		printColorDistribution("0-81 To 1", lastStep);

//		BufferedImage letter = filter_colorConvert(lastStep,  31, 250,
//				color(0xFF, 0xFF, 0xFF));
////		printColorDistribution("10-letter", letter);
//
//		BufferedImage block = filter_colorConvert(lastStep,  0, 31, color(0xFF, 0xFF, 0xFF));
////		printColorDistribution("10-block", block);

		return lastStep;
	}

	public BufferedImage adjustDataRect(BufferedImage imageFrom, Point point, ColorRange colorRange, short lineWidth, short maxOffset, int lineMinLength)
			throws IOException, FileNotFoundException {

		Point startLeft = new Point(lineWidth, 700);
		LineSegment lineLeft = matchVerticalLine(imageFrom, colorRange, startLeft, lineWidth, lineMinLength, maxOffset, imageFrom);

		Point startTop = new Point(lineLeft.getA().getX() + lineWidth * 2, lineLeft.getA().getY() - lineWidth * 2);

		LineSegment lineTop = matchHorizonLine(imageFrom, colorRange, startTop, lineWidth, lineMinLength, maxOffset, imageFrom);

		Point startRight = new Point(lineTop.getB().getX() - lineWidth, lineTop.getB().getY() + lineWidth * 2);
		LineSegment lineRight = matchVerticalLine(imageFrom, colorRange, startRight, lineWidth, lineMinLength, maxOffset, imageFrom);

		Point startBottom = new Point(lineLeft.getB().getX() + lineWidth * 2, lineLeft.getB().getY() - lineWidth * 2);
		LineSegment lineBottom = matchHorizonLine(imageFrom, colorRange, startBottom, lineWidth, lineMinLength, maxOffset, imageFrom);

		Point actualTopLeft = getCrossPoint(lineTop, lineLeft, true);
		Point expectedTopLeft = new Point(160, 520);
		BufferedImage imageTo = null;
		{
			int width = imageFrom.getWidth();
			int height = imageFrom.getHeight();
			imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = imageTo.createGraphics();
			AffineTransform trans = new AffineTransform();
			g2d.setTransform(trans);

			double gradientTop = ((double) lineTop.getB().getY() - lineTop.getA().getY()) / (lineTop.getB().getX() - lineTop.getA().getX());
			double gradientLeft = ((double) lineLeft.getB().getX() - lineLeft.getA().getX()) / (lineLeft.getB().getY() - lineLeft.getA().getY());
			double rotateLeft = Math.atan(gradientLeft);

			g2d.rotate(rotateLeft, actualTopLeft.getX(), actualTopLeft.getY());
			g2d.shear(0, -gradientLeft - gradientTop);
			g2d.translate(expectedTopLeft.getX() - actualTopLeft.getX(), expectedTopLeft.getY() - actualTopLeft.getY());

			g2d.drawImage(imageFrom, null, null);

			if (debug) {
				Brush brushRawReact = new Brush(imageTo, 3, RED_COLOR);
				brushRawReact.drawLine(lineLeft);
				brushRawReact.drawLine(lineTop);
				brushRawReact.drawLine(lineRight);
				brushRawReact.drawLine(lineBottom);

//				Point topleft = getCrossPoint(lineTop, lineLeft, true);
				Point rightbottom = getCrossPoint(lineRight, lineBottom, true);

				Brush brushRule = new Brush(imageTo, 3, GREEN_COLOR);
				brushRule.drawRect(expectedTopLeft, rightbottom);
			}

//			Brush redBrush = new Brush(newImg, 10, RED_COLOR);
//			redBrush.drawRect(computedStartPoint.getX(), computedStartPoint.getY(), lineTop.getB().getX(), lineLeft.getB().getY());
		}
		imageTo = filter_colorConvert(imageTo, colorrange(0, 0), color(0xFF, 0xFF, 0xFF));

		return imageTo;

	}

	public BufferedImage spread(BufferedImage image, ColorRange colorRange, int step) throws IOException, FileNotFoundException {
		int width = image.getWidth();
		int height = image.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				result.setRGB(x, y, rgb);
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				int red = (rgb >> 16) & 255;

				if (!colorRange.include(red)) continue;

				for (int xx = -step; xx <= step; xx++) {
					for (int yy = -step; yy <= step; yy++) {
						if (0 <= x + xx && x + xx < width && 0 <= y + yy && y + yy < height) {
							result.setRGB(x + xx, y + yy, rgb);
						}
					}
				}
			}
		}

		return result;
	}

	public BufferedImage minus(BufferedImage imageTarget, BufferedImage image, ColorRange colorRange) throws IOException, FileNotFoundException {
		int width = imageTarget.getWidth();
		int height = imageTarget.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				result.setRGB(x, y, color(0xFF));
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				if (colorRange.include(red)) {
					result.setRGB(x, y, color(0xFF));
				} else {
					result.setRGB(x, y, imageTarget.getRGB(x, y));
				}
			}
		}

		return result;
	}

	public BufferedImage plus(BufferedImage imageA, BufferedImage imageB, ColorRange colorRange) throws IOException, FileNotFoundException {
		int width = imageA.getWidth();
		int height = imageA.getHeight();
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				result.setRGB(x, y, color(0xFF));
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = imageA.getRGB(x, y);
				int red = (rgb >> 16) & 255;

				if (colorRange.include(red)) {
					result.setRGB(x, y, BLUE_COLOR);
				}
			}
		}

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = imageB.getRGB(x, y);
				int red = (rgb >> 16) & 255;

				if (colorRange.include(red)) {
					result.setRGB(x, y, RED_COLOR);
				}
			}
		}

		return result;
	}
}
