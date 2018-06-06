package com.diegoa.inmovinesrest.services.clientes.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
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

    @Autowired
    AgentesRepository agentesRepository;

    @Autowired
    InmueblesRepository inmueblesRepository;

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
    public Clientes create(Clientes clientes, long idAgente, long idAgenteEntrada, long idInteres) {

        if (clientes != null) {

            Agentes agentes = agentesRepository.findById(idAgente).get();
            Agentes agenteEntrada = agentesRepository.findById(idAgenteEntrada).get();
            Inmuebles inmuebleInteres = inmueblesRepository.findById(idInteres).get();

            Clientes clientesCreado = new Clientes();

            clientesCreado.copyParameters(clientes);
            clientesCreado.setAgente(agentes);
            clientesCreado.setAgenteEntrada(agenteEntrada);
            clientesCreado.setInmuebleInteres(inmuebleInteres);

            return this.clientesRepository.save(clientesCreado);


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
    public boolean delete(long id) {

        Optional<Clientes> optionalClientes = this.clientesRepository.findById(id);

        if (optionalClientes.isPresent()) {

            Clientes clientes = optionalClientes.get();
            this.clientesRepository.delete(clientes);


            if(this.clientesRepository.findById(id).isPresent()){

                return false;

            }else{

                return true;
            }


        } else {

            logger.error("El cliente con id " + id + " no existe en la base de datos");
            throw new RuntimeException("El cliente con id " + id + " no existe en la base de datos");
        }

    }

    @Override
    public Clientes listByEmailAndPass(String email, String pass) {

        List<Clientes> clientes = (List<Clientes>) this.clientesRepository.findAll();

        for (Clientes c : clientes) {

            if (c.getDatosPersonales().getMail().equals(email) && c.getAccountHash().equals(pass)) {

                return c;
            }

        }

        throw new RuntimeException("No se encuentra ningun cliente con email: " + email + " y con password: " + pass);


    }

    @Override
    public List<Inmuebles> listInmuebleByCliente(long id) {

        Optional<Clientes> cliente = this.clientesRepository.findById(id);

        if (cliente.isPresent()) {

            Clientes cliente1 = cliente.get();
            return cliente1.getInmueblesList();
        }

        throw new RuntimeException("No se encuentra ningun cliente con id: " + id);
    }


}
