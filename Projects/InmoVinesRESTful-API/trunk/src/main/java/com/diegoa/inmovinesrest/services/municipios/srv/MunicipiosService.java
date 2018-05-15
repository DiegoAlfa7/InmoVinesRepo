package com.diegoa.inmovinesrest.services.municipios.srv;

import com.diegoa.inmovinesrest.entities.Municipios;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MunicipiosService extends InmoVinesService {

    Page<Municipios> listAllByPage(Pageable pageable);

    Municipios findOneById(long ID);

    List<Municipios> listAll();


}