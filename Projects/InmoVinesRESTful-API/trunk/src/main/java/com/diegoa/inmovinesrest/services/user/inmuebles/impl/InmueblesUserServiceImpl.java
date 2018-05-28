package com.diegoa.inmovinesrest.services.user.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.user.inmuebles.srv.InmueblesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InmueblesUserServiceImpl implements InmueblesUserService {

    @Autowired
    InmueblesRepository inmueblesRepository;


    @Override
    public Page<Inmuebles> listAllByPage(Pageable pageable) {

        return this.inmueblesRepository.findAll(pageable);

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
