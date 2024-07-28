package com.msme.crm.core.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class CRMPropertiesDao implements Comparable<CRMPropertiesDao> {
    Integer id;
    String Name;
    String Type;
    String Value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public int compareTo(CRMPropertiesDao o) {
        return Integer.compare(this.getName().length(),o.getName().length());
    }
}
