package com.diegoa.inmovinesrest.controllers;

import com.diegoa.inmovinesrest.entities.repositories.InmueblesRepository;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inmovinescrm")
public class InmueblesController {


    @Autowired
    InmueblesRepository inmueblesRepository;


    @RequestMapping("/inmueble")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Inmuebles getInmueble(){

        return inmueblesRepository.findAll().get(0);
    }


    @GetMapping("/hello")

    @ResponseBody
    public String sayHi() {

        return "hi!";


    }
}
