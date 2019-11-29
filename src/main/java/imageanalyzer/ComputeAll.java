package imageanalyzer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ComputeAll extends ComputeOne {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		File folder = new File(args[0]);
		File tmp = new File("tmp");

		File[] files = folder.listFiles();

		for (File file : files) {
			BufferedImage lastStep = new ComputeAll().computeRect(file);
			ImageIO.write(lastStep, "bmp", new FileOutputStream("dest2\\" + file.getName() + "_result.bmp"));
		}

//		String fileName = "SKM_C454e19111215260_0019.tif1574392416780__adjustDataRect.bmp";
//		new ComputeAll().compute(new File(fileName));

	}

	public BufferedImage computeRect(File file) throws IOException, FileNotFoundException {
		this.fileName = file.getName();

		BufferedImage lastStep = (BufferedImage) ImageIO.read(file);

		lastStep = computerRect(lastStep);

		return lastStep;

	}

}
