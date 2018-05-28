package com.diegoa.inmovinesrest.controllers.secured;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.user.inmuebles.impl.InmueblesUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    InmueblesUserServiceImpl inmueblesUserService;


    @GetMapping("/inmuebles")
    @ResponseBody
    public Page<Inmuebles> getInmuebles(Pageable pageable) {

        return inmueblesUserService.listAllByPage(pageable);

    }


}