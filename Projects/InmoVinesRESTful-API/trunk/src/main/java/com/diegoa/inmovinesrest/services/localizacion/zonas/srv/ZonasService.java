package com.diegoa.inmovinesrest.services.localizacion.zonas.srv;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ZonasService {

    Page<Zonas> listAllByPage(Pageable pageable);

    Zonas findOneById(long ID);

    List<Zonas> listAll();

    public void create(Zonas zonas);

    public void update(Zonas zonas);

    public void delete(long id);


}
