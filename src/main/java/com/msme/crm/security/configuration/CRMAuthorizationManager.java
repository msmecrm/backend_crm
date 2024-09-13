package com.msme.crm.security.configuration;

import com.msme.crm.core.dao.CRMPropertiesDao;
import com.msme.crm.core.entities.CRMProperties;
import com.msme.crm.core.repository.CRMPropertiesRepository;
import com.msme.crm.security.dao.CrmRoleDao;
import com.msme.crm.security.entities.CRMRoles;
import com.msme.crm.security.entities.CRMUsers;
import com.msme.crm.security.repository.CrmRoleCustomRepository;
import com.msme.crm.security.repository.CrmUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.http.HttpHeaders;
import java.util.*;
import java.util.function.Supplier;

import static java.util.Comparator.comparingInt;


@Service
public class CRMAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final CrmRoleCustomRepository customRolRepository;
    private final CRMPropertiesRepository crmPropertiesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CRMAuthorizationManager(CrmRoleCustomRepository customRolRepository, CRMPropertiesRepository crmPropertiesRepository, ModelMapper modelMapper) {
        this.customRolRepository = customRolRepository;
        this.crmPropertiesRepository = crmPropertiesRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object)
    {
        System.out.println("inside check ");
        String requestUrl = object.getRequest().getRequestURI();
        String accessType =  object.getRequest().getMethod();

        System.out.println("requestUrl "+ requestUrl);
        System.out.println("accessType "+ accessType);

        List<CRMProperties> crmProperties = crmPropertiesRepository.findAll();
        List<CRMPropertiesDao> crmPropertiesDaoList = new ArrayList<>(crmProperties.stream()
                .filter((a) -> a.getType().equalsIgnoreCase("CONTROLLER"))
                .map((a) -> modelMapper.map(a, CRMPropertiesDao.class))
                .toList());

        Collections.sort(crmPropertiesDaoList);
        boolean isRestricted= true;

        for(CRMPropertiesDao crmPropertiesDao : crmPropertiesDaoList)
        {
            if(requestUrl.contains(crmPropertiesDao.getName())){

                if(!crmPropertiesDao.getValue().equalsIgnoreCase("RESTRICTED")) {
                    isRestricted = false;
                }
             requestUrl = requestUrl.replace(crmPropertiesDao.getName(),"");
             break;
            }
        }

        if(!isRestricted|| requestUrl.contains("error"))
            return new AuthorizationDecision(true);


        UserDetails userDetails = (CRMUsers)authentication.get().getPrincipal();
        String userName = userDetails.getUsername();
        System.out.println("userName "+userName);

        System.out.println( "Finally requestUrl "+ requestUrl);
        int lastBackSlashPosition = requestUrl.indexOf("/");
        System.out.println( "lastBackSlashPosition is "+ lastBackSlashPosition);

        String ScreenName = requestUrl;

        if(lastBackSlashPosition>0) {
         ScreenName = requestUrl.substring(0,lastBackSlashPosition);
        }


        System.out.println( "Finally derived screen name is "+ ScreenName);
        return new AuthorizationDecision(customRolRepository.findUserAccessToScreen(ScreenName,userName,accessType));
    }
}
