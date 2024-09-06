package com.msme.crm.security.repository;

import com.msme.crm.security.entities.crmRoleScreenMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface crmRoleScreenMappingRepository extends JpaRepository<crmRoleScreenMapping,Integer> {

}
