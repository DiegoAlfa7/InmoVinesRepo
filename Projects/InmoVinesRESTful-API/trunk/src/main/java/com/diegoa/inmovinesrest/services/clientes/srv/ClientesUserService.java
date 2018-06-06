package com.diegoa.inmovinesrest.services.clientes.srv;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientesUserService {

    Page<Clientes> listAllByPage(Pageable pageable);

    Clientes findOneById(long ID);

    List<Clientes> listAll();

    public Clientes create(Clientes clientes, long idAgente, long idAgenteEntrada, long idInteres);

    public Clientes update(Clientes clientes);

    public boolean delete(long id);

    Clientes listByEmailAndPass (String email, String pass);

    List <Inmuebles> listInmuebleByCliente (long id);


}
