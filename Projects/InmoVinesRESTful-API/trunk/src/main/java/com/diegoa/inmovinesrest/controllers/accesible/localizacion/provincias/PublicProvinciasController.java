package com.diegoa.inmovinesrest.controllers.accesible.localizacion.provincias;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Provincias;
import com.diegoa.inmovinesrest.services.localizacion.provincias.impl.ProvinciasServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Diego Alfaro
 * @version 0.0.1
 */
@CrossOrigin()
@RestController
@RequestMapping("user")
public class PublicProvinciasController {

    @Autowired
    ProvinciasServiceImpl provinciasService;

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos las Provincias que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos las Provincias de la BBDD.
     * @throws RuntimeException si ocurre un error durante la búsqueda de la lista.
     * @apiNote <b>ENDPOINT: .../user/provincias
     * `
     */
    @RequestMapping(value = "/provincias", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Provincias> getProvinciasList() throws JsonProcessingException {

        List<Provincias> provinciasList = provinciasService.listAll();

        return new ResponseEntity(provinciasList, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Provincias. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON de la provincia de la BBDD.
     * @throws RuntimeException si ocurre un error durante la búsqueda o serialización del objeto.
     * @apiNote <b>ENDPOINT: .../user/provincias/{id}</b>
     */
    @RequestMapping(value = "/provincias/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Provincias> getProvinciaById(@PathVariable("id") long id) throws RuntimeException {

        Provincias provincias = provinciasService.findOneById(id);

        return new ResponseEntity(provincias, HttpStatus.OK);


    }

    /**
     * Esta operación del controlador se encarga de devolver una lista con los Municipios pertenecientes a una Provincia en concreto. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<List<Municipios>> Respuesta Http con la representación string del JSON de la lista de Municipios de la BBDD.
     * @throws RuntimeException si ocurre un error durante la busqueda en BBDD.
     * @apiNote <b>ENDPOINT: .../user/provincias/{id}/municipios</b>
     */
    @RequestMapping(value = "/provincias/{id}/municipios", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Municipios>> getProvinciasComunidadById(@PathVariable("id") long id) throws JsonProcessingException {

        List<Municipios> provinciasList = provinciasService.findMunicipiosById(id);

        return new ResponseEntity(provinciasList, HttpStatus.OK);

    }
}
