package com.diegoa.inmovinesrest.controllers;

import com.diegoa.inmovinesrest.entities.repositories.InmueblesRepository;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class InmueblesController {


    @Autowired
    InmueblesRepository inmueblesRepository;


    @GetMapping("/inmueble")
    @ResponseBody
    public Inmuebles getInmueble() throws JsonProcessingException {

        return inmueblesRepository.findAll().get(0);


    }


    @GetMapping("/hello")

    @ResponseBody
    public String sayHi(){

        return "hi!";


    }
}
