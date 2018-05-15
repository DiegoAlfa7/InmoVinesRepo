package com.diegoa.inmovinesrest.repositories.localizacion;

import com.diegoa.inmovinesrest.entities.localizacion.Provincias;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciasRepository extends PagingAndSortingRepository<Provincias,Long> {}
