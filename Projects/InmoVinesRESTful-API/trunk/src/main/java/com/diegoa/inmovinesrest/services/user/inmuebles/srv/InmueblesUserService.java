package com.diegoa.inmovinesrest.services.user.inmuebles.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface InmueblesUserService extends InmoVinesService {

    Page<Inmuebles> listAllByPage(Pageable pageable);

    Inmuebles findOneById(long ID);

    List<Inmuebles> listAll();


}