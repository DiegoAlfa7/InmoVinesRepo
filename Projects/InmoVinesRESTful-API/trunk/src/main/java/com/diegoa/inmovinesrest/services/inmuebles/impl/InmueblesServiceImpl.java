package com.diegoa.inmovinesrest.services.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.inmuebles.srv.InmueblesService;
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


    @Override
    public Page<Inmuebles> listAllByPage(Pageable pageable) {

        return this.inmueblesRepository.findAll(pageable);

    }

    @Override
    public Inmuebles findOneById(long ID) {

        Optional<Inmuebles> mayb_inmueble = this.inmueblesRepository.findById(ID);

        if(mayb_inmueble.isPresent()){

            return mayb_inmueble.get();

        }else{

            return null;

        }

    }

    /**
     *
     * @param i la instancia de Inmuebles que debe ser guardada en el entorno de persistencia
     *
     */
    public void create(Inmuebles i){

        this.inmueblesRepository.save(i);


    }

    /**
     *
     * @param i entidad de tipo Inmuebles que representa un nuevo estado (valores cambiados) de una entidad existente en el
     *          contexto de persistencia
     * @return
     */
    public void update(Inmuebles i){

        this.inmueblesRepository.save(i);

    }

    /**
     *
     * @param ID el id de una entidad de tipo Inmuebles
     */
    public void deleteById(long ID){

        Optional<Inmuebles> inmuebleOptional = this.inmueblesRepository.findById(ID);

        if(inmuebleOptional.isPresent()){

            Inmuebles inmueble = inmuebleOptional.get();
            this.inmueblesRepository.delete(inmueble);



        }



    }

    /**
     *
     * @param ID entidad de tipo Inmuebles que debe existir en la BBDD / contexto de persistencia
     */
    public void delete(long ID){

        Optional<Inmuebles> inmuebleOptional = this.inmueblesRepository.findById(ID);

        if(inmuebleOptional.isPresent()){

            Inmuebles inmueble = inmuebleOptional.get();
            this.inmueblesRepository.delete(inmueble);



        }



    }

    @Override
    public List<Inmuebles> listAll() {
        return null;
    }
}
