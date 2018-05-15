package com.diegoa.inmovinesrest.repositories.inmuebles;

import com.diegoa.inmovinesrest.entities.inmuebles.Gestiones;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionesRepository extends PagingAndSortingRepository<Gestiones,Long> {}
