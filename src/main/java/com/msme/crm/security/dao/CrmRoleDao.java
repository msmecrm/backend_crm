package com.msme.crm.security.dao;

import com.msme.crm.security.entities.ScreenDefinition;

import java.util.List;

public class CrmRoleDao {
    private String roleName;
    private String roleDescription;
    private  Integer roleID;
    private List<crmRoleScreenMappingDao> screenAccess;


    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public List<crmRoleScreenMappingDao> getScreenAccess() {
        return screenAccess;
    }

    public void setScreenAccess(List<crmRoleScreenMappingDao> screenAccess) {
        this.screenAccess = screenAccess;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "CrmRoleDao{" +
                "roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
