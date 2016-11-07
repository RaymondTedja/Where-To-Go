/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.wtg.controller;

import au.edu.uts.aip.wtg.DataStoreException;
import au.edu.uts.aip.wtg.dto.User;
import au.edu.uts.aip.wtg.dao.UserDAO;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TOSHIBA
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private User user = new User();

    // Properties of the Java Bean, used by JSF fields
    private String username;
    private String password;
    private String email;

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void findUser(String name) throws SQLException, NamingException {
        user = new UserDAO().read(name);
    }
    
    /**
     * this method is called when user want to register at WTG web site
     *
     * @return an outcome corresponding to a appropriate page, to the login page
     * if the registration success 
     * A valid error message appears if the registration not success
     */
    public String register() throws NoSuchAlgorithmException {
        UserDAO dao = new UserDAO();

        try {
            dao.addAccount(user);
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            return "/login?faces-redirect=true";
        } catch (DataStoreException e) {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            showError("That username is not available.\n Please choose another username");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Source = Practice Solution from week 5 Attempt to log in using the
     * username/password properties set by JSF. Uses the container-managed
     * authentication to perform the password check.
     *
     * @return an outcome corresponding to a secure welcome page, null if the
     * login failed
     */
    public String loginContainer() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.login(username, password);
            return "/user/index?faces-redirect=true";

        } catch (ServletException e) {

            showError("Incorrect username or password");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Source = Practice Solution from week 5 Logs out the current user of the
     * container-managed authentication.
     *
     * @return an outcome corresponding to the login page
     * @throws ServletException if there is no currently logged in user
     */
    public String logoutContainer() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    /**
     * Logs out the current user of the container-managed authentication.
     *
     * @return a list of user
     * @throws DataStoreException if there is either SQL errors or JNDI Naming
     * issues
     */
    public ArrayList<User> getAllUsers() throws DataStoreException {
        return new UserDAO().findAll();
    }

    /**
     * Source = Practice Solution from week 5 Adds a message to the current
     * faces context, so that it will appear in a h:messages element.
     *
     * @param message the text of the error message to show the user
     */
    private void showError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(message));
    }

}
