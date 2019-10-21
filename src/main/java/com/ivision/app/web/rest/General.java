package com.ivision.app.web.rest;


import java.net.URLEncoder;

import com.ivision.app.service.util.Base64Util;
import com.ivision.app.service.util.FileUtil;
import com.ivision.app.service.util.HttpUtil;



public class General {

	/**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static void main(String[] args) {
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        String filePath = "C:\\Users\\wanglei\\Desktop\\demo\\44.JPG";
        
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8")+"&"+URLEncoder.encode("language_type", "UTF-8") + "=" + URLEncoder.encode("JAP", "UTF-8");
            
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.7e6eea5086c31eab7621cdd04283850a.2592000.1573976597.282335-17209008";
            String result = HttpUtil.post(otherHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
