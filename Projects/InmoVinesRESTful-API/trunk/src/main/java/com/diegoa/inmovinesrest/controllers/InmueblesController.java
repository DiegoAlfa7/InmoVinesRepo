package com.diegoa.inmovinesrest.controllers;


import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.diegoa.inmovinesrest.services.inmuebles.impl.InmueblesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inmovinescrm")
public class InmueblesController {


    @Autowired
    InmueblesServiceImpl inmueblesService;


    @GetMapping("/hola")
    @ResponseBody
    public String getInmueble() {

        return "hi";
    }


}