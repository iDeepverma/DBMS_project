package com.shop.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int empID;
    private String name;
    private Date DOB;
    private String email;
    private String phone;
    private int salary;
    private Date joinDate;
    private int role;
    private String address;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmpID() {
        return empID;
    }

    public String getName() {
        return name;
    }

    public Date getDOB() {
        return DOB;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }
}
