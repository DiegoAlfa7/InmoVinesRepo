package com.diegoa.inmovinesrest.services.user.agentes.srv;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AgentesUserService extends InmoVinesService {

    Page<Agentes> listAllByPage(Pageable pageable);

    Agentes findOneById(long ID);

    List<Agentes> listAll();


}
