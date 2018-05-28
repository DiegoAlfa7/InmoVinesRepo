package com.diegoa.inmovinesrest.services.user.clientes.impl;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesReposiroty;
import com.diegoa.inmovinesrest.services.user.clientes.srv.ClientesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientesUserServiceImpl implements ClientesUserService {

    @Autowired
    ClientesReposiroty clientesReposiroty;


    @Override
    public Page<Clientes> listAllByPage(Pageable pageable) {
        return clientesReposiroty.findAll(pageable);
    }

    @Override
    public Clientes findOneById(long ID) {
        return null;
    }

    @Override
    public List<Clientes> listAll() {
        return null;
    }
}
