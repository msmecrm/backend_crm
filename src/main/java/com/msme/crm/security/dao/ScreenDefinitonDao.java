package com.msme.crm.security.dao;

import jakarta.persistence.Column;

public class ScreenDefinitonDao {

    private String screenName;
    private String route;
    private String parentId;

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
