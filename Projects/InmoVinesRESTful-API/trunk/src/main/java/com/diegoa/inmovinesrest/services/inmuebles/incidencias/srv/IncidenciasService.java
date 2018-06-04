package com.diegoa.inmovinesrest.services.inmuebles.incidencias.srv;

import com.diegoa.inmovinesrest.entities.inmuebles.incidencias.Incidencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IncidenciasService {

    Page<Incidencias> listAllByPage(Pageable pageable);

    Incidencias findOneById(long ID);

    List<Incidencias> listAll();

    public Incidencias create(Incidencias incidencias);

    public Incidencias update(Incidencias incidencias);

    public boolean delete(long id);
}
