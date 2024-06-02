package com.msme.crm.security.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="CRM_ROLE")
public class CRMRoles {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    private Integer roleid;
    @Column
    private String roleName;
    @Column
    private String roleDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE})
    @JoinTable(name = "CRMROLE_SCREEN_MAPPING", joinColumns = @JoinColumn(name = "roleID"),
            inverseJoinColumns = @JoinColumn(name = "screenId"))
    private List<ScreenDefinition> screenIds;

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public List<ScreenDefinition> getScreenIds() {
        return screenIds;
    }

    public void setScreenIds(List<ScreenDefinition> screenIds) {
        this.screenIds = screenIds;
    }

    @Override
    public String toString() {
        return "CRMRoles{" +
                "roleid=" + roleid +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", screenIds=" + screenIds +
                '}';
    }
}
