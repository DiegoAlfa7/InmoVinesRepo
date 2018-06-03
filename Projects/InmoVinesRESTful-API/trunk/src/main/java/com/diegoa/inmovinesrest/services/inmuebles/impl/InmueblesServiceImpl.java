package com.diegoa.inmovinesrest.services.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.inmuebles.srv.InmueblesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @
 */
@Service
public class InmueblesServiceImpl implements InmueblesService {


    @Autowired
    InmueblesRepository inmueblesRepository;

    Logger logger = Logger.getLogger(InmueblesServiceImpl.class);


    @Override
    public Page<Inmuebles> listAllByPage(Pageable pageable) {

        return this.inmueblesRepository.findAll(pageable);

    }

    @Override
    public Inmuebles findOneById(long ID) {

        Optional<Inmuebles> mayb_inmueble = this.inmueblesRepository.findById(ID);

        if (mayb_inmueble.isPresent()) {

            return mayb_inmueble.get();

        } else {

            return null;

        }

    }

    @Override
    public List<Inmuebles> listAll() {
        return (List<Inmuebles>) inmueblesRepository.findAll();
    }

    /**
     * @param i la instancia de Inmuebles que debe ser guardada en el entorno de persistencia
     */
    @Override
    public void create(Inmuebles i) {

        if (i != null) {

            this.inmueblesRepository.save(i);
        } else {

            logger.error("El inmueble a crear viene vacio por parametros");
            throw new RuntimeException("El inmueble a crear viene vacio por parametros");
        }


    }

    /**
     * @param i entidad de tipo Inmuebles que representa un nuevo estado (valores cambiados) de una entidad existente en el
     *          contexto de persistencia
     * @return
     */
    @Override
    public void update(Inmuebles i) {

        Optional<Inmuebles> optionalInmuebles = inmueblesRepository.findById(i.getId());

        if (optionalInmuebles.isPresent()) {

            Inmuebles inmueblesPersistent = optionalInmuebles.get();

            inmueblesPersistent.copyParameters(i);

            this.inmueblesRepository.save(inmueblesPersistent);
        } else {

            logger.error("El inmueble con id " + i.getId() + " no existe en la base de datos");
            throw new RuntimeException("El inmueble con id " + i.getId() + " no existe en la base de datos");


        }

    }

    /**
     * @param ID el id de una entidad de tipo Inmuebles
     */
    @Override
    public void delete(long ID) {

        Optional<Inmuebles> inmuebleOptional = this.inmueblesRepository.findById(ID);

        if (inmuebleOptional.isPresent()) {

            Inmuebles inmueble = inmuebleOptional.get();
            this.inmueblesRepository.delete(inmueble);


        } else {

            logger.error("El inmueble con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El inmueble con id " + ID + " no existe en la base de datos");
        }


    }


}