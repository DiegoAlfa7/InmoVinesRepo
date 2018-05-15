package com.diegoa.inmovinesrest.repositories;

import com.diegoa.inmovinesrest.entities.Comunidades;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadesRepository extends PagingAndSortingRepository<Comunidades,Long> {}
