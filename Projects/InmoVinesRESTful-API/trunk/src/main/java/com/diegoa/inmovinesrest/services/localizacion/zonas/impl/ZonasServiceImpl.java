package com.diegoa.inmovinesrest.services.localizacion.zonas.impl;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.repositories.localizacion.ZonasRepository;
import com.diegoa.inmovinesrest.services.localizacion.zonas.srv.ZonasService;
import org.apache.log4j.Logger;
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

    Logger logger = Logger.getLogger(ZonasServiceImpl.class);

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

    @Override
    public Zonas create(Zonas zonas) {

        if (zonas != null) {

           return this.zonasRepository.save(zonas);

        } else {

            logger.error("La zona a crear viene vacia por parametros");
            throw new RuntimeException("La zona a crear viene vacia por parametros");
        }

    }

    @Override
    public Zonas update(Zonas zonas) {

        Optional<Zonas> optionalZonas = zonasRepository.findById(zonas.getId());

        if (optionalZonas.isPresent()) {

            Zonas zonasPersistant = optionalZonas.get();

            zonasPersistant.copyParameters(zonas);

            return  this.zonasRepository.save(zonasPersistant);

        } else {

            logger.error("La zona con id " + zonas.getId() + " no existe en la base de datos");
            throw new RuntimeException("La zona con id " + zonas.getId() + " no existe en la base de datos");

        }

    }

    @Override
    public boolean delete(long id) {

        Optional<Zonas> optionalIncidencias = zonasRepository.findById(id);

        if (optionalIncidencias.isPresent()) {

            Zonas zona = optionalIncidencias.get();

            this.zonasRepository.delete(zona);

            Optional zonaBorrada = this.zonasRepository.findById(id);

            if (zonaBorrada.isPresent()) {

                return false;

            } else {

                return true;
            }

        } else {

            logger.error("La zona con id " + id + " no existe en la base de datos");
            throw new RuntimeException("La zona con id " + id + " no existe en la base de datos");

        }
    }


}
