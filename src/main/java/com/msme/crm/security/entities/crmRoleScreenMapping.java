package com.msme.crm.security.entities;

import jakarta.persistence.*;

@Entity
@Table(name="CRMROLE_SCREEN_MAPPING")
public class crmRoleScreenMapping {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private Integer roleId;
    @Column
    private Integer screenId;

    @Column
    private Boolean viewRecord = false;
    @Column
    private Boolean CreateRecord = false;
    @Column
    private Boolean editRecord = false;
    @Column
    private Boolean deleteRecord = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public Boolean getViewRecord() {
        return viewRecord;
    }

    public void setViewRecord(Boolean viewRecord) {
        this.viewRecord = viewRecord;
    }

    public Boolean getCreateRecord() {
        return CreateRecord;
    }

    public void setCreateRecord(Boolean createRecord) {
        CreateRecord = createRecord;
    }

    public Boolean getEditRecord() {
        return editRecord;
    }

    public void setEditRecord(Boolean editRecord) {
        this.editRecord = editRecord;
    }

    public Boolean getDeleteRecord() {
        return deleteRecord;
    }

    public void setDeleteRecord(Boolean deleteRecord) {
        this.deleteRecord = deleteRecord;
    }

    @Override
    public String toString() {
        return "crmRoleScreenMapping{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", screenId=" + screenId +
                ", viewRecord=" + viewRecord +
                ", CreateRecord=" + CreateRecord +
                ", editRecord=" + editRecord +
                ", deleteRecord=" + deleteRecord +
                '}';
    }
}