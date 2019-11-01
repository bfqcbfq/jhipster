/**
  * Copyright 2019 bejson.com 
  */
package com.ivision.app.domain;

import java.io.Serializable;

public class WordsResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String words;
    private Location location;
    public void setWords(String words) {
         this.words = words;
     }
     public String getWords() {
         return words;
     }

    public void setLocation(Location location) {
         this.location = location;
     }
     public Location getLocation() {
         return location;
     }

}