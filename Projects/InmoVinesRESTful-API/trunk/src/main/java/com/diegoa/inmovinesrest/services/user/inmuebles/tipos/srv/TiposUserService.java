package com.diegoa.inmovinesrest.services.user.inmuebles.tipos.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.Tipos;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TiposUserService extends InmoVinesService {

    Page<Tipos> listAllByPage(Pageable pageable);

    Tipos findOneById(long ID);

    List<Tipos> listAll();


}
