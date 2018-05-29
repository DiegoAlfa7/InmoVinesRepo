package com.diegoa.inmovinesrest.services.inmuebles.gestiones.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.Gestiones;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GestionesService extends InmoVinesService {

    Page<Gestiones> listAllByPage(Pageable pageable);

    Gestiones findOneById(long ID);

    List<Gestiones> listAll();


}
