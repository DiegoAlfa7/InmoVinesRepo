package com.diegoa.inmovinesrest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class InmueblesController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHi(){

        return "hi!";


    }
}
