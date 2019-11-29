package imageanalyzer;

import static imageanalyzer.common.Colors.colorrange;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageanalyzer.common.Point;

// 根据整体框定位图片位置和偏移，并调整
public class StartLineFilter extends StartColorDistribute {

	public static void main(String[] args) throws FileNotFoundException, IOException {
//		File file = new File("images\\back2.png");

//		String fileName = "3580.tif";// .tif";
//		String fileName = "9210.png";// .tif";
//		String fileName = "1111.png";// .tif";

//		String fileName = "4400-04.tif";// .tif"; 包括两者

//		String fileName = "4400-02.tif";// .tif";字体等
//		String fileName = "4400-99.tif";// .tif"; // 选择框
		String fileName = "SKM_C454e19111215260_0001.tif_result.bmp";// .tif";
//		String fileName = "4400.tif1573700426462__7- color 61-229.bmp";// .tif";

		new StartLineFilter().parseRecogizeRect(fileName);

	}

	public void parseRecogizeRect(String fileName) throws IOException, FileNotFoundException {
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

		lastStep = adjustDataRect(lastStep, new Point(200, 500), colorrange(0, 100), (short) 10, (short) 200, 200);
		printColorDistribution("adjustDataRect", lastStep);

	}


}
