package com.diegoa.inmovinesrest.repositories.localizacion;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComunidadesRepository extends PagingAndSortingRepository<Comunidades,Long> {}
