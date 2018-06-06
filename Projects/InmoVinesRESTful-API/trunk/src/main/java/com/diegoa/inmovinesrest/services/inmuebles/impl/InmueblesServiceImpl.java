package com.diegoa.inmovinesrest.services.inmuebles.impl;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Gestiones;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.Tipos;
import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Provincias;
import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.misc.CONSTANTES;
import com.diegoa.inmovinesrest.repositories.agentes.AgentesRepository;
import com.diegoa.inmovinesrest.repositories.clientes.ClientesRepository;
import com.diegoa.inmovinesrest.repositories.clientes.InteresesRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.GestionesRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.InmueblesRepository;
import com.diegoa.inmovinesrest.repositories.inmuebles.TiposRepository;
import com.diegoa.inmovinesrest.repositories.localizacion.ComunidadesRepository;
import com.diegoa.inmovinesrest.repositories.localizacion.MunicipiosRepository;
import com.diegoa.inmovinesrest.repositories.localizacion.ProvinciasRepository;
import com.diegoa.inmovinesrest.repositories.localizacion.ZonasRepository;
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
 * Este Servicio se encarga de las operaciones que se realizan sobre las entidades de Inmueble y otros recursos directamente relacionados.
 *
 * @author Diego Alfaro
 * @since 0.0.1
 */
@Service
public class InmueblesServiceImpl implements InmueblesService {

    @Autowired
    InteresesRepository interesesRepository;

    @Autowired
    ClientesRepository clientesRepository;

    @Autowired
    AgentesRepository agentesRepository;

    @Autowired
    InmueblesRepository inmueblesRepository;

    @Autowired
    ComunidadesRepository comunidadesRepository;

    @Autowired
    ProvinciasRepository provinciasRepository;

    @Autowired
    MunicipiosRepository municipiosRepository;

    @Autowired
    ZonasRepository zonasRepository;

    @Autowired
    GestionesRepository gestionesRepository;

    @Autowired
    TiposRepository tiposRepository;


    Logger logger = Logger.getLogger(InmueblesServiceImpl.class);

    public void managePersistence(Inmuebles inmueblesPersistent, Inmuebles inmuebles) {






    }


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
            logger.error("No se encontró ninguna instancia de tipo Inmuebles con el id: " + ID);
            throw new RuntimeException("No se encontró ninguna instancia de tipo Inmuebles con el id: " + ID);

        }

    }

    @Override
    public List<Inmuebles> listAll() {
        return (List<Inmuebles>) inmueblesRepository.findAll();
    }

    /**
     * @param i la instancia de Inmuebles que debe ser guardada en el entorno de persistencia
     * @param idCliente
     * @param idAgente
     * @param idComunidad
     * @param idProvincia
     * @param idMunicipio
     * @param idZona
     * @param idGestion
     * @param idTipo
     */
    @Override
    public Inmuebles create(Inmuebles i, long idCliente, long idAgente, long idComunidad, long idProvincia, long idMunicipio, long idZona, long idGestion, long idTipo) throws RuntimeException {

        if (i != null) {

            //Retrieve all entities from database
            Clientes clientePropietario = clientesRepository.findById(idCliente).get();
            Agentes agente = agentesRepository.findById(idAgente).get();
            Comunidades comunidad = comunidadesRepository.findById(idComunidad).get();
            Provincias provincias = provinciasRepository.findById(idProvincia).get();
            Municipios municipio = municipiosRepository.findById(idMunicipio).get();
            Zonas zonas = zonasRepository.findById(idZona).get();
            Gestiones gestiones = gestionesRepository.findById(idGestion).get();
            Tipos tipos = tiposRepository.findById(idTipo).get();

            Inmuebles inmuebleBeingCreated = new Inmuebles();

            return inmuebleBeingCreated;






        } else {

            logger.error("El inmueble a crear viene vacio por parametros");
            throw new RuntimeException("El inmueble a crear viene vacio por parametros");
        }


    }

    /**
     * Este método se encarga de acutalizar los valores de un inmueble mediante un objeto Inmuebles que se introduce por los parámetros
     *
     * @param i entidad de tipo Inmuebles que representa un nuevo estado (valores cambiados) de una entidad existente en la BBDD
     * @return Inmuebles el nuevo estado de la entidad en la base de datos
     * @throws RuntimeException Si no existe un Inmueble en la BBDD con el mismo ID que el inmueble pasado por parámetros
     */
    @Override
    public Inmuebles update(Inmuebles i) throws RuntimeException {

        Optional<Inmuebles> optionalInmuebles = inmueblesRepository.findById(i.getId());

        if (optionalInmuebles.isPresent()) {

            Inmuebles inmueblesPersistent = optionalInmuebles.get();

            inmueblesPersistent.copyParameters(i);


            return inmueblesPersistent;
        } else {

            logger.error("El inmueble con id " + i.getId() + " no existe en la base de datos");
            throw new RuntimeException("El inmueble con id " + i.getId() + " no existe en la base de datos");


        }

    }

    /**
     * Este método se encarga de realizar la acción de borrado sobre una entidad Inmuebles basándose en un ID que utiliza como parámetro de búsqueda
     * y además comprueba que la operación delete() se produjo con éxito, devolviendo true o false en consecuencia.
     *
     * @param ID el id de una entidad de tipo Inmuebles
     * @return true si se efectuó correctamente el borrado, false en caso contrario
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
                this.logger.error("No existe propietario para el inmueble que se encontró: REF." + inmueblePersistente.getReferencia());
                throw new RuntimeException("No existe propietario para el inmueble que se encontró: REF." + inmueblePersistente.getReferencia());

            }

        } else {
            this.logger.error("No existe un inmueble en la BBDD con el ID: " + ID);
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

            List<Clientes> allClientes = (List<Clientes>) this.clientesRepository.findAll();
            List<Clientes> clientesFiltered = new ArrayList<Clientes>();

            for (Clientes i : allClientes) {

                if (i.getInmuebleInteres().equals(inmueblesOptional.get())) {

                    clientesFiltered.add(i);

                }

            }
            return clientesFiltered;


        } else {
            this.logger.error("No existe un inmueble en la BBDD con el ID: " + ID);
            throw new RuntimeException("No existe un inmueble en la BBDD con el ID: " + ID);

        }
    }

    /**
     * Uno de los métodos más potentes de este servicio. Realiza una búsqueda exhaustiva entre los inmuebles de la BBDD en función de los parametros introducidos,
     * siempre y cuando estos existan y sean válidos.
     *
     * @param tipo
     * @param pMax
     * @param pMin
     * @param hab
     * @param banos
     * @param mUtiles
     * @param comunidad
     * @return
     */
    @Override
    public List<Inmuebles> listByFilter(String tipo, String pMax, String pMin, String hab, String banos, String mUtiles, String comunidad) {

        List<Inmuebles> inmueblesList = (List<Inmuebles>) this.inmueblesRepository.findAll();


        if (tipo != null && !tipo.isEmpty()) {
            //Filter the list with tipo

            inmueblesList = this.filterByIdTipo(inmueblesList, tipo);


        }

        if (pMax != null && !pMax.isEmpty()) {
            // Filter by precio Máximo

            inmueblesList = this.filterByPMax(inmueblesList, pMax);


        }
        if (pMin != null && !pMin.isEmpty()) {
            // Filter by precio Mínimo

            inmueblesList = this.filterByPMin(inmueblesList, pMin);


        }
        if (hab != null && !hab.isEmpty()) {
            // Filter by precio Máximo

            inmueblesList = this.filterByNHab(inmueblesList, hab);


        }
        if (banos != null && !banos.isEmpty()) {
            // Filter by numero de Baños

            inmueblesList = this.filterByNBanos(inmueblesList, banos);


        }
        if (mUtiles != null && !mUtiles.isEmpty()) {
            // Filter by metros Utiles

            inmueblesList = this.filterByMUtiles(inmueblesList, mUtiles);


        }
        if (comunidad != null && !comunidad.isEmpty()) {
            // Filter by ID Comunidad

            inmueblesList = this.filterByComunidad(inmueblesList, comunidad);


        }

        return inmueblesList;


    }

    /**
     * Se buscan los Inmuebles cuyo ID de comunidad es el mismo que el que se pasa por parametros.
     *
     * @param inmueblesList
     * @param comunidad
     * @return
     */
    private List<Inmuebles> filterByComunidad(List<Inmuebles> inmueblesList, String comunidad) {
        int idComunidad;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            idComunidad = Integer.parseInt(comunidad);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'comunidad' no puede convertirse a número");

        }
        for (Inmuebles i : inmueblesList) {

            if (i.getLocalizacion().getComunidad().getId() == idComunidad) {

                filtered.add(i);

            }

        }
        return filtered;
    }

    /**
     * Se buscan los Inmuebles con un numero mayor o igual de metros cuadrados utiles que el que se pasa por parametros.
     *
     * @param inmueblesList
     * @param mUtiles
     * @return
     */
    private List<Inmuebles> filterByMUtiles(List<Inmuebles> inmueblesList, String mUtiles) {
        int metrosUtiles;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            metrosUtiles = Integer.parseInt(mUtiles);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'mUtiles' no puede convertirse a número");

        }
        for (Inmuebles i : inmueblesList) {

            if (i.getCaracteristicas().getM2Utiles() >= metrosUtiles) {

                filtered.add(i);

            }

        }
        return filtered;
    }

    /**
     * Se buscan los Inmuebles con un numero mayor o igual de baños que el que se pasa por parametros.
     *
     * @param inmueblesList
     * @param banos
     * @return
     */
    private List<Inmuebles> filterByNBanos(List<Inmuebles> inmueblesList, String banos) {
        int numBanos;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            numBanos = Integer.parseInt(banos);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'banos' no puede convertirse a número");

        }
        for (Inmuebles i : inmueblesList) {

            if (i.getCaracteristicas().getnBanos() >= numBanos) {

                filtered.add(i);

            }

        }
        return filtered;
    }

    private List<Inmuebles> filterByNHab(List<Inmuebles> inmueblesList, String hab) {
        int numHabitaciones;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            numHabitaciones = Integer.parseInt(hab);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'hab' no puede convertirse a número");

        }
        for (Inmuebles i : inmueblesList) {

            if (i.getCaracteristicas().getnHabitaciones() >= numHabitaciones) {

                filtered.add(i);

            }

        }
        return filtered;

    }

    /**
     * Se buscan los Inmuebles con un precio mayor al dado por parámetros, teniendo en cuenta su tipo de gestión
     *
     * @param inmueblesList
     * @param pMin
     * @return
     */
    private List<Inmuebles> filterByPMin(List<Inmuebles> inmueblesList, String pMin) {

        int precioMin;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            precioMin = Integer.parseInt(pMin);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'pMin' no puede convertirse a número");

        }

        for (Inmuebles i : inmueblesList) {

            if (i.getGestiones().getId() == CONSTANTES.GESTION_VENTA) {

                if (i.getPrecioCompra() > precioMin) {

                    filtered.add(i);

                }

            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_AoC) {

                if (i.getPrecioAlquilerOpcionCompra() > precioMin) {

                    filtered.add(i);

                }

            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_ALQUILER) {

                if (i.getPrecioAlquiler() > precioMin) {

                    filtered.add(i);

                }

            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_TRASPASO) {

                if (i.getPrecioTraspaso() > precioMin) {

                    filtered.add(i);

                }

            }

        }

        return filtered;

    }

    /**
     * Se buscan los Inmuebles con un precio inferior al dado por parámetros, teniendo en cuenta su tipo de gestión
     *
     * @param inmueblesList que contiene todos los inmuebles del Tipo con id tipo
     * @param pMax
     * @return
     */
    private List<Inmuebles> filterByPMax(List<Inmuebles> inmueblesList, String pMax) {

        int precioMax;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            precioMax = Integer.parseInt(pMax);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'pMax' no puede convertirse a número");

        }

        for (Inmuebles i : inmueblesList) {

            if (i.getGestiones().getId() == CONSTANTES.GESTION_VENTA) {

                if (i.getPrecioCompra() < precioMax) {

                    filtered.add(i);

                }

            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_AoC) {

                if (i.getPrecioAlquilerOpcionCompra() < precioMax) {

                    filtered.add(i);

                }


            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_ALQUILER) {

                if (i.getPrecioAlquiler() < precioMax) {

                    filtered.add(i);

                }


            } else if (i.getGestiones().getId() == CONSTANTES.GESTION_TRASPASO) {

                if (i.getPrecioTraspaso() < precioMax) {

                    filtered.add(i);

                }


            }


        }

        return filtered;

    }


    /**
     * Se buscan los Inmuebles con un tipo con ID == tipo
     *
     * @param inmueblesList que contiene todos los inmuebles del Tipo con id tipo
     * @param tipo
     * @return
     */
    private List<Inmuebles> filterByIdTipo(List<Inmuebles> inmueblesList, String tipo) {

        long idTipo;
        List<Inmuebles> filtered = new ArrayList<>();


        try {
            idTipo = Long.parseLong(tipo);

        } catch (NumberFormatException e) {

            throw new RuntimeException("El valor del parámetro 'tipo' no puede convertirse a número");

        }

        for (Inmuebles i : inmueblesList) {

            if (i.getTipos().getId().equals(idTipo)) {

                filtered.add(i);

            }


        }

        return filtered;

    }


}