package com.ivision.app.domain;

public class Probability {
	
	private Double average;
	
	private Double min;
	
	private Double variance;
	
	

	public Probability() {
	}



	public Probability(Double average, Double min, Double variance) {
		super();
		this.average = average;
		this.min = min;
		this.variance = variance;
	}



	public Double getAverage() {
		return average;
	}



	public void setAverage(Double average) {
		this.average = average;
	}



	public Double getMin() {
		return min;
	}



	public void setMin(Double min) {
		this.min = min;
	}



	public Double getVariance() {
		return variance;
	}



	public void setVariance(Double variance) {
		this.variance = variance;
	}



	@Override
	public String toString() {
		return "Probability [average=" + average + ", min=" + min + ", variance=" + variance + "]";
	}
	
	
	
	

}
