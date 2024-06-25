package com.msme.crm.security.configuration;

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
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;


@Service
public class CRMAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final CrmRoleCustomRepository customRolRepository;

    @Autowired
    public CRMAuthorizationManager(CrmRoleCustomRepository customRolRepository) {
        this.customRolRepository = customRolRepository;
    }


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object)
    {

        UserDetails userDetails = (CRMUsers)authentication.get().getPrincipal();
        String userName = userDetails.getUsername();
        String requestUrl = object.getRequest().getRequestURI();
        String accessType =  object.getRequest().getMethod();
        System.out.println("userName "+userName);
        System.out.println("requestUrl "+ requestUrl);
        System.out.println("accessType "+ accessType);
        int lastBackSlashPosition = requestUrl.lastIndexOf("/");

        String ScreenName = requestUrl.substring(lastBackSlashPosition + 1);
        return new AuthorizationDecision(customRolRepository.findUserAccessToScreen(ScreenName,userName,accessType));
    }
}
