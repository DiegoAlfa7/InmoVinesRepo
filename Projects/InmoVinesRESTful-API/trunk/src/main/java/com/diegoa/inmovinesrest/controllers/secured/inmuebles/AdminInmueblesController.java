package com.diegoa.inmovinesrest.controllers.secured.inmuebles;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    InmueblesServiceImpl inmueblesUserService;


    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los inmuebles de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     *                 'page', 'size' y 'sort' debidamente introducidos
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Inmuebles\></b> -- <h1>HTTP 200 OK</h1>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto. --<h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/page[?page=PAGE&size=SIZE&sort=SHORT,asc|desc]</b>
     */
    @RequestMapping(value = "/inmuebles/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Inmuebles>> getInmueblesPage(Pageable pageable) throws JsonProcessingException {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);




    }

    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los inmuebles de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     *
     * @param id Parámetro utilizado en la búsqueda indexada sobre la base de datos
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del objeto Inmueble -- <h1>HTTP 200 OK</h1>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @throws RuntimeException si ocurre algún eror durante la búsqueda con el ID dado.-- <h1>HTTP 500 INTERNAL ERROR</h1>
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}</b>
     */
    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Inmuebles> getInmuebleById( @PathVariable("id") long id) throws JsonProcessingException {


        Inmuebles i = inmueblesUserService.findOneById(id);
        if(i == null){throw  new RuntimeException("El Inmueble con id: "+id+" no ha devuelto resultados");}

        return new ResponseEntity(i, HttpStatus.OK);

    }


}