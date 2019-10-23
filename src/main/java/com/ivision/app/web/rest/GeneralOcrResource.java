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
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.domain.BeanRsource;
import com.ivision.app.domain.GeneralOcrBean;
import com.ivision.app.domain.Location;
import com.ivision.app.domain.WordsResult;

/**
 * 调用百度通用文字识别API，实现文字识别
 *
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
		
		List<WordsResult> wordsResultList = new ArrayList<>();
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

			// if("jpg".equalsIgnoreCase(fileType) || "png".equalsIgnoreCase(fileType) ||
			// "bmp".equalsIgnoreCase(fileType)) {

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

			// 定义文件下载本地新名称
			String newFileName = dateFormat.format(now) + System.nanoTime() + fileType;

			// 新文件的路径
			String newFilePath = filePath + newFileName;

			try {

				// 将传来的文件写入新建的文件
				uploadFile.transferTo(new File(newFilePath));

//				JSONObject jsonObject = getResultByIocr(newFilePath);
//				String wordsResultNum = null;
//
//					wordsResultNum = jsonObject.get("words_result_num").toString();
//					
//					
//					
//
//					if ((Integer.valueOf(wordsResultNum))>=1) {
//						
//						JSONArray jsonArray = jsonObject.getJSONArray("words_result");
//						List<Object> list = jsonArray.toList();
//						
//						for (int i = 0; i < list.size(); i++) {
//							
//							WordsResult wordResult = new WordsResult();
//							HashMap<String, String> map =(HashMap) list.get(i);
//							wordResult.setWords(map.get("words"));
//							wordsResultList.add(wordResult);
//							
//							
//							
//							
//						}
//					}
				
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
	public ResponseEntity<List<WordsResult>> showUploadFileDetails(@RequestParam(value = "filepath") String filepath)
			throws IOException {
		List<WordsResult> resultByIocr = getResultByIocr(filepath);
		
				return ResponseEntity.ok(resultByIocr);
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

}
