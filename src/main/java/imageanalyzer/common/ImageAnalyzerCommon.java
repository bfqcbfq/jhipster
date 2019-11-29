package imageanalyzer.common;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageAnalyzerCommon {
	Logger log = LoggerFactory.getLogger(ImageAnalyzerCommon.class);

	protected static int RED_COLOR = new Color(0xFF, 1, 1).getRGB();
	protected static int GREEN_COLOR = new Color(1, 0xFF, 1).getRGB();
	protected static int BLUE_COLOR = new Color(1, 1, 0xFF).getRGB();

	protected boolean debug = true;

	protected String fileName = null;

	protected List<int[]> colors = new ArrayList<>();
	protected List<String> hints = new ArrayList<>();

//	protected int expectedPointCount = 76;

	static int[][] PointMatrixes = new int[20][];
	static int[] PointCountMatrixes;
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
		PointCountMatrixes = new int[] { 0, 1, 4, 9, 12, 21, 24, 37, 40, 57, 76 };

	}

	protected int[] printColorDistribution(String hint, BufferedImage lastStep) throws FileNotFoundException, IOException {
		String realname = System.currentTimeMillis() + "__" + hint;

		log.debug("==========      {}          ===========", hint);
		writeImageAll(realname, lastStep);

		int[] cntRed = printColorDistribution(lastStep);
		colors.add(cntRed);
		hints.add(hint);

//		{
//
//			BufferedWriter out = new BufferedWriter(new FileWriter("tmp\\" + fileName + realname + ".log"));
//
//			StringBuilder sb = new StringBuilder();
//			sb.append(hints.get(0));
//			if (hints.size() > 1) {
//				for (int x = 1; x < hints.size(); x++) {
//					sb.append(",");
//					sb.append(hints.get(x));
//				}
//			}
//			sb.append("\n");
//
//			for (int i = 0; i < 256; i++) {
//				sb.append(colors.get(0)[i]);
//				if (colors.size() > 1) {
//					for (int x = 1; x < colors.size(); x++) {
//						sb.append(",");
//						sb.append(colors.get(x)[i]);
//					}
//				}
//
//				sb.append("\n");
//			}
//			out.write(sb.toString());
//			out.close();
//		}

		return cntRed;
	}

	private void writeImageAll(String hint, BufferedImage imageTo) throws IOException, FileNotFoundException {
		ImageIO.write(imageTo, "png", new FileOutputStream("tmp\\" + fileName + hint + ".png"));
	}

	protected int[] printColorDistribution(BufferedImage lastStep) throws IOException {
		int[] cntRed = new int[256];
		int[] cntGreen = new int[256];
		int[] cntBlue = new int[256];
		int width = lastStep.getWidth();
		int height = lastStep.getHeight();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = lastStep.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				int green = (rgb >> 8) & 255;
				int blue = (rgb) & 255;
				cntRed[red] += 1;
				cntGreen[green] += 1;
				cntBlue[blue] += 1;
			}
		}
		return cntRed;
	}

	protected void printColorDistributionAfterRedArithmeticMean(BufferedImage lastStep) {
		int[] cntRed = new int[256];
		int[] cntGreen = new int[256];
		int[] cntBlue = new int[256];
		int width = lastStep.getWidth();
		int height = lastStep.getHeight();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = lastStep.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				int green = (rgb >> 8) & 255;
				int blue = (rgb) & 255;
				cntRed[red] += 1;
				cntGreen[green] += 1;
				cntBlue[blue] += 1;
			}
		}

		int range1 = 4;

		int[] cntAfterRed = new int[256];
		int[] cntAfterGreen = new int[256];
		int[] cntAfterBlue = new int[256];

		{
			for (int i = 0; i < 256; i++) {
				int from = i - range1;
				from = from > 0 ? from : 0;
				int to = i + range1;
				to = to <= 0xFF ? to : 0xFF;

				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				for (int j = from; j <= to; j++) {
					sumRed += cntRed[j];
					sumGreen += cntGreen[j];
					sumBlue += cntBlue[j];
				}
				cntAfterRed[i] = (int) sumRed / (to - from);
				cntAfterGreen[i] = (int) sumGreen / (to - from);
				cntAfterBlue[i] = (int) sumBlue / (to - from);
			}
		}

		cntRed = cntAfterRed;
		cntGreen = cntAfterGreen;
		cntBlue = cntAfterBlue;

		cntAfterRed = new int[256];
		cntAfterGreen = new int[256];
		cntAfterBlue = new int[256];

		{
			for (int i = 0; i < 256; i++) {
				int from = i - range1;
				from = from > 0 ? from : 0;
				int to = i + range1;
				to = to <= 0xFF ? to : 0xFF;

				int sumRed = 0;
				int sumGreen = 0;
				int sumBlue = 0;
				for (int j = from; j <= to; j++) {
					sumRed += cntRed[j];
					sumGreen += cntGreen[j];
					sumBlue += cntBlue[j];
				}
				cntAfterRed[i] = (int) sumRed / (to - from);
				cntAfterGreen[i] = (int) sumGreen / (to - from);
				cntAfterBlue[i] = (int) sumBlue / (to - from);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 256; i++) {
			sb.append(String.format("%3d,%10d,%10d,%10d", i, cntRed[i], cntGreen[i], cntBlue[i]));
			sb.append("\n");
		}
		System.out.println(sb.toString());

	}

	@SuppressWarnings("unused")
	public BufferedImage filter_colorConvert(BufferedImage imageStepFrom, ColorRange colorRange, int newcolor) throws IOException, FileNotFoundException {
		int width = imageStepFrom.getWidth();
		int height = imageStepFrom.getHeight();

		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 找出最左边一条线的左边和右边，及宽度
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = imageStepFrom.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				int green = (rgb >> 8) & 255;
				int blue = (rgb) & 255;

				if (colorRange.include(red)) {
					imageTo.setRGB(x, y, newcolor);
				} else {
					imageTo.setRGB(x, y, rgb);
				}
			}
		}
		return imageTo;
	}

	@SuppressWarnings("unused")
	public BufferedImage filter_ClearAloneLightGray(BufferedImage imageFrom, int matrixSize, ColorRange colorRange, int replaceColor)
			throws IOException, FileNotFoundException {

		int width = imageFrom.getWidth();
		int height = imageFrom.getHeight();
		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int centerPos = matrixSize / 2;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int xxfrom = x - centerPos;
				xxfrom = xxfrom >= 0 ? xxfrom : 0;
				int xxto = x + centerPos;
				xxto = xxto < width ? xxto : width - 1;

				int yyfrom = y - centerPos;
				yyfrom = yyfrom >= 0 ? yyfrom : 0;
				int yyto = y + centerPos;
				yyto = yyto < height ? yyto : height - 1;

				int cntDarkerColor = 0;
				int cntInRangeColor = 0;
				for (int xx = xxfrom; xx <= xxto; xx++) {
					for (int yy = yyfrom; yy <= yyto; yy++) {
						int rgb = imageFrom.getRGB(xx, yy);
						int rgbFromRed = (rgb >> 16) & 255;
						// int rgbFrom = 255 - rgbFromRed;

						if (rgbFromRed < colorRange.getFrom()) {
							cntDarkerColor++;
						} else if (rgbFromRed < colorRange.getTo()) {
							cntInRangeColor++;
						}
					}
				}

				if (cntDarkerColor < 3) {
					imageTo.setRGB(x, y, replaceColor);
				} else {
					imageTo.setRGB(x, y, imageFrom.getRGB(x, y));
				}
			}
		}
		return imageTo;
	}

	public BufferedImage filter_ColorRange(BufferedImage imageStepFrom, int from, int to) throws IOException, FileNotFoundException {
		int width = imageStepFrom.getWidth();
		int height = imageStepFrom.getHeight();
		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 找出最左边一条线的左边和右边，及宽度
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = imageStepFrom.getRGB(x, y);
				int red = (rgb >> 16) & 255;
//TODO				int green = (rgb >> 8) & 255;
//TODO				int blue = (rgb) & 255;
				int m = red;

				if (from <= m && m <= to) {
					// m = m;
				} else {
					m = 0xff;
				}
				imageTo.setRGB(x, y, new Color(m, m, m).getRGB());
			}
		}

		return imageTo;
	}

	public BufferedImage filter_ClearRange(BufferedImage imageStepFrom, int fromX, int toX, int fromY, int toY) throws IOException, FileNotFoundException {
		int width = imageStepFrom.getWidth();
		int height = imageStepFrom.getHeight();
		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int colorWhite = new Color(0xFF, 0xFF, 0xFF).getRGB();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = imageStepFrom.getRGB(x, y);
				if (fromX <= x && x <= toX && fromY <= y && y <= toY) {
					imageTo.setRGB(x, y, colorWhite);
				} else {
					imageTo.setRGB(x, y, rgb);
				}
			}
		}

		return imageTo;
	}

	public int countPointOfRange(BufferedImage imageStepFrom, Point leftTop, Point rightbottom, ColorRange colorRange)
			throws IOException, FileNotFoundException {
//		int width = imageStepFrom.getWidth();
//		int height = imageStepFrom.getHeight();
		int fromX = leftTop.x;
		int toX = rightbottom.x;
		int fromY = leftTop.y;
		int toY = rightbottom.y;

		int cntPoint = 0;
		for (int x = fromX; x <= toX; x++) {
			for (int y = fromY; y < toY; y++) {
				int rgb = imageStepFrom.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				if (colorRange.include(red)) {
					cntPoint++;
				}
			}
		}

		return cntPoint;
	}

	// TODO not used
	@SuppressWarnings("unused")
	public BufferedImage filter_clearAloneColor(BufferedImage imageFrom, int width, int height, int wide, int narrow)
			throws IOException, FileNotFoundException {

		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int[] cntBefore = printColorDistribution(imageFrom);

		int[] cntAfter = new int[256];

		// 找出最左边一条线的左边和右边，及宽度
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// 过滤白色和其他主要颜色
				{
					if (imageFrom.getRGB(x, y) == -1) {
						imageTo.setRGB(x, y, imageFrom.getRGB(x, y));
						continue;
					}

					int rgb = imageFrom.getRGB(x, y);
					int rgbFromRed = (rgb >> 16) & 255;

					if (cntBefore[rgbFromRed] > 300) {
						imageTo.setRGB(x, y, imageFrom.getRGB(x, y));
						continue;
					}
				}

				int[] cntInner = new int[256];
				int[] colorInner = new int[256];

				boolean dealed = false;
				boolean alonePoint = false;
				// xx
				int maxColorXX = 0;
				int maxColorXXCount = 0;
				int maxColorYY = 0;
				int maxColorYYCount = 0;
				{

					int xwide = wide / 2;
					int ywide = narrow / 2;

					for (int my = 0; my <= ywide && y + my < height; my++) {
						for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int my = -1; my >= -ywide && y + my > 0; my--) {
						for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					for (int my = 0; my <= ywide && y + my < height; my++) {
						for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int my = -1; my >= -ywide && y + my > 0; my--) {
						for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					int maxColorCount = 0;
					int maxColor = 0;

					int lastColorCount = 0;
					int lastMaxColor = 0;

					for (int i = 0; i < 0xFE; i++) {
						if (cntInner[i] == 0) {
						} else if (maxColorCount < cntInner[i]) {
							lastColorCount = maxColorCount;

							maxColor = colorInner[i];
							maxColorCount = cntInner[i];
						} else if (maxColorCount == cntInner[i]) {
							lastColorCount = maxColorCount;
							lastMaxColor = maxColor;
						} else if (lastColorCount < cntInner[i]) {
							lastColorCount = cntInner[i];
							lastMaxColor = colorInner[i];
						}
					}

					maxColorXX = maxColor;
					maxColorXXCount = maxColorCount;
				}

				if (dealed == false) {

					int xwide = narrow / 2;
					int ywide = wide / 2;

					for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
						for (int my = 0; my <= ywide && y + my < height; my++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
						for (int my = -1; my >= -ywide && y + my > 0; my--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
						for (int my = 0; my <= ywide && y + my < height; my++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
						for (int my = -1; my >= -ywide && y + my > 0; my--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					int maxColorCount = 0;
					int maxColor = 0;

					int lastColorCount = 0;
					int lastMaxColor = 0;

					for (int i = 0; i < 0xFE; i++) {
						if (cntInner[i] == 0) {
						} else if (maxColorCount < cntInner[i]) {
							lastColorCount = maxColorCount;

							maxColor = colorInner[i];
							maxColorCount = cntInner[i];
						} else if (maxColorCount == cntInner[i]) {
							lastColorCount = maxColorCount;
							lastMaxColor = maxColor;
						} else if (lastColorCount < cntInner[i]) {
							lastColorCount = cntInner[i];
							lastMaxColor = colorInner[i];
						}
					}

					maxColorYY = maxColor;
					maxColorYYCount = maxColorCount;
				}

				int replaceColor = maxColorXXCount > maxColorXXCount ? maxColorXX : maxColorYY;

				imageTo.setRGB(x, y, replaceColor);
			}
		}

		return imageTo;
	}

	// TODO not used
	@SuppressWarnings("unused")
	public BufferedImage filter_Winwinall(BufferedImage imageFrom, int width, int height, int wide, int narrow) throws IOException, FileNotFoundException {

		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int[] cntAfter = new int[256];

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (imageFrom.getRGB(x, y) == -1) {
					imageTo.setRGB(x, y, imageFrom.getRGB(x, y));
					continue;
				}

				int[] cntInner = new int[256];
				int[] colorInner = new int[256];

				boolean dealed = false;
				boolean alonePoint = false;
				// xx
				{

					int xwide = wide / 2;
					int ywide = narrow / 2;

					for (int my = 0; my <= ywide && y + my < height; my++) {
						for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int my = -1; my >= -ywide && y + my > 0; my--) {
						for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					for (int my = 0; my <= ywide && y + my < height; my++) {
						for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int my = -1; my >= -ywide && y + my > 0; my--) {
						for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					int maxColorCount = 0;
					int maxColor = 0;

					int lastColorCount = 0;
					int lastMaxColor = 0;

					for (int i = 0; i < 0xFE; i++) {
						if (cntInner[i] == 0) {
						} else if (maxColorCount < cntInner[i]) {
							lastColorCount = maxColorCount;

							maxColor = colorInner[i];
							maxColorCount = cntInner[i];
						} else if (maxColorCount == cntInner[i]) {
							lastColorCount = maxColorCount;
							lastMaxColor = maxColor;
						} else if (lastColorCount < cntInner[i]) {
							lastColorCount = cntInner[i];
							lastMaxColor = colorInner[i];
						}
					}

					if (lastColorCount != 0 && maxColorCount / lastColorCount > 2) {
						imageTo.setRGB(x, y, maxColor);
						dealed = true;
					}
					if (maxColorCount < 3) {
						alonePoint = true;
					}
				}

				if (dealed == false) {

					int xwide = narrow / 2;
					int ywide = wide / 2;

					for (int mx = 0; mx <= xwide && x + mx < width; mx++) {
						for (int my = 0; my <= ywide && y + my < height; my++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
						for (int my = -1; my >= -ywide && y + my > 0; my--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}
					for (int mx = -1; mx >= -xwide && x + mx > 0; mx--) {
						for (int my = 0; my <= ywide && y + my < height; my++) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
						for (int my = -1; my >= -ywide && y + my > 0; my--) {
							int rgb = imageFrom.getRGB(x + mx, y + my);
							int rgbFromRed = (rgb >> 16) & 255;
							if (rgbFromRed == 0xFF) {
								break;
							}
							cntInner[rgbFromRed]++;
							colorInner[rgbFromRed] = rgb;
						}
					}

					int maxColorCount = 0;
					int maxColor = 0;

					int lastColorCount = 0;
					int lastMaxColor = 0;

					for (int i = 0; i < 0xFE; i++) {
						if (cntInner[i] == 0) {
						} else if (maxColorCount < cntInner[i]) {
							lastColorCount = maxColorCount;

							maxColor = colorInner[i];
							maxColorCount = cntInner[i];
						} else if (maxColorCount == cntInner[i]) {
							lastColorCount = maxColorCount;
							lastMaxColor = maxColor;
						} else if (lastColorCount < cntInner[i]) {
							lastColorCount = cntInner[i];
							lastMaxColor = colorInner[i];
						}
					}

					if (lastColorCount != 0 && maxColorCount / lastColorCount > 2) {
						cntAfter[(maxColor >> 16) & 255] += 1;
						imageTo.setRGB(x, y, maxColor);
						dealed = true;
					}
					if (maxColorCount < 3 && alonePoint) {
						alonePoint = true;
					}
				}
				if (!dealed || alonePoint) {
					cntAfter[(imageFrom.getRGB(x, y) >> 16) & 255] += 1;
					imageTo.setRGB(x, y, imageFrom.getRGB(x, y));

				}
			}
		}

		return imageTo;
	}

	public BufferedImage filter_AverageInMatrix(BufferedImage imageFrom, int matrixSize, ColorRange src, ColorRange matrix)
			throws IOException, FileNotFoundException {

		int width = imageFrom.getWidth();
		int height = imageFrom.getHeight();
		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int centerPos = matrixSize / 2;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int srcRgb = imageFrom.getRGB(x, y);
				int red = (srcRgb >> 16) & 255;

				if (src.include(red)) {
					int xxfrom = x - centerPos;
					xxfrom = xxfrom >= 0 ? xxfrom : 0;
					int xxto = x + centerPos;
					xxto = xxto < width ? xxto : width - 1;

					int yyfrom = y - centerPos;
					yyfrom = yyfrom >= 0 ? yyfrom : 0;
					int yyto = y + centerPos;
					yyto = yyto < height ? yyto : height - 1;

					int sumColor = 0;
					int cntColor = 0;

					for (int xx = xxfrom; xx <= xxto; xx++) {
						for (int yy = yyfrom; yy <= yyto; yy++) {
							int rgb = imageFrom.getRGB(xx, yy);
							int rgbFromRed = (rgb >> 16) & 255;
							// int rgbFrom = 255 - rgbFromRed;

							if (matrix.include(rgbFromRed)) {
								sumColor += rgbFromRed;
								cntColor += 1;
							}
						}
					}

					if (cntColor >= 1) {
						int c = sumColor / cntColor;
						imageTo.setRGB(x, y, new Color(c, c, c).getRGB());
					} else {
						imageTo.setRGB(x, y, srcRgb);
					}
				} else {
					imageTo.setRGB(x, y, srcRgb);
				}
			}
		}

		return imageTo;
	}

	@SuppressWarnings("unused")
	public BufferedImage filter_upgradeWhenNear(BufferedImage imageFrom, int matrixSize, ColorRange colorRange, ColorRange targetColorRange, int replaceColor,
			int workFactor) throws IOException, FileNotFoundException {
		int width = imageFrom.getWidth();
		int height = imageFrom.getHeight();
		BufferedImage imageTo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int centerPos = matrixSize / 2;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				int rgb = imageFrom.getRGB(x, y);
				int red = (rgb >> 16) & 255;
				if (colorRange.include(red)) {

					int xxfrom = x - centerPos;
					xxfrom = xxfrom >= 0 ? xxfrom : 0;
					int xxto = x + centerPos;
					xxto = xxto < width ? xxto : width - 1;

					int yyfrom = y - centerPos;
					yyfrom = yyfrom >= 0 ? yyfrom : 0;
					int yyto = y + centerPos;
					yyto = yyto < height ? yyto : height - 1;

					int cntTargetColor = 0;
					int cntInRangeColor = 0;
					for (int xx = xxfrom; xx <= xxto; xx++) {
						for (int yy = yyfrom; yy <= yyto; yy++) {
							int rgbIn = imageFrom.getRGB(xx, yy);
							int rgbFromRed = (rgbIn >> 16) & 255;
							// int rgbFrom = 255 - rgbFromRed;

							if (targetColorRange.include(rgbFromRed)) {
								cntTargetColor++;
							} else if (colorRange.include(rgbFromRed)) {
								cntInRangeColor++;
							}
						}
					}
					
					if(cntTargetColor==0) continue;
					if (cntTargetColor / matrixSize >= workFactor) {
						imageTo.setRGB(x, y, replaceColor);
					} else {
						imageTo.setRGB(x, y, rgb);
					}
				} else {
					imageTo.setRGB(x, y, rgb);
				}
			}
		}

		return imageTo;
	}

	protected int matchPoint(BufferedImage imageFrom, ColorRange colorRange, int x, int y, short expectedLineWidth, int expectedPointCountXXXXX) {
		int[] pointMatrix = PointMatrixes[expectedLineWidth];
		int expectedPointCount = PointCountMatrixes[expectedLineWidth];
		int cntPoint = 0;
		int yOffset = expectedLineWidth / 2;
		for (int yy = -yOffset; yy < expectedLineWidth - yOffset; yy++) {
			int xWidth = pointMatrix[yy + yOffset];
			for (int xx = -xWidth / 2; xx < xWidth - xWidth / 2; xx++) {
				int px = x + xx;
				int py = y + yy;
				if (px >= 0 && py >= 0) {
					int rgbx = imageFrom.getRGB(px, py);
					int redx = (rgbx >> 16) & 255;
					if (colorRange.include(redx)) {
						cntPoint++;
					}
				}
			}
		}

		if (((float) cntPoint) / expectedPointCount >= 0.7) {
			return cntPoint;
		} else {
			return 0;

		}
	}

	protected int bestMatchPointVertical(BufferedImage imageFrom, ColorRange colorRange, int x, int y, short expectedLineWidth, int expectedPointCount) {
		// 取最匹配的点作为直线的中心。
		int maxZIndex = 0;
		int maxZ = 0;
		int offset = 2;
		for (int z = 0; z <= offset; z++) {
			int cntPoint = matchPoint(imageFrom, colorRange, x + z, y, expectedLineWidth, expectedPointCount);
			if (cntPoint == expectedPointCount) {
				maxZIndex = z;
				return x + maxZIndex;
			}
			if (maxZ < cntPoint) {
				maxZ = cntPoint;
				maxZIndex = z;
			}
		}

		for (int z = -1; z >= -offset; z--) {
			int cntPoint = matchPoint(imageFrom, colorRange, x + z, y, expectedLineWidth, expectedPointCount);
			if (cntPoint == expectedPointCount) {
				maxZIndex = z;
				return x + maxZIndex;
			}
			if (maxZ < cntPoint) {
				maxZ = cntPoint;
				maxZIndex = z;
			}
		}
		if (maxZ == 0) {
			return 0;
		} else {
			return x + maxZIndex;
		}
	}

	protected int bestMatchPointHorizon(BufferedImage imageFrom, ColorRange colorRange, int x, int y, short expectedLineWidth, int expectedPointCount) {
		// 取最匹配的点作为直线的中心。
		int maxZIndex = 0;
		int maxZ = 0;
		int offset = 2;
		for (int z = 0; z <= offset; z++) {
			int cntPoint = matchPoint(imageFrom, colorRange, x, y + z, expectedLineWidth, expectedPointCount);
//			if (cntPoint == expectedPointCount) {
			if ((double) cntPoint / expectedPointCount >= 0.9) {
				maxZIndex = z;
				return y + maxZIndex;
			}
			if (maxZ < cntPoint) {
				maxZ = cntPoint;
				maxZIndex = z;
			}
		}

		for (int z = -1; z >= -offset; z--) {
			int cntPoint = matchPoint(imageFrom, colorRange, x, y + z, expectedLineWidth, expectedPointCount);
			if (cntPoint == expectedPointCount) {
				maxZIndex = z;
				return y + maxZIndex;
			}
			if (maxZ < cntPoint) {
				maxZ = cntPoint;
				maxZIndex = z;
			}
		}

		if ((double) maxZ / expectedPointCount >= 0.66) {
			return y + maxZIndex;
		} else {
			return 0;
		}
	}

	protected LineSegment matchHorizonLine(BufferedImage imageFrom, ColorRange colorRange, Point startPoint, short expectedLineWidth, int lineMinLength,
			short maxOffset, BufferedImage imageTo) {
		LineSegment lineH;
		int width = imageFrom.getWidth();
//		int height = imageFrom.getHeight();
		int expectedPointCount = PointCountMatrixes[expectedLineWidth];
		int halfOfLineWidth = expectedLineWidth / 2;
		// Brush green3Brush = new Brush(imageTo, 3, GREEN_COLOR);

		int ax = 0;
		int ay = 0;
		int bx = 0;
		int by = 0;

		// 向下找第一根线
		int x = startPoint.getX();
		int y = startPoint.getY();
		int[] lineTops = new int[width];

		boolean matched = false;
		for (; y < startPoint.getY() + maxOffset; y++) {
			int rgb = imageFrom.getRGB(x, y);
			int red = (rgb >> 16) & 255;
			if (red == 0xFF) continue; // 忽略空

			if (!colorRange.include(red)) continue;// 当前像素颜色不在范围内，继续寻找

			y += halfOfLineWidth;
			int cntPoint = matchPoint(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);// 标记开始的点

			if ((double) cntPoint / expectedPointCount >= 0.9) {
				matched = true;
				break;
			} else {
				continue;
			}
		}

		if (matched) {
			for (; x < width; x++) {
				// 取最匹配的点作为直线的中心。
				int yTop = bestMatchPointHorizon(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);
				if (yTop > 0) {
					y = yTop;
					lineTops[x] = yTop;
					// green3Brush.drawPoint(x, y - 20);
				} else {
					break;
				}
			}
			bx = x - 1;

			x = startPoint.getX();
			y = lineTops[x];
			for (; x > expectedLineWidth; x--) {
				// 取最匹配的点作为直线的中心。

				int yTop = bestMatchPointHorizon(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);
				if (yTop > 0) {
					y = yTop;
					lineTops[x] = yTop;
					// green3Brush.drawPoint(x, y - 20);
				} else {
					break;
				}
			}

			ax = x + 1;

			// 当前取直线终端的倾角，TODO 后期采取平滑倾角
			int xxRotateFrom = (int) ((bx - ax) * 0.2) + ax;
			int xxRotateTo = (int) ((bx - ax) * 0.8) + ax;

			double rotate = ((double) lineTops[xxRotateTo] - lineTops[xxRotateFrom]) / (xxRotateTo - xxRotateFrom);

			int yyRotateFrom = lineTops[xxRotateFrom];
			int yyRotateTo = lineTops[xxRotateTo];

			ay = yyRotateTo - (int) ((xxRotateTo - ax) * rotate);
			by = (int) ((bx - xxRotateFrom) * rotate) + yyRotateFrom;

			// green3Brush.drawLine(ax, ay, bx, by);
		}

		lineH = LineSegment.line(ax, ay, bx, by);
		return lineH;
	}

	protected LineSegment matchVerticalLine(BufferedImage imageFrom, ColorRange colorRange, Point startPoint, short expectedLineWidth, int lineMinLength,
			short maxOffset, BufferedImage imageTo) {
		LineSegment lineV;
//		int width = imageFrom.getWidth();
		int height = imageFrom.getHeight();
		int halfOfLineWidth = expectedLineWidth / 2;

		int expectedPointCount = PointCountMatrixes[expectedLineWidth];
		// Brush red3Brush = new Brush(imageTo, 3, RED_COLOR);
		// Brush green3Brush = new Brush(imageTo, 3, GREEN_COLOR);
		// Brush blue3Brush = new Brush(imageTo, 3, BLUE_COLOR);
		log.debug("match start from point [{}]", startPoint);

		int ax = 0;
		int ay = 0;

		int bx = 0;
		int by = 0;
		// 竖线
		int x = startPoint.getX();
		int y = startPoint.getY();

		boolean matched = false;
		int[] lineLefts = new int[height];

		for (; x < startPoint.getX() + maxOffset; x++) {
			int rgb = imageFrom.getRGB(x, y);
			int red = (rgb >> 16) & 255;
			if (red == 0xFF) continue; // 忽略空

			if (!colorRange.include(red)) continue;// 当前像素颜色不在范围内，继续寻找

			x += halfOfLineWidth;
			int cntPoint = matchPoint(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);// 标记开始的点
			// int xLeft = bestMatchPointVertical(imageFrom, colorRange, x, y,
			// expectedLineWidth, expectedPointCount);

			if ((float) cntPoint / expectedPointCount >= 0.9) {
				matched = true;
				break;
			} else {
				continue;
			}
		}

		if (matched) {
			for (; y < height; y++) {
				// 取最匹配的点作为直线的中心。
				int xLeft = bestMatchPointVertical(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);
				if (xLeft > 0) {
					x = xLeft;
					lineLefts[y] = xLeft;
					// green3Brush.drawPoint(x - 20, y);
				} else {
					break;
				}
			}
			by = y - 1;

			y = startPoint.getY();
			x = lineLefts[y];
			for (; y > expectedLineWidth; y--) {
				// 取最匹配的点作为直线的中心。

				int xLeft = bestMatchPointVertical(imageFrom, colorRange, x, y, expectedLineWidth, expectedPointCount);
				if (xLeft > 0) {
					x = xLeft;
					lineLefts[y] = xLeft;
					// green3Brush.drawPoint(x - 20, y);
				} else {
					break;
				}
			}

			ay = y + 1;

			// 当前取直线终端的倾角，TODO 后期采取平滑倾角
			int yyRotateFrom = (int) ((by - ay) * 0.2) + ay;
			int yyRotateTo = (int) ((by - ay) * 0.8) + ay;

			double rotate = ((double) lineLefts[yyRotateTo] - lineLefts[yyRotateFrom]) / (yyRotateTo - yyRotateFrom);

			int xxRotateFrom = lineLefts[yyRotateFrom];
			int xxRotateTo = lineLefts[yyRotateTo];

			ax = xxRotateTo - (int) ((yyRotateTo - ay) * rotate);
			bx = (int) ((by - yyRotateFrom) * rotate) + xxRotateFrom;

			// green3Brush.drawLine(ax, ay, bx, by);
		}
		lineV = LineSegment.line(ax, ay, bx, by);
		return lineV;
	}

	public static Point getCrossPoint(LineSegment lsegA, LineSegment lsegB, boolean allowExtend) {
		float x;
		float y;
		float x1 = lsegA.getA().getX();
		float y1 = lsegA.getA().getY();
		float x2 = lsegA.getB().getX();
		float y2 = lsegA.getB().getY();
		float x3 = lsegB.getA().getX();
		float y3 = lsegB.getA().getY();
		float x4 = lsegB.getB().getX();
		float y4 = lsegB.getB().getY();
		float k1 = Float.MAX_VALUE;
		float k2 = Float.MAX_VALUE;
		boolean flag1 = false;
		boolean flag2 = false;

		if ((x1 - x2) == 0) flag1 = true;
		if ((x3 - x4) == 0) flag2 = true;

		if (!flag1) k1 = (y1 - y2) / (x1 - x2);
		if (!flag2) k2 = (y3 - y4) / (x3 - x4);

		if (k1 == k2) return null;

		if (flag1) {
			if (flag2) return null;
			x = x1;
			if (k2 == 0) {
				y = y3;
			} else {
				y = k2 * (x - x4) + y4;
			}
		} else if (flag2) {
			x = x3;
			if (k1 == 0) {
				y = y1;
			} else {
				y = k1 * (x - x2) + y2;
			}
		} else {
			if (k1 == 0) {
				y = y1;
				x = (y - y4) / k2 + x4;
			} else if (k2 == 0) {
				y = y3;
				x = (y - y2) / k1 + x2;
			} else {
				x = (k1 * x2 - k2 * x4 + y4 - y2) / (k1 - k2);
				y = k1 * (x - x2) + y2;
			}
		}
		if (allowExtend || between(x1, x2, x) && between(y1, y2, y) && between(x3, x4, x) && between(y3, y4, y)) {
			Point point = new Point((int) x, (int) y);
			if (point.equals(lsegA.getA()) || point.equals(lsegA.getB())) return null;
			return point;
		} else {
			return null;
		}
	}

	public static boolean between(float a, float b, float target) {
		if (target >= a - 0.01 && target <= b + 0.01 || target <= a + 0.01 && target >= b - 0.01)
			return true;
		else
			return false;
	}
}
