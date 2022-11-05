package com.shop.demo.service;

import com.shop.demo.model.Employee;
import com.shop.demo.model.User;
import com.shop.demo.repository.UserMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    private EmployeeService employeeService;

    private String SESSION_AUTH_KEY = "AUTH_USER";
    private String SESSION_USER_NAME = "AUTH_USERNAME";


    public boolean checkCredentials(int userID, String password){
        Employee user = employeeService.getEmployeeByID(userID);
        return user.getPassword().equals(password);
    }

    public void loginUser(HttpSession session, int userID){



        session.setAttribute(SESSION_AUTH_KEY,userID);
        session.setAttribute(SESSION_USER_NAME,employeeService.getEmployeeByID(userID).getName());
    }

    public void logoutUser(HttpSession session){
        session.removeAttribute(SESSION_AUTH_KEY);
        session.removeAttribute(SESSION_USER_NAME);
    }

    public Integer getCurrentUser(HttpSession session){
        try {
            return Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString());
        } catch (Exception e){
            System.out.println("Exception in Authentication service yayyy :-)");
            return null;
        }
    }

    public Boolean isAuthenticated(HttpSession session){
        return getCurrentUser(session) != null;
    }

    public Boolean isAdmin(HttpSession session){
        try{
            Employee user = employeeService.getEmployeeByID(Integer.parseInt(session.getAttribute(SESSION_AUTH_KEY).toString()));
            return user.getRole()==1;
        }
        catch (Exception e){
            System.out.println("Exception in isAdmin");
            return false;
        }
    }
}
