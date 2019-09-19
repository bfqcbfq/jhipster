package com.ivision.app.domain;

/**
 * 上传图片位置坐标实体类
 *
 */
public class Location {

    private int top;
    private int left;
    private int width;
    private int height;
    public void setTop(int top) {
         this.top = top;
     }
     public int getTop() {
         return top;
     }

    public void setLeft(int left) {
         this.left = left;
     }
     public int getLeft() {
         return left;
     }

    public void setWidth(int width) {
         this.width = width;
     }
     public int getWidth() {
         return width;
     }

    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

}