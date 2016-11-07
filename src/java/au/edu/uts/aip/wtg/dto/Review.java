/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.dto;

import javax.validation.constraints.*;

/**
 * A class that stores the information about Review
 * @author 12108573
 */
public class Review {
    private String creator;
    private int rating;
    private String story;
    private String theSpot;

    /**
     * Creates a new review (Default constructor)
     */
    public Review(){
        
    }
    
    public Review(int rating, String story){
        this.rating = rating;
        this.story = story;
    }
    
    /**
     * Creates a new review with parameters
     * @param creator is the name of user who created this review
     * @param rating is the rating that is filled by user
     * @param story is the comment written by users to express their experiences
     */
    public Review(String creator, int rating, String story){
        this.creator = creator;
        this.rating = rating;
        this.story = story;
    }
    
    /**
     * to get or request for the creator's name 
     * @return creator must not be null and it is a primary key for this class
     */
    public String getCreator(){
        return creator;
    }
    
    /**
     * to set creator's name into the review
     * @param creator 
     */
    public void setCreator(String creator){
        this.creator = creator;
    }
    
    /**
     * The rating should be more than 1 and lower than 5
     * to get or request for the rating of a particular review
     * @return rating must not be null 
     */
    @Min(1)
    @Max(5)
    public int getRating(){
        return rating;
    }
    
    /**
     * to set a rating into a particular review
     * @param rating 
     */
    public void setRating(int rating){
        this.rating = rating;
    }
    
    /**
     * to get or request for the story or the comment of a particular review
     * @return story can be null which mean that users can choose not to put any comment 
     */
    public String getStory(){
        return story;
    }
    
    /**
     * to set the comment or story for the review about a particular tourist spot
     * @param story 
     */
    public void setStory(String story){
        this.story = story;
    }
    
        /**
     * to get or request for the tourist spot of a particular review
     * @return story can be null which mean that users can choose not to put any comment 
     */
    public String getTheSpot(){
        return theSpot;
    }
    
    /**
     * to set the Tourist Spot of that Review
     * @param story 
     */
    public void setTheSpot(String theSpot){
        this.theSpot = theSpot;
    }
   
    /**
    public int getID(){
        return id;
    }
    
    public void setID(int id){
        this.id = id;
    }
    **/
    
    public String toString(){
        return //"Review ID: " + this.id + "\n" +
                "creator: " + this.creator + "\n"+
                "rating: " + this.rating + "\n"+
                "story: " + this.story + "\n";
    }
}
