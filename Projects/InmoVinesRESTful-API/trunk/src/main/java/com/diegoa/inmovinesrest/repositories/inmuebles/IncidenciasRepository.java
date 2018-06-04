package com.diegoa.inmovinesrest.repositories.inmuebles;

import com.diegoa.inmovinesrest.entities.inmuebles.incidencias.Incidencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IncidenciasRepository extends PagingAndSortingRepository<Incidencias, Long> {

    Page<Incidencias> findAll(Pageable pageable);
}
