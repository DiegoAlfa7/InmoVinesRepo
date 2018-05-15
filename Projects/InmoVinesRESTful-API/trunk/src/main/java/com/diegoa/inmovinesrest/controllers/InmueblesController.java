package com.diegoa.inmovinesrest.controllers;

<<<<<<< HEAD
import com.diegoa.inmovinesrest.entities.repositories.InmueblesRepository;
import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
=======
>>>>>>> 114fb6df80fd0db8d32c642f5dd17aa602e194bf
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
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


=======

@Controller
public class InmueblesController {

>>>>>>> 114fb6df80fd0db8d32c642f5dd17aa602e194bf
    @GetMapping("/hello")
    @ResponseBody
    public String sayHi() {

        return "hi!";


    }
}
