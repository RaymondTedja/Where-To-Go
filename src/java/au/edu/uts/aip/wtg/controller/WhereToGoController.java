/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.controller;
import au.edu.uts.aip.wtg.dto.TouristSpot;
import au.edu.uts.aip.wtg.dao.TouristSpotDAO;
import java.sql.SQLException;
import java.util.*;
import javax.enterprise.context.*;
import javax.inject.*;
import javax.naming.NamingException;

/**
 * A BackingBean to support the index.xhtml
 * @author 12108573
 */
@Named
@RequestScoped
public class WhereToGoController {


    /**
     * Retrieve all of the tourist spot in WTG database
     *
     * @return all previously created tourist spot or an empty list if none have
     * been created
     * @throws SQLException and NamingException
     */

    public List<TouristSpot> getTouristSpotList() throws SQLException, NamingException {
        return new TouristSpotDAO().findAll();
    }    
}
