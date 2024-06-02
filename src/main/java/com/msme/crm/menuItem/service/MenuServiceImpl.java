package com.msme.crm.menuItem.service;

import com.msme.crm.menuItem.dto.MenuDTO;
import com.msme.crm.menuItem.entities.Menu;
import com.msme.crm.menuItem.entities.Role;
import com.msme.crm.menuItem.entities.RoleMenuAccess;
import com.msme.crm.menuItem.repository.MenuRepository;
import com.msme.crm.menuItem.repository.RoleMenuAccessRepository;
import com.msme.crm.menuItem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RoleMenuAccessRepository roleMenuAccessRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<MenuDTO> getMenusByRole(String roleName) {
        List<MenuDTO> menuDTOs=null;
        // Fetch role IDs by role name
        Role role = roleRepository.findByRoleName(roleName);
        if(role!=null){
            List<RoleMenuAccess> roleMenuAccessList = roleMenuAccessRepository.findByIdRoleId(role.getRoleId());
            List<Integer> menuIds = roleMenuAccessList.stream()
                    .map(roleMenuAccess -> roleMenuAccess.getId().getMenuId())
                    .collect(Collectors.toList());
           if(menuIds!=null) {
                // Fetch menus and transform them into DTOs with parent menu IDs
                List<Menu> menus = menuRepository.findAllById(menuIds);
                menuDTOs = menus.stream().map(menu -> {
                            MenuDTO dto = new MenuDTO();
                            dto.setMenuId(menu.getMenuId());
                            dto.setMenuName(menu.getMenuName());
                            dto.setRoute(menu.getRoute());
                            // Handle the case where parentMenuId is null
                            Optional<Integer> parentMenuId = Optional.ofNullable(menu.getParentMenuId());
                            dto.setParentMenuId(parentMenuId.orElse(null));
                            return dto;
                        })
                        .collect(Collectors.toList());
            }
        }
        // Fetch menu IDs accessible by the given role IDs


        return menuDTOs;
    }
}
