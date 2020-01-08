package com.ivision.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class MyWebMvcConfigurer implements WebMvcConfigurer  {
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		//获取文件的真实路径 work_project代表项目工程名 需要更改
//        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\content\\";
//        String os = System.getProperty("os.name");
//        if (os.toLowerCase().startsWith("win")) {
//            registry.addResourceHandler("/src/main/webapp/content/images/ocr/upload/**").
//                    addResourceLocations("file:"+path);
//        }else{
//        	//linux和mac系统 可以根据逻辑再做处理
//            registry.addResourceHandler("/src/main/webapp/content/**").
//                    addResourceLocations("file:"+path);
//        }
//        
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//	}
//
//}
