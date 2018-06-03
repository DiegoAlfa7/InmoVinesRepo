package com.diegoa.inmovinesrest.services.tareas.impl;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
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
    public void create(Tareas tareas) {

        if (tareas != null) {

            this.tareasRepository.save(tareas);

        } else {

            logger.error("La tarea a crear viene vacia por parametros");
            throw new RuntimeException("La tarea a crear viene vacia por parametros");
        }

    }

    @Override
    public void update(Tareas tareas) {

        Optional<Tareas> optionalTareas = tareasRepository.findById(tareas.getId());

        if (optionalTareas.isPresent()) {

            Tareas tareasPersistant = optionalTareas.get();

            tareasPersistant.copyParameters(tareas);

            this.tareasRepository.save(tareasPersistant);

        } else {

            logger.error("La tarea con id " + tareas.getId() + " no existe en la base de datos");
            throw new RuntimeException("La tarea con id " + tareas.getId() + " no existe en la base de datos");

        }

    }

    @Override
    public void delete(long id) {

        Optional<Tareas> optionalTareas = tareasRepository.findById(id);

        if (optionalTareas.isPresent()) {

            Tareas tareas = optionalTareas.get();

            this.tareasRepository.delete(tareas);

        } else {

            logger.error("La tarea con id " + id + " no existe en la base de datos");
            throw new RuntimeException("La tarea con id " + id + " no existe en la base de datos");
        }

    }
}
