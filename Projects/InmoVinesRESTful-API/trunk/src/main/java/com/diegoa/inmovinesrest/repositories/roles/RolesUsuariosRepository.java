package com.diegoa.inmovinesrest.repositories.roles;

import com.diegoa.inmovinesrest.entities.roles.RolesUsuarios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesUsuariosRepository extends PagingAndSortingRepository<RolesUsuarios,Long> {


}
