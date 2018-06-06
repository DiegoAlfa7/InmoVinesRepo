package com.diegoa.inmovinesrest.controllers.secured.incidencias;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.entities.inmuebles.incidencias.Incidencias;
import com.diegoa.inmovinesrest.services.inmuebles.incidencias.impl.IncidenciasServiceImpl;
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
 * de Incidencias para las aplicaciones identificadas como administrador.
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
public class AdminIncidenciasController {

    @Autowired
    IncidenciasServiceImpl incidenciasService;

    /**
     * Esta operación del controlador se encarga de encontrar una única entidad de tipo Incidencias. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @param id Parámetro utilizado en la búsqueda indexada sobre la base de datos.
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmueble de la BBDD. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre un error durante la busqueda del inmueble.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     *                          * @throws RuntimeException si ocurre algún eror durante la búsqueda con el ID dado.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/{id}</b>
     */
    @GetMapping(value = "/incidencias/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Incidencias> getIncidenciaById(@PathVariable("id") long id) {

        Incidencias incidencias = incidenciasService.findOneById(id);

        return new ResponseEntity(incidencias, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable las incidencias de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Inmuebles\></b> -- <h1>HTTP 200 OK</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/incidencias/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Incidencias>> getIncidenciasPage(Pageable pageable) {

        Page<Incidencias> incidencias = incidenciasService.listAllByPage(pageable);

        return new ResponseEntity(incidencias, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos las Incidencias que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos las incidencias den la BBDD. -- <h1>HTTP 200 OK</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias
     */
    @RequestMapping(value = "/incidencias", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Incidencias> getIncidenciasList() {

        List<Incidencias> inmueblesList = this.incidenciasService.listAll();

        return new ResponseEntity(inmueblesList, HttpStatus.OK);
    }

    /**
     * Esta operación del controlador se encarga de modificar una entidad de tipo Incidencias existente en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmuebles modificado con los nuevos valores. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la actualización en la BBD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/modificar
     */
    @RequestMapping(value = "incidencias/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Incidencias> updateIncidencia(@RequestBody @Valid Incidencias incidencias) {

        Incidencias inmueblesModificado = incidenciasService.update(incidencias);

        return new ResponseEntity(inmueblesModificado, HttpStatus.OK);

    }


    @RequestMapping(value = "incidencias/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Incidencias> addIncidencia(
            @RequestBody @Valid Incidencias incidencias,
            @RequestParam long id_inmueble
    ) {

        Incidencias incidenciasCreada = incidenciasService.create(incidencias, id_inmueble);

        if (incidenciasCreada != null) {

            return new ResponseEntity(incidenciasCreada, HttpStatus.CREATED);

        } else {

            throw new RuntimeException("TUS PUTOS MUERTOS");
        }

    }

    /**
     * Esta operación del controlador se encarga de eliminar una entidad de tipo Incidencia existente en la BBDD <b>
     * mediante una búsqueda indexada</b> con el ID recogido de los parámetros de la petición.
     *
     * @return ResponseEntity<Void> Respuesta Http vacía. -- <h1>HTTP 200 OK</h1>
     * Respuesta Http NOT_MODIFIED vacía. si no se ha borrado la entidad -- <h1>HTTP 304 NOT_MODIFIED</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/{id}
     */
    @RequestMapping(value = "/incidencias/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity deleteIncidencia(@PathVariable("id") long id) {

        if (this.incidenciasService.delete(id)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }


    //CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS -.- -.- -.- CUSTOM METHODS


    /**
     * Esta operación del controlador se encarga de conseguir la lista de incidencias relacionadas con un Cliente,
     * mas concretamente las incidencias de todos sus inmuebles.
     *
     * @return ResponseEntity<List       <       Incidencias>> lista de todas las incidencias relacionadas con el cliente-- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/cliente/{id}
     */
    @RequestMapping(value = "/incidencias/cliente/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity getClienteIncidencias(@PathVariable("id") long id) {

        List<Incidencias> incidenciasList = this.incidenciasService.listAllByClienteID(id);

        return new ResponseEntity(incidenciasList, HttpStatus.OK);


    }


    /**
     * Esta operación del controlador se encarga de conseguir la lista de incidencias relacionadas con un Inmueble en función
     * del ID de este último
     *
     * @return ResponseEntity<List       <       Incidencias>> lista de todas las incidencias relacionadas con el Inmueble-- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/incidencias/inmueble/{id}
     */
    @RequestMapping(value = "/incidencias/inmueble/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity getInmuebleIncidencias(@PathVariable("id") long id) {

        List<Incidencias> incidenciasList = this.incidenciasService.listAllByInmuebleID(id);

        return new ResponseEntity(incidenciasList, HttpStatus.OK);


    }

}
