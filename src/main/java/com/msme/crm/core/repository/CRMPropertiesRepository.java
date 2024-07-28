package com.msme.crm.core.repository;

import com.msme.crm.core.entities.CRMProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRMPropertiesRepository extends JpaRepository<CRMProperties,Integer> {
}
