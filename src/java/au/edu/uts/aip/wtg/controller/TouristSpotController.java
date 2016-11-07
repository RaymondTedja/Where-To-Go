/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.controller;

import au.edu.uts.aip.wtg.DataStoreException;
import au.edu.uts.aip.wtg.dto.TouristSpot;
import au.edu.uts.aip.wtg.dao.TouristSpotDAO;
import java.sql.*;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.naming.NamingException;

/**
 * A BackingBean to support the index.xhtml Facelet.
 */
@Named
@RequestScoped
public class TouristSpotController {

    private String name;
    

    private TouristSpot spot = new TouristSpot();

    /**
     * 
     * Not working as intended
     * 
     *  private String spotName;
     *  public void setSpotName(String spotName) {   
     *  this.spotName = spotName;
     * }
     **/
    
    /**
     * Retrieve the current "working" tourist spot.
     *
     * @return a touristSpot object that can be manipulated
     */
    public TouristSpot getSpot() {
        return spot;
    }

    /**    not working as intended
    public ArrayList<Review> getReviewList() throws SQLException, NamingException, DataStoreException {
        return new ReviewDAO().findAll(spotName);
    }
    **/
    /**
     * Create a new touristSpot in WTG database. The current "working" spot
     * (i.e., obtained by calling getSpot()) will be saved.
     *
     * @return a JSF redirect to the main index
     */
    public String addSpot() {
        try {
            new TouristSpotDAO().create(spot);
            return "/user/index?face-redirect=true";
        } catch (DataStoreException e) {
            showError("That name is not available.\n Please choose another name");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Look for specific touristSpot that in WTG database. The class variable
     * called "spot" will be changed into the new tourist spot that we just look
     * for
     */
    public void findSpot(String name) {
        try {
            spot = new TouristSpotDAO().read(name);
        } catch (SQLException | NamingException e) {
            showError("That touristSpot is not available in WTG");
            e.printStackTrace();
        }
    }

    /**
     * Update a selected touristSpot that existed in WTG database. The current
     * "working" touristSpot (i.e., obtained by calling getSpot()) will be
     * saved.
     *
     * @return a JSF redirect to the main index page
     */
    public String saveChanges() {
        try {
            new TouristSpotDAO().update(spot);
            return "/user/index?face-redirect=true";
        } catch (DataStoreException e) {
            showError("That touristSpot is not available in WTG");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete the selected touristSpot in the underlying WTG database. The
     * current "working" touristSpot (i.e., obtained by calling getSpot()) will
     * be deleted.
     *
     * @return a JSF redirect to the main index page
     */
    public String deleteSpot() {
        try {
            new TouristSpotDAO().delete(spot.getName());
            return "/user/index?face-redirect=true";
        } catch (DataStoreException e) {
            showError("That tourist Spot is not avalaible in WTG");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Source = Solution from week 5 practice Adds a message to the current
     * faces context, so that it will appear in a h:messages element.
     *
     * @param message the text of the error message to show the user
     */
    private void showError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

}
