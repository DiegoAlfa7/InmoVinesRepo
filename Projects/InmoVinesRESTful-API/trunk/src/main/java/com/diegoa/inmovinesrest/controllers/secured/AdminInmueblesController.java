package com.diegoa.inmovinesrest.controllers.secured;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    @GetMapping("/inmuebles")
    @ResponseBody
    public Page<Inmuebles> getInmuebles(Pageable pageable) {


        Page<Inmuebles> pagina = inmueblesUserService.listAllByPage(pageable);

        return pagina;


    }

    @GetMapping("/inmuebles/{id}")
    @ResponseBody
    public Inmuebles getInmuebleById(@PathVariable("id") long id, Pageable pageable) {


        Inmuebles i = inmueblesUserService.findOneById(id);
        return i;


    }


}