package com.msme.crm.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CRM_USERS")
public class CRMUsers implements UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
    @Column
    private Integer employeeID;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String photo;
    @Column
    private Integer phoneNumber;
    @Column
    private String landingPage;
    @Column
    private Integer ManagerID;
    @Column
    private boolean userStatus;

    @Column
    @Enumerated(EnumType.STRING)
    CrmRole crmRole;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(crmRole.name()));
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userStatus;
    }

}

