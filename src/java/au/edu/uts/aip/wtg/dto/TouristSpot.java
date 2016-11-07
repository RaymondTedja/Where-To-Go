/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.dto;

import java.io.Serializable;

/**
 * A class that store a detailed information about a tourist spot
 * @author TOSHIBA
 */
public class TouristSpot implements Serializable{
    // name is the unique identifier for this class, each spot have their own unique name
    private String name;
    private String type;
    private String state;
    private String city;
    private int avgRating;
    //private ReviewList reviewList;
    
    public TouristSpot(){
        
    }
    
    /**
     * Creates a new tourist sport with several parameters
     * @param name is the name for a particular tourist spot
     * @param type is the type of the tourist spot. Example: Mountain, Lake, Beach, National Park, etc
     * @param state is the location of the tourist spot in terms of state 
     * @param city is indicating which city is near with a particular tourist sport 
     */
    public TouristSpot(String name, String type, String state, String city){
        this.name = name;
        this.type = type;
        this.state = state;
        this.city = city;
    }
    
    /**
     * to get or request for the name of a particular tourist spot
     * @return name must not be null, it is a primary key 
     */
    public String getName(){
        return name;
    }
    
    /**
     * to set a new name for a particular tourist spot
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * to get or request for the type of a particular tourist spot 
     * @return type must not be null 
     */
    public String getType(){
        return type;
    }
    
    /**
     * to set the type of tourist spot. example: mountain, beach, jungle
     * @param type 
     */
    public void setType(String type){
        this.type = type;
    }
    
    /**
     * to get or request for the state where the spot is located at
     * @return state must not be null 
     */
    public String getState(){
        return state;
    }
    
    /**
     * to set a new state for a particular tourist spot
     * @param state 
     */
    public void setState(String state){
        this.state = state;
    }
    
    /**
     * to get or request for the name of the city that near the tourist spot
     * @return city must not be null 
     */
    public String getCity(){
        return city;
    }
    
    /**
     * to set the city of where the tourist spot is located
     * @param city 
     */
    public void setCity(String city){
        this.city = city;
    }
    
    /**
     * to get or request for the average rating for a particular tourist spot
     * @return avgRating may be null
     */
    public int getAvgRating(){
        return avgRating;
    }
    
    /**
     * to set the average rating for a particular tourist spot
     * @param avgRating 
     */
    public void setAvgRating(int avgRating){
        this.avgRating = avgRating;
    }
    
    /**
    public ReviewList getReviewList(){
        return reviewList;
    }
    
    public void setReviewList(ReviewList reviewList){
        this.reviewList = reviewList;
    }
    **/
    
    
    public String toString(){
        return this.name + "\n"+
                "State: " + this.state + "\n"+
                "Type: " + this.type + "\n" +
                "Review: " + "\n"; 
                //+
                //reviewList.toString();
    }
}
