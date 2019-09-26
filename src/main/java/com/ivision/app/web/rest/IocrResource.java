package com.ivision.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.domain.DeliverMessage;
import com.ivision.app.domain.DeliveryDetails;
import com.ivision.app.domain.Invoice;
import com.ivision.app.service.util.ExcelUtils;

/**
 * 
 * 调用百度API实现文字识别
 * 
 * <p>
 * 实现文件的上传，下载及页面展示
 * </p>
 */
@RestController
@RequestMapping("/api")
public class IocrResource {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

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
	 * @param uploadFiles
	 * @return Map<String, List<String>> 返回文件路径
	 * @throws IOException
	 */
	@PostMapping("/upload")
	public ResponseEntity<Map<String, List<String>>> getfileRecord(
			@RequestParam(value = "file", required = false) MultipartFile[] uploadFiles) throws IOException {

		Map<String, List<String>> filePathMap = new HashMap<>();

		List<String> filePathList = new ArrayList<String>();

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
			String newFileName = dateFormat.format(now) + System.currentTimeMillis() + fileType;
			// 新文件的路径
			String newFilePath = filePath + newFileName;
			filePathList.add(newFilePath);
			filePathMap.put("filepaths", filePathList);

			try {
				uploadFile.transferTo(new File(newFilePath)); // 将传来的文件写入新建的文件

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return ResponseEntity.ok(filePathMap);

	}

	/**
	 * 获取上传文件明细
	 * 
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/showDetails")
	public ResponseEntity<Invoice> showUploadFileDetails(@RequestParam(value = "filepath") String filepath)
			throws IOException {
		// 调用百度API
		JSONObject resultByIocr = getResultByIocr(filepath);

		// 将返回的数据转换为实体对象
		Invoice invoice = jsonToObject(resultByIocr);
		if (invoice != null) {

			return ResponseEntity.ok(invoice);

		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * 导出Excel，并下载
	 * 
	 * @param filepath
	 * @param response
	 * @return
	 */
	@GetMapping("/download")
	public String export(@RequestParam(value = "filepath") String filepath, HttpServletResponse response) {
		response.setContentType("application/binary;charset=UTF-8");

		try {
			ServletOutputStream out = response.getOutputStream();
			String newFileName = "北京神丰科技有限公司";
			String name = new String(URLEncoder.encode(newFileName, "utf-8"));
			String fileName = new String((name + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

			// 调用百度API接口
			JSONObject resultByIocr = getResultByIocr(filepath);
			Invoice invoice = jsonToObject(resultByIocr);
			// Events e = eventsdao.getEventsInfoById(ids);
			String title = invoice.getTitle();
			DeliverMessage deliverMessage = invoice.getDeliverMessage();
			List<DeliveryDetails> deliveryDetails = invoice.getDeliveryDetails();

			// String time = raceTime + " " + startTime;
			// 获取表头1
			String[] head = { title };
			String[] headnum = { "0,0,0,15" };
			// 获取表头2
			String[] head1 = { "发货单号", "发货单位", "发货日期", "地址", "联系电话", "备注", "经手人（签字或盖章）", "领料人（签字或盖章）" };
			String[] headnum1 = { "1,1,0,15" };
			String[] titles = { "仓库", "料号", "品牌", "单位", "数量", "单重", "合计重量", "批次号", "出货日期", "备注" };

			this.exportFencers(head, headnum, head1, headnum1, titles, out, deliverMessage, deliveryDetails);

//  if(type==1){
//      // 导出个人赛参赛选手信息
//      
//  } else if(type==2){
//      // 导出团体赛参赛选手信息
//      
//  }

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
	}

	/**
	 * 实现Excel文件的下载
	 * 
	 * @param filepath
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/download1")
	public void exportExcel(@RequestParam(value = "filepath") String filepath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DeliveryDetails deliveryDetails = null;
		List<DeliveryDetails> deliverDetailsList = null; // 产品详细List

		deliveryDetails = new DeliveryDetails("仓库1", "G-6", "三菱", "包", 100f, 25f, 300f, 1234, "2019-09-20", "加急");
		deliverDetailsList = new ArrayList<DeliveryDetails>();
		deliverDetailsList.add(deliveryDetails);

		String fileName = "北京神丰科技有限公司出货单";

		String[] columnNames = { "仓库", "料号", "品牌", "单位", "数量", "单重", "合计重量", "批次号", "出货日期", "备注" };
		String[] columns = { "storehouseNo", "materialNo", "brand", "unit", "quantity", "singleWeight", "totalWeight",
				"batchNo", "date", "comment" };
		ExcelUtils.exportExcel(response, deliverDetailsList, columnNames, columns, "invoiceList", fileName);
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
	public JSONObject getResultByIocr(String filepath) throws IOException {

		AipOcr client = new AipOcr(appId, apiKey, secretKey);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
//		options.put("recognize_granularity", "big");
//		options.put("detect_direction", "true");
//		options.put("vertexes_location", "true");
//		options.put("probability", "true");
		// client.toString(2);

		options.put("templateSign", "a432753989cd6d48923766dc3bdd3977");
		// options.put("classifierId", "31232");

		// 参数为本地路径
		JSONObject custom = client.custom(filepath, options);

		return custom;
	}

	/**
	 * 将获取json数据转化为实体对象
	 * 
	 * @param jsonObject
	 * @return Invoice
	 */
	public Invoice jsonToObject(JSONObject jsonObject) {

		Invoice invoice = new Invoice();
		DeliverMessage deliverMessage = new DeliverMessage();
		DeliveryDetails deliveryDetails = new DeliveryDetails();
		List<DeliveryDetails> deliveryDetailsList = new ArrayList<>();
		// 获得账票标题
		String templateName = jsonObject.getJSONObject("data").get("templateName").toString();
		invoice.setTitle(templateName);

		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ret");
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < list.size(); i++) {

			HashMap<String, String> map = (HashMap) list.get(i);

			String word = map.get("word");
			switch (i) {

			case 0:
				deliverMessage.setHandler(word);
				break;
			case 1:
				deliverMessage.setContactNUmber(word);
				break;
			case 2:
				deliverMessage.setDeliveryCompany(word);
				break;
			case 3:
				deliverMessage.setDeliveryDate(word);
				break;
			case 4:
				deliverMessage.setAddress(word);
				break;
			case 5:
				deliverMessage.setNote(word);
				break;
			case 6:
				deliverMessage.setDeliveryNo(word);
				break;
			case 7:
				deliverMessage.setPicker(word);
				break;

			case 8:
				deliveryDetails.setStorehouseNo(word);
				break;
			case 9:
				deliveryDetails.setMaterialNo(word);
				break;
			case 10:
				deliveryDetails.setBrand(word);
				break;
			case 11:
				deliveryDetails.setUnit(word);
				break;
			case 12:
				deliveryDetails.setQuantity(Float.parseFloat(word));
				break;
			case 13:
				deliveryDetails.setSingleWeight(Float.parseFloat(word));
				break;
			case 14:
				deliveryDetails.setTotalWeight(Float.parseFloat(word));
				break;
			case 15:
				deliveryDetails.setBatchNo(Integer.parseInt(word));
				break;
			case 16:
				deliveryDetails.setDate(word);
				break;
			case 17:
				deliveryDetails.setComment(word);
				break;

			case 18:
				deliveryDetails.setStorehouseNo(word);
				break;
			case 19:
				deliveryDetails.setMaterialNo(word);
				break;
			case 20:
				deliveryDetails.setBrand(word);
				break;
			case 21:
				deliveryDetails.setUnit(word);
				break;
			case 22:
				deliveryDetails.setQuantity(Float.parseFloat(word));
				break;
			case 23:
				deliveryDetails.setSingleWeight(Float.parseFloat(word));
				break;
			case 24:
				deliveryDetails.setTotalWeight(Float.parseFloat(word));
				break;
			case 25:
				deliveryDetails.setBatchNo(Integer.parseInt(word));
				break;
			case 26:
				deliveryDetails.setDate(word);
				break;
			case 27:
				deliveryDetails.setComment(word);
				break;

			case 28:
				deliveryDetails.setStorehouseNo(word);
				break;
			case 29:
				deliveryDetails.setMaterialNo(word);
				break;
			case 30:
				deliveryDetails.setBrand(word);
				break;
			case 31:
				deliveryDetails.setUnit(word);
				break;
			case 32:
				deliveryDetails.setQuantity(Float.parseFloat(word));
				break;
			case 33:
				deliveryDetails.setSingleWeight(Float.parseFloat(word));
				break;
			case 34:
				deliveryDetails.setTotalWeight(Float.parseFloat(word));
				break;
			case 35:
				deliveryDetails.setBatchNo(Integer.parseInt(word));
				break;
			case 36:
				deliveryDetails.setDate(word);
				break;
			case 37:
				deliveryDetails.setComment(word);
				break;

			case 38:
				deliveryDetails.setStorehouseNo(word);
				break;
			case 39:
				deliveryDetails.setMaterialNo(word);
				break;
			case 40:
				deliveryDetails.setBrand(word);
				break;
			case 41:
				deliveryDetails.setUnit(word);
				break;
			case 42:
				deliveryDetails.setQuantity(Float.parseFloat(word));
				break;
			case 43:
				deliveryDetails.setSingleWeight(Float.parseFloat(word));
				break;
			case 44:
				deliveryDetails.setTotalWeight(Float.parseFloat(word));
				break;
			case 45:
				deliveryDetails.setBatchNo(Integer.parseInt(word));
				break;
			case 46:
				deliveryDetails.setDate(word);
				break;
			case 47:
				deliveryDetails.setComment(word);
				break;

			case 48:
				deliveryDetails.setStorehouseNo(word);
				break;
			case 49:
				deliveryDetails.setMaterialNo(word);
				break;
			case 50:
				deliveryDetails.setBrand(word);
				break;
			case 51:
				deliveryDetails.setUnit(word);
				break;
			case 52:
				deliveryDetails.setQuantity(Float.parseFloat(word));
				break;
			case 53:
				deliveryDetails.setSingleWeight(Float.parseFloat(word));
				break;
			case 54:
				deliveryDetails.setTotalWeight(Float.parseFloat(word));
				break;
			case 55:
				deliveryDetails.setBatchNo(Integer.parseInt(word));
				break;
			case 56:
				deliveryDetails.setDate(word);
				break;
			case 57:
				deliveryDetails.setComment(word);
				break;

			}
			invoice.setDeliverMessage(deliverMessage);
			deliveryDetailsList.add(deliveryDetails);
			invoice.setDeliveryDetails(deliveryDetailsList);

		}

		return invoice;

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
			ServletOutputStream out, DeliverMessage deliverMessage, List<DeliveryDetails> deliveryDetails)
			throws Exception {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet

			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// 第三步，在sheet中添加表头第0行,老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			HSSFRow hssfRow1 = hssfSheet.createRow(1);
			HSSFRow hssfRow2 = hssfSheet.createRow(2);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			// hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;// 第一行

			HSSFCell hssfCell1 = null;// 第二行

			HSSFCell hssfCell2 = null;// 第三行

			// 第一行表头
			for (int i = 0; i < head.length; i++) {
				hssfCell = hssfRow.createCell(i);// 列索引从0开始
				hssfCell.setCellValue(head[i]);// 列名1
				hssfCell.setCellStyle(hssfCellStyle);// 列居中显示
			}
			/*
			 * //测试 hssfCell = hssfRow.createCell(1);// 列索引从10开始
			 * hssfCell.setCellValue("退款");// 列名1 hssfCell.setCellStyle(hssfCellStyle);//
			 * 列居中显示
			 */// 动态合并单元格
			for (int i = 0; i < headnum.length; i++) {

				hssfSheet.autoSizeColumn(i, true);
				String[] temp = headnum[i].split(",");
				Integer startrow = Integer.parseInt(temp[0]);
				Integer overrow = Integer.parseInt(temp[1]);
				Integer startcol = Integer.parseInt(temp[2]);
				Integer overcol = Integer.parseInt(temp[3]);
				hssfSheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
			}

			// TODO 第二行写入多个字段
			// 第二行表头
			for (int i = 0; i < head1.length; i++) {
				hssfCell1 = hssfRow1.createCell(i);// 列索引从0开始
				hssfCell1.setCellValue(head1[i]);// 列名1
				hssfCell1.setCellStyle(hssfCellStyle);// 列居中显示
			}

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

			for (int i = 0; i < headnum1.length; i++) {

				hssfSheet.autoSizeColumn(i, true);
				String[] temp = headnum1[i].split(",");
				Integer startrow = Integer.parseInt(temp[0]);
				Integer overrow = Integer.parseInt(temp[1]);
				Integer startcol = Integer.parseInt(temp[2]);
				Integer overcol = Integer.parseInt(temp[3]);
				hssfSheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
			}
			// 第三行表头 不需要合并单元格
			for (int i = 0; i < titles.length; i++) {
				hssfCell2 = hssfRow2.createCell(i);// 列索引从0开始
				hssfCell2.setCellValue(titles[i]);// 列名1
				hssfCell2.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 第五步，写入实体数据

			if (deliveryDetails != null && !deliveryDetails.isEmpty()) {

				for (int i = 0; i < deliveryDetails.size(); i++) {
					hssfRow = hssfSheet.createRow(i + 3);
					DeliveryDetails de = deliveryDetails.get(i);

					// 第六步，创建单元格，并设置值
					String storehouseNo = de.getStorehouseNo();

					if (StringUtils.isEmpty(storehouseNo)) {
						storehouseNo = "-";
					}
					hssfRow.createCell(0).setCellValue(storehouseNo);

					String materialNo = "";
					if (de.getMaterialNo() != null) {
						materialNo = de.getMaterialNo();
					}
					hssfRow.createCell(1).setCellValue(materialNo);

					String brand = "";
					if (de.getBrand() != null) {
						brand = de.getBrand();
					}
					hssfRow.createCell(2).setCellValue(brand);

					String unit = "";
					if (de.getUnit() != null) {
						unit = de.getUnit();
					}
					hssfRow.createCell(3).setCellValue(unit);

					float quantity = 0.0f;
					if (de.getQuantity() != 0) {
						quantity = de.getQuantity();
					}
					hssfRow.createCell(4).setCellValue(quantity);

					float singleWeight = 0.0f;
					if (de.getSingleWeight() != 0) {
						singleWeight = de.getSingleWeight();
					}
					hssfRow.createCell(5).setCellValue(singleWeight);

					float totalWeight = 0.0f;
					if (de.getTotalWeight() != 0) {
						totalWeight = de.getTotalWeight();
					}
					hssfRow.createCell(6).setCellValue(totalWeight);

					int batchNo = 0;
					if (de.getBatchNo() != 0) {
						batchNo = de.getBatchNo();
					}
					hssfRow.createCell(7).setCellValue(batchNo);

					String date = "";
					if (de.getDate() != null) {
						date = de.getDate();
					}
					hssfRow.createCell(8).setCellValue(date);

					String comment = "";
					if (de.getComment() != null) {
						comment = de.getComment();
					}
					hssfRow.createCell(9).setCellValue(comment);

				}

			}

			// 第七步，将文件输出到客户端浏览器
			try {
				workbook.write(out);
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
