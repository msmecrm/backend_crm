package com.msme.crm.menuItem.service;

import com.msme.crm.menuItem.dto.MenuDTO;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getMenusByRole(String roleName);
}

