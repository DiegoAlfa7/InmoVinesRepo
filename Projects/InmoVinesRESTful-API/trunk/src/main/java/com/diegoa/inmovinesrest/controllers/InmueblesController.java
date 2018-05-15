package com.diegoa.inmovinesrest.controllers;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inmovinescrm")
public class InmueblesController {


    @Autowired
    InmueblesServiceImpl inmueblesService;


    @RequestMapping("/inmueble")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Inmuebles getInmueble() {

        return inmueblesService.listAll().get(0);
    }


}