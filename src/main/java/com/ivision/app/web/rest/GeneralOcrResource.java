/**
 * Copyright 2019 ivision.com 
 */
package com.ivision.app.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.cache.Cache;
import com.ivision.app.domain.BaseResource;
import com.ivision.app.domain.IvisionSurveyBean;
import com.ivision.app.domain.MitsubishiSurvey;
import com.ivision.app.domain.GeneralOcrWordsResult;
import com.ivision.app.domain.IvisionAndNriSemira;

/**
 * 调用百度通用文字识别API，实现文字识别
 * <p>
 * 实现日语文字识别
 * </p>
 *
 */

@RestController
@RequestMapping("/api/ocr/general")
public class GeneralOcrResource {
	
	private static List<IvisionAndNriSemira> IvisionAndNriSemiraList = new ArrayList<>();
	private static List<MitsubishiSurvey> mitsubishiSurveyList = new ArrayList<>();
	
	// 设置APPID/AK/SK
	@Value("${iocr.app.id}")
	private String appId;

	@Value("${iocr.api.key}")
	private String apiKey;

	@Value("${iocr.secret.key}")
	private String secretKey;

	// 定义上传文件的存放位置
	@Value("${file.downLoad.path}")
	private String filePath;

	/**
	 * 实现文件上传
	 * 
	 * @param uploadFiles 接收上传文件对象
	 * @return Map<String, List<String>> 返回文件路径
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public ResponseEntity<Object> getfileRecord(
			@RequestParam(value = "file", required = false) MultipartFile[] uploadFiles) throws IOException {

		BaseResource baseResource = new BaseResource();

		// 判断文件夹是否存在,不存在则创建
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		for (MultipartFile uploadFile : uploadFiles) {
			// 获取原始图片的扩展名
			String originalFileName = uploadFile.getOriginalFilename();

			// 获取文件类型
			String fileType = originalFileName.substring(originalFileName.lastIndexOf("."));

			if (!(".jpg".equalsIgnoreCase(fileType) || ".jpeg".equalsIgnoreCase(fileType)
					|| ".bpm".equalsIgnoreCase(fileType) || ".png".equalsIgnoreCase(fileType)
					|| ".tif".equalsIgnoreCase(fileType))) {
				baseResource.setErrorMessage("您上传的文件有误，请再确认一下");

				return ResponseEntity.ok(baseResource);

			}

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义文件下载本地新名称
			String newFileName = dateFormat.format(now) + System.nanoTime() + fileType;

			// 新文件的路径
			String newFilePath = filePath + newFileName;

			try {

				// 将传来的文件写入新建的文件
				uploadFile.transferTo(new File(newFilePath));

				IvisionSurveyBean ivisionSurvey = getResultByIocr(newFilePath);

				ivisionSurvey.setFilepath(newFilePath);

				Cache.put("ivisionSurvey", ivisionSurvey, Cache.CACHE_HOLD_TIME_24H);

				return ResponseEntity.ok(ivisionSurvey);

			} catch (FileAlreadyExistsException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return null;

	}

	/**
	 * 获取上传文件明细
	 * 
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/showDetails")
	public ResponseEntity<IvisionSurveyBean> showUploadFileDetails(@RequestParam(value = "filepath") String filepath)
			throws IOException {

		IvisionSurveyBean ivisionSurvey = (IvisionSurveyBean) Cache.get("ivisionSurvey");

		return ResponseEntity.ok(ivisionSurvey);
	}

	/**
	 * 导出Excel，并下载
	 * 
	 * @param filepath
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/download")
	public void exportExcel(@RequestParam(value = "filepath") String filepath, HttpServletResponse response) throws Exception {

		response.setContentType("application/force-download;charset=UTF-8");

			ServletOutputStream out;
			try {
				out = response.getOutputStream();
			
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义Excel文件新名称
			String newFileName = dateFormat.format(now) + System.currentTimeMillis();
			String fileName = new String((newFileName).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

			// 调用百度API接口
			IvisionSurveyBean ivisionSurvey = (IvisionSurveyBean) Cache.get("ivisionSurvey");
			List<GeneralOcrWordsResult> resultByIocrList = ivisionSurvey.getWordsResult();

			this.exportFencers(out, resultByIocrList);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}



	/**
	 * 百度文字识别位置高精度版API调用（返回数据结构不佳）
	 * 
	 * iOCR自定义模板文字识别（效果较好）
	 * 
	 * @param filepath
	 * @return JSONObject
	 * @throws IOException
	 */
	public IvisionSurveyBean getResultByIocr(String iocrFilepath) throws IOException {

		// 实例化百度文字识别接口，并传入必要参数
		AipOcr client = new AipOcr(appId, apiKey, secretKey);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(6000);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		// 指定设定识别语言为 日语;检测图片朝向;检测语言类别
		options.put("language_type", "JAP");
		options.put("detect_direction", "true");
		options.put("detect_language", "true");
		options.put("probability", "true");

		// 设定是否检测语言种类参数
		// options.put("detect_language","true");

		// 参数为本地路径
		JSONObject jsonObject = client.general(iocrFilepath, options);

		String jsonObjectStr = jsonObject.toString();
		IvisionSurveyBean IvisionSurvey = JSON.parseObject(jsonObjectStr, IvisionSurveyBean.class);

		return IvisionSurvey;
	}

	/**
	 * 导出识别文字信息为Excel文件
	 * 
	 * @param head
	 * @param headnum
	 * @param head1
	 * @param headnum1
	 * @param titles
	 * @param out
	 * @param deliverMessage
	 * @param deliveryDetails
	 * @throws Exception
	 */
	private void exportFencers(ServletOutputStream out, List<GeneralOcrWordsResult> deliveryDetails)
			throws Exception {
		// 读取源文件
        FileInputStream fis = new FileInputStream("D:\\FilesAndDatas\\serverResources\\三菱重工MGS-CN调查问卷.xlsx");
        XSSFWorkbook workBook;
		try {
			workBook = new XSSFWorkbook(fis);

        // 进行模板的克隆(接下来的操作都是针对克隆后的sheet)
        XSSFSheet sheet = workBook.cloneSheet(0);
        workBook.setSheetName(0, "调查问卷结果"); // 给sheet命名

        // 插入行
         //sheet.shiftRows(4, 4 + mitsubishiSurveyList.size(), mitsubishiSurveyList.size(), false, false);// 第1个参数是指要开始插入的行，第2个参数是结尾行数,第三个参数表示动态添加的行数
        for (GeneralOcrWordsResult wordsResult : deliveryDetails) {
            XSSFRow creRow = sheet.createRow(0);
            
			creRow.createCell(1).setCellValue(wordsResult.getWords());
        }

        // 输出为一个新的Excel，也就是动态修改完之后的excel
        workBook.removeSheetAt(0); // 移除workbook中的模板sheet
        workBook.write(out);

        fis.close();
        out.flush();
        out.close();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 /**
    *
    *(2003 xls后缀 导出)
    * @param TODO
    * @return void 返回类型
    * @author xsw
    * @2016-12-7上午10:44:00
    */
   public  void createXLS(String importFilePath,String exportFilePath) throws IOException{
       try {
           //excel模板路径
           File fi=new File(importFilePath);
           POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
           //读取excel模板
           HSSFWorkbook wb = new HSSFWorkbook(fs);
           //读取了模板内所有sheet内容
           HSSFSheet sheet = wb.getSheetAt(0);

           //如果这行没有了，整个公式都不会有自动计算的效果的
           sheet.setForceFormulaRecalculation(true);


           //在相应的单元格进行赋值
           HSSFCell cell = sheet.getRow(11).getCell(6);//第11行 第6列
           cell.setCellValue(1);
           HSSFCell cell2 = sheet.getRow(11).getCell(7);
           cell2.setCellValue(2);
           sheet.getRow(12).getCell(6).setCellValue(12);
           sheet.getRow(12).getCell(7).setCellValue(12);
           //修改模板内容导出新模板
           FileOutputStream out = new FileOutputStream(exportFilePath);
           wb.write(out);
           out.close();
       }catch (Exception e) {
           System.out.println("文件读取错误!");
       }

   }

   /**
    *
    *(2007 xlsx后缀 导出)
    * @param
    * @return void 返回类型
    * @author xsw
    * @2016-12-7上午10:44:30
    */
   public  void createXLSX(String importFilePath,String exportFilePath) throws IOException{
       //excel模板路径
       File fi=new File(importFilePath);
       InputStream in = new FileInputStream(fi);
       //读取excel模板
       XSSFWorkbook wb = new XSSFWorkbook(in);
       //读取了模板内所有sheet内容
       XSSFSheet sheet = wb.getSheetAt(0);

       //如果这行没有了，整个公式都不会有自动计算的效果的
       sheet.setForceFormulaRecalculation(true);


       //在相应的单元格进行赋值
       XSSFCell cell = sheet.getRow(11).getCell(6);//第12行 第7列
       cell.setCellValue(1);
       XSSFCell cell2 = sheet.getRow(11).getCell(7);
       cell2.setCellValue(2);
       sheet.getRow(12).getCell(6).setCellValue(3);
       sheet.getRow(12).getCell(7).setCellValue(4);
       //修改模板内容导出新模板
       FileOutputStream out = new FileOutputStream(exportFilePath);
       wb.write(out);
       out.close();
   }

   /**
    * @param @param  file
    * @param @return
    * @param @throws IOException
    * @return List<String> (excel每行拼接成List中的String)
    * @throws
    * @Title: readExcel
    * @Description: TODO(对外提供读取excel 的方法)
    */
   public  synchronized void readExcel(String importFilePath,String exportFilePath) throws IOException {
       File file=new File(importFilePath);
       String fileName = file.getName();
       //List<String> list = new ArrayList<String>();
       //根据其名称获取后缀
       String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
               .substring(fileName.lastIndexOf(".") + 1);
       if ("xls".equals(extension)) {
          // read2003Excel(new FileInputStream(file),exportFilePath);
       } else if ("xlsx".equals(extension) || "xlsm".equals(extension)) {
          // read2007Excel(new FileInputStream(file),exportFilePath);
       } else if ("tmp".equals(extension)) {
          // read2007Excel(new FileInputStream(file),exportFilePath);
       } else {
           throw new IOException("不支持的文件类型");
       }
   }
   
   /**
    * 替换单元格的内容，单元格的获取位置是合并单元格之前的位置，也就是下标都是合并之前的下表
    * 
    * @param cell
    *            单元格
    * @param value
    *            需要设置的值
    */
   public static void replaceCellValue(Cell cell, Object value) {
       String val = value != null ? String.valueOf(value) : "";
       cell.setCellValue(val);
   }

}
