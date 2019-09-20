package com.ivision.app.web.rest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.aip.ocr.AipOcr;
import com.ivision.app.domain.Ret;
import com.ivision.app.service.util.ExcelUtils;
import com.ivision.app.service.util.FileUtils;

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
	public String getfileRecord(@RequestParam(value = "file", required = false) MultipartFile[] uploadFiles)
			throws IOException {

		 log.debug("REST request to upload MultipartFile[] : {}", uploadFiles);

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

			try {
				uploadFile.transferTo(new File(newFilePath)); // 将传来的文件写入新建的文件
				return "上传成功";

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return "上传失败";

	}

	/**
	 * 
	 * 
	 * @param response
	 * @param request
	 * @param comcomSetQueryDtoStr
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/download")
	public String exportExcel(HttpServletResponse response, HttpServletRequest request) throws Exception {
		// Status status = new Status();

		String templateName = null; // 所属分公司
		List<Ret> retList = null; // 产品名称

		String filepath = "D:\\FilesAndDatas\\download\\201909191009111568858953094.jpg";
		// 获取前台参数信息
		JSONObject jsonObject = getResultByIocr(filepath);

		jsonObject.getJSONObject("data").get("templateName"); // 表格标题

		retList = (List<Ret>) jsonObject.getJSONObject("data").get("ret"); // 产品名称
		// JsonUtils.jsonToList(string11, Ret.class);

		String fileName = "北京神丰科技有限公司出货单.xls";
		
		String [] columnNames= {"编号","仓库","料号","品牌","单位","数量","单重","合计重量","批次号","出货日期","备注","品牌","编号","仓库","料号","品牌"};
		String [] columns= {"sid","sname","sex","birthday"};
		ExcelUtils.exportExcel(response, retList, columnNames, columns, "神风科技", fileName);

		return null;
	}
	
	
	//将Excel表格信息导入到数据库
	@PostMapping("/uploadFile")
	public boolean uploadFile(HttpServletRequest request,Ret ret,MultipartFile myfile,HttpServletResponse response) {
		try {
			//调用工具类FileUtils上传,前台传来的文件形参为myfile
			String newFileName = FileUtils.upload(myfile, request);
			String realPath = request.getSession().getServletContext().getRealPath("file");
			//将Excel文件的内容放入到String类型的双层数组中
			String[][] strings = ExcelUtils.readexcell(realPath+File.separator+newFileName, 1);
			//循环数组,依次拿取数组内的数据放入到实体类的对象里,以便于添加数据到数据库的表单内
			for (int i = 0; i < strings.length; i++) {
//				String [] stuData=strings[i];
//				Student user=new Student();
//				//从索引为1的开始拿数据,因为Excel表格内第一行是主键id,添加数据不需要id,不过如果在这写了也无所谓,没影响,添加的sql注意就好了.
//				user.setSname(stuData[1]);
//				user.setSex(stuData[2]);
//				user.setBirthday(stuData[3]);
//				studentMapper.addStu(user);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}	

	// 百度文字识别位置高精度版API调用（返回数据结构不佳）
	// iOCR自定义模板文字识别（效果较好）
	public JSONObject getResultByIocr(String filePath) throws IOException {

		AipOcr client = new AipOcr(appId, apiKey, secretKey);

		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		List<String> resultList = new ArrayList<String>();
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
		JSONObject custom = client.custom(filePath, options);
		
		
		
//		Object object = res.get("data");
//		
//		 Data data = new Data();
//		BeanUtils.copyProperties(object,data);
//		 
//		String templateName = data.getTemplateName();
//		
//		List<Ret> retList = data.getRet();
//		
//		for(Ret ret : retList) {
//			String word_name = ret.getWord_name();
//			String word = ret.getWord();
//			System.out.println(word_name+word);
//		}

//		 res.getJSONArray("data");
//
//		for (int i = 0; i < jsonArray.length(); i++) {
//			//jsonArray.getJSONObject(i).get
//			String word = jsonArray.getJSONObject(i).getString("templateName");
//			resultList.add(word);
//		}

		return custom;
	}

}
