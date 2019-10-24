/**
  * Copyright 2019 bejson.com 
  */
package com.ivision.app.domain;
import java.util.List;


public class GeneralOcrBean {

    private long logId;
    private List<WordsResult> wordsResult;
    private int wordsResultNum;
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public List<WordsResult> getWordsResult() {
		return wordsResult;
	}
	public void setWordsResult(List<WordsResult> wordsResult) {
		this.wordsResult = wordsResult;
	}
	public int getWordsResultNum() {
		return wordsResultNum;
	}
	public void setWordsResultNum(int wordsResultNum) {
		this.wordsResultNum = wordsResultNum;
	}
    
    
    

}