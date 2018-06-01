package com.diegoa.inmovinesrest.controllers.accesible.localizacion.municipios;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.services.localizacion.municipios.impl.MunicipiosServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Daniel Arroyo
 * @version 0.0.1
 */
@CrossOrigin(origins = {"http://localhost:8087", "http://localhost:8080"})
@RestController
@RequestMapping("user")
public class PublicMunicipiosController {

    @Autowired
    MunicipiosServiceImpl municipiosService;

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Agentes que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los agentes den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes
     * `
     */
    @RequestMapping(value = "/municipios", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Municipios> getMunicipiosList() throws JsonProcessingException {

        List<Municipios> municipiosList = municipiosService.listAll();

        return new ResponseEntity(municipiosList, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Agentes. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes/{id}</b>
     */
    @RequestMapping(value = "/municipios/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Municipios> getAgenteById(@PathVariable("id") long id) throws JsonProcessingException {

        Municipios municipios = municipiosService.findOneById(id);

        if (municipios != null) {
            return new ResponseEntity(municipios, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se puede listar un municipio vacio");
        }

    }
}
