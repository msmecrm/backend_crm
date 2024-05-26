package com.msme.crm.menuItem.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RoleMenuAccessId implements Serializable {
    @Column(name = "role_id")
    private int roleId;

    @Column(name = "menu_id")
    private int menuId;

    // Getters and setters
}
