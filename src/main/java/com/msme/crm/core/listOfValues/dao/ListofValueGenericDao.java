package com.msme.crm.core.listOfValues.dao;

import com.msme.crm.core.listOfValues.IListResult;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class ListofValueGenericDao implements IListResult {

    private String name;
    private String description;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setValues(Object[] sqlReturnValue, String outputColumnNames) {

        System.out.println("Inside the setValues of ListofValueGenericDao " + outputColumnNames);
        String[] listOfFields = outputColumnNames.split("~");
        ListofValueGenericDao listofValueGenericDao = this;
        Class<?> classObj = listofValueGenericDao.getClass();
        int idx = 0;
        try {
            Method[] methods = classObj.getMethods();
            for (int jdx = 0; idx < listOfFields.length; jdx++) {
                //Method method = classObj.getDeclaredMethod("set"+listOfFields[jdx]);
                for (Method method : methods) {
                    if (method.getName().equalsIgnoreCase("set" + listOfFields[jdx])) {
                        method.invoke(listofValueGenericDao, sqlReturnValue[idx]);
                        System.out.println("method  " + method.getName());
                        System.out.println("type  " + method.getParameterTypes().getClass());
                        System.out.println("sqlReturnValue[idx]  " + sqlReturnValue[idx]);
                        idx++;
                    }
                }
            }

            System.out.println("output is " + listofValueGenericDao);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public String toString() {
        return "ListofValueGenericDao{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
