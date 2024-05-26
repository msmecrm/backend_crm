package com.msme.crm.menuItem.repository;

import com.msme.crm.menuItem.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    // Add any additional query methods if needed
}

