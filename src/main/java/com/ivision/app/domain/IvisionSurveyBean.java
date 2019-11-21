/**
  * Copyright 2019 bejson.com 
  */
package com.ivision.app.domain;
import java.util.List;

/**
 * Auto-generated: 2019-11-14 10:14:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class IvisionSurveyBean extends BaseResource {

    private long logId;
    private List<GeneralOcrWordsResult> wordsResult;
    private int wordsResult_num;
    private int direction;
	public IvisionSurveyBean(long logId, List<GeneralOcrWordsResult> wordsResult, int wordsResult_num, int direction) {
		super();
		this.logId = logId;
		this.wordsResult = wordsResult;
		this.wordsResult_num = wordsResult_num;
		this.direction = direction;
	}
	public IvisionSurveyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public List<GeneralOcrWordsResult> getWordsResult() {
		return wordsResult;
	}
	public void setWordsResult(List<GeneralOcrWordsResult> wordsResult) {
		this.wordsResult = wordsResult;
	}
	public int getWordsResult_num() {
		return wordsResult_num;
	}
	public void setWordsResult_num(int wordsResult_num) {
		this.wordsResult_num = wordsResult_num;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
   

}