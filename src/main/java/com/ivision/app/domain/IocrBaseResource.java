/**
  * Copyright 2019 bejson.com 
  */
package com.ivision.app.domain;

/**
 * Auto-generated: 2019-11-21 10:40:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class IocrBaseResource {

    private String error_msg;
    private IocrData data;
    private int error_code;
    public void setError_msg(String error_msg) {
         this.error_msg = error_msg;
     }
     public String getError_msg() {
         return error_msg;
     }

    public void setData(IocrData data) {
         this.data = data;
     }
     public IocrData getData() {
         return data;
     }

    public void setError_code(int error_code) {
         this.error_code = error_code;
     }
     public int getError_code() {
         return error_code;
     }

}