package com.msme.crm.security.service;

import com.msme.crm.security.dao.CrmRoleDao;
import com.msme.crm.security.dao.CrmUserDao;
import com.msme.crm.security.dao.ScreenDefinitonDao;
import com.msme.crm.security.entities.CRMUsers;
import com.msme.crm.security.entities.ScreenDefinition;
import com.msme.crm.security.repository.CrmRoleRepository;
import com.msme.crm.security.repository.CrmScreenDefinitionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.msme.crm.security.entities.CRMRoles;
import org.springframework.stereotype.Service;
import  com.msme.crm.security.repository.CrmUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuMaintenanceService {
    @Autowired
    private CrmRoleRepository crmRoleRepository;
    @Autowired
    private CrmScreenDefinitionRepository crmScreenDefinitionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  CrmUserRepository crmUserRepository;


    public CrmRoleDao createRoleDefiniton(CrmRoleDao crmRoleDao) {
       CRMRoles crmRole = modelMapper.map(crmRoleDao, CRMRoles.class);
       return modelMapper.map(crmRoleRepository.save(crmRole),CrmRoleDao.class);
    }

    public ScreenDefinitonDao createScreenDefiniton(ScreenDefinitonDao screenDefinitonDao) {
        ScreenDefinition screenDefinition = modelMapper.map(screenDefinitonDao,ScreenDefinition.class);
        return modelMapper.map(crmScreenDefinitionRepository.save(screenDefinition),ScreenDefinitonDao.class);
    }


    public CrmRoleDao getRoleDefiniton(String roleName) {
       return modelMapper.map(crmRoleRepository.findByRoleName(roleName).get(),CrmRoleDao.class);
    }

    public ScreenDefinitonDao getScreenDefiniton(String screenName) {
        return modelMapper.map(crmScreenDefinitionRepository.findByScreenName(screenName).get(),ScreenDefinitonDao.class);
    }


    public List<CrmRoleDao> getSceenAvalibleToUser(CRMUsers user) {
        System.out.println("userid "+user.getId());
        var userlocal =  crmUserRepository.getReferenceById(user.getId());
        System.out.println("role Details "+userlocal.getCrmRoles());
        List<CRMRoles> roles   =  userlocal.getCrmRoles();
        System.out.println("roles "+roles);
        return roles.stream().map(a ->modelMapper.map(a,CrmRoleDao.class)).collect(Collectors.toList());
    }


    public CrmUserDao createUser(CrmUserDao userDao)
    {
       CRMUsers user = modelMapper.map(userDao,CRMUsers.class);
        return modelMapper.map(crmUserRepository.save(user),CrmUserDao.class);
    }

    public List<CrmUserDao> getAllUsers(){
        List<CrmUserDao> userList = crmUserRepository.findAll().stream().map((inputUser) -> modelMapper.map(inputUser,CrmUserDao.class)).toList();
        return userList;
    }

    public CrmUserDao getUser(Integer userId){
        return modelMapper.map(crmUserRepository.getReferenceById(userId),CrmUserDao.class);
    }


}
