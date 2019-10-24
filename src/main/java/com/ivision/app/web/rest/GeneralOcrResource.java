/**
 * 
 */
package com.ivision.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.domain.BeanRsource;
import com.ivision.app.domain.DeliverMessage;
import com.ivision.app.domain.DeliveryDetails;
import com.ivision.app.domain.GeneralOcrBean;
import com.ivision.app.domain.Invoice;
import com.ivision.app.domain.Location;
import com.ivision.app.domain.MxDeliverMessage;
import com.ivision.app.domain.MxDeliveryDetails;
import com.ivision.app.domain.MxInvoice;
import com.ivision.app.domain.WordsResult;
import com.ivision.app.domain.YdDeliverMessage;
import com.ivision.app.domain.YdDeliveryDetails;
import com.ivision.app.domain.YdInvoice;

/**
 * 调用百度通用文字识别API，实现文字识别
 *<p>
 *实现日语文字识别
 *</p>
 *
 */

@RestController
@RequestMapping("/api/ocr/general")
public class GeneralOcrResource {

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
		
		BeanRsource beanRsource = new BeanRsource();

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

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义文件下载本地新名称
			String newFileName = dateFormat.format(now) + System.nanoTime() + fileType;

			// 新文件的路径
			String newFilePath = filePath + newFileName;

			try {

				// 将传来的文件写入新建的文件
				uploadFile.transferTo(new File(newFilePath));
				beanRsource.setFilepath(newFilePath);
					
					return  ResponseEntity.ok(beanRsource);

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
	public ResponseEntity<Map<String,List<WordsResult>>> showUploadFileDetails(@RequestParam(value = "filepath") String filepath)
			throws IOException {
		Map<String,List<WordsResult>> wordsResults = new HashMap<>();
		
		List<WordsResult> resultByIocr = getResultByIocr(filepath);
		wordsResults.put("wordsResult", resultByIocr);
		
				return ResponseEntity.ok(wordsResults);
	}
	
	
	/**
	 * 导出Excel，并下载
	 * 
	 * @param filepath
	 * @param response
	 * @return
	 */
	@GetMapping("/download")
	public String exportExcel(@RequestParam(value = "filepath") String filepath, HttpServletResponse response) {

		response.setContentType("application/force-download;charset=UTF-8");

		try {
			ServletOutputStream out = response.getOutputStream();
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义Excel文件新名称
			String newFileName = dateFormat.format(now) + System.currentTimeMillis();
			String fileName = new String((newFileName).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

			// 调用百度API接口
			List<WordsResult> resultByIocrList = getResultByIocr(filepath);

						String title = "個人精算通知メール";
						// 获取表头1
						String[] head = { title };
						String[] headnum = { "0,0,0,15" };
						// 获取表头2
						//String[] head1 = { "发货单号", "发货单位", "发货日期", "地址", "联系电话", "备注", "经手人（签字或盖章）", "领料人（签字或盖章）" };
						// String[] headnum1 = { "1,1,0,15" };
						//String[] titles = { "仓库", "料号", "品牌", "单位", "数量", "单重", "合计重量", "批次号", "出货日期", "备注" };

						this.exportFencers(head, headnum, null, null, null, out, null, resultByIocrList,
								null, null, null, null);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
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
	public List<WordsResult> getResultByIocr(String iocrFilepath) throws IOException {

		AipOcr client = new AipOcr(appId, apiKey, secretKey);
		List<WordsResult> wordsResultList = new ArrayList<>();

		// client.setConnectionTimeoutInMillis(2000);
		// client.setSocketTimeoutInMillis(6000);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		options.put("language_type", "JAP");
		// options.put("detect_language","true");

		// 参数为本地路径
		// JSONObject custom = client.custom(iocrFilepath, options);
		JSONObject jsonObject = client.general(iocrFilepath, options);
		
		
		String wordsResultNum = null;

		wordsResultNum = jsonObject.get("words_result_num").toString();
		
		
		

		if ((Integer.valueOf(wordsResultNum))>=1) {
			
			JSONArray jsonArray = jsonObject.getJSONArray("words_result");
			List<Object> list = jsonArray.toList();
			
			for (int i = 0; i < list.size(); i++) {
				
				WordsResult wordResult = new WordsResult();
				HashMap<String, String> map =(HashMap) list.get(i);
				wordResult.setWords(map.get("words"));
				wordsResultList.add(wordResult);
				
			}
		}

		return wordsResultList;

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
	private void exportFencers(String[] head, String[] headnum, String[] head1, String[] headnum1, String[] titles,
			ServletOutputStream out, DeliverMessage deliverMessage, List<WordsResult> deliveryDetails,
			MxDeliverMessage mxDeliverMessage, List<MxDeliveryDetails> mxDeliveryDetails,
			YdDeliverMessage ydDeliverMessage, List<YdDeliveryDetails> ydDeliveryDetails) throws Exception {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// 第三步，在sheet中添加表头第0行,老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			//HSSFRow hssfRow1 = hssfSheet.createRow(1);
			HSSFRow hssfRow2 = hssfSheet.createRow(2);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			// hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;// 第一行

			//HSSFCell hssfCell1 = null;// 第二行

			HSSFCell hssfCell2 = null;// 第三行

			// 第一行表头
			for (int i = 0; i < head.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(head[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 动态合并单元格
			for (int i = 0; i < headnum.length; i++) {

				hssfSheet.autoSizeColumn(i, true);
				String[] temp = headnum[i].split(",");
				Integer startrow = Integer.parseInt(temp[0]);
				Integer overrow = Integer.parseInt(temp[1]);
				Integer startcol = Integer.parseInt(temp[2]);
				Integer overcol = Integer.parseInt(temp[3]);
				hssfSheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
			}

//			// 第二行表头
//			for (int i = 0; i < head1.length; i++) {
//				hssfCell1 = hssfRow1.createCell(i);// 列索引从0开始
//				hssfCell1.setCellValue(head1[i]);// 列名1
//				hssfCell1.setCellStyle(hssfCellStyle);// 列居中显示
//			}

			if (deliverMessage != null) {

				hssfRow = hssfSheet.createRow(2);

				// 创建单元格，并设置值
				String deliveryNo = deliverMessage.getDeliveryNo();
				if (StringUtils.isEmpty(deliveryNo)) {
					deliveryNo = "-";
				}
				hssfRow.createCell(0).setCellValue(deliveryNo);

				String deliveryCompany = "";
				if (deliverMessage.getDeliveryCompany() != null) {
					deliveryCompany = deliverMessage.getDeliveryCompany();
				}
				hssfRow.createCell(1).setCellValue(deliveryCompany);

				String deliveryDate = "";
				if (deliverMessage.getDeliveryDate() != null) {
					deliveryDate = deliverMessage.getDeliveryDate();
				}
				hssfRow.createCell(2).setCellValue(deliveryDate);

				String address = "";
				if (deliverMessage.getAddress() != null) {
					address = deliverMessage.getAddress();
				}
				hssfRow.createCell(3).setCellValue(address);

				String contactNUmber = "";
				if (deliverMessage.getContactNUmber() != null) {
					contactNUmber = deliverMessage.getContactNUmber();
				}
				hssfRow.createCell(4).setCellValue(contactNUmber);

				String note = "";
				if (deliverMessage.getNote() != null) {
					note = deliverMessage.getNote();
				}
				hssfRow.createCell(5).setCellValue(note);

				String handler = "";
				if (deliverMessage.getHandler() != null) {
					handler = deliverMessage.getHandler();
				}
				hssfRow.createCell(6).setCellValue(handler);

				String picker = "";
				if (deliverMessage.getPicker() != null) {
					picker = deliverMessage.getPicker();
				}
				hssfRow.createCell(7).setCellValue(picker);

			}

//			for (int i = 0; i < headnum1.length; i++) {
//
//				hssfSheet.autoSizeColumn(i, true);
//				String[] temp = headnum1[i].split(",");
//				Integer startrow = Integer.parseInt(temp[0]);
//				Integer overrow = Integer.parseInt(temp[1]);
//				Integer startcol = Integer.parseInt(temp[2]);
//				Integer overcol = Integer.parseInt(temp[3]);
//				hssfSheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
//			}
			// 第三行表头 不需要合并单元格
			
			if (titles != null && !(titles.length < 0))
			for (int i = 0; i < titles.length; i++) {
				hssfCell2 = hssfRow2.createCell(i);// 列索引从0开始
				hssfCell2.setCellValue(titles[i]);// 列名1
				hssfCell2.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 第五步，写入实体数据

			if (deliveryDetails != null && !deliveryDetails.isEmpty()) {

				for (int i = 0; i < deliveryDetails.size(); i++) {
					hssfRow = hssfSheet.createRow(i + 3);
					WordsResult de = deliveryDetails.get(i);

					// 第六步，创建单元格，并设置值
					String storehouseNo = de.getWords();

					if (StringUtils.isEmpty(storehouseNo)) {
						storehouseNo = "-";
					}
					hssfRow.createCell(0).setCellValue(storehouseNo);

				}

			}
			
			//明歆制衣
			else if (mxDeliveryDetails != null && !mxDeliveryDetails.isEmpty()) {

				for (int i = 0; i < mxDeliveryDetails.size(); i++) {
					hssfRow = hssfSheet.createRow(i + 3);
					MxDeliveryDetails de = mxDeliveryDetails.get(i);

					// 第六步，创建单元格，并设置值
					String storehouseNo = de.getStyleNo();

					if (StringUtils.isEmpty(storehouseNo)) {
						storehouseNo = "-";
					}
					hssfRow.createCell(0).setCellValue(storehouseNo);

					String materialNo = "";
					if (de.getStyle() != null) {
						materialNo = de.getStyle();
					}
					hssfRow.createCell(1).setCellValue(materialNo);

					String brand = "";
					if (de.getColor() != null) {
						brand = de.getColor();
					}
					hssfRow.createCell(2).setCellValue(brand);

					String unit = "";
					if (de.getUnit() != null) {
						unit = de.getUnit();
					}
					hssfRow.createCell(3).setCellValue(unit);

					String quantity = "";
					if (de.getModelS() != null) {
						quantity = de.getModelS();
					}
					hssfRow.createCell(4).setCellValue(quantity);

					String singleWeight = "";
					if (de.getModelM() != null) {
						singleWeight = de.getModelM();
					}
					hssfRow.createCell(5).setCellValue(singleWeight);

					String totalWeight = "";
					if (de.getModelL() != null) {
						totalWeight = de.getModelL();
					}
					hssfRow.createCell(6).setCellValue(totalWeight);

					String batchNo = "";
					if (de.getSubtotal() != null) {
						batchNo = de.getSubtotal();
					}
					hssfRow.createCell(7).setCellValue(batchNo);

					String date = "";
					if (de.getUnitPrice() != null) {
						date = de.getUnitPrice();
					}
					hssfRow.createCell(8).setCellValue(date);

					String account = "";
					if (de.getAccount() != null) {
						account = de.getAccount();
					}
					hssfRow.createCell(9).setCellValue(account);
					
					String comment = "";
					if (de.getComment() != null) {
						comment = de.getComment();
					}
					hssfRow.createCell(10).setCellValue(comment);

				}

			}
			
			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
				workbook.close();
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("导出信息失败！");
		}

	}

}
