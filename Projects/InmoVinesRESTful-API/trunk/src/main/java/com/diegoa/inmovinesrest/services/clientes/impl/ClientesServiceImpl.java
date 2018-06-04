package com.diegoa.inmovinesrest.services.clientes.impl;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesRepository;
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
    ClientesRepository clientesRepository;


    Logger logger = Logger.getLogger(ClientesServiceImpl.class);


    @Override
    public Page<Clientes> listAllByPage(Pageable pageable) {
        return clientesRepository.findAll(pageable);
    }

    @Override
    public Clientes findOneById(long ID) {


        Optional<Clientes> optCliente = clientesRepository.findById(ID);

        return optCliente.isPresent() ? optCliente.get() : null;
    }

    @Override
    public List<Clientes> listAll() {

        return (List<Clientes>) clientesRepository.findAll();
    }

    @Override
    public Clientes create(Clientes clientes) {

        if (clientes != null) {

           return this.clientesRepository.save(clientes);
        } else {

            logger.error("El cliente a crear viene vacio por parametros");
            throw new RuntimeException("El cliente a crear viene vacio por parametros");
        }


    }

    @Override
    public Clientes update(Clientes clientes) {

        Optional<Clientes> optionalClientes = this.clientesRepository.findById(clientes.getId());

        if (optionalClientes.isPresent()) {

            Clientes clientesPersistent = optionalClientes.get();

            clientesPersistent.copyParameters(clientes);

            return this.clientesRepository.save(clientesPersistent);
        } else {

            logger.error("El cliente con id " + clientes.getId() + " no existe en la base de datos");
            throw new RuntimeException("El cliente con id " + clientes.getId() + " no existe en la base de datos");
        }


    }

    @Override
    public void delete(long id) {

        Optional<Clientes> optionalClientes = this.clientesRepository.findById(id);

        if (optionalClientes.isPresent()) {

            Clientes clientes = optionalClientes.get();
            this.clientesRepository.delete(clientes);


        } else {

            logger.error("El cliente con id " + id + " no existe en la base de datos");
            throw new RuntimeException("El cliente con id " + id + " no existe en la base de datos");
        }

    }


}
