package com.msme.crm.menuItem.repository;

import com.msme.crm.menuItem.entities.Menu;
import com.msme.crm.menuItem.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Add any additional query methods if needed
    Role findByRoleName(String roleName);
}

