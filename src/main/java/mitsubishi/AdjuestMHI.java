package mitsubishi;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static imageanalyzer.common.Colors.*;
import static imageanalyzer.common.Shapes.*;

import javax.imageio.ImageIO;

import imageanalyzer.StartColorDistribute;
import imageanalyzer.common.Point;

public class AdjuestMHI extends StartColorDistribute {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String basePath = "D:\\FilesAndDatas\\serverResources\\formMHI";
		
		String[] filePathArr = new File(basePath).list();
		for(String filePath : filePathArr) {
			
		new AdjuestMHI().parse(new File(basePath+"\\"+filePath));
		
		}
		System.out.println("文件解析完成");
	}

	public AdjuestMHI() throws IOException {
	}

	private BufferedImage convertImageRGBType(BufferedImage in) {
		if (in.getType() == BufferedImage.TYPE_INT_ARGB) {
			return in;
		}
		BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(in, 0, 0, null);
		g.dispose();
		return newImage;
	}

	public BufferedImage parse(File file) throws IOException, FileNotFoundException {
		this.fileName = file.getName();
		BufferedImage lastStep = (BufferedImage) ImageIO.read(file);
		lastStep = convertImageRGBType(lastStep);
		super.debug = false;
		lastStep = filterColor(lastStep);
		return lastStep;
	}

	protected BufferedImage filterColor(BufferedImage lastStep) throws IOException, FileNotFoundException {
		// 过滤所有超过229的色彩
		lastStep = filter_ColorRange(lastStep, 0, 229);

		// 如果亮灰色周围没有其他更深的颜色，则更新成白色
		lastStep = filter_ClearAloneLightGray(lastStep, 5, colorrange(193, 229), color(0xFF)); // OK
		// 101
		// 如果是0-37 则更新成字体颜色
		lastStep = filter_colorConvert(lastStep, colorrange(0, 50), color(1, 0xFF, 0xFF));
		lastStep = filter_AverageInMatrix(lastStep, 3, colorrange(50, 229), colorrange(0, 229));// OK
		lastStep = filter_AverageInMatrix(lastStep, 5, colorrange(50, 229), colorrange(0, 229));// OK
		lastStep = filter_AverageInMatrix(lastStep, 10, colorrange(50, 229), colorrange(0, 229));// OK
		lastStep = filter_colorConvert(lastStep, colorrange(0, 103), color(1));
		lastStep = filter_colorConvert(lastStep, colorrange(104, 229), color(0xFF));
		printColorDistribution("5- clear-rect", lastStep);

		return lastStep;
	}
}
