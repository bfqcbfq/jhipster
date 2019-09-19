package com.ivision.app.domain;

/**
 * 上传图片识别的文字内容
 */
public class Ret {

    private Location location;
    private String word_name;
    private String word;
    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

    public void setWord_name(String word_name) {
         this.word_name = word_name;
     }
     public String getWord_name() {
         return word_name;
     }

    public void setWord(String word) {
         this.word = word;
     }
     public String getWord() {
         return word;
     }

}