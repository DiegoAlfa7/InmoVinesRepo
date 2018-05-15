package com.diegoa.inmovinesrest.repositories;

import com.diegoa.inmovinesrest.entities.Municipios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipiosRepository extends PagingAndSortingRepository<Municipios,Long> {}
