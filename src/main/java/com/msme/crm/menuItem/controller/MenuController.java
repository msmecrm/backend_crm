package com.msme.crm.menuItem.controller;

import com.msme.crm.menuItem.dto.MenuDTO;
import com.msme.crm.menuItem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{roleName}")
    public List<MenuDTO> getMenusByRole(@PathVariable String roleName) {
        return menuService.getMenusByRole(roleName);
    }
}

