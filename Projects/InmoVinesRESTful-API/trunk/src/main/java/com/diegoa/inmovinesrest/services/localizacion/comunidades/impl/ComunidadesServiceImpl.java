package com.diegoa.inmovinesrest.services.localizacion.comunidades.impl;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.entities.localizacion.Provincias;
import com.diegoa.inmovinesrest.repositories.localizacion.ComunidadesRepository;
import com.diegoa.inmovinesrest.services.localizacion.comunidades.srv.ComunidadesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Arroyo
 * @version 0.0.1
 */
@Service
public class ComunidadesServiceImpl implements ComunidadesService {

    @Autowired
    ComunidadesRepository comunidadesRepository;


    @Override
    public Comunidades findOneById(long ID) {

        Optional<Comunidades> optionalComunidades = this.comunidadesRepository.findById(ID);

        if (optionalComunidades.isPresent()) {
            return optionalComunidades.get();
        }

       throw new RuntimeException("La comunidad con ID: "+ID+" no produjo ningún resultado");
    }

    @Override
    public List<Comunidades> listAll() {

        return (List<Comunidades>) this.comunidadesRepository.findAll();
    }
    @Override
    public List<Provincias> findProvinciasById(long ID) {

        Optional<Comunidades> optionalComunidades = this.comunidadesRepository.findById(ID);

        if (optionalComunidades.isPresent()) {
            return optionalComunidades.get().getProvinciasList();
        }

        throw new RuntimeException("La comunidad con ID: "+ID+" no produjo ningún resultado");
    }
}
