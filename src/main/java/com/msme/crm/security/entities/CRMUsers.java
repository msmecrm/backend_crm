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
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CRM_USERS")
public class CRMUsers implements UserDetails {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
    @JoinTable(name = "CRMUSER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    List<CRMRoles> crmRoles;

    @Override
    public String toString() {
        return "CRMUsers{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", landingPage='" + landingPage + '\'' +
                ", ManagerID=" + ManagerID +
                ", userStatus=" + userStatus +
                ", crmRoles=" + crmRoles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return crmRoles.stream().map((a)-> new SimpleGrantedAuthority(a.getRoleid().toString())).collect(Collectors.toList());
       // return List.of(new SimpleGrantedAuthority(crmRole.name()));
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

