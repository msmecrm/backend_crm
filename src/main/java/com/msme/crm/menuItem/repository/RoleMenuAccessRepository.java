package com.msme.crm.menuItem.repository;

import com.msme.crm.menuItem.entities.RoleMenuAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleMenuAccessRepository extends JpaRepository<RoleMenuAccess, Integer> {



    List<RoleMenuAccess> findByIdRoleId(int roleId);
}

