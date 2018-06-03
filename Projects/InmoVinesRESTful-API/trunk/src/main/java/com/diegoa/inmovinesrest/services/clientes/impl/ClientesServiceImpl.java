package com.diegoa.inmovinesrest.services.clientes.impl;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesReposiroty;
import com.diegoa.inmovinesrest.services.clientes.srv.ClientesUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ClientesServiceImpl implements ClientesUserService {

    @Autowired
    ClientesReposiroty clientesReposiroty;


    Logger logger = Logger.getLogger(ClientesServiceImpl.class);


    @Override
    public Page<Clientes> listAllByPage(Pageable pageable) {
        return clientesReposiroty.findAll(pageable);
    }

    @Override
    public Clientes findOneById(long ID) {


        Optional<Clientes> optCliente = clientesReposiroty.findById(ID);

        return optCliente.isPresent() ? optCliente.get() : null;
    }

    @Override
    public List<Clientes> listAll() {

        return (List<Clientes>) clientesReposiroty.findAll();
    }

    @Override
    public void create(Clientes clientes) {

        if (clientes != null) {

            this.clientesReposiroty.save(clientes);
        } else {

            logger.error("El cliente a crear viene vacio por parametros");
            throw new RuntimeException("El cliente a crear viene vacio por parametros");
        }


    }

    @Override
    public void update(Clientes clientes) {

        Optional<Clientes> optionalClientes = this.clientesReposiroty.findById(clientes.getId());

        if (optionalClientes.isPresent()) {

            Clientes clientesPersistent = optionalClientes.get();

            clientesPersistent.copyParameters(clientes);

            this.clientesReposiroty.save(clientesPersistent);
        } else {

            logger.error("El cliente con id " + clientes.getId() + " no existe en la base de datos");
            throw new RuntimeException("El cliente con id " + clientes.getId() + " no existe en la base de datos");
        }


    }

    @Override
    public void delete(long id) {

        Optional<Clientes> optionalClientes = this.clientesReposiroty.findById(id);

        if (optionalClientes.isPresent()) {

            Clientes clientes = optionalClientes.get();
            this.clientesReposiroty.delete(clientes);


        } else {

            logger.error("El cliente con id " + id + " no existe en la base de datos");
            throw new RuntimeException("El cliente con id " + id + " no existe en la base de datos");
        }

    }


}
