package com.diegoa.inmovinesrest.services.user.localizacion.zonas.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ZonasService extends InmoVinesService {

    Page<Zonas> listAllByPage(Pageable pageable);

    Zonas findOneById(long ID);

    List<Zonas> listAll();


}
