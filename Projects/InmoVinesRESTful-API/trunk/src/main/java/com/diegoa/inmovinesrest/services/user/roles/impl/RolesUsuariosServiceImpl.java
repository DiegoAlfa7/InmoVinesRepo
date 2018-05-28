package com.diegoa.inmovinesrest.services.user.roles.impl;
import com.diegoa.inmovinesrest.repositories.roles.RolesUsuariosRepository;
import com.diegoa.inmovinesrest.services.user.roles.srv.RolesUsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesUsuariosServiceImpl implements RolesUsuariosService {

    @Autowired
    RolesUsuariosRepository rolesUsuariosRepository;


}
