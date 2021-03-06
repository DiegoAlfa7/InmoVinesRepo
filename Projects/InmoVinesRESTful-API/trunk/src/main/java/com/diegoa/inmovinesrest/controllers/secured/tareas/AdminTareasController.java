package com.diegoa.inmovinesrest.controllers.secured.tareas;


import com.diegoa.inmovinesrest.entities.agentes.Tareas;
import com.diegoa.inmovinesrest.services.tareas.impl.TareasServiceImpl;
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
 * de Tareas de Agentes para las aplicaciones identificadas como administrador.
 * @author Daniel Arroyo
 * @since 0.0.1
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminTareasController {

    @Autowired
    TareasServiceImpl tareasService;

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable las tareas de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Tareas\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/tareas/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/tareas/page", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Tareas>> getTareasPage(Pageable pageable) throws JsonProcessingException {

        Page<Tareas> tareas = tareasService.listAllByPage(pageable);

        return new ResponseEntity(tareas, HttpStatus.OK);
    }


    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos las Tareas que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todas las tareas den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/tareas
     * `
     */
    @RequestMapping(value = "/tareas", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Tareas> getTareasList() throws JsonProcessingException {

        List<Tareas> tareas = tareasService.listAll();

        return new ResponseEntity(tareas, HttpStatus.OK);
    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todas las Tareas que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los agentes den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/tareas/id
     * `
     */
    @RequestMapping(value = "/tareas/agente/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Tareas> getTareaById(@PathVariable("id") long id) throws JsonProcessingException {


       List<Tareas> tareas = tareasService.listByIdAgente(id);
       return new ResponseEntity(tareas, HttpStatus.OK);


    }

    /**
     * Esta operación del controlador se encarga de crear una nueva instancia de Tareas en la Base de datos
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON de la tarea creada.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/tareas/nuevo
     * `
     */
    @RequestMapping(value = "/tareas/nuevo", consumes = {"application/json"},produces = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Tareas> create(
            @RequestBody @Valid Tareas tarea,
            @RequestParam long idAgente
    ) {


        Tareas tareaCreada = this.tareasService.create(tarea, idAgente);
        return new ResponseEntity(tareaCreada, HttpStatus.OK);


    }

    /**
     * Esta operación del controlador se encarga de modificar una instancia de Tareas de la Base de datos en función de un JSON
     * con nuevos valores
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON de la tarea creada.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/tareas/nuevo
     * `
     */
    @RequestMapping(value = "/tareas/modificar", consumes = {"application/json"},produces = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Tareas> update(
            @RequestBody @Valid Tareas tarea
    ) {


        Tareas tareaCreada = this.tareasService.update(tarea);
        return new ResponseEntity(tareaCreada, HttpStatus.OK);


    }


}
