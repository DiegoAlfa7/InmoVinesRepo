package com.diegoa.inmovinesrest.services.agentes.cargos.srv;

import com.diegoa.inmovinesrest.entities.agentes.Cargos;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CargosService extends InmoVinesService {

    Page<Cargos> listAllByPage(Pageable pageable);

    Cargos findOneById(long ID);

    List<Cargos> listAll();


}
