package com.msme.crm.security.repository;


import com.msme.crm.security.entities.ScreenDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrmScreenDefinitionRepository extends JpaRepository<ScreenDefinition,Integer> {
    Optional<ScreenDefinition> findByScreenName(String screenName);

}
