package com.diegoa.inmovinesrest.services.inmuebles.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InmueblesService {

    Page<Inmuebles> listAllByPage(Pageable pageable);

    Inmuebles findOneById(long ID);

    List<Inmuebles> listAll();

    public void create(Inmuebles inmuebles);

    public void update(Inmuebles i);

    public void delete(long id);
}
