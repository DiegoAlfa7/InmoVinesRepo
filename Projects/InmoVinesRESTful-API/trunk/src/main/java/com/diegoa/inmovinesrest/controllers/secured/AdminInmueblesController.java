package com.diegoa.inmovinesrest.controllers.secured;


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
@RestController
@RequestMapping("admin")
public class AdminInmueblesController {

    @Autowired
    InmueblesServiceImpl inmueblesUserService;



    @GetMapping(value = "/inmuebles", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Page<Inmuebles>> getInmuebles(Pageable pageable) throws JsonProcessingException {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);


        return new ResponseEntity(pagina, HttpStatus.OK);




    }

    @GetMapping(value = "/inmuebles/{id}", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Inmuebles> getInmuebleById( @PathVariable("id") long id,  Pageable pageable) throws JsonProcessingException {


        Inmuebles i = inmueblesUserService.findOneById(id);

        return new ResponseEntity(i, HttpStatus.OK);

    }


}