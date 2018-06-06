package com.diegoa.inmovinesrest.services.tareas.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.repositories.agentes.TareasRepository;
import com.diegoa.inmovinesrest.services.tareas.srv.TareasService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    TareasRepository tareasRepository;

    @Autowired
    AgentesRepository agentesRepository;

    Logger logger = Logger.getLogger(TareasServiceImpl.class);


    @Override
    public Tareas findOneById(long id_agente) {

        Optional<Tareas> optionalTareas = this.tareasRepository.findById(id_agente);

        if (optionalTareas.isPresent()) {
            return optionalTareas.get();
        }

        return null;
    }

    @Override
    public List<Tareas> listAll() {
        return (List<Tareas>) this.tareasRepository.findAll();
    }

    @Override
    public Page<Tareas> listAllByPage(Pageable pageable) {
        return this.tareasRepository.findAll(pageable);
    }

    @Override
    public Tareas create(Tareas tareas, long idAgente) {

        if (tareas != null) {
            Agentes agente = this.agentesRepository.findById(idAgente).get();
            Tareas tareasCreated = new Tareas();
            tareasCreated.copyParameters(tareas);
            tareasCreated.setAgente(agente);
            return this.tareasRepository.save(tareasCreated);

        } else {

            logger.error("La tarea a crear viene vacia por parametros");
            throw new RuntimeException("La tarea a crear viene vacia por parametros");
        }

    }

    @Override
    public Tareas update(Tareas tareas) {

        Optional<Tareas> optionalTareas = tareasRepository.findById(tareas.getId());

        if (optionalTareas.isPresent()) {

            Tareas tareasPersistant = optionalTareas.get();

            tareasPersistant.copyParameters(tareas);

            return this.tareasRepository.save(tareasPersistant);

        } else {

            logger.error("La tarea con id " + tareas.getId() + " no existe en la base de datos");
            throw new RuntimeException("La tarea con id " + tareas.getId() + " no existe en la base de datos");

        }

    }

    @Override
    public boolean delete(long id) {

        Optional<Tareas> optionalTareas = tareasRepository.findById(id);

        if (optionalTareas.isPresent()) {

            Tareas tareas = optionalTareas.get();

            this.tareasRepository.delete(tareas);

            if(this.tareasRepository.findById(id).isPresent()){

                return false;
            }else{

                return true;
            }

        } else {

            logger.error("La tarea con id " + id + " no existe en la base de datos");
            throw new RuntimeException("La tarea con id " + id + " no existe en la base de datos");
        }

    }

    @Override
    public List<Tareas> listByIdAgente(long id) {
        Optional<Agentes> agentesOptional = this.agentesRepository.findById(id);
        if(agentesOptional.isPresent()){

            return agentesOptional.get().getTareasList();


        }else{

            throw new RuntimeException("El Agente con id: "+id+" no devolvió resultados");
        }

    }
}
