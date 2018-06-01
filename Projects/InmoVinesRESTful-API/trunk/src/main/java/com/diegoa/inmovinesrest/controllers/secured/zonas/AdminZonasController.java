package com.diegoa.inmovinesrest.controllers.secured.zonas;

import com.diegoa.inmovinesrest.entities.localizacion.Zonas;
import com.diegoa.inmovinesrest.services.localizacion.zonas.impl.ZonasServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador Restful encargado de la distribución de datos
 * de las zonas para las aplicaciones identificadas como administrador.
 * @author Daniel Arroyo
 * @since 0.0.1
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminZonasController {

    @Autowired
    ZonasServiceImpl zonasService;

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
    public ResponseEntity<Zonas> getTareaById(@PathVariable("id") long id) throws JsonProcessingException {

        Zonas zonas = zonasService.findOneById(id);

        if (zonas != null) {
            return new ResponseEntity(zonas, HttpStatus.OK);
        } else {
            throw new RuntimeException("El id: "+id+" no ha devuelto resultados");
        }
    }


}
