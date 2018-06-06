package com.diegoa.inmovinesrest.controllers.secured.zonas;

import com.diegoa.inmovinesrest.entities.localizacion.Municipios;
import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.services.localizacion.municipios.impl.MunicipiosServiceImpl;
import com.diegoa.inmovinesrest.services.localizacion.zonas.impl.ZonasServiceImpl;
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
 * de las zonas para las aplicaciones identificadas como administrador.
 *
 * @author Daniel Arroyo
 * @since 0.0.1
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminZonasController {

    @Autowired
    ZonasServiceImpl zonasService;

    @Autowired
    MunicipiosServiceImpl municipiosService;

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable las zonas de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Tareas\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/zonas/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/zonas/page", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Zonas>> getZonasPage(Pageable pageable) throws JsonProcessingException {

        Page<Zonas> tareas = zonasService.listAllByPage(pageable);

        return new ResponseEntity(tareas, HttpStatus.OK);
    }


    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todos las Zonas que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todas las tareas den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/zonas
     * `
     */
    @RequestMapping(value = "/zonas", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Zonas> getZonasList() throws JsonProcessingException {

        List<Zonas> zonas = zonasService.listAll();

        return new ResponseEntity(zonas, HttpStatus.OK);
    }

    /**
     * Esta operación del controlador se encarga de devolver en una Lista genérica <b>todas las Zonas que haya en la base de datos.</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del array de todos las zonas den la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     * @apiNote <b>ENDPOINT: .../admin/zonas/id
     * `
     */
    @RequestMapping(value = "/zonas/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Zonas> getZonaById(@PathVariable("id") long id) throws JsonProcessingException {

        Zonas zonas = zonasService.findOneById(id);

        if (zonas != null) {
            return new ResponseEntity(zonas, HttpStatus.OK);
        } else {
            throw new RuntimeException("El id: " + id + " no ha devuelto resultados");
        }
    }

    /**
     * Esta operación del controlador se encarga de crear una nueva entidad de tipo Zonas en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agente creado -- <h1>HTTP 204 CREATED</h1>
     * @throws RuntimeException si ocurre un error durante la inserción del objeto. --<h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/zonas/nuevo</b>
     */
    @RequestMapping(value = "zonas/nuevo", consumes = "application/json", method = {RequestMethod.POST, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Zonas> addZona(@RequestBody @Valid Zonas zonas) {

        Zonas zonaCreada = zonasService.create(zonas);

        return new ResponseEntity(zonaCreada, HttpStatus.CREATED);
    }

    /**
     * Esta operación del controlador se encarga de modificar una entidad de tipo Zonas existente en la BBDD <b>en función de un JSON válido que represente
     * un ojeto de dicho tipo </b>con los valores elegidos. <b>No hará falta mapear las relaciones ORM de tipo @OneToMany</b>
     *
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del agentes modificado con los nuevos valores. -- <h1>HTTP 200 OK</h1>
     * @throws RuntimeException si ocurre algun error durante la actualización en la BBD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/zonas/nuevo</b>
     */
    @RequestMapping(value = "zonas/modificar", produces = "application/json", method = {RequestMethod.PUT, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Zonas> updateZona(@RequestBody @Valid Zonas zonas) {

        Zonas zonaModificada = zonasService.update(zonas);

        return new ResponseEntity(zonaModificada, HttpStatus.OK);

    }

    /**
     * Esta operación del controlador se encarga de eliminar una entidad de tipo Zonas existente en la BBDD <b>
     * mediante una búsqueda indexada</b> con el ID recogido de los parámetros de la petición.
     *
     * @return ResponseEntity<Void> Respuesta Http OK vacía si se ha borrado correctemente la entidad -- <h1>HTTP 200 OK</h1>
     * Respuesta Http NOT_MODIFIED vacía. si no se ha borrado la entidad -- <h1>HTTP 304 NOT_MODIFIED</h1>
     * @throws RuntimeException si ocurre algun error durante la búsqueda o el borrado en la BBDD -- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/zonas/{id}</b>
     */
    @RequestMapping(value = "/zonas/{id}", produces = "application/json", method = {RequestMethod.DELETE, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity deleteZona(@PathVariable("id") long id) {

        if (this.zonasService.delete(id)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_MODIFIED);
    }



    /**
     * Esta operación del controlador se encarga de devolver una lista con las Zonas pertenecientes a un Municipio en concreto. Realiza una búsqueda en los DAOS en función de
     * el ID facilitado.
     *
     * @return ResponseEntity<List<Zonas>> Respuesta Http con la representación string del JSON de la lista de Zonas de la BBDD.
     * @throws RuntimeException si ocurre un error durante la busqueda en BBDD.
     * @apiNote <b>ENDPOINT: .../admin/zonas/municipio/{id}</b>
     */
    @RequestMapping(value = "/zonas/municipio/{id}", produces = "application/json", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<List<Zonas>> getProvinciasComunidadById(@PathVariable("id") long id) throws JsonProcessingException {

        List<Zonas> provinciasList = municipiosService.findZonasById(id);

        return new ResponseEntity(provinciasList, HttpStatus.OK);

    }


}
