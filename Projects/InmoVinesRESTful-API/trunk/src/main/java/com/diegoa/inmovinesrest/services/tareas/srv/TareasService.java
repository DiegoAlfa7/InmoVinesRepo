package com.diegoa.inmovinesrest.services.tareas.srv;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TareasService extends InmoVinesService {

    Tareas findOneById(long id);

    List<Tareas> listAll();

    Page<Tareas> listAllByPage (Pageable pageable);


}
