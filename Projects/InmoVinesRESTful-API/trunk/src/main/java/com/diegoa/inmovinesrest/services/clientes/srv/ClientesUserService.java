package com.diegoa.inmovinesrest.services.clientes.srv;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.services.InmoVinesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientesUserService extends InmoVinesService {

    Page<Clientes> listAllByPage(Pageable pageable);

    Clientes findOneById(long ID);

    List<Clientes> listAll();


}
