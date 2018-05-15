package com.diegoa.inmovinesrest.services.provincias.srv;

import com.diegoa.inmovinesrest.entities.Provincias;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProvinciasService extends InmoVinesService {

    Page<Provincias> listAllByPage(Pageable pageable);

    Provincias findOneById(long ID);

    List<Provincias> listAll();


}
