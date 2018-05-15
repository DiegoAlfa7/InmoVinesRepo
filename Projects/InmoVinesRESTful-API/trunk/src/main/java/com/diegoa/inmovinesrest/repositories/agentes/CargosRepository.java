package com.diegoa.inmovinesrest.repositories.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Cargos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargosRepository extends PagingAndSortingRepository<Cargos,Long> {}
