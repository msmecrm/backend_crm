package com.msme.crm.security.controller;

import com.msme.crm.security.dao.CrmRoleDao;
import com.msme.crm.security.dao.ScreenDefinitonDao;
import com.msme.crm.security.service.MenuMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuMaintenanceService menuMaintenance;

    @PostMapping("/createRole")
    public ResponseEntity<CrmRoleDao> createrole(@RequestBody CrmRoleDao request)
    {
        return ResponseEntity.ok(menuMaintenance.createRoleDefiniton(request));
    }

    @PostMapping("/createScreenDefintion")
    public ResponseEntity<ScreenDefinitonDao> createscreenDefinition(@RequestBody ScreenDefinitonDao screenDefinitonDao)
    {
        return ResponseEntity.ok(menuMaintenance.createScreenDefiniton(screenDefinitonDao));
    }



    @GetMapping("/getRoleDetails")
    public ResponseEntity<CrmRoleDao> getRoleDetails(@RequestBody String roleName)
    {
        return ResponseEntity.ok(menuMaintenance.getRoleDefiniton(roleName));
    }

    @GetMapping("/getScreenDetails")
    public ResponseEntity<ScreenDefinitonDao> getScreenDetails(@RequestBody String screenName)
    {
        return ResponseEntity.ok(menuMaintenance.getScreenDefiniton(screenName));
    }

}
