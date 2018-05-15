package com.diegoa.inmovinesrest.repositories.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareasRepository extends PagingAndSortingRepository<Tareas,Long> {}
