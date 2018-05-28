package com.diegoa.inmovinesrest.services.user.agentes.tareas.srv;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TareasUserService extends InmoVinesService {

    Page<Tareas> listAllByPage(Pageable pageable);

    Tareas findOneById(long ID);

    List<Tareas> listAll();


}
