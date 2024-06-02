package com.msme.crm.security.service;

import com.msme.crm.security.dao.AuthenticationRequest;
import com.msme.crm.security.dao.AuthenticationResponse;
import com.msme.crm.security.dao.RegisterRequest;
import com.msme.crm.security.entities.CRMRoles;
import com.msme.crm.security.entities.CRMUsers;
import com.msme.crm.security.repository.CrmRoleRepository;
import com.msme.crm.security.repository.CrmUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CrmUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MenuMaintenanceService menuMaintenanceService;
    private final ModelMapper modelMapper;
    private final CrmRoleRepository crmRoleRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var roles = request.getRolesList();
        System.out.println(request);
       List<CRMRoles> rolelist= roles.stream().map(a-> crmRoleRepository.findByRoleName(a.getRoleName()).get()).toList();
        var user = CRMUsers.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .crmRoles(rolelist)
                .userStatus(true)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .screenAccesList(menuMaintenanceService.getSceenAvalibleToUser(user))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        var screenAccessist = menuMaintenanceService.getSceenAvalibleToUser(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .screenAccesList(menuMaintenanceService.getSceenAvalibleToUser(user))
                .build();
    }






}
