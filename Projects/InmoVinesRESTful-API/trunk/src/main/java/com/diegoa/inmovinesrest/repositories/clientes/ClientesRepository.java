package com.diegoa.inmovinesrest.repositories.clientes;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientesRepository extends PagingAndSortingRepository<Clientes,Long> {
}
