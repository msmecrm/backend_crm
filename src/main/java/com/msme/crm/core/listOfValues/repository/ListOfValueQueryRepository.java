package com.msme.crm.core.listOfValues.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListOfValueQueryRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public ListOfValueQueryRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List executeSqlQuery(String lovName, String lovQuery, List<String> lovBindingFieldValues)
    {
        String screenAccessQuerySql =  lovQuery;
        Query screenAccessQuery =  entityManager.createNativeQuery(screenAccessQuerySql);
        for(int idx =0; idx< lovBindingFieldValues.size(); idx++)
        {
            screenAccessQuery.setParameter(idx+1, lovBindingFieldValues.get(idx));
        }
        List retunedvalues = screenAccessQuery.getResultList();
        System.out.println("returned Values "+retunedvalues);
        return retunedvalues;
    }
}
