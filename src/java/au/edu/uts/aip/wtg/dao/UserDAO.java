/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.dao;

import au.edu.uts.aip.wtg.DataStoreException;
import au.edu.uts.aip.wtg.Sha;
import au.edu.uts.aip.wtg.dto.User;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TOSHIBA
 */
public class UserDAO {
        
    // Several final static variable that will be useful in this class
    private static final String JNDI_NAME = "jdbc/aip";
    private static final String SELECT_ACCOUNT =
            "select username, password, email, userType " +
            "from accountWTG ";
    private static final String ACCOUNT_ALL = SELECT_ACCOUNT;
    private static final String USER_USERNAME = SELECT_ACCOUNT + " where username = ? ";
    private static final String ADD_ACCOUNT = "insert into accountWTG " + " (username, password, email, userType) values" + "(?, ?, ?, ?)";
    
   /**
     * Instantiate a new TouristSpot object based on the data received from SQL statement
     * @return a TouristSpot object
     * @throws SQLException (Invalid SQL statement issues)
     */
    private User createRowDTO(ResultSet rs) throws SQLException {
        User result = new User();
        result.setUsername(rs.getString("username"));
        result.setPassword(rs.getString("password"));
        result.setEmail(rs.getString("email"));
        result.setType(rs.getString("userType"));
        return result;
    }

    public User find(String username) throws DataStoreException {
        UserDAO dao = new UserDAO();
        ArrayList<User> users = dao.findAll();
        for (User user : users) {
          if (user.getUsername().equals(username))
                return user;
        } 
        return null;
    }
    
    /**
     * Creates a new Account and saved it into WTG database
     * @throws DataStoreException (SQL and naming exception combined) & NoSuchAlgorithmException
     */
    public void addAccount(User user) throws DataStoreException, NoSuchAlgorithmException{
        try{
            DataSource ds = InitialContext.doLookup(JNDI_NAME);
            try(Connection conn = ds.getConnection();
                PreparedStatement ps = conn.prepareStatement(ADD_ACCOUNT)){
                
                String encryptedPassword = Sha.hash256(user.getPassword());
                        
                ps.setString(1, user.getUsername());
                ps.setString(2, encryptedPassword);
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getType());
                        
                ps.execute();
                
            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }
    }
    
    /**
     * Retrieves the account in the database, corresponding to a given username.
     * @return a User object corresponding to the username, or null if no matching user was found
     * @throws DataStoreException if an exception occurred while communicating with the database.
     */
    public User read(String username) throws NamingException, SQLException {
            DataSource ds = (DataSource)InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 PreparedStatement ps = conn.prepareStatement(USER_USERNAME)) {

                ps.setString(1, username);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        User result = new User();
                        result.setUsername(rs.getString("username"));
                        result.setPassword(rs.getString("password"));
                        result.setEmail(rs.getString("email"));
                        result.setType(rs.getString("userType"));
                        return result;
                    } else {
                        // user not found
                        return null;
                    }
                }
            }
    }
   
    /**
     * Retrieves all users from the database as a complete list of Data Transfer Objects.
     * @return a list containing every row of the database
     * @throws DataStoreException if an exception occurred while communicating with the database.
     */
    public ArrayList<User> findAll() throws DataStoreException {
        ArrayList<User> results = new ArrayList<>();
        try {
            DataSource ds = (DataSource)InitialContext.doLookup(JNDI_NAME);
            try (Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(ACCOUNT_ALL)) {

                while (rs.next()) {
                    results.add(createRowDTO(rs));
                }
                return results;
            }
        } catch (NamingException | SQLException e) {
            throw new DataStoreException(e);
        }   
      
    }
    
    
    
     
    
}


