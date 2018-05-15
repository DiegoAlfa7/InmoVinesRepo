package com.diegoa.inmovinesrest.services.roles.impl;
import com.diegoa.inmovinesrest.repositories.roles.RolesUsuariosRepository;
import com.diegoa.inmovinesrest.services.roles.srv.RolesUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RolesUsuariosServiceImpl implements RolesUsuariosService {

    @Autowired
    RolesUsuariosRepository rolesUsuariosRepository;


}
