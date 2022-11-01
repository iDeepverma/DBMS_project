package com.shop.demo.service;

import com.shop.demo.dao.UserDAO;
import com.shop.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(@Qualifier("user_mysql_repo") UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void createUser(String username, String password, boolean isEmployee){
        userDAO.createUser(username,password,isEmployee);
    }

    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);
    }

    public void updateUser(String username, User user){
        userDAO.updateUser(username,user);
    }

}
