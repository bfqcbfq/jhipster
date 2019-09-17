package com.ivision.app.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;

@RestController
@RequestMapping("/api")
public class IocrResource{

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

	@Value("${file.downLoad.path}")
	private String filePath; // 定义上传文件的存放位置


	@PostMapping("/upload")
	public ResponseEntity<String> getfileResult(MultipartFile upload, HttpServletResponse response) throws IOException {
		log.debug("REST request to upload MultipartFile : {}", upload);

		// 判断文件夹是否存在,不存在则创建
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}

		String originalFileName = upload.getOriginalFilename();// 获取原始图片的扩展名
		//
		String newFileName = System.currentTimeMillis() + originalFileName;
		String newFilePath = filePath + newFileName; // 新文件的路径

		try {
			upload.transferTo(new File(newFilePath)); // 将传来的文件写入新建的文件
			if (new File(newFilePath).exists()) {
				//TODO
			}
			AipOcr client = new AipOcr(appId, apiKey, secretKey);

			client.setConnectionTimeoutInMillis(2000);
			client.setSocketTimeoutInMillis(60000);

			String reslut = getResultByIocr(client, newFilePath);

			return ResponseEntity.status(HttpStatus.OK).body(reslut);
		} catch (IllegalStateException e) {
			// 处理异常
			throw new IllegalStateException();
		} catch (IOException e1) {
			// 处理异常
			throw new IOException();
		}

	}

	// 位置高精度版
	public String getResultByIocr(AipOcr client, String filePath) throws IOException {

		// InvoiceUploadResult uploadResult = new InvoiceUploadResult();
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();
		options.put("recognize_granularity", "big");
		options.put("detect_direction", "true");
		options.put("vertexes_location", "true");
		options.put("probability", "true");
		// client.toString();

		// 参数为本地路径
		String image = filePath;
		JSONObject res = client.accurateGeneral(image, options);

		// 参数为二进制数组
//		byte[] file = readFile(image);
//		res = client.accurateGeneral(file, options);
		return res.toString(2);
	}

	public byte[] readFile(String filePath) throws IOException {

		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file  " + file.getName()) ;
		}
		fi.close();
		return buffer;

	}

	/*
	 * public void fileDownLoad(HttpServletResponse response,String fileName,String
	 * filePath){ File file = new File(filePath);
	 * 
	 * response.setContentType("application/force-download");// 设置强制下载不打开    
	 * 
	 * response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
	 * byte[] buffer = new byte[1024]; FileInputStream fis = null;
	 * BufferedInputStream bis = null; try { fis = new FileInputStream(file); bis =
	 * new BufferedInputStream(fis); OutputStream outputStream =
	 * response.getOutputStream(); int i = bis.read(buffer); while (i != -1) {
	 * //outputStream.write(buffer, 0, i); //i = bis.read(buffer);                
	 *  }                               }catch(Exception e) { //e.printStackTrace();
	 * }          }
	 */

}
