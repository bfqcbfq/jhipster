package com.ivision.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.alibaba.fastjson.JSON;
import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.aop.constant.CommonConstant;
import com.ivision.app.cache.Cache;
import com.ivision.app.domain.BaseResource;
import com.ivision.app.domain.DeliverMessage;
import com.ivision.app.domain.DeliveryDetails;
import com.ivision.app.domain.Invoice;
import com.ivision.app.domain.IocrBaseResource;
import com.ivision.app.domain.MitsubishiSurvey;
import com.ivision.app.domain.MxDeliverMessage;
import com.ivision.app.domain.MxDeliveryDetails;
import com.ivision.app.domain.MxInvoice;
import com.ivision.app.domain.YdDeliverMessage;
import com.ivision.app.domain.YdDeliveryDetails;
import com.ivision.app.domain.YdInvoice;

/**
 * 
 * 调用百度自定义文字识别API，实现上传图片识别
 * 
 * <p>
 * 实现文件的上传，下载及页面展示
 * </p>
 */
@RestController
@RequestMapping("/api/ocr/iocr")
public class IocrResource {

	// 缓存同一模板List
	private static List<Invoice> invoiceCacheList = new ArrayList<>();
	private static List<MxInvoice> mxInvoiceCacheList = new ArrayList<>();
	private static List<YdInvoice> ydInvoiceCacheList = new ArrayList<>();
	private static List<MitsubishiSurvey> mitsubishiSurveyList = new ArrayList<>();

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	// 设置APPID/AK/SK
	@Value("${iocr.app.id}")
	private String appId;

	@Value("${iocr.api.key}")
	private String apiKey;

	@Value("${iocr.secret.key}")
	private String secretKey;

	// 設置模板ID
	@Value("${iocr.template.id1}")
	private String templateId1;

	@Value("${iocr.template.id2}")
	private String templateId2;

	@Value("${iocr.template.id3}")
	private String templateId3;

	@Value("${iocr.template.id4}")
	private String templateId4;

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
			@RequestParam(value = "file", required = false) MultipartFile[] uploadFiles, HttpServletRequest request)
			throws IOException {

		List<String> errorMessageList = new ArrayList<String>();

		Invoice invoice = null;
		MxInvoice mxInvoice = null;
		YdInvoice ydInvoice = null;
		MitsubishiSurvey mitsubishiSurvey = null;
		BaseResource baseResource = null;

		// 判断文件夹是否存在,不存在则创建
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		if (uploadFiles == null || uploadFiles.length < 0) {

			// 参数错误，返回400，没有响应体
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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

				List<JSONObject> jsonObjectList = getResultByIocr(newFilePath);

				for (JSONObject jsonObject : jsonObjectList) {
					IocrBaseResource iocrBaseResource = JSON.parseObject(jsonObject.toString(), IocrBaseResource.class);
					int error_code = iocrBaseResource.getError_code();

					String errorCode = error_code + "";
					if (!errorCode.equals(CommonConstant.OCR_IOCR_ERRORCODE)) {
						errorMessageList.add(errorCode);
						// 上传错误，返回错误信息
						if (errorMessageList.size() == jsonObjectList.size()) {
							baseResource = new BaseResource();
							baseResource.setErrorMessage("您上传的文件有误，请再确认一下");

							return ResponseEntity.ok(baseResource);

						} else {

							continue;
						}
					}

					else {
						String templateSign = jsonObject.getJSONObject("data").get("templateSign").toString();

						if (templateSign.equals(templateId1)) {
							invoice = new Invoice();
							invoice = jsonToInvoiceF(jsonObject);
							invoice.setTemplateType("神丰科技发货单");
							invoice.setType(CommonConstant.OCR_IOCR_YINGFENG_TYPE);
							// 将数据放在缓存中
							Cache.put("invoice", invoice, Cache.CACHE_HOLD_TIME_24H);
							invoiceCacheList.add(invoice);
							return ResponseEntity.ok(invoice);
						} else if (templateSign.equals(templateId2)) {
							mxInvoice = new MxInvoice();
							mxInvoice = jsonToMxInvoice(jsonObject);
							mxInvoice.setTemplateType("明歆制衣出货单");
							mxInvoice.setType(CommonConstant.OCR_IOCR_MINGXING_TYPE);

							Cache.put("mxInvoice", mxInvoice, Cache.CACHE_HOLD_TIME_24H);
							mxInvoiceCacheList.add(mxInvoice);
							return ResponseEntity.ok(mxInvoice);
						} else if (templateSign.equals(templateId3)) {
							mxInvoice = new MxInvoice();
							ydInvoice = jsonToYdInvoice(jsonObject);
							ydInvoice.setTemplateType("易达软件出库单");
							ydInvoice.setType(CommonConstant.OCR_IOCR_YIDA_TYPE);

							Cache.put("ydInvoice", ydInvoice, Cache.CACHE_HOLD_TIME_24H);
							ydInvoiceCacheList.add(ydInvoice);
							return ResponseEntity.ok(ydInvoice);

						} else if (templateSign.equals(templateId4)) {
							mitsubishiSurvey = new MitsubishiSurvey();
							
							mitsubishiSurvey = jsonToMitsubishiSurvey(jsonObject);
							mitsubishiSurvey.setTemplateType("三菱重工调查问卷");
							mitsubishiSurvey.setFilpathType(CommonConstant.OCR_IOCR_SANLING_TYPE);
							
							Cache.put("mitsubishiSurvey", mitsubishiSurvey, Cache.CACHE_HOLD_TIME_24H);
							mitsubishiSurveyList.add(mitsubishiSurvey);
							return ResponseEntity.ok(mitsubishiSurvey);
							
						}
					}

				}

			} catch (FileAlreadyExistsException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		// 上传发生错误，返回500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	/**
	 * 获取上传文件明细
	 * 
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/showDetails")
	public ResponseEntity<Object> showUploadFileDetails(@RequestParam(value = "filepathType") String filepathType,
			HttpServletRequest request) throws IOException {

		if (filepathType.equals(CommonConstant.OCR_IOCR_YINGFENG_TYPE)) {
			Invoice invoice = (Invoice) Cache.get("invoice");
			if (invoice == null) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			return ResponseEntity.ok(invoice);
		}

		if (filepathType.equals("2")) {
			MxInvoice mxInvoice = (MxInvoice) Cache.get("mxInvoice");
			if (mxInvoice == null) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			return ResponseEntity.ok(mxInvoice);

		}
		if (filepathType.equals("3")) {
			YdInvoice ydInvoice = (YdInvoice) Cache.get("ydInvoice");
			if (ydInvoice == null) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			return ResponseEntity.ok(ydInvoice);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

	/**
	 * 导出Excel，并下载
	 * 
	 * @param filepath
	 * @param response
	 * @return
	 */
	@GetMapping("/download")
	public void exportExcel(@RequestParam(value = "filepathType") String filepathType, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("application/force-download;charset=UTF-8");

		try {
			ServletOutputStream out = response.getOutputStream();
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义Excel文件新名称
			String newFileName = dateFormat.format(now) + System.currentTimeMillis();
			String fileName = new String((newFileName).getBytes(), "UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");

			// HttpSession session = request.getSession();

			if (filepathType.equals(CommonConstant.OCR_IOCR_YINGFENG_TYPE)) {
				// 获取表头1
				String title = CommonConstant.OCR_IOCR_YINGFENG_TITLE;
				String[] head = { title };
				String[] headnum = { "0,0,0,15" };
				String[] titles = { "仓库", "料号", "品牌", "单位", "数量", "单重", "合计重量", "批次号", "出货日期", "备注" };
				this.exportFencers(head, headnum, null, null, titles, out, invoiceCacheList, null, null);

			} else if (filepathType.equals(CommonConstant.OCR_IOCR_MINGXING_TYPE)) {
				String title = CommonConstant.OCR_IOCR_MINGXING_TITLE;

				// 获取表头1
				String[] head = { title };
				String[] headnum = { "0,0,0,15" };
				String[] headnum1 = { "1,1,0,15" };
				String[] titles = { "款号", "款式", "颜色", "单位", "S", "M", "L", "小计", "单价", "金额", "备注" };

				this.exportFencers(head, headnum, null, headnum1, titles, out, null, mxInvoiceCacheList, null);
			} else if (filepathType.equals(CommonConstant.OCR_IOCR_YIDA_TYPE)) {
				// 获取表头1
				String title = CommonConstant.OCR_IOCR_YIDA_TITLE;
				String[] head = { title };
				String[] headnum = { "0,0,0,15" };
				// 获取表头2
				String[] headnum1 = { "1,1,0,15" };
				String[] titles = { "序号", "配件编号", "配件名称", "车型", "产地", "单位", "单价", "数量", "金额", "备注" };

				this.exportFencers(head, headnum, null, headnum1, titles, out, null, null, ydInvoiceCacheList);
			}

		} catch (Exception e) {
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
	public List<JSONObject> getResultByIocr(String iocrFilepath) throws IOException {

		List<JSONObject> jbList = new ArrayList<JSONObject>();

		AipOcr client = new AipOcr(appId, apiKey, secretKey);

		client.setConnectionTimeoutInMillis(10000);
		client.setSocketTimeoutInMillis(180000);

		List<String> templateSignList = new ArrayList<String>();
		templateSignList.add(templateId1);
		templateSignList.add(templateId2);
		templateSignList.add(templateId3);
		templateSignList.add(templateId4);

		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		for (String templateId : templateSignList) {

			options.put("templateSign", templateId);

			// 参数为本地路径
			JSONObject custom = client.custom(iocrFilepath, options);
			jbList.add(custom);

		}

		return jbList;

	}

	/**
	 * 将获取json数据转化为实体对象
	 * 
	 * @param jsonObject
	 * @return Invoice
	 */
	public Invoice jsonToInvoiceF(JSONObject jsonObject) {

		Invoice invoice = new Invoice();
		DeliverMessage deliverMessage = new DeliverMessage();
		// 將表格中每行數據放在一個對象中
		// 表格第一行
		DeliveryDetails deliveryDetails = new DeliveryDetails();
		// 表格第二行
		DeliveryDetails deliveryDetails1 = new DeliveryDetails();
		// 表格第三行
		DeliveryDetails deliveryDetails2 = new DeliveryDetails();
		// 表格第四行
		DeliveryDetails deliveryDetails3 = new DeliveryDetails();
		// 表格第五行
		DeliveryDetails deliveryDetails4 = new DeliveryDetails();
		// 表格第六行
		DeliveryDetails deliveryDetails5 = new DeliveryDetails();
		// 表格第七行
		DeliveryDetails deliveryDetails6 = new DeliveryDetails();

		List<DeliveryDetails> deliveryDetailsList = deliveryDetailsList = new ArrayList<>();
		// 获得账票标题
		String templateName = jsonObject.getJSONObject("data").get("templateName").toString();
		invoice.setTitle(templateName);

		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ret");
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < list.size(); i++) {

			@SuppressWarnings({ "unchecked", "rawtypes" })
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
				// 表格外數據
				invoice.setDeliverMessage(deliverMessage);
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
				deliveryDetails.setQuantity(word);
				break;
			case 13:
				deliveryDetails.setSingleWeight(word);
				break;
			case 14:
				deliveryDetails.setTotalWeight(word);
				break;
			case 15:
				deliveryDetails.setBatchNo(word);
				break;
			case 16:
				deliveryDetails.setDate(word);
				break;
			case 17:
				deliveryDetails.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails);
				break;

			case 18:
				deliveryDetails1.setStorehouseNo(word);
				break;
			case 19:
				deliveryDetails1.setMaterialNo(word);
				break;
			case 20:
				deliveryDetails1.setBrand(word);
				break;
			case 21:
				deliveryDetails1.setUnit(word);
				break;
			case 22:
				deliveryDetails1.setQuantity(word);
				break;
			case 23:
				deliveryDetails1.setSingleWeight(word);
				break;
			case 24:
				deliveryDetails1.setTotalWeight(word);
				break;
			case 25:
				deliveryDetails1.setBatchNo(word);
				break;
			case 26:
				deliveryDetails1.setDate(word);
				break;
			case 27:
				deliveryDetails1.setComment(word);
				// 表格第二行
				deliveryDetailsList.add(deliveryDetails1);
				break;

			case 28:
				deliveryDetails2.setStorehouseNo(word);
				break;
			case 29:
				deliveryDetails2.setMaterialNo(word);
				break;
			case 30:
				deliveryDetails2.setBrand(word);
				break;
			case 31:
				deliveryDetails2.setUnit(word);
				break;
			case 32:
				deliveryDetails2.setQuantity(word);
				break;
			case 33:
				deliveryDetails2.setSingleWeight(word);
				break;
			case 34:
				deliveryDetails2.setTotalWeight(word);
				break;
			case 35:
				deliveryDetails2.setBatchNo(word);
				break;
			case 36:
				deliveryDetails2.setDate(word);
				break;
			case 37:
				deliveryDetails2.setComment(word);
				// 表格第三行
				deliveryDetailsList.add(deliveryDetails2);
				break;

			case 38:
				deliveryDetails3.setStorehouseNo(word);
				break;
			case 39:
				deliveryDetails3.setMaterialNo(word);
				break;
			case 40:
				deliveryDetails3.setBrand(word);
				break;
			case 41:
				deliveryDetails3.setUnit(word);
				break;
			case 42:
				deliveryDetails3.setQuantity(word);
				break;
			case 43:
				deliveryDetails3.setSingleWeight(word);
				break;
			case 44:
				deliveryDetails3.setTotalWeight(word);
				break;
			case 45:
				deliveryDetails3.setBatchNo(word);
				break;
			case 46:
				deliveryDetails3.setDate(word);
				break;
			case 47:
				deliveryDetails3.setComment(word);
				// 表格第四行
				deliveryDetailsList.add(deliveryDetails3);
				break;

			case 48:
				deliveryDetails4.setStorehouseNo(word);
				break;
			case 49:
				deliveryDetails4.setMaterialNo(word);
				break;
			case 50:
				deliveryDetails4.setBrand(word);
				break;
			case 51:
				deliveryDetails4.setUnit(word);
				break;
			case 52:
				deliveryDetails4.setQuantity(word);
				break;
			case 53:
				deliveryDetails4.setSingleWeight(word);
				break;
			case 54:
				deliveryDetails4.setTotalWeight(word);
				break;
			case 55:
				deliveryDetails4.setBatchNo(word);
				break;
			case 56:
				deliveryDetails4.setDate(word);
				break;
			case 57:
				deliveryDetails4.setComment(word);
				// 表格第五行
				deliveryDetailsList.add(deliveryDetails4);
				break;

			case 58:
				deliveryDetails5.setStorehouseNo(word);
				break;
			case 59:
				deliveryDetails5.setMaterialNo(word);
				break;
			case 60:
				deliveryDetails5.setBrand(word);
				break;
			case 61:
				deliveryDetails5.setUnit(word);
				break;
			case 62:
				deliveryDetails5.setQuantity(word);
				break;
			case 63:
				deliveryDetails5.setSingleWeight(word);
				break;
			case 64:
				deliveryDetails5.setTotalWeight(word);
				break;
			case 65:
				deliveryDetails5.setBatchNo(word);
				break;
			case 66:
				deliveryDetails5.setDate(word);
				break;
			case 67:
				deliveryDetails5.setComment(word);
				// 表格第六行
				deliveryDetailsList.add(deliveryDetails5);
				break;

			case 68:
				deliveryDetails6.setStorehouseNo(word);
				break;
			case 69:
				deliveryDetails6.setMaterialNo(word);
				break;
			case 70:
				deliveryDetails6.setBrand(word);
				break;
			case 71:
				deliveryDetails6.setUnit(word);
				break;
			case 72:
				deliveryDetails6.setQuantity(word);
				break;
			case 73:
				deliveryDetails6.setSingleWeight(word);
				break;
			case 74:
				deliveryDetails6.setTotalWeight(word);
				break;
			case 75:
				deliveryDetails6.setBatchNo(word);
				break;
			case 76:
				deliveryDetails6.setDate(word);
				break;
			case 77:
				deliveryDetails6.setComment(word);
				// 表格第七行
				deliveryDetailsList.add(deliveryDetails6);
				break;
			}

		}

		invoice.setDeliveryDetails(deliveryDetailsList);

		return invoice;

	}

	public MxInvoice jsonToMxInvoice(JSONObject jsonObject) {

		MxInvoice invoice = new MxInvoice();
		MxDeliverMessage deliverMessage = new MxDeliverMessage();

		// 將表格中每行數據放在一個對象中
		// 表格第一行
		MxDeliveryDetails deliveryDetails = new MxDeliveryDetails();
		// 表格第二行
		MxDeliveryDetails deliveryDetails1 = new MxDeliveryDetails();
		// 表格第三行
		MxDeliveryDetails deliveryDetails2 = new MxDeliveryDetails();
		// 表格第四行
		MxDeliveryDetails deliveryDetails3 = new MxDeliveryDetails();
		// 表格第五行
		MxDeliveryDetails deliveryDetails4 = new MxDeliveryDetails();
		// 表格第六行
		MxDeliveryDetails deliveryDetails5 = new MxDeliveryDetails();
		// 表格第七行
		List<MxDeliveryDetails> deliveryDetailsList = new ArrayList<>();
		// 获得账票标题
		String templateName = jsonObject.getJSONObject("data").get("templateName").toString();
		invoice.setTitle(templateName);

		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ret");
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < list.size(); i++) {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, String> map = (HashMap) list.get(i);

			String word = map.get("word");

			switch (i) {

			case 0:
				deliverMessage.setDeliveryNo(word);
				break;
			case 1:
				deliverMessage.setBusinessCode(word);
				break;
			case 2:
				deliverMessage.setTotalQuantity(word);
				break;
			case 3:
				deliverMessage.setTotalAmount(word);
				break;
			case 4:
				deliverMessage.setOrderMaker(word);
				break;
			case 5:
				deliverMessage.setDeliveryDate(word);
				break;
			case 6:
				deliverMessage.setAddress(word);
				break;
			case 7:
				deliverMessage.setDeliverySign(word);
				break;
			case 8:
				deliverMessage.setHandlerSign(word);
				// 表格外数据
				invoice.setMxDeliverMessage(deliverMessage);
				break;

			case 9:
				deliveryDetails.setStyleNo(word);
				break;
			case 10:
				deliveryDetails.setStyle(word);
				break;
			case 11:
				deliveryDetails.setColor(word);
				break;
			case 12:
				deliveryDetails.setUnit(word);
				break;
			case 13:
				deliveryDetails.setModelS(word);
				break;
			case 14:
				deliveryDetails.setModelM(word);
			case 15:
				deliveryDetails.setModelL(word);
				break;
			case 16:
				deliveryDetails.setSubtotal(word);
				break;
			case 17:
				deliveryDetails.setUnitPrice(word);
				break;
			case 18:
				deliveryDetails.setAccount(word);
				break;
			case 19:
				deliveryDetails.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails);
				break;

			case 20:
				deliveryDetails1.setStyleNo(word);
				break;
			case 21:
				deliveryDetails1.setStyle(word);
				break;
			case 22:
				deliveryDetails1.setColor(word);
				break;
			case 23:
				deliveryDetails1.setUnit(word);
				break;
			case 24:
				deliveryDetails1.setModelS(word);
				break;
			case 25:
				deliveryDetails1.setModelM(word);
			case 26:
				deliveryDetails1.setModelL(word);
				break;
			case 27:
				deliveryDetails1.setSubtotal(word);
				break;
			case 28:
				deliveryDetails1.setUnitPrice(word);
				break;
			case 29:
				deliveryDetails1.setAccount(word);
				break;
			case 30:
				deliveryDetails1.setComment(word);
				// 表格第二行
				deliveryDetailsList.add(deliveryDetails1);
				break;

			case 31:
				deliveryDetails2.setStyleNo(word);
				break;
			case 32:
				deliveryDetails2.setStyle(word);
				break;
			case 33:
				deliveryDetails2.setColor(word);
				break;
			case 34:
				deliveryDetails2.setUnit(word);
				break;
			case 35:
				deliveryDetails2.setModelS(word);
				break;
			case 36:
				deliveryDetails2.setModelM(word);
			case 37:
				deliveryDetails2.setModelL(word);
				break;
			case 38:
				deliveryDetails2.setSubtotal(word);
				break;
			case 39:
				deliveryDetails2.setUnitPrice(word);
				break;
			case 40:
				deliveryDetails2.setAccount(word);
				break;
			case 41:
				deliveryDetails2.setComment(word);
				// 表格第三行
				deliveryDetailsList.add(deliveryDetails2);
				break;

			case 42:
				deliveryDetails3.setStyleNo(word);
				break;
			case 43:
				deliveryDetails3.setStyle(word);
				break;
			case 44:
				deliveryDetails3.setColor(word);
				break;
			case 45:
				deliveryDetails3.setUnit(word);
				break;
			case 46:
				deliveryDetails3.setModelS(word);
				break;
			case 47:
				deliveryDetails3.setModelM(word);
			case 48:
				deliveryDetails3.setModelL(word);
				break;
			case 49:
				deliveryDetails3.setSubtotal(word);
				break;
			case 50:
				deliveryDetails3.setUnitPrice(word);
				break;
			case 51:
				deliveryDetails3.setAccount(word);
				break;
			case 52:
				deliveryDetails3.setComment(word);
				// 表格第四行
				deliveryDetailsList.add(deliveryDetails3);
				break;

			case 53:
				deliveryDetails4.setStyleNo(word);
				break;
			case 54:
				deliveryDetails4.setStyle(word);
				break;
			case 55:
				deliveryDetails4.setColor(word);
				break;
			case 56:
				deliveryDetails4.setUnit(word);
				break;
			case 57:
				deliveryDetails4.setModelS(word);
				break;
			case 58:
				deliveryDetails4.setModelM(word);
			case 59:
				deliveryDetails4.setModelL(word);
				break;
			case 60:
				deliveryDetails4.setSubtotal(word);
				break;
			case 61:
				deliveryDetails4.setUnitPrice(word);
				break;
			case 62:
				deliveryDetails4.setAccount(word);
				break;
			case 63:
				deliveryDetails4.setComment(word);
				// 表格第五行
				deliveryDetailsList.add(deliveryDetails4);
				break;

			case 64:
				deliveryDetails5.setStyleNo(word);
				break;
			case 65:
				deliveryDetails5.setStyle(word);
				break;
			case 66:
				deliveryDetails5.setColor(word);
				break;
			case 67:
				deliveryDetails5.setUnit(word);
				break;
			case 68:
				deliveryDetails5.setModelS(word);
				break;
			case 69:
				deliveryDetails5.setModelM(word);
			case 70:
				deliveryDetails5.setModelL(word);
				break;
			case 71:
				deliveryDetails5.setSubtotal(word);
				break;
			case 72:
				deliveryDetails5.setUnitPrice(word);
				break;
			case 73:
				deliveryDetails5.setAccount(word);
				break;
			case 74:
				deliveryDetails5.setComment(word);
				// 表格第六行
				deliveryDetailsList.add(deliveryDetails5);
				break;

			}

		}

		invoice.setDeliveryDetails(deliveryDetailsList);

		return invoice;

	}

	public YdInvoice jsonToYdInvoice(JSONObject jsonObject) {

		YdInvoice invoice = new YdInvoice();
		YdDeliverMessage deliverMessage = new YdDeliverMessage();

		// 將表格中每行數據放在一個對象中
		// 表格第一行
		YdDeliveryDetails deliveryDetails = new YdDeliveryDetails();
		// 表格第二行
		YdDeliveryDetails deliveryDetails1 = new YdDeliveryDetails();
		// 表格第三行
		YdDeliveryDetails deliveryDetails2 = new YdDeliveryDetails();
		// 表格第四行
		YdDeliveryDetails deliveryDetails3 = new YdDeliveryDetails();
		// 表格第五行
		YdDeliveryDetails deliveryDetails4 = new YdDeliveryDetails();

		List<YdDeliveryDetails> deliveryDetailsList = new ArrayList<>();
		// 获得账票标题
		String templateName = jsonObject.getJSONObject("data").get("templateName").toString();
		invoice.setTitle(templateName);

		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ret");
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < list.size(); i++) {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, String> map = (HashMap) list.get(i);

			String word = map.get("word");

			switch (i) {

			case 0:
				deliverMessage.setMainBusiness(word);
				break;
			case 1:
				deliverMessage.setOrderMaker(word);
				break;
			case 2:
				deliverMessage.setCsahier(word);
				break;
			case 3:
				deliverMessage.setCustomerSign(word);
				break;
			case 4:
				deliverMessage.setDeliveryNo(word);
				break;
			case 5:
				deliverMessage.setTotalAmountSmall(word);
				break;
			case 6:
				deliverMessage.setInvoiceType(word);
				break;
			case 7:
				deliverMessage.setTotalAmountBig(word);
				break;
			case 8:
				deliverMessage.setDeliveryDate(word);
				break;
			case 9:
				deliverMessage.setSettleStyle(word);
				break;
			case 10:
				deliverMessage.setCompanyPhone(word);
				break;
			case 11:
				deliverMessage.setCustomerName(word);
				break;
			case 12:
				deliverMessage.setAddress(word);
				break;
			case 13:
				deliverMessage.setCustomerPhone(word);
				break;
			case 14:
				deliverMessage.setDeliveryer(word);
				break;
			case 15:
				deliverMessage.setReceiver(word);
				break;
			case 16:
				deliverMessage.setTotalAccount(word);
				// 表格外數據
				invoice.setYdDeliverMessage(deliverMessage);
				break;

			case 17:
				deliveryDetails.setOrderNumber(word);
				break;
			case 18:
				deliveryDetails.setPartsNumber(word);
				break;
			case 19:
				deliveryDetails.setPartsName(word);
				break;
			case 20:
				deliveryDetails.setVehicleType(word);
				break;
			case 21:
				deliveryDetails.setProductionAarea(word);
				break;
			case 22:
				deliveryDetails.setUnit(word);
				break;
			case 23:
				deliveryDetails.setUnitPrice(word);
				break;
			case 24:
				deliveryDetails.setQuantity(word);
				break;
			case 25:
				deliveryDetails.setAccount(word);
				break;
			case 26:
				deliveryDetails.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails);
				break;

			case 27:
				deliveryDetails1.setOrderNumber(word);
				break;
			case 28:
				deliveryDetails1.setPartsNumber(word);
				break;
			case 29:
				deliveryDetails1.setPartsName(word);
				break;
			case 30:
				deliveryDetails1.setVehicleType(word);
				break;
			case 31:
				deliveryDetails1.setProductionAarea(word);
				break;
			case 32:
				deliveryDetails1.setUnit(word);
				break;
			case 33:
				deliveryDetails1.setUnitPrice(word);
				break;
			case 34:
				deliveryDetails1.setQuantity(word);
				break;
			case 35:
				deliveryDetails1.setAccount(word);
				break;
			case 36:
				deliveryDetails1.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails1);
				break;

			case 37:
				deliveryDetails2.setOrderNumber(word);
				break;
			case 38:
				deliveryDetails2.setPartsNumber(word);
				break;
			case 39:
				deliveryDetails2.setPartsName(word);
				break;
			case 40:
				deliveryDetails2.setVehicleType(word);
				break;
			case 41:
				deliveryDetails2.setProductionAarea(word);
				break;
			case 42:
				deliveryDetails2.setUnit(word);
				break;
			case 43:
				deliveryDetails2.setUnitPrice(word);
				break;
			case 44:
				deliveryDetails2.setQuantity(word);
				break;
			case 45:
				deliveryDetails2.setAccount(word);
				break;
			case 46:
				deliveryDetails2.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails2);
				break;

			case 47:
				deliveryDetails3.setOrderNumber(word);
				break;
			case 48:
				deliveryDetails3.setPartsNumber(word);
				break;
			case 49:
				deliveryDetails3.setPartsName(word);
				break;
			case 50:
				deliveryDetails3.setVehicleType(word);
				break;
			case 51:
				deliveryDetails3.setProductionAarea(word);
				break;
			case 52:
				deliveryDetails3.setUnit(word);
				break;
			case 53:
				deliveryDetails3.setUnitPrice(word);
				break;
			case 54:
				deliveryDetails3.setQuantity(word);
				break;
			case 55:
				deliveryDetails3.setAccount(word);
				break;
			case 56:
				deliveryDetails3.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails3);
				break;

			case 57:
				deliveryDetails4.setOrderNumber(word);
				break;
			case 58:
				deliveryDetails4.setPartsNumber(word);
				break;
			case 59:
				deliveryDetails4.setPartsName(word);
				break;
			case 60:
				deliveryDetails4.setVehicleType(word);
				break;
			case 61:
				deliveryDetails4.setProductionAarea(word);
				break;
			case 62:
				deliveryDetails4.setUnit(word);
				break;
			case 63:
				deliveryDetails4.setUnitPrice(word);
				break;
			case 64:
				deliveryDetails4.setQuantity(word);
				break;
			case 65:
				deliveryDetails4.setAccount(word);
				break;
			case 66:
				deliveryDetails4.setComment(word);
				// 表格第一行
				deliveryDetailsList.add(deliveryDetails4);
				break;
			}

		}

		invoice.setYdDeliveryDetails(deliveryDetailsList);

		return invoice;

	}


	public MitsubishiSurvey jsonToMitsubishiSurvey(JSONObject jsonObject) {
		
		MitsubishiSurvey mitsubishiSurvey = new MitsubishiSurvey();
		
		
		JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("ret");
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < list.size(); i++) {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			HashMap<String, String> map = (HashMap) list.get(i);

			String word = map.get("word");

			switch (i) {
			
			case 0:
			mitsubishiSurvey.setQuestionOne(word);
			break;
			
			case 1:
			mitsubishiSurvey.setEmail(word);
			break;
			
			case 2:
				mitsubishiSurvey.setCompanyName(word);
				break;
				
			case 3:
				mitsubishiSurvey.setTelphone(word);
				break;
				
			case 4:
				mitsubishiSurvey.setQuestionSeven(word);
				break;
				
			case 5:
				mitsubishiSurvey.setQuestionSix(word);
				break;
				
			case 6:
				mitsubishiSurvey.setQuestionNine(word);
				break;
				
			case 7:
				mitsubishiSurvey.setQuestionEight(word);
				break;
				
			case 8:
				mitsubishiSurvey.setQuestionThree(word);
				break;
				
			case 9:
				mitsubishiSurvey.setQuestionTwo(word);
				break;
				
			case 10:
				mitsubishiSurvey.setQuestionTen(word);
				break;
				
			case 11:
				mitsubishiSurvey.setQuestionFive(word);
				break;
				
			case 12:
				mitsubishiSurvey.setQuestionFour(word);
				break;
				
			case 13:
				mitsubishiSurvey.setName(word);
				break;
				
			case 14:
				mitsubishiSurvey.setComment(word);
				break;
			}
			}

		return mitsubishiSurvey;
		
		
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
	 * @param invoiceCacheList
	 * @param mxInvoiceCacheList
	 * @param ydInvoiceCacheList
	 * @throws Exception
	 */
	private void exportFencers(String[] head, String[] headnum, String[] head1, String[] headnum1, String[] titles,
			ServletOutputStream out, List<Invoice> invoiceCacheList, List<MxInvoice> mxInvoiceCacheList,
			List<YdInvoice> ydInvoiceCacheList) throws Exception {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet hssfSheet = workbook.createSheet("sheet1");

			// 第三步，在sheet中添加表头第0行,老版本poi对Excel的行数列数有限制short
			HSSFRow hssfRow = hssfSheet.createRow(0);
			// HSSFRow hssfRow1 = hssfSheet.createRow(1);
			HSSFRow hssfRow2 = hssfSheet.createRow(2);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
			// 居中样式
			// hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			HSSFCell hssfCell = null;// 第一行

			// HSSFCell hssfCell1 = null;// 第二行

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

			// 发货方信息（表格外）
//			if (deliverMessage != null) {
//
//				hssfRow = hssfSheet.createRow(2);
//
//				// 创建单元格，并设置值
//				String deliveryNo = deliverMessage.getDeliveryNo();
//				if (StringUtils.isEmpty(deliveryNo)) {
//					deliveryNo = "-";
//				}
//				hssfRow.createCell(0).setCellValue(deliveryNo);
//
//				String deliveryCompany = "";
//				if (deliverMessage.getDeliveryCompany() != null) {
//					deliveryCompany = deliverMessage.getDeliveryCompany();
//				}
//				hssfRow.createCell(1).setCellValue(deliveryCompany);
//
//				String deliveryDate = "";
//				if (deliverMessage.getDeliveryDate() != null) {
//					deliveryDate = deliverMessage.getDeliveryDate();
//				}
//				hssfRow.createCell(2).setCellValue(deliveryDate);
//
//				String address = "";
//				if (deliverMessage.getAddress() != null) {
//					address = deliverMessage.getAddress();
//				}
//				hssfRow.createCell(3).setCellValue(address);
//
//				String contactNUmber = "";
//				if (deliverMessage.getContactNUmber() != null) {
//					contactNUmber = deliverMessage.getContactNUmber();
//				}
//				hssfRow.createCell(4).setCellValue(contactNUmber);
//
//				String note = "";
//				if (deliverMessage.getNote() != null) {
//					note = deliverMessage.getNote();
//				}
//				hssfRow.createCell(5).setCellValue(note);
//
//				String handler = "";
//				if (deliverMessage.getHandler() != null) {
//					handler = deliverMessage.getHandler();
//				}
//				hssfRow.createCell(6).setCellValue(handler);
//
//				String picker = "";
//				if (deliverMessage.getPicker() != null) {
//					picker = deliverMessage.getPicker();
//				}
//				hssfRow.createCell(7).setCellValue(picker);
//
//			}

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
			for (int i = 0; i < titles.length; i++) {
				hssfCell2 = hssfRow2.createCell(i);// 列索引从0开始
				hssfCell2.setCellValue(titles[i]);// 列名1
				hssfCell2.setCellStyle(hssfCellStyle);// 列居中显示
			}

			// 第五步，写入实体数据
			if (invoiceCacheList != null && !invoiceCacheList.isEmpty()) {

				int InvoiceBeginIndex = 0;
				for (int i = 0; i < invoiceCacheList.size(); i++) {

					List<DeliveryDetails> deliveryDetails = invoiceCacheList.get(i).getDeliveryDetails();

					InvoiceBeginIndex = i * deliveryDetails.size();

					for (int j = 0; j < deliveryDetails.size(); j++) {
						hssfRow = hssfSheet.createRow(j + InvoiceBeginIndex + 3);
						DeliveryDetails de = deliveryDetails.get(j);

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

						String quantity = "";
						if (de.getQuantity() != null) {
							quantity = de.getQuantity();
						}
						hssfRow.createCell(4).setCellValue(quantity);

						String singleWeight = "";
						if (de.getSingleWeight() != null) {
							singleWeight = de.getSingleWeight();
						}
						hssfRow.createCell(5).setCellValue(singleWeight);

						String totalWeight = "";
						if (de.getTotalWeight() != null) {
							totalWeight = de.getTotalWeight();
						}
						hssfRow.createCell(6).setCellValue(totalWeight);

						String batchNo = "";
						if (de.getBatchNo() != null) {
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

			}

			// 明歆制衣
			else if (mxInvoiceCacheList != null && !mxInvoiceCacheList.isEmpty()) {
				int mxInvoiceBeginIndex = 0;
				for (int i = 0; i < mxInvoiceCacheList.size(); i++) {

					List<MxDeliveryDetails> mxDeliveryDetails = mxInvoiceCacheList.get(i).getDeliveryDetails();

					mxInvoiceBeginIndex = i * mxDeliveryDetails.size();

					for (int j = 0; j < mxDeliveryDetails.size(); j++) {
						hssfRow = hssfSheet.createRow(j + mxInvoiceBeginIndex + 3);
						MxDeliveryDetails de = mxDeliveryDetails.get(j);

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

			}

			else if (ydInvoiceCacheList != null && !ydInvoiceCacheList.isEmpty()) {

				int ydInvoiceBeginIndex = 0;
				for (int i = 0; i < ydInvoiceCacheList.size(); i++) {

					List<YdDeliveryDetails> ydDeliveryDetails = ydInvoiceCacheList.get(i).getYdDeliveryDetails();

					ydInvoiceBeginIndex = i * ydDeliveryDetails.size();

					for (int j = 0; j < ydDeliveryDetails.size(); j++) {
						hssfRow = hssfSheet.createRow(j + ydInvoiceBeginIndex + 3);
						YdDeliveryDetails de = ydDeliveryDetails.get(j);

						// 第六步，创建单元格，并设置值
						String storehouseNo = de.getOrderNumber();

						if (StringUtils.isEmpty(storehouseNo)) {
							storehouseNo = "-";
						}
						hssfRow.createCell(0).setCellValue(storehouseNo);

						String materialNo = "";
						if (de.getPartsNumber() != null) {
							materialNo = de.getPartsNumber();
						}
						hssfRow.createCell(1).setCellValue(materialNo);

						String brand = "";
						if (de.getPartsName() != null) {
							brand = de.getPartsName();
						}
						hssfRow.createCell(2).setCellValue(brand);

						String unit = "";
						if (de.getVehicleType() != null) {
							unit = de.getVehicleType();
						}
						hssfRow.createCell(3).setCellValue(unit);

						String quantity = "";
						if (de.getProductionAarea() != null) {
							quantity = de.getProductionAarea();
						}
						hssfRow.createCell(4).setCellValue(quantity);

						String singleWeight = "";
						if (de.getUnit() != null) {
							singleWeight = de.getUnit();
						}
						hssfRow.createCell(5).setCellValue(singleWeight);

						String totalWeight = "";
						if (de.getUnitPrice() != null) {
							totalWeight = de.getUnitPrice();
						}
						hssfRow.createCell(6).setCellValue(totalWeight);

						String batchNo = "";
						if (de.getQuantity() != null) {
							batchNo = de.getQuantity();
						}
						hssfRow.createCell(7).setCellValue(batchNo);

						String date = "";
						if (de.getAccount() != null) {
							date = de.getAccount();
						}
						hssfRow.createCell(8).setCellValue(date);

						String comment = "";
						if (de.getComment() != null) {
							comment = de.getComment();
						}
						hssfRow.createCell(9).setCellValue(comment);

					}

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
