package com.diegoa.inmovinesrest.repositories;

import com.diegoa.inmovinesrest.entities.Zonas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonasRepository extends PagingAndSortingRepository<Zonas,Long> {}
