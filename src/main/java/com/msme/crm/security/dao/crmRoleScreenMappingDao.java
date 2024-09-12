package com.msme.crm.security.dao;



public class crmRoleScreenMappingDao {

    private Integer roleId;
    private Integer screenId;
    private Boolean viewRecord;
    private Boolean CreateRecord;
    private Boolean editRecord ;
    private Boolean deleteRecord;

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
}
