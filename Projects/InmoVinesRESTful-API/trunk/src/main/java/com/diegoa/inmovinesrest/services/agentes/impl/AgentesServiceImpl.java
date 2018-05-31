package com.diegoa.inmovinesrest.services.agentes.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.services.agentes.srv.AgentesService;
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


    @Override
    public Page<Agentes> listAllByPage(Pageable pageable) {
        return this.agentesRepository.findAll(pageable);
    }



    /**
     *
     * @param ID long que representa el ID de BBDD
     * @return
     */
    @Override
    public Agentes findOneById(long ID) {

        Optional<Agentes> optionalAgentes = this.agentesRepository.findById(ID);

        if(optionalAgentes.isPresent()) return optionalAgentes.get();

        return null;

    }

    @Override
    public List<Agentes> listAll() {
        return (List<Agentes>) this.agentesRepository.findAll();
    }

    /**
     *
     * @param i la instancia de Agentes que debe ser guardada en el entorno de persistencia
     *
     */
    public void create(Agentes i){

        this.agentesRepository.save(i);


    }

    /**
     *
     * @param i entidad de tipo Agentes que representa un nuevo estado (valores cambiados) de una entidad existente en el
     *          contexto de persistencia
     * @return
     */
    public void update(Agentes i){

        this.agentesRepository.save(i);

    }

    /**
     *
     * @param ID el id de una entidad de tipo Agentes
     */
    public void deleteById(long ID){

        Optional<Agentes> agenteOptional = this.agentesRepository.findById(ID);

        if(agenteOptional.isPresent()){

            Agentes agente = agenteOptional.get();
            this.agentesRepository.delete(agente);



        }



    }

    /**
     *
     * @param ID entidad de tipo Agentes que debe existir en la BBDD / contexto de persistencia
     */
    public void delete(long ID){

        Optional<Agentes> agenteOptional = this.agentesRepository.findById(ID);

        if(agenteOptional.isPresent()){

            Agentes agente = agenteOptional.get();
            this.agentesRepository.delete(agente);



        }



    }

}
