package imageanalyzer;

import static imageanalyzer.common.Colors.colorrange;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageanalyzer.common.Point;

public class AdjuestAll extends StartColorDistribute {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File folder = new File(args[0]);
		File tmp = new File("tmp");

		File[] files = folder.listFiles();

		for (File file : files) {
			BufferedImage lastStep = new AdjuestAll().parse(file);
			ImageIO.write(lastStep, "png", new FileOutputStream("dest\\" + file.getName() + "_result.png"));
		}
	}
	BufferedImage mask;
	public AdjuestAll() throws IOException {
		mask = (BufferedImage) ImageIO.read(new File("empty-mask2.bmp"));
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

//		lastStep = filter_colorConvert(lastStep, colorrange(31, 250), color(0xFF));

		lastStep = minus(lastStep, mask, colorrange(1, 229));
		printColorDistribution("minus", lastStep);
		
		return lastStep;
	}


}
