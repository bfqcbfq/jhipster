package com.ivision.app.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

	//文件上传功能调用(1:上传文件FileUtils.upload)
	public static String upload(MultipartFile myfile,HttpServletRequest request) throws IllegalStateException, IOException {
				//获取源文件的名称 和类型 例如 abc.jpg;
				String oldname = myfile.getOriginalFilename();
				String fileType = oldname.substring(oldname.lastIndexOf("."));
				//文件重新命名,定义新文件名
				String newName = UUID.randomUUID().toString()+fileType;
				//定义文件上传路径
				//获取当前项目的根路径
				//D:\stsworkspace\fileUpDown\src\main\webapp
				//(2:获取文件路径)String path =request.getSession().getServletContext().getRealPath("file");
				String path =request.getSession().getServletContext().getRealPath("file");
				//String path ="D:\\stsworkspace\\fileUpDown\\src\\main\\webapp\\files";
				//创建文件
				//(3:路径+/+文件名,1)
				File file = new File(path+File.separator+newName);
				//完成文件复制
				myfile.transferTo(file);
				return newName;
	}
	
	//文件下载功能
	public  static void downLoad(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {
		//设置http传输格式
				//设置下载的文件名
				response.setContentType("application/force-download");// 设置强制下载不打开
				// 设置文件名
			 	response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
				//获取文件路径
				String path = request.getServletContext().getRealPath("file/image");
				//通过文件名获取文件,更加文件名 加文件的路径 创建文件对象
				File file = new File(path+File.separator+fileName);
				//获取输入流对象，
				InputStream is = new FileInputStream(file);
				//获取输出流对象
				ServletOutputStream outputStream = response.getOutputStream();
				//输入流转换为输出流对象
				int b;
				while((b=is.read())!=-1) {
					outputStream.write(b);
				}
				outputStream.close();
	}
}

