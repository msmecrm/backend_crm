package com.msme.crm.menuItem.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "role_menus_access")
public class RoleMenuAccess {
    @EmbeddedId
    private RoleMenuAccessId id;

    // Getters and setters
}
