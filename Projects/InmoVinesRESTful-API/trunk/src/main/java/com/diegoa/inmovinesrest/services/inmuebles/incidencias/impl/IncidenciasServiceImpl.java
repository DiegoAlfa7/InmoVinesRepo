package com.diegoa.inmovinesrest.services.inmuebles.incidencias.impl;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.incidencias.Incidencias;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.IncidenciasRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import com.diegoa.inmovinesrest.services.inmuebles.incidencias.srv.IncidenciasService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciasServiceImpl implements IncidenciasService {

    @Autowired
    IncidenciasRepository incidenciasRepository;

    @Autowired
    InmueblesRepository inmueblesRepository;

    @Autowired
    ClientesRepository clientesRepository;

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
    public boolean delete(long id) throws RuntimeException {

        Optional<Incidencias> optionalIncidencias = incidenciasRepository.findById(id);

        if (optionalIncidencias.isPresent()) {

            Incidencias incidencias = optionalIncidencias.get();

            this.incidenciasRepository.delete(incidencias);

            Optional incidenciaBorrada = this.incidenciasRepository.findById(id);

            if (incidenciaBorrada.isPresent()) {

                return false;

            } else {

                return true;
            }

        } else {

            logger.error("La incidencia con id " + id + " no existe en la base de datos");
            throw new RuntimeException("La incidencia con id " + id + " no existe en la base de datos");

        }

    }



    @Override
    public List<Incidencias> listAllByInmuebleID(long ID) {


        Optional<Inmuebles> inmuebleOptional =  this.inmueblesRepository.findById(ID);

        if (inmuebleOptional.isPresent()) {

            Inmuebles inmueble = inmuebleOptional.get();
            List<Incidencias> incidenciasList = inmueble.getIncidenciasList();
            return incidenciasList;

        } else {

            logger.error("El agente con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + ID + " no existe en la base de datos");
        }


    }

    @Override
    public List<Incidencias> listAllByClienteID(long ID) {


        Optional<Clientes> clientesOptional =  this.clientesRepository.findById(ID);

        if (clientesOptional.isPresent()) {

            Clientes clientes = clientesOptional.get();
            List<Inmuebles> inmueblesOfCliente = clientes.getInmueblesList();
            //Lista de incidencias de los inmuebles
            List<Incidencias> incidenciasOfCliente = new ArrayList<Incidencias>();
            for(Inmuebles i : inmueblesOfCliente ){

                incidenciasOfCliente.addAll(i.getIncidenciasList());


            }

            return incidenciasOfCliente;

        } else {

            logger.error("El agente con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + ID + " no existe en la base de datos");
        }


    }
}
