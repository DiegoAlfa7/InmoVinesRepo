package com.diegoa.inmovinesrest.controllers.secured.inmuebles;


import com.diegoa.inmovinesrest.entities.agentes.Agentes;
import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
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
 * de Inmuebles para las aplicaciones identificadas como administrador.
 * <p>
 * <hr/>
 * <br/><b>C O R S</b> eneabled for this Controller --> origins = {"http://localhost:8087","http://localhost:8080" }
 *
 * @author Diego Alfaro
 * @since 0.0.1
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminInmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesService;

    //CRUD METHODS -.- -.- -.- CRUD METHODS -.- -.- -.- CRUD METHODS -.- -.- -.- CRUD METHODS -.- -.- -.- CRUD METHODS -.- -.- -.- CRUD METHODS -.- -.- -.- CRUD METHODS

    /**
     * Esta operación del controlador se encarga de encontrar una única entidad de tipo Inmuebles. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @param id Parámetro utilizado en la búsqueda indexada sobre la base de datos.
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmueble de la BBDD. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre un error durante la busqueda del inmueble.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     *  * @throws RuntimeException si ocurre algún eror durante la búsqueda con el ID dado.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}</b>
     */
    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Inmuebles> getInmuebleById( @PathVariable("id") long id) {


        Inmuebles i = inmueblesService.findOneById(id);


        return new ResponseEntity(i, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los inmuebles de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     * 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Inmuebles\></b> -- <h1>HTTP 200 OK</h1>
     *
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/inmuebles/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Inmuebles>> getInmueblesPage(Pageable pageable) {


        Page<Inmuebles> pagina = inmueblesService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);




    }
    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos los Inmuebles que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos los inmuebles den la BBDD. -- <h1>HTTP 200 OK</h1>
     *
     * @apiNote <b>ENDPOINT: .../admin/inmuebles
     *
     */
    @RequestMapping(value = "/inmuebles", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Inmuebles> getInmueblesList() {

        List<Inmuebles> inmueblesList = this.inmueblesService.listAll();

        return new ResponseEntity(inmueblesList, HttpStatus.OK);
    }

    /**
     * Esta operación del controlador se encarga de crear una nueva entidad de tipo Inmuebles en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmueble creado -- <h1>HTTP 204 CREATED</h1>
     * @throws RuntimeException si ocurre un error durante la inserción del objeto. --<h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/nuevo
     *
     */
    @RequestMapping(value = "inuebles/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Agentes> addInmueble(@RequestBody @Valid Inmuebles inmueble) {

       Inmuebles inmuebleCreado =  inmueblesService.create(inmueble);

        return new ResponseEntity(inmuebleCreado, HttpStatus.CREATED);
    }
    /**
     * Esta operación del controlador se encarga de modificar una entidad de tipo Inmuebles existente en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmuebles modificado con los nuevos valores. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la actualización en la BBD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/modificar
     *
     */
    @RequestMapping(value = "inmuebles/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Inmuebles> updateInmueble(@RequestBody @Valid Inmuebles inmuebles) {

        Inmuebles inmueblesModificado = inmueblesService.update(inmuebles);

        return new ResponseEntity(inmueblesModificado, HttpStatus.OK);

    }
    /**
     * Esta operación del controlador se encarga de eliminar una entidad de tipo Inmuebles existente en la BBDD <b>
     * mediante una búsqueda indexada</b> con el ID recogido de los parámetros de la petición.
     *
     * @return ResponseEntity<Void> Respuesta Http vacía. -- <h1>HTTP 200 OK</h1>
     * Respuesta Http NOT_MODIFIED vacía. si no se ha borrado la entidad -- <h1>HTTP 304 NOT_MODIFIED</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}
     *
     */
    @RequestMapping(value = "/inmuebles/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity deleteInmueble(@PathVariable("id") long id) {

        if (this.inmueblesService.delete(id)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }




    //CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS




    /**
     * Esta operación del controlador se encarga de encontrar la entidad Clientes asociada al Inmueble con el ID especificado como "CLIENTE PROPIETARIO"
     * y devuelve el resultado.
     *
     * @return ResponseEntity<Clientes> El Cliente propietario del Inmueble, en formato JSON -- <h1>HTTP 200 OK</h1>
     *
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}/propietario
     *
     */
    @RequestMapping(value = "/inmuebles/{id}/propietario", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Clientes> getClientePropietario(@PathVariable("id") long id) {

        Clientes c = this.inmueblesService.getClientePropietarioByID(id);

        return new ResponseEntity(c, HttpStatus.OK);

    }

    /**
     *
     * Esta operación del controlador se encarga de encontrar las entidades de tipo Clientes de la base de datos que tienen como "INMUEBLE DE INTERÉS" el inmueble con el ID que se introdujo en la URL
     * y devuelve el resultado.
     *
     * @return ResponseEntity<List<Clientes>> Los Clientes interesados en el Inmueble, en formato JSON -- <h1>HTTP 200 OK</h1>
     *
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}/interesados
     *
     */
    @RequestMapping(value = "/inmuebles/{id}/interesados", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Clientes>> getClientesInteresados(@PathVariable("id") long id) {

        List<Clientes> c = this.inmueblesService.getInteresadosByID(id);

        return new ResponseEntity(c, HttpStatus.OK);

    }





}