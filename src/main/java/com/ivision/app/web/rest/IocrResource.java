package com.ivision.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;

@RestController
@RequestMapping("/api")
public class IocrResource {

	private final Logger log = LoggerFactory.getLogger(IocrResource.class);

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

	@PostMapping("/upload")
	public ResponseEntity<List<String>> getfileRecord(
			@RequestParam(value = "file", required = true) MultipartFile[] uploadFiles) throws IOException {

		log.debug("REST request to upload MultipartFile[] : {}", uploadFiles);
		
		//定义读取文件返回结果
		List<String> reslut = null;
		// 判断文件夹是否存在,不存在则创建
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		for(MultipartFile uploadFile : uploadFiles) {
		// 获取原始图片的扩展名
		String originalFileName = uploadFile.getOriginalFilename();

		// 定义文件下载本地新名称
		String newFileName = System.currentTimeMillis() + originalFileName;
		// 新文件的路径
		String newFilePath = filePath + newFileName;

		try {
			uploadFile.transferTo(new File(newFilePath)); // 将传来的文件写入新建的文件

			AipOcr client = new AipOcr(appId, apiKey, secretKey);

			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);

			reslut = getResultByIocr(client, newFilePath);

		} catch (IllegalStateException e) {
			// 处理异常
			throw new IllegalStateException();
		} catch (IOException e1) {
			// 处理异常
			throw new IOException();
		}
		}
		return ResponseEntity.status(HttpStatus.OK).body(reslut);

	}

	// 百度文字识别位置高精度版API调用
	public List<String> getResultByIocr(AipOcr client, String filePath) throws IOException {

		List<String> resultList = new ArrayList<String>();
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("recognize_granularity", "big");
		options.put("detect_direction", "true");
		options.put("vertexes_location", "true");
		options.put("probability", "true");
		// client.toString();

		// 参数为本地路径
		JSONObject res = client.accurateGeneral(filePath, options);
		JSONArray jsonArray = res.getJSONArray("words_result");
		
		for(int i=0;i<jsonArray.length();i++) {
			String word = jsonArray.getJSONObject(i).getString("words");
			resultList.add(word);
		}
		

		return resultList;
	}
	
	@PostMapping("/download")
	public ResponseEntity<String> downloadFiles(){
		return null;
		
	}
	

}
