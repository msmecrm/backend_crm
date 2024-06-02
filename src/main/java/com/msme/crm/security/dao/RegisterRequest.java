package com.msme.crm.security.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Integer phoneNumber;
    private String landingPage;
    private Integer ManagerID;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", landingPage='" + landingPage + '\'' +
                ", ManagerID=" + ManagerID +
                ", userStatus=" + userStatus +
                ", rolesList=" + rolesList +
                '}';
    }

    private boolean userStatus;
    private List<CrmRoleDao> rolesList;


}
