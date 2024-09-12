package com.msme.crm.security.service;

import com.msme.crm.security.dao.CrmRoleDao;
import com.msme.crm.security.dao.CrmUserDao;
import com.msme.crm.security.dao.ScreenDefinitonDao;
import com.msme.crm.security.dao.crmRoleScreenMappingDao;
import com.msme.crm.security.entities.CRMUsers;
import com.msme.crm.security.entities.ScreenDefinition;
import com.msme.crm.security.entities.crmRoleScreenMapping;
import com.msme.crm.security.repository.CrmRoleRepository;
import com.msme.crm.security.repository.CrmScreenDefinitionRepository;
import com.msme.crm.security.repository.crmRoleScreenMappingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.msme.crm.security.entities.CRMRoles;
import org.springframework.stereotype.Service;
import  com.msme.crm.security.repository.CrmUserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuMaintenanceService {
    @Autowired
    private CrmRoleRepository crmRoleRepository;
    @Autowired
    private CrmScreenDefinitionRepository crmScreenDefinitionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  CrmUserRepository crmUserRepository;

    @Autowired
    private crmRoleScreenMappingRepository crmRoleScreenMappingRepository;


    public CrmRoleDao createRoleDefiniton(CrmRoleDao crmRoleDao) {
       CRMRoles crmRole = modelMapper.map(crmRoleDao, CRMRoles.class);
        crmRoleRepository.save(crmRole);
        crmRoleDao.setRoleID(crmRole.getRoleid());
        List<crmRoleScreenMappingDao>  crmRoleScreenMappingDaoList =populateScreenDetails(crmRoleDao);

        crmRoleDao.setScreenAccess(crmRoleScreenMappingDaoList);
       return crmRoleDao;
    }

    private List<crmRoleScreenMappingDao> populateScreenDetails(CrmRoleDao crmRoleDao) {
        List<crmRoleScreenMappingDao> crmRoleScreenMappingDaoList = new ArrayList<>();
            for(int idx = 0; idx< crmRoleDao.getScreenAccess().size(); idx++) {
                crmRoleScreenMappingDao crmRoleScreenMappingDao = crmRoleDao.getScreenAccess().get(idx);
                crmRoleScreenMapping crmRoleScreenMapEntity = new crmRoleScreenMapping();
                crmRoleScreenMapEntity.setRoleId(crmRoleDao.getRoleID());
                crmRoleScreenMapEntity.setScreenId(crmRoleScreenMappingDao.getScreenId());
                crmRoleScreenMapEntity.setDeleteRecord(crmRoleScreenMappingDao.getDeleteRecord());
                crmRoleScreenMapEntity.setEditRecord(crmRoleScreenMappingDao.getEditRecord());
                crmRoleScreenMapEntity.setViewRecord(crmRoleScreenMappingDao.getViewRecord());
                crmRoleScreenMapEntity.setCreateRecord(crmRoleScreenMappingDao.getCreateRecord());
                crmRoleScreenMappingDao = modelMapper.map(crmRoleScreenMappingRepository.save(crmRoleScreenMapEntity),crmRoleScreenMappingDao.class);
                crmRoleScreenMappingDaoList.add(crmRoleScreenMappingDao);
            }
            return crmRoleScreenMappingDaoList;
    }



    public CrmRoleDao editRole(CrmRoleDao crmRoleDao) {
            CRMRoles crmRole = crmRoleRepository.findByRoleName(crmRoleDao.getRoleName()).get();
            crmRole.setRoleDescription(crmRoleDao.getRoleDescription());
            crmRoleDao = modelMapper.map(crmRoleRepository.save(crmRole),CrmRoleDao.class);
            Long totalRecordsDelete = crmRoleScreenMappingRepository.deleteByRoleId(crmRoleDao.getRoleID());
            List<crmRoleScreenMappingDao>  crmRoleScreenMappingDaoList  = populateScreenDetails(crmRoleDao);
            crmRoleDao.setScreenAccess(crmRoleScreenMappingDaoList);
            return crmRoleDao;
    }

    public ScreenDefinitonDao createScreenDefiniton(ScreenDefinitonDao screenDefinitonDao) {
        ScreenDefinition screenDefinition = modelMapper.map(screenDefinitonDao,ScreenDefinition.class);
        return modelMapper.map(crmScreenDefinitionRepository.save(screenDefinition),ScreenDefinitonDao.class);
    }


    public CrmRoleDao getRoleDefiniton(String roleName) {
        CrmRoleDao crmRoleDao =  modelMapper.map(crmRoleRepository.findByRoleName(roleName).get(),CrmRoleDao.class);
        crmRoleDao.setScreenAccess(fetchroleswithScreenDetails(crmRoleDao));
        return crmRoleDao;
    }

    public List<CrmRoleDao> getAllRoles(){
        return crmRoleRepository.findAll().stream().map((inputRole) -> modelMapper.map(inputRole,CrmRoleDao.class)).toList();

    }

    public ScreenDefinitonDao getScreenDefiniton(String screenName) {
        return modelMapper.map(crmScreenDefinitionRepository.findByScreenName(screenName).get(),ScreenDefinitonDao.class);
    }


    public List<crmRoleScreenMappingDao> fetchroleswithScreenDetails(CrmRoleDao crmRoleDao)
    {
        List<crmRoleScreenMapping>  roleScreenMappingList = crmRoleScreenMappingRepository.findByRoleId(crmRoleDao.getRoleID());
        System.out.println("Role Id to be used is "+crmRoleDao.getRoleID());
        System.out.println("roleScreenMappingList Id to be used is "+roleScreenMappingList);
        List<crmRoleScreenMappingDao> crmRoleScreenMappingDaoList = roleScreenMappingList.stream().map(a ->modelMapper.map(a,crmRoleScreenMappingDao.class)).collect(Collectors.toList());
        System.out.println("crmRoleScreenMappingDaoList Id to be used is "+crmRoleScreenMappingDaoList);
        return crmRoleScreenMappingDaoList;
    }

    public List<CrmRoleDao> getSceenAvalibleToUser(CRMUsers user) {
        System.out.println("userid "+user.getId());
        var userlocal =  crmUserRepository.getReferenceById(user.getId());
        System.out.println("role Details "+userlocal.getCrmRoles());
        List<CRMRoles> roles   =  userlocal.getCrmRoles();
        System.out.println("roles "+roles);
        List<CrmRoleDao> roleDaos = roles.stream().map(a ->modelMapper.map(a,CrmRoleDao.class)).collect(Collectors.toList());
        roleDaos.stream().forEach((a)-> a.setScreenAccess(fetchroleswithScreenDetails(a)));
        return roleDaos;
    }


    public CrmUserDao createUser(CrmUserDao userDao)
    {
       CRMUsers user = modelMapper.map(userDao,CRMUsers.class);
        CrmUserDao crmUserDao = modelMapper.map(crmUserRepository.save(user),CrmUserDao.class);
        crmUserDao.setPassword("");
        return crmUserDao;
    }

    public List<CrmUserDao> getAllUsers(){
        List<CrmUserDao> crmUserDaoList = new ArrayList<>();
          for(CRMUsers users:crmUserRepository.findAll())
            {
                  CrmUserDao crmUserDao = modelMapper.map(users,CrmUserDao.class);
                  crmUserDao.setPassword("");
                  crmUserDaoList.add(crmUserDao);
            }
      //  List<CrmUserDao> userList = crmUserRepository.findAll().stream().map((inputUser) -> modelMapper.map(inputUser,CrmUserDao.class)).toList();
        return crmUserDaoList;
    }

    public CrmUserDao getUser(Integer userId){
        return modelMapper.map(crmUserRepository.getReferenceById(userId),CrmUserDao.class);
    }

    public CrmUserDao UpdateUser(CrmUserDao userDao)
    {
        CRMUsers user = crmUserRepository.getReferenceById(userDao.getId());
        CRMUsers incomingDetails =  modelMapper.map(userDao,CRMUsers.class);
        user.setCrmRoles(incomingDetails.getCrmRoles());
        user.setFirstName(incomingDetails.getFirstName());
        user.setPassword(incomingDetails.getPassword());
        user.setFirstName(incomingDetails.getFirstName());
        user.setLastName(incomingDetails.getLastName());
        user.setEmployeeID(incomingDetails.getEmployeeID());
        user.setEmail(incomingDetails.getEmail());
        user.setUserStatus(incomingDetails.isUserStatus());
        user.setLandingPage(incomingDetails.getLandingPage());
        user.setManagerID(incomingDetails.getManagerID());
        CrmUserDao crmUserDao = modelMapper.map(crmUserRepository.save(user),CrmUserDao.class);
        crmUserDao.setPassword("");
        return crmUserDao;
    }


}
