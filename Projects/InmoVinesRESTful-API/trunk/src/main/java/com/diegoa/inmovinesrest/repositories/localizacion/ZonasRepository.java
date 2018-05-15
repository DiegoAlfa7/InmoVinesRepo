package com.diegoa.inmovinesrest.repositories.localizacion;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonasRepository extends PagingAndSortingRepository<Zonas,Long> {}
