package com.msme.crm.security.repository;

import com.msme.crm.security.entities.ScreenDefinition;
import com.msme.crm.security.entities.crmRoleScreenMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface crmRoleScreenMappingRepository extends JpaRepository<crmRoleScreenMapping,Integer> {
        List<crmRoleScreenMapping> findByRoleId(int roleid);
        long deleteByRoleId(int RoleId);
}
