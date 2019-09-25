package com.ivision.app.domain;

/**
 * 上传图片识别的文字内容
 */
public class Ret {

    private Location location;
    private String word_name;
    private String word;
    private Probability probability;
	public Ret() {
	}
	public Ret(Location location, String word_name, String word, Probability probability) {
		super();
		this.location = location;
		this.word_name = word_name;
		this.word = word;
		this.probability = probability;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getWord_name() {
		return word_name;
	}
	public void setWord_name(String word_name) {
		this.word_name = word_name;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Probability getProbability() {
		return probability;
	}
	public void setProbability(Probability probability) {
		this.probability = probability;
	}
	@Override
	public String toString() {
		return "Ret [location=" + location + ", word_name=" + word_name + ", word=" + word + ", probability="
				+ probability + "]";
	}
	
	

}