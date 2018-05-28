package com.diegoa.inmovinesrest.services.localizacion.comunidades.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ComunidadesService extends InmoVinesService {

    Page<Comunidades> listAllByPage(Pageable pageable);

    Comunidades findOneById(long ID);

    List<Comunidades> listAll();


}
