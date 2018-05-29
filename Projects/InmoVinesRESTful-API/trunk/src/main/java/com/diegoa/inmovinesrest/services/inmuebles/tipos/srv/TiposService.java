package com.diegoa.inmovinesrest.services.inmuebles.tipos.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.Tipos;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TiposService extends InmoVinesService {

    Page<Tipos> listAllByPage(Pageable pageable);

    Tipos findOneById(long ID);

    List<Tipos> listAll();


}
