package com.msme.crm.security.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SCREEN_DEFINITION")
public class ScreenDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer screenId;
    @Column
    private String screenName;
    @Column
    private String route;

    @Column
    private String parentId;
    @Column
    private Boolean viewRecord = false;
    @Column
    private Boolean CreateRecord= false;
    @Column
    private Boolean editRecord=false;

    @Column
    private Boolean deleteRecord=false;


    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getRoute() {
        return route;
    }


    public void setRoute(String route) {
        this.route = route;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
        return "ScreenDefinition{" +
                "screenId=" + screenId +
                ", screenName='" + screenName + '\'' +
                ", route='" + route + '\'' +
                ", parentId='" + parentId + '\'' +
                ", viewRecord=" + viewRecord +
                ", CreateRecord=" + CreateRecord +
                ", editRecord=" + editRecord +
                ", deleteRecord=" + deleteRecord +
                '}';
    }
}
