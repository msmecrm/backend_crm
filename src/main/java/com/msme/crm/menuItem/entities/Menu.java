package com.msme.crm.menuItem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@Entity
@Table(name = "menus")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name="route")
    private String route;
    @Column(name = "parent_menu_id")
    private Integer parentMenuId;



    // Getters and setters
}
