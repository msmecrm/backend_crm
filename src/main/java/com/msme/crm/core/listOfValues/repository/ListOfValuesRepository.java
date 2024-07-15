package com.msme.crm.core.listOfValues.repository;

import com.msme.crm.core.entities.ListOfValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ListOfValuesRepository extends JpaRepository<ListOfValueEntity, Integer> {
    Optional<ListOfValueEntity> findByname(String name);

}
