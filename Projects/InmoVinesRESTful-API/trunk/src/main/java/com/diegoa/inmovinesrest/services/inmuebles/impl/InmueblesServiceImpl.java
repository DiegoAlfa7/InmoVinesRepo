package com.diegoa.inmovinesrest.services.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.inmuebles.srv.InmueblesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public class InmueblesServiceImpl implements InmueblesService {

    @Autowired
    InmueblesRepository inmueblesRepository;


    @Override
    public Page<Inmuebles> listAllByPage(Pageable pageable) {
        return null;
    }

    @Override
    public Inmuebles findOneById(long ID) {
        return null;
    }

    @Override
    public List<Inmuebles> listAll() {
        return null;
    }
}
