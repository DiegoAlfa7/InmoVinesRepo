package com.diegoa.inmovinesrest.controllers.secured.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador Restful encargado de la distribución de datos
 * de Agentes para las aplicaciones identificadas como administrador.
 * <p>
 * <hr/>
 * <br/><b>C O R S</b> eneabled for this Controller --> origins = {"http://localhost:8087","http://localhost:8080" }
 *
 * @author Daniel Arroyo
 * @since 0.0.1
 */

@CrossOrigin(origins = {"http://localhost:8087", "http://localhost:8080"})
@RestController
@RequestMapping("admin")
public class AdminAgentesController {

    @Autowired
    AgentesServiceImpl agentesService;


    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los agentes de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Agentes\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/agentes/page", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Agentes>> getAgentesPage(Pageable pageable) {

        Page<Agentes> page = agentesService.listAllByPage(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }


    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Agentes. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes/{id}</b>
     */
    @RequestMapping(value = "/agentes/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> getAgenteById(@PathVariable("id") long id) {

        Agentes agentes = agentesService.findOneById(id);

        if (agentes != null) {
            return new ResponseEntity(agentes, HttpStatus.OK);
        } else {
            throw new RuntimeException("El agente con id: " + id + " no ha devuelto ningún resultado");
        }

    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Agentes que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los agentes den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes
     * `
     */
    @RequestMapping(value = "/agentes", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> getAgentesList() {

        List<Agentes> agentes = agentesService.listAll();

        return new ResponseEntity(agentes, HttpStatus.OK);
    }

    @RequestMapping(value = "/agentes/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> addAgente(@RequestBody @Valid Agentes agentes) {

        Agentes agenteCreado = agentesService.create(agentes);

        return new ResponseEntity(agenteCreado, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/agentes/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> updateAgente(@RequestBody @Valid Agentes agentes) {

        Agentes agentesModificado = agentesService.update(agentes);

        return new ResponseEntity(agentesModificado, HttpStatus.OK);

    }

    @RequestMapping(value = "/agentes/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Void> deleteAgente(@PathVariable("id") long id) {

        this.agentesService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }
}
