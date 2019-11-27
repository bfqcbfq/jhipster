package com.ivision.app.demo;

public class MultiplicationDemo {
	
	
	public static void main(String[] args) {
		
		String x = "" ;
		
		for (int i = 1; i < 10; i++) {
			
			
			for (int j = 1; j <= i; j++) {
				
				x+=j+"*"+i+"="+j*i+";";
				
			}
			
			System.out.println(x);
		}
	}

}
