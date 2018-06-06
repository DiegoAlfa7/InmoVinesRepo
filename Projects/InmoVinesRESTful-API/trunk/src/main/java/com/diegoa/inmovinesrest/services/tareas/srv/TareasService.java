package com.diegoa.inmovinesrest.services.tareas.srv;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TareasService {

    Tareas findOneById(long id);

    List<Tareas> listAll();

    Page<Tareas> listAllByPage(Pageable pageable);



    Tareas create(Tareas tareas, long idAgente);

    public Tareas update(Tareas tareas);

    public boolean delete(long id);


    List<Tareas> listByIdAgente(long id);
}
