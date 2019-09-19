package com.ivision.app.domain;

/**
 * 调用百度API返回JSON对应实体
 *
 */
public class JsonRootBean {

    private String error_msg;
    private Data data;
    private int error_code;
    public void setError_msg(String error_msg) {
         this.error_msg = error_msg;
     }
     public String getError_msg() {
         return error_msg;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}