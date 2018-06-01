package com.diegoa.inmovinesrest.services.localizacion.zonas.impl;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.repositories.localizacion.ZonasRepository;
import com.diegoa.inmovinesrest.services.localizacion.zonas.srv.ZonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Arroyo
 * @version 0.0.1
 */
@Service
public class ZonasServiceImpl implements ZonasService {

    @Autowired
    ZonasRepository zonasRepository;

    @Override
    public Page<Zonas> listAllByPage(Pageable pageable) {

        return this.zonasRepository.findAll(pageable);
    }

    @Override
    public Zonas findOneById(long ID) {

        Optional<Zonas> optionalZonas = this.zonasRepository.findById(ID);

        if (optionalZonas.isPresent()) {
            return optionalZonas.get();
        }
        return null;
    }

    @Override
    public List<Zonas> listAll() {

        return (List<Zonas>) this.zonasRepository.findAll();
    }
}
