package com.diegoa.inmovinesrest.repositories.inmuebles;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmueblesRepository extends PagingAndSortingRepository<Inmuebles,Long> {


    Page<Inmuebles> findAll(Pageable pageable);


}
