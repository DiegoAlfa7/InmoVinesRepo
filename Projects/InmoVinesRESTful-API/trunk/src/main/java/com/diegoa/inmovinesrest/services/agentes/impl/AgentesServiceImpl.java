package com.diegoa.inmovinesrest.services.agentes.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.services.agentes.srv.AgentesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentesServiceImpl implements AgentesService {

    @Autowired
    AgentesRepository agentesRepository;

    Logger logger = Logger.getLogger(AgentesServiceImpl.class);


    @Override
    public Page<Agentes> listAllByPage(Pageable pageable) {
        return this.agentesRepository.findAll(pageable);
    }


    /**
     * @param ID long que representa el ID de BBDD
     * @return
     */
    @Override
    public Agentes findOneById(long ID) {

        Optional<Agentes> optionalAgentes = this.agentesRepository.findById(ID);

        if (optionalAgentes.isPresent()) return optionalAgentes.get();

        return null;

    }

    @Override
    public List<Agentes> listAll() {
        return (List<Agentes>) this.agentesRepository.findAll();
    }

    /**
     * @param agentes la instancia de Agentes que debe ser guardada en el entorno de persistencia
     */
    public Agentes create(Agentes agentes) {

        if (agentes != null) {

            return this.agentesRepository.save(agentes);
        } else {

            logger.error("El agente a crear viene vacio por parametros");
            throw new RuntimeException("El agente a crear viene vacio por parametros");
        }


    }


    /**
     * @param agentes entidad de tipo Agentes que representa un nuevo estado (valores cambiados) de una entidad existente en el
     *                contexto de persistencia
     * @return
     */
    @Override
    public Agentes update(Agentes agentes) {

        Optional<Agentes> optionalAgentes = agentesRepository.findById(agentes.getId());

        if (optionalAgentes.isPresent()) {

            Agentes agentesPersistent = optionalAgentes.get();
            agentesPersistent.copyParameters(agentes);

            return this.agentesRepository.save(agentesPersistent);

        } else {

            logger.error("El agente con id " + agentes.getId() + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + agentes.getId() + " no existe en la base de datos");
        }

    }

    /**
     * @param ID el id de una entidad de tipo Agentes
     */
    @Override
    public boolean delete(long ID) {

        Optional<Agentes> agenteOptional = this.agentesRepository.findById(ID);

        if (agenteOptional.isPresent()) {

            Agentes agente = agenteOptional.get();
            this.agentesRepository.delete(agente);
            //Nos aseguramos de que efectivamente se borró la entidad
            Optional i = this.agentesRepository.findById(ID);

            if (i.isPresent()){
                //No se ha borrado correctamente
                return false;

            }else{
                //El borrado transcurrió correctamente
                return true;
            }
        } else {

            logger.error("El agente con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + ID + " no existe en la base de datos");
        }


    }


}
