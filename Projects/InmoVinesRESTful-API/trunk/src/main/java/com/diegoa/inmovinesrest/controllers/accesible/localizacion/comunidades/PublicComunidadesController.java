package com.diegoa.inmovinesrest.controllers.accesible.localizacion.comunidades;

import com.diegoa.inmovinesrest.entities.localizacion.Comunidades;
import com.diegoa.inmovinesrest.services.localizacion.comunidades.impl.ComunidadesServiceImpl;
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
public class PublicComunidadesController {

    @Autowired
    ComunidadesServiceImpl comunidadesService;

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos las Comunidades que haya en la base de datos.</b>
     *
     * @return ResponseEntity<Comunidades> Respuesta Http con la representación string del JSON del array de todas las comunidades den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../user/comunidades
     * `
     */
    @RequestMapping(value = "/comunidades", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Comunidades> getComunidadesList() throws JsonProcessingException {

        List<Comunidades> comunidadesList = comunidadesService.listAll();

        return new ResponseEntity(comunidadesList, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Comunidades. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<Comunidades> Respuesta Http con la representación string del JSON del agente de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../user/comunidades/{id}</b>
     */
    @RequestMapping(value = "/comunidades/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Comunidades> getAgenteById(@PathVariable("id") long id) throws JsonProcessingException {

        Comunidades comunidades = comunidadesService.findOneById(id);

        if (comunidades != null) {
            return new ResponseEntity(comunidades, HttpStatus.OK);
        } else {
            throw new RuntimeException("No se puede listar la comunidad nula");
        }
    }
}
