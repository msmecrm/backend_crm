package com.msme.crm.security.repository;

import com.msme.crm.security.entities.CRMRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrmRoleRepository extends JpaRepository<CRMRoles,Integer> {
     Optional<CRMRoles> findByRoleName(String roleName);
}
