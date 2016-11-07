/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.dao;

import au.edu.uts.aip.wtg.DataStoreException;
import au.edu.uts.aip.wtg.dto.TouristSpot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TOSHIBA
 */
public class TouristSpotDAO {

    // Several final static variable that will be useful in this class
    private static final String JNDI_NAME = "jdbc/aip";
    private static final String SELECT_TOURIST_SPOT
            = "select spotName, spotType, spotState, spotCity "
            + "from tourist_Spot_WTG ";
    private static final String TOURIST_SPOT_ALL = SELECT_TOURIST_SPOT;
    private static final String TOURIST_SPOT_NAME = SELECT_TOURIST_SPOT + " where spotName = ?";
    private static final String ADD_SPOT = "insert into tourist_Spot_WTG " + " (spotName, spotType, spotState, spotCity) values" + "(?, ?, ?, ?)";
    private static final String UPDATE_SPOT = "update tourist_Spot_WTG " + " set spotType = ?, spotState = ?, spotCity = ?" + " where spotName = ?";
    private static final String DELETE_SPOT = "delete from tourist_Spot_WTG " + " where spotName = ?";

    /**
     * Instantiate a new TouristSpot object based on the data received from SQL statement
     * @return a TouristSpot object
     * @throws SQLException (Invalid SQL statement issues)
     */
    private TouristSpot createRowDTO(ResultSet rs) throws SQLException {
        TouristSpot result = new TouristSpot();
        result.setName(rs.getString("spotName"));
        result.setType(rs.getString("spotType"));
        result.setState(rs.getString("spotState"));
        result.setCity(rs.getString("spotCity"));
        return result;
    }

    /**
     * Creates a new tourist spot and saved it into WTG database
     * @throws DataStoreException (SQL and naming exception combined)
     */
    public void create(TouristSpot spot) throws DataStoreException {
        try {
            DataSource ds = (DataSource) InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(ADD_SPOT)) {
                ps.setString(1, spot.getName());
                ps.setString(2, spot.getType());
                ps.setString(3, spot.getState());
                ps.setString(4, spot.getCity());

                ps.executeUpdate();
            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
    
    /**
     * Retrieves a particular tourist spot from WTG database as a working Data Transfer
     * Object.
     * @return a row of database that based on the name that is passed from controller
     * @throws NamingException (JNDI naming issues) & SQLException (Invalid SQL statement issues)
     */
    public TouristSpot read(String name) throws NamingException, SQLException {
        DataSource ds = InitialContext.doLookup(JNDI_NAME);
        try (Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(TOURIST_SPOT_NAME)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createRowDTO(rs);
                } else {
                    // user not found
                    return null;
                }
            }
        }
    }

    /**
     * Update a specific Tourist spot and then fill it into WTG database
     * @throws DataStoreException (JNDI & SQL exception combined together) 
     */
    public void update(TouristSpot spot) throws DataStoreException {
        try {
            DataSource ds = (DataSource) InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(UPDATE_SPOT)) {

                ps.setString(1, spot.getType());
                ps.setString(2, spot.getState());
                ps.setString(3, spot.getCity());
                ps.setString(4, spot.getName());
                ps.execute();
            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
   
    /**
     * Deletes a specific tourist spot from the database 
     * @throws DataStoreException (JNDI & SQL exception combined together) 
     */
    public void delete(String name) throws DataStoreException {
        try {
            DataSource ds = (DataSource) InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                    PreparedStatement ps = conn.prepareStatement(DELETE_SPOT)) {
                
                ps.setString(1, name);
                ps.execute();
            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
    
    /**
     * Retrieves all tourist spot from the database as a complete list of Data Transfer
     * Objects.
     * @return a list containing every row of the database
     * @throws NamingException (JNDI naming issues) & SQLException (Invalid SQL statement issues)
     */
    public ArrayList<TouristSpot> findAll() throws NamingException, SQLException {

        DataSource ds = (DataSource) InitialContext.doLookup(JNDI_NAME);
        try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_TOURIST_SPOT)) {
            
            ArrayList<TouristSpot> results = new ArrayList<>();
            
            while (rs.next()) {
                results.add(createRowDTO(rs));
            }
            return results;
        }
    }

}
