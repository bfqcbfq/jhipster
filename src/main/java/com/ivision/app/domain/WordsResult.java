/**
  * Copyright 2019 bejson.com 
  */
package com.ivision.app.domain;

/**
 * Auto-generated: 2019-11-14 10:14:51
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class WordsResult {

    private Probability probability;
    private String words;
    private Location location;
    public void setProbability(Probability probability) {
         this.probability = probability;
     }
     public Probability getProbability() {
         return probability;
     }

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