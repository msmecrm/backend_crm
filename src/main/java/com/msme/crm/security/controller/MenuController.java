package com.msme.crm.security.controller;

import com.msme.crm.security.dao.*;
import com.msme.crm.security.service.AuthenticationService;
import com.msme.crm.security.service.MenuMaintenanceService;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuMaintenanceService menuMaintenance;

    @Autowired
    private AuthenticationService authenticationService;

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

    @PostMapping("/UserManagementScreen")
    public ResponseEntity<CrmUserDao> createUser(@RequestBody CrmUserDao crmUserDao){
        return ResponseEntity.ok(menuMaintenance.createUser(crmUserDao)) ;
    }

    @PutMapping("/UserManagementScreen")
    public ResponseEntity<CrmUserDao> UpdateUser(@RequestBody CrmUserDao crmUserDao){
        return ResponseEntity.ok(menuMaintenance.UpdateUser(crmUserDao)) ;
    }

    @GetMapping(value= {"/UserManagementScreen"})
    public ResponseEntity<?> fetchallUser(){
        return ResponseEntity.ok(( menuMaintenance.getAllUsers()));
    }

    @GetMapping(value= {"/UserManagementScreen/{userid}"})
    public ResponseEntity<CrmUserDao> fetchUser(@PathVariable Integer userid){
        return ResponseEntity.ok(menuMaintenance.getUser(userid)) ;
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
