package com.diegoa.inmovinesrest.repositories;

import com.diegoa.inmovinesrest.entities.Provincias;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciasRepository extends PagingAndSortingRepository<Provincias,Long> {}
