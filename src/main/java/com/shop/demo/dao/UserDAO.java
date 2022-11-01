package com.shop.demo.dao;

import com.shop.demo.model.User;

public interface UserDAO {
    void createUser(String username, String password, boolean isEmployee);
    User getUserByUsername(String username);
    void updateUser(String username, User user);
}
