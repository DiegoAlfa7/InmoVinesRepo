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
 * de inmuebles para las aplicaciones identificadas como administrador.
 *
 * @since 0.0.1
 * @author Diego Alfaro
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminInmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesUserService;


    /**
     * Esta operación del controlador se encarga de listar dentro de un pageable los <b>Inmuebles</b> de la base de datos, agrupándolos en
     * función del tipo de página que se pida en los parámetros de la URL.
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/page[?page={page}&size={size}&sort={objectProperty},asc|desc]</b>
     * @param pageable Se refiere a la PageRequest que se espera que venga junto con la petición, con los parámetros
     * 'page', 'size' y 'sort' debidamente introducidos
     *
     * @return ResponseEntity<String> Respuesta Http con el la representación string del JSON del objeto de la <b>Page<\Inmuebles\></b>
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @RequestMapping(value = "/inmuebles/page", produces = {"application/json"}, method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @ResponseBody
    public ResponseEntity<Page<Inmuebles>> getInmuebles(Pageable pageable) throws JsonProcessingException {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);




    }

    /**
     * Esta operación del controlador se encarga de una única entidad de tipo Inmuebles. Realiza una búsqueda en los Repositorios en función de
     * el ID facilitado y devuelve su representación JSON.
     *
     * @apiNote <b>ENDPOINT: .../admin/inmuebles/{id}</b>
     * @return ResponseEntity<String> Respuesta Http con la representación string del JSON del inmueble de la BBDD.
     * @throws JsonProcessingException si ocurre un error durante la serialización del objeto.
     */
    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Inmuebles> getInmuebleById( @PathVariable("id") long id) throws JsonProcessingException {


        Inmuebles i = inmueblesUserService.findOneById(id);
        if(i == null){throw  new RuntimeException("El Inmueble con id: "+id+" no ha devuelto resultados");}

        return new ResponseEntity(i, HttpStatus.OK);

    }


}