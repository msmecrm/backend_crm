package com.msme.crm.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.msme.crm.security.entities.CRMUsers;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrmUserRepository extends JpaRepository<CRMUsers, Integer> {

    Optional<CRMUsers> findByEmail(String email);
}
