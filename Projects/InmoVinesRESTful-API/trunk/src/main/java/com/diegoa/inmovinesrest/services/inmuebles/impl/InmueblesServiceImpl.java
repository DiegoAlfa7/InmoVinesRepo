package com.diegoa.inmovinesrest.services.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesReposiroty;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.services.inmuebles.srv.InmueblesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @
 */
@Service
public class InmueblesServiceImpl implements InmueblesService {

    @Autowired
    ClientesReposiroty clientesReposiroty;

    @Autowired
    InmueblesRepository inmueblesRepository;
    Logger logger = Logger.getLogger(InmueblesServiceImpl.class);


    @Override
    public Page<Inmuebles> listAllByPage(Pageable pageable) {

        return this.inmueblesRepository.findAll(pageable);

    }

    @Override
    public Inmuebles findOneById(long ID) throws RuntimeException {

        Optional<Inmuebles> mayb_inmueble = this.inmueblesRepository.findById(ID);

        if (mayb_inmueble.isPresent()) {

            return mayb_inmueble.get();

        } else {

            throw new RuntimeException("No se encontró ninguna instancia de tipo Inmuebles con el id: " + ID);

        }

    }

    @Override
    public List<Inmuebles> listAll() {
        return (List<Inmuebles>) inmueblesRepository.findAll();
    }

    /**
     * @param i la instancia de Inmuebles que debe ser guardada en el entorno de persistencia
     */
    @Override
    public Inmuebles create(Inmuebles i) throws RuntimeException {

        if (i != null) {

            return this.inmueblesRepository.save(i);

        } else {

            logger.error("El inmueble a crear viene vacio por parametros");
            throw new RuntimeException("El inmueble a crear viene vacio por parametros");
        }


    }

    /**
     * @param i entidad de tipo Inmuebles que representa un nuevo estado (valores cambiados) de una entidad existente en el
     *          contexto de persistencia
     * @return
     */
    @Override
    public Inmuebles update(Inmuebles i) throws RuntimeException {

        Optional<Inmuebles> optionalInmuebles = inmueblesRepository.findById(i.getId());

        if (optionalInmuebles.isPresent()) {

            Inmuebles inmueblesPersistent = optionalInmuebles.get();

            inmueblesPersistent.copyParameters(i);

            return this.inmueblesRepository.save(inmueblesPersistent);
        } else {

            logger.error("El inmueble con id " + i.getId() + " no existe en la base de datos");
            throw new RuntimeException("El inmueble con id " + i.getId() + " no existe en la base de datos");


        }

    }

    /**
     * @param ID el id de una entidad de tipo Inmuebles
     */
    @Override
    public boolean delete(long ID) throws RuntimeException {

        Optional<Inmuebles> inmuebleOptional = this.inmueblesRepository.findById(ID);

        if (inmuebleOptional.isPresent()) {

            Inmuebles inmueble = inmuebleOptional.get();
            this.inmueblesRepository.delete(inmueble);
            //Nos aseguramos de que efectivamente se borró la entidad
            Optional i = this.inmueblesRepository.findById(ID);

            if (i.isPresent()) {
                //No se ha borrado correctamente
                return false;

            } else {
                //El borrado transcurrió correctamente
                return true;
            }


        } else {

            logger.error("El inmueble con id " + ID + " no existe en la base de datos");
            throw new RuntimeException("El inmueble con id " + ID + " no existe en la base de datos");
        }


    }

    /**
     * This method searches for an instance of Inmuebles by the ID given and returns its property 'clientePropietario'
     *
     * @param ID the long value for the indexed search in Inmuebles
     * @return Clientes
     * @throws RuntimeException si no se encuentra un inmueble por el id dado o si el inmueble no tiene un cliente propietario asociado
     */
    @Override
    public Clientes getClientePropietarioByID(long ID) throws RuntimeException {

        Optional<Inmuebles> inmueblesOptional = this.inmueblesRepository.findById(ID);


        if (inmueblesOptional.isPresent()) {

            Inmuebles inmueblePersistente = inmueblesOptional.get();

            if (inmueblePersistente.getClientePropietario() != null) {

                return inmueblePersistente.getClientePropietario();

            } else {
                //No existe propietario para el inmueble que se encontró
                throw new RuntimeException("No existe propietario para el inmueble que se encontró: REF." + inmueblePersistente.getReferencia());

            }

        } else {

            throw new RuntimeException("No existe un inmueble en la BBDD con el ID: " + ID);

        }


    }

    /**
     * Este método busca una instancia de Inmuebles con el ID que se le pase por parámetros, y después realiza con este un filtrado sobre la tabla de Clientes
     * mediante la propiedad "INMUEBLE DE INTERÉS"
     *
     * @param ID the long value for the indexed search in Inmuebles
     * @return Clientes
     * @throws RuntimeException si no se encuentra un inmueble por el id dado o si el inmueble no tiene un cliente propietario asociado
     */
    @Override
    public List<Clientes> getInteresadosByID(long ID) {

        Optional<Inmuebles> inmueblesOptional = this.inmueblesRepository.findById(ID);

        if (inmueblesOptional.isPresent()) {

            List<Clientes> allClientes = (List<Clientes>) this.clientesReposiroty.findAll();
            List<Clientes> clientesFiltered = new ArrayList<Clientes>();

            for (Clientes c : allClientes) {

                if (c.getInmuebleInteres().equals(inmueblesOptional.get())) {

                    clientesFiltered.add(c);

                }

            }
            return clientesFiltered;


        } else {

            throw new RuntimeException("No existe un inmueble en la BBDD con el ID: " + ID);

        }
    }


}