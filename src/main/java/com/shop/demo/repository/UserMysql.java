package com.shop.demo.repository;

import com.shop.demo.dao.UserDAO;
import com.shop.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository("user_mysql_repo")
public class UserMysql implements UserDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(String username, String password, boolean isEmployee) {
        String sql = "INSERT INTO User(username, password, isEmployee) VALUES (?,?,?)";
        Object[] args = new Object[]{
                username,
                password,
                isEmployee
        };
        jdbcTemplate.update(sql,args);
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username=?";
        Object[] args = new Object[]{
                username
        };
        return jdbcTemplate.queryForObject(sql,args, BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public void updateUser(String username, User user) {
        String sql = "UPDATE User SET password=? WHERE username=?";
        Object[] args = new Object[]{
                username,
                user.getPassword()
        };
        jdbcTemplate.update(sql,args);
    }
}
