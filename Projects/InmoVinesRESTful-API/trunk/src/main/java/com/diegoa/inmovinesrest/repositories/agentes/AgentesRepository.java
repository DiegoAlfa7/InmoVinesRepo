package com.diegoa.inmovinesrest.repositories.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentesRepository extends PagingAndSortingRepository<Agentes,Long> {




}
