package com.msme.crm.core.entities;

import jakarta.persistence.*;

@Entity
public class ListOfValueEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column
    Integer ID;
    @Column
    String name;

    @Column
    String SqlQuery;

    @Column
    String outputColumnNames;

    @Column
    String assignmentField;

    @Column
    String daoClassName;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSqlQuery() {
        return SqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        SqlQuery = sqlQuery;
    }

    public String getOutputColumnNames() {
        return outputColumnNames;
    }

    public void setOutputColumnNames(String outputColumnNames) {
        this.outputColumnNames = outputColumnNames;
    }

    public String getAssignmentField() {
        return assignmentField;
    }

    public void setAssignmentField(String assignmentField) {
        this.assignmentField = assignmentField;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    @Override
    public String toString() {
        return "ListOfValueEntity{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", SqlQuery='" + SqlQuery + '\'' +
                ", outputColumnNames='" + outputColumnNames + '\'' +
                ", assignmentField='" + assignmentField + '\'' +
                ", daoClassName='" + daoClassName + '\'' +
                '}';
    }
}
