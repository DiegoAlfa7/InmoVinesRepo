package com.diegoa.inmovinesrest.repositories.inmuebles;

import com.diegoa.inmovinesrest.entities.inmuebles.Tipos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposRepository extends PagingAndSortingRepository<Tipos,Long> {}
