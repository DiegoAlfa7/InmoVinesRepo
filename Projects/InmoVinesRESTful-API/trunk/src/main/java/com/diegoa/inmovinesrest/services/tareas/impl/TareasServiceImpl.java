package com.diegoa.inmovinesrest.services.tareas.impl;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import com.diegoa.inmovinesrest.repositories.agentes.TareasRepository;
import com.diegoa.inmovinesrest.services.tareas.srv.TareasService;
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
}
