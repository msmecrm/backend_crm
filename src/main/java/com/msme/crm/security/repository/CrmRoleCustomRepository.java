package com.msme.crm.security.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

@Repository
public class CrmRoleCustomRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public CrmRoleCustomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean findUserAccessToScreen(String ScreenName, String UserName, String AccessType)
    {
        if(UserName.isBlank()||ScreenName.isBlank()) return false;

        HashMap<String,String> authoritiesMap = new HashMap<>();
        authoritiesMap.put(RequestMethod.GET.toString(),"View_record");
        authoritiesMap.put(RequestMethod.PUT.toString(),"update_record");
        authoritiesMap.put(RequestMethod.DELETE.toString(),"delete_record");
        authoritiesMap.put(RequestMethod.POST.toString(),"create_record");

        authoritiesMap.get(AccessType);
        System.out.println("ScreenName Values "+ScreenName);
        System.out.println("User Values "+UserName);

        String screenAccessQuerySql =  "select count(1) from " +
                                "defaultdb.crmrole_screen_mapping, " +
                                "defaultdb.screen_definition, " +
                                "defaultdb.crm_users, " +
                                "defaultdb.crmuser_role_mapping " +
                                "where crmrole_screen_mapping.screen_id = screen_definition.screen_id " +
                                "And crm_users.id = crmuser_role_mapping.id " +
                                "And crmuser_role_mapping.roleid = crmrole_screen_mapping.roleid " +
                                "And screen_name = :ScreenName " +
                                "and email = :User  And " +
                                authoritiesMap.get(AccessType) +" = "+1;

        Query screenAccessQuery =  entityManager.createNativeQuery(screenAccessQuerySql)
                                            .setParameter("ScreenName",ScreenName)
                                            .setParameter("User",UserName)
                                            ;

        Long retunedvalues = (Long)screenAccessQuery.getSingleResult();
        System.out.println("returned Values "+retunedvalues);
        return retunedvalues>0L;
    }
}
