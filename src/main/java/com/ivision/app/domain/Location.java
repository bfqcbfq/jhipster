package com.ivision.app.domain;

/**
 * 上传图片位置坐标实体类
 *
 */
public class Location {

    private Integer top;
    private Integer left;
    private Integer width;
    private Integer height;
    
    
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Location(Integer top, Integer left, Integer width, Integer height) {
		super();
		this.top = top;
		this.left = left;
		this.width = width;
		this.height = height;
	}


	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Integer getLeft() {
		return left;
	}
	public void setLeft(Integer left) {
		this.left = left;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}


	@Override
	public String toString() {
		return "Location [top=" + top + ", left=" + left + ", width=" + width + ", height=" + height + "]";
	}
   

}