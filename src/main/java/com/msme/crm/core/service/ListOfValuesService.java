package com.msme.crm.core.service;

import com.msme.crm.core.entities.ListOfValueEntity;
import com.msme.crm.core.listOfValues.IListResult;
import com.msme.crm.core.repository.ListOfValueQueryRepository;
import com.msme.crm.core.repository.ListOfValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListOfValuesService {

    @Autowired
    final ListOfValueQueryRepository listOfValueQueryRepository;

    @Autowired
    final ListOfValuesRepository listOfValuesRepository;

    public ListOfValuesService(ListOfValueQueryRepository listOfValueQueryRepository, ListOfValuesRepository listOfValuesRepository) {
        this.listOfValueQueryRepository = listOfValueQueryRepository;
        this.listOfValuesRepository = listOfValuesRepository;
    }

    public List<IListResult> processLOVRequest(String lovName, List<String> assignmentValues){
        System.out.println("inside processLOVRequest "+lovName);
        Optional<ListOfValueEntity> lovqueryResult = listOfValuesRepository.findByname(lovName);

        System.out.println(lovqueryResult.get());

     List fieldValues  = listOfValueQueryRepository.executeSqlQuery
                        (lovqueryResult.get().getName(),
                        lovqueryResult.get().getSqlQuery()
                        ,assignmentValues);
     List<IListResult> returnValue = new ArrayList<>();

     for(int idx =0; idx< fieldValues.size(); idx++)
        {
            String objectName =  lovqueryResult.get().getDaoClassName() != null ? lovqueryResult.get().getDaoClassName() : "com.msme.crm.core.dao.ListofValueGenericDao";
            IListResult lovDaoValue = null;
            try {
                lovDaoValue = (IListResult)Class.forName(objectName)
                                               .getDeclaredConstructor()
                                               .newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            lovDaoValue.setValues((Object[])fieldValues.get(idx), lovqueryResult.get().getOutputColumnNames());
            returnValue.add(lovDaoValue);
        }
     System.out.println("return list is "+returnValue);
     return returnValue;
    }

}
