package com.diegoa.inmovinesrest.controllers.secured.agentes;

import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.services.agentes.impl.AgentesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

@CrossOrigin()
@RestController
@RequestMapping("admin")
public class AdminAgentesController {

    @Autowired
    AgentesServiceImpl agentesService;

    /**
     * Esta operación del controlador se encarga de encontrar una única entidad de tipo Agentes. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @param id Parámetro utilizado en la búsqueda indexada sobre la base de datos.
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente de la BBDD. -- <h1>HTTP 200 OK</h1>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     *  * @throws RuntimeException si ocurre algún eror durante la búsqueda con el ID dado.-- <h1>HTTP 500 INTERNAL ERROR</h1>
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
     * Esta operación del controlador se encarga de listar dentro de un pageable los agentes de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Agentes\></b> -- <h1>HTTP 200 OK</h1>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/agentes/page", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Agentes>> getAgentesPage(Pageable pageable) {

        Page<Agentes> page = agentesService.listAllByPage(pageable);

        return new ResponseEntity(page, HttpStatus.OK);
    }




    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Agentes que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los agentes den la BBDD. -- <h1>HTTP 200 OK</h1>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/agentes</b>
     *
     */
    @RequestMapping(value = "/agentes", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> getAgentesList() {

        List<Agentes> agentes = agentesService.listAll();

        return new ResponseEntity(agentes, HttpStatus.OK);
    }

    /**
     * Esta operación del controlador se encarga de crear una nueva entidad de tipo Agentes en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente creado -- <h1>HTTP 204 CREATED</h1>
     * @throws RuntimeException si ocurre un error durante la inserción del objeto. --<h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/nuevo</b>
     *
     */
    @RequestMapping(value = "agentes/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> addAgente(@RequestBody @Valid Agentes agentes) {

        Agentes agenteCreado = agentesService.create(agentes);

        return new ResponseEntity(agenteCreado, HttpStatus.CREATED);
    }

    /**
     * Esta operación del controlador se encarga de modificar una entidad de tipo Agentes existente en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agentes modificado con los nuevos valores. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la actualización en la BBD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/nuevo</b>
     *
     */
    @RequestMapping(value = "agentes/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> updateAgente(@RequestBody @Valid Agentes agentes) {

        Agentes agentesModificado = agentesService.update(agentes);

        return new ResponseEntity(agentesModificado, HttpStatus.OK);

    }
    /**
     * Esta operación del controlador se encarga de eliminar una entidad de tipo Agentes existente en la BBDD <b>
     * mediante una búsqueda indexada</b> con el ID recogido de los parámetros de la petición.
     *
     * @return ResponseEntity<Void> Respuesta Http OK vacía si se ha borrado correctemente la entidad -- <h1>HTTP 200 OK</h1>
     * Respuesta Http NOT_MODIFIED vacía. si no se ha borrado la entidad -- <h1>HTTP 304 NOT_MODIFIED</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/{id}</b>
     *
     */
    @RequestMapping(value = "/agentes/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Void> deleteAgente(@PathVariable("id") long id) {

        if (this.agentesService.delete(id)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



    //CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS

    /**
     * Esta operación del controlador se encarga de encontrar los clientes asociados a un agente mediante la propiedad "AGENTE"
     *
     *
     * @return ResponseEntity<List<Clientes>> Lista de clientes que pertenecen al agente en cuestión, en formato JSON -- <h1>HTTP 200 OK</h1>
     *
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/{id}/clientes
     *
     */
    @RequestMapping(value = "/agentes/{id}/clientes", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Clientes>> getClientesAsociados(@PathVariable("id") long id) {

        List<Clientes> c = this.agentesService.getClientesAsignadosById(id);

        return new ResponseEntity(c, HttpStatus.OK);

    }
    /**
     * Esta operación del controlador se encarga de encontrar una instancia de tipo Agente en la BBDD a partir de los parámetros de contraseña y email
     * que se envíen como parte de una petición HTTP POST con parámetros APPLICATION_FORM_URLENCODED, los parámetros son: <b>email</b> y <b>pass</b>
     *
     *
     * @return ResponseEntity<Agentes> Agente cuya combinación email-pass coincide con la de los parámetros, en formato JSON -- <h1>HTTP 200 OK</h1>
     *
     * @throws RuntimeException si ocurre algun error durante la búsquedaa en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/agentes/login
     *
     */
    @RequestMapping(value = "/agentes/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE ,produces = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> getAgenteByEmailAndPassword(@RequestParam Map<String, String> map) {
        if(map != null) {

            String pass = map.get("pass");
            String email = map.get("email");

            if((pass != null && email != null) && (!pass.isEmpty() && !email.isEmpty())){

                Agentes a = this.agentesService.findAgenteByLogin(pass, email);
                return new ResponseEntity(a,HttpStatus.OK);

            }


        }
        throw new RuntimeException("Los parámetros de entrada no son validos");
    }

}
