package com.msme.crm.menuItem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuDTO {
    private int menuId;
    private String menuName;
    private String route;
    private Integer parentMenuId; // Added field for parent menu ID

    // Constructors, getters, setters
    public MenuDTO(){

    }
}
