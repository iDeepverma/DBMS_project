package com.shop.demo.service;

import com.shop.demo.model.User;
import com.shop.demo.repository.UserMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    private String SESSION_AUTH_KEY = "AUTH_USER";

    public boolean checkCredentials(String username, String password){
        User user = userService.getUserByUsername(username);
        return user.getPassword().equals(password);
    }

    public void loginUser(HttpSession session, String username)
}
