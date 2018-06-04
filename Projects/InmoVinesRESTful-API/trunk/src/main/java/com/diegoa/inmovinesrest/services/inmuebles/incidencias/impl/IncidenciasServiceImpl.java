package com.diegoa.inmovinesrest.services.inmuebles.incidencias.impl;

import com.diegoa.inmovinesrest.entities.inmuebles.incidencias.Incidencias;
import com.diegoa.inmovinesrest.repositories.inmuebles.IncidenciasRepository;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import com.diegoa.inmovinesrest.services.inmuebles.incidencias.srv.IncidenciasService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class IncidenciasServiceImpl implements IncidenciasService {

    @Autowired
    IncidenciasRepository incidenciasRepository;

    Logger logger = Logger.getLogger(InmueblesServiceImpl.class);


    @Override
    public Page<Incidencias> listAllByPage(Pageable pageable) {

        return this.incidenciasRepository.findAll(pageable);
    }

    @Override
    public Incidencias findOneById(long ID) {

        Optional<Incidencias> optionalIncidencias = this.incidenciasRepository.findById(ID);

        if (optionalIncidencias.isPresent()) {

            return optionalIncidencias.get();
        }

        return null;

    }

    @Override
    public List<Incidencias> listAll() {

        return (List<Incidencias>) this.incidenciasRepository.findAll();
    }

    @Override
    public Incidencias create(Incidencias incidencias) {

        if (incidencias != null) {

            return this.incidenciasRepository.save(incidencias);

        } else {

            logger.error("La incidencia a crear viene vacia por parametros");
            throw new RuntimeException("La incidencia a crear viene vacia por parametros");
        }
    }

    @Override
    public Incidencias update(Incidencias incidencias) {

        Optional<Incidencias> optionalIncidencias = this.incidenciasRepository.findById(incidencias.getId());

        if (optionalIncidencias.isPresent()) {

            Incidencias incidenciasPersistent = optionalIncidencias.get();
            incidenciasPersistent.copyParameters(incidencias);

            return this.incidenciasRepository.save(incidenciasPersistent);

        } else {

            logger.error("La incidencia con id " + incidencias.getId() + " no existe en la base de datos");
            throw new RuntimeException("La incidencia con id " + incidencias.getId() + " no existe en la base de datos");
        }

    }

    @Override
    public void delete(long id) {

        Optional<Incidencias>


    }
}
