package com.diegoa.inmovinesrest.services.agentes.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.services.agentes.srv.AgentesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Este Servicio se encarga de las operaciones que se realizan sobre las entidades de Inmueble y otros recursos directamente relacionados.
 * @since 0.0.1
 * @author Daniel Arroyo
 */
@Service
public class AgentesServiceImpl implements AgentesService {

    @Autowired
    AgentesRepository agentesRepository;

    Logger logger = Logger.getLogger(AgentesServiceImpl.class);


    @Override
    public Page<Agentes> listAllByPage(Pageable pageable) {
        return this.agentesRepository.findAll(pageable);
    }


    /**
     * @param ID long que representa el ID de BBDD
     * @return
     */
    @Override
    public Agentes findOneById(long ID) {

        Optional<Agentes> optionalAgentes = this.agentesRepository.findById(ID);

        if (optionalAgentes.isPresent()) return optionalAgentes.get();

        return null;

    }

    @Override
    public List<Agentes> listAll() {
        return (List<Agentes>) this.agentesRepository.findAll();
    }

    /**
     * @param agentes la instancia de Agentes que debe ser guardada en el entorno de persistencia
     */
    public Agentes create(Agentes agentes) {

        if (agentes != null) {

            return this.agentesRepository.save(agentes);
        } else {

            logger.error("El agente a crear viene vacio por parametros");
            throw new RuntimeException("El agente a crear viene vacio por parametros");
        }


    }


    /**
     * Este método se encarga de acutalizar los valores de un agente mediante un objeto agentes que se introduce por los parámetros
     * @param agentes entidad de tipo Agentes que representa un nuevo estado (valores cambiados) de una entidad existente en la BBDD
     * @throws RuntimeException Si no existe un Agente en la BBDD con el mismo ID que el agente pasado por parámetros
     * @return Agentes el nuevo estado de la entidad en la base de datos
     */
    @Override
    public Agentes update(Agentes agentes) throws RuntimeException{

        Optional<Agentes> optionalAgentes = agentesRepository.findById(agentes.getId());

        if (optionalAgentes.isPresent()) {

            Agentes agentesPersistent = optionalAgentes.get();
            agentesPersistent.copyParameters(agentes);

            return this.agentesRepository.save(agentesPersistent);

        } else {

            logger.error("El agente con id " + agentes.getId() + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + agentes.getId() + " no existe en la base de datos");
        }

    }

    /**
     * Este método se encarga de realizar la acción de borrado sobre una entidad Agentes basándose en un ID que utiliza como parámetro de búsqueda
     * y además comprueba que la operación delete() se produjo con éxito, devolviendo true o false en consecuencia.
     * @return true si se efectuó correctamente el borrado, false en caso contrario
     * @param ID el id de una entidad de tipo Agentes
     */
    @Override
    public boolean delete(long ID) {

        Optional<Agentes> agenteOptional = this.agentesRepository.findById(ID);

        if (agenteOptional.isPresent()) {

            Agentes agente = agenteOptional.get();
            this.agentesRepository.delete(agente);
            //Nos aseguramos de que efectivamente se borró la entidad
            Optional i = this.agentesRepository.findById(ID);

            if (i.isPresent()){
                //No se ha borrado correctamente
                return false;

            }else{
                //El borrado transcurrió correctamente
                return true;
            }
        } else {

            logger.error("El agente con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + ID + " no existe en la base de datos");
        }


    }

    /**
     * Este método se encarga de devolver la lista de Clientes asociados a un agente de la base de datos, mediante su ID
     * @param ID el ID del agente de la base de datos del que se quiere recuperar la lista de Clientes
     * @throws RuntimeException si no existe un agente con el ID de los parámetros en la BBDD
     * @return List<Clientes> con los clientes asociados al agente, en formato JSON
     */
    @Override
    public List<Clientes> getClientesAsignadosById(long ID) throws RuntimeException{

        Optional<Agentes> agenteOptional = this.agentesRepository.findById(ID);

        if (agenteOptional.isPresent()) {

           return agenteOptional.get().getClientesAsignados();

        } else {

            logger.error("El agente con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El agente con id " + ID + " no existe en la base de datos");
        }

    }

    /**
     * Ejecuta una búsqueda parametrizada sobre la base de datos, para devolver una entidad
     * de tipo Agentes.
     * @param pass
     * @param email
     * @return
     */
    @Override
    public Agentes findAgenteByLogin(String pass, String email) {

        List<Agentes> agentesList = (List<Agentes>) this.agentesRepository.findAll();

        for (Agentes a : agentesList){


            if(a.getMail().equals(email) && a.getPassword().equals(pass)){
                //Match
                return a;

            }


        }

        throw new RuntimeException("No se han encontrado coincidencias para los resultados");


    }
}
