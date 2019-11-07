package com.ivision.app.web.rest;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
/**
 * 类功能描述：
 *
 * @author：xxx
 * @createTime：2019/1/4 9:00
 */
public class PdfToImageResource {
 
    private static final Logger logger = LoggerFactory.getLogger(PdfToImageResource.class);
 
    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 500;
    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";
 
    /**
     * pdf转图片
     *
     * @param pdfPath PDF路径
     * @return 图片路径
     * @throws IOException 
     * @throws InvalidPasswordException 
     */
    public static void pdfToImage(String pdfPath, String imgPath) throws InvalidPasswordException, IOException {
        try {
                       
           System.setProperty("sun.java2d.cmm","sun.java2d.cmm.kcms.KcmsServiceProvider");
            //图像合并使用参数
            // 总宽度
            int width = 0;
            // 保存一张图片中的RGB数据
            int[] singleImgRGB;
            int shiftHeight = 0;
            //保存每张图片的像素值
            BufferedImage imageResult = null;
            //利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(new File(pdfPath));
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            //循环每个页码
            for (int i = 0, len = pdDocument.getNumberOfPages(); i < len; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);
                int imageHeight = image.getHeight();
                int imageWidth = image.getWidth();
                //计算高度和偏移量
                if (i == 0) {
                    //使用第一张图片宽度;
                    width = imageWidth;
                    //保存每页图片的像素值
                    imageResult = new BufferedImage(width, imageHeight * len, BufferedImage.TYPE_INT_RGB);
                } else {
                    // 计算偏移高度
                    shiftHeight += imageHeight;
                }
                singleImgRGB = image.getRGB(0, 0, width, imageHeight, null, 0, width);
                // 写入流中
                imageResult.setRGB(0, shiftHeight, width, imageHeight, singleImgRGB, 0, width);
            }
            pdDocument.close();
            // 写图片
            ImageIO.write(imageResult, DEFAULT_FORMAT, new File(imgPath));
        } catch (Exception e) {
           logger.error("PDF转图片失败");
            e.printStackTrace();
        }
    }
 
 
    public static void main(String[] args) throws InvalidPasswordException, IOException {
  pdfToImage("C:\\Users\\wanglei\\Desktop\\demo\\pdf\\菱信 CN NO.23.pdf","C:\\Users\\wanglei\\Desktop\\demo\\pdf\\SKM_C454e19103013291.jpg");
  System.out.println("导出图片成功");
    }
 
}