/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.dto;

import java.util.ArrayList;
import javax.validation.constraints.Size;

/**
 * A class that stores the information about the user's account details
 * @author 12108573
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String type;
    
    public User(){
        
    }
    
    /**
     * Creates a new account for user with parameters
     * @param username is the name of user who created this review
     * @param password is the main point of the reason why we create the memo
     * @param email is the date when the memo is first created
     * @param type is the type that define they are normal user or administrator.
     */
    public User(String username, String password, String email, String type){
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
     // this.reviewIDs = new ArrayList<String>();
    }
    
    /**
     * The user name must be more than 6 characters
     * to get or request for the name of user
     * @return name must not be null, it is a primary key so it should not be duplicated
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * to set a new username for a particular user
     * @param username 
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * password must be at least 6 letter or digit
     * to get the password from an account
     * @return password must not be null
     */
    @Size(min=6)
    public String getPassword() {
        return password;
    }

    /**
     * to set a new password into a particular user
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * to get or request for type of a particular user
     * @return type must not be null
     */
    public String getType() {
        return type;
    }
    
    /**
     * to set a new value of type for User
     * @param type has 2 value, such as standard and administrator
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * to get or request for the email from a particular user
     * @return email must not be null
     */
    public String getEmail() {
        return email;
    }

    /**
     * to set the new value of email into a particular user
     * @param email the new value of email to set for this user
     */
    public void setEmail(String email) {
        this.email = email;
    }
        
}
