package com.msme.crm.security.dao;

import com.msme.crm.security.entities.CrmRole;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private boolean userStatus;
    private CrmRole role;


}
