package com.msme.crm.security.dao;

import java.util.List;

public class CrmUserDao {

    private Integer id;
    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Integer phoneNumber;
    private String landingPage;
    private Integer ManagerID;
    private boolean userStatus;

    private List<CrmRoleDao> roles;

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getManagerID() {
        return ManagerID;
    }

    public void setManagerID(Integer managerID) {
        ManagerID = managerID;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CrmRoleDao> getRoles() {
        return roles;
    }

    public void setRoles(List<CrmRoleDao> roles) {
        this.roles = roles;
    }
}
