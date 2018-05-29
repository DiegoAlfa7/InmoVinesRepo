package com.diegoa.inmovinesrest.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InmoVinesService {

    <T> Page<T> listAllByPage(Pageable pageable);

    <T> T findOneById(long ID);

    <T> List<T> listAll();
}
